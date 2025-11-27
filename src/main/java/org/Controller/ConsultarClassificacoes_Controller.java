package org.Controller;

import org.Model.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultarClassificacoes_Controller {

    private final Empresa empresa;
    private final RegistoAlunos registoAlunos;
    private final RegistoCursos registoCursos; // Caso seja necessário buscar detalhes extra

    public ConsultarClassificacoes_Controller(Empresa empresa) {
        this.empresa = empresa;
        this.registoAlunos = empresa.getRegistoAlunos();
        this.registoCursos = empresa.getRegistoCursos();
    }

    // 1. Obter lista de cursos onde o aluno está inscrito
    public List<Curso> obterCursosAluno(String nomeAluno) {
        Aluno aluno = registoAlunos.getAluno(nomeAluno);
        if (aluno == null) return new ArrayList<>();

        List<Curso> cursosInscrito = new ArrayList<>();
        // Percorre as inscrições do aluno
        for (Inscricao i : aluno.getInscricoes()) {
            cursosInscrito.add(i.getCurso());
        }
        return cursosInscrito;
    }

    // 2. Obter as classificações formatadas (Pauta Individual)
    public List<String> obterClassificacoes(String nomeAluno, String siglaCurso) {
        List<String> linhasPauta = new ArrayList<>();

        // Recuperar objetos
        Aluno aluno = registoAlunos.getAluno(nomeAluno);
        Curso curso = registoCursos.getCurso(siglaCurso);

        if (aluno == null || curso == null) return linhasPauta;

        // Encontrar a inscrição específica (para obter a lista de notas)
        Inscricao inscricaoAlvo = null;
        for (Inscricao i : aluno.getInscricoes()) {
            if (i.getCurso().equals(curso)) {
                inscricaoAlvo = i;
                break;
            }
        }

        if (inscricaoAlvo == null) return linhasPauta;

        // Lista de todas as notas desta inscrição
        List<Classificacao> notasDoAluno = inscricaoAlvo.getClassificacoes();

        // 3. Iterar sobre os Módulos do Curso para construir a tabela
        for (Modulo m : curso.getModulos()) {
            // Polimorfismo: O módulo calcula a sua própria nota baseado na lista
            double nota = m.calcularNota(notasDoAluno);

            String status = (nota > 0) ? String.format("%.2f", nota) : "N/A";
            linhasPauta.add("Módulo: " + m.getTitulo() + " | Nota: " + status);
        }

        // 4. Calcular Nota Final do Curso
        double notaFinal = curso.calcularNota(notasDoAluno);
        linhasPauta.add("-----------------------------");
        linhasPauta.add("NOTA FINAL DO CURSO: " + String.format("%.2f", notaFinal));

        return linhasPauta;
    }

    // Auxiliar para a UI (Login)
    public List<Aluno> getListaAlunos() {
        return registoAlunos.getAlunos();
    }
}