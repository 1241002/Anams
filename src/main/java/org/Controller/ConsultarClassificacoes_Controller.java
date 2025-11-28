package org.Controller;

import org.Model.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultarClassificacoes_Controller {

    private final Empresa empresa;
    private final RegistoAlunos registoAlunos;
    private final RegistoCursos registoCursos;

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
        for (Inscricao i : aluno.getInscricoes()) {
            cursosInscrito.add(i.getCurso());
        }
        return cursosInscrito;
    }

    // 2. Obter as classificações formatadas (Pauta Individual)
    public List<String> obterClassificacoes(String nomeAluno, String siglaCurso) {
        List<String> linhasPauta = new ArrayList<>();

        Aluno aluno = registoAlunos.getAluno(nomeAluno);
        Curso curso = registoCursos.getCurso(siglaCurso);

        if (aluno == null || curso == null) return linhasPauta;

        // Encontrar a inscrição específica
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

        // 3. Iterar sobre os Módulos para construir a tabela
        boolean temTodasAsNotas = true;

        for (Modulo m : curso.getModulos()) {
            // Polimorfismo: O módulo calcula a sua própria nota
            double nota = m.calcularNota(notasDoAluno);

            String status;
            if (nota > 0) {
                status = String.format("%.2f", nota);
            } else {
                status = "N/A";
                temTodasAsNotas = false; // Se falta uma, não há nota final
            }
            linhasPauta.add("Módulo: " + m.getTitulo() + " | Nota: " + status);
        }

        // 4. Calcular Nota Final do Curso (se tiver todas)
        if (temTodasAsNotas && !curso.getModulos().isEmpty()) {
            double notaFinal = curso.calcularNota(notasDoAluno);
            linhasPauta.add("-----------------------------");
            linhasPauta.add("NOTA FINAL DO CURSO: " + String.format("%.2f", notaFinal));
        } else {
            linhasPauta.add("-----------------------------");
            linhasPauta.add("NOTA FINAL: Pendente (faltam avaliações)");
        }

        return linhasPauta;
    }

    public List<Aluno> getListaAlunos() {
        return registoAlunos.getAlunos();
    }
}