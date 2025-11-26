package org.Controller;

import org.Model.*;
import java.util.List;
import java.util.ArrayList;

public class AnularInscricao_Controller {
    private final RegistoAlunos registoAlunos;
    private final RegistoCursos registoCursos;

    public AnularInscricao_Controller(Empresa empresa) {
        // Obtém acesso aos registos através da Empresa
        this.registoAlunos = empresa.getRegistoAlunos();
        this.registoCursos = empresa.getRegistoCursos();
    }

    public List<Inscricao> listInscricoesDoAluno(String nomeAluno) {
        // Busca o aluno real no registo pelo nome
        Aluno aluno = registoAlunos.getAluno(nomeAluno);
        if (aluno == null) {
            return new ArrayList<>();
        }
        return aluno.getInscricoes();
    }

    public boolean anularInscricao(String idCurso, String nomeAluno) {
        // 1. Encontrar o Aluno Real no sistema
        Aluno aluno = registoAlunos.getAluno(nomeAluno);
        if (aluno == null) {
            System.out.println("Erro: Aluno não encontrado no sistema.");
            return false;
        }

        // 2. Encontrar o Curso Real no sistema (usando o RegistoCursos)
        Curso curso = registoCursos.getCurso(idCurso);
        if (curso == null) {
            System.out.println("Erro: Curso não encontrado.");
            return false;
        }

        // 3. Procurar a Inscrição correspondente nos dados do Aluno
        Inscricao inscricaoAlvo = null;
        for (Inscricao i : aluno.getInscricoes()) {
            // Verifica se a inscrição é para este curso e se está ativa
            if (i.getCurso().equals(curso) && "ativa".equalsIgnoreCase(i.getEstado())) {
                inscricaoAlvo = i;
                break;
            }
        }

        if (inscricaoAlvo == null) {
            System.out.println("Erro: Não existe inscrição ativa deste aluno neste curso.");
            return false;
        }

        // 4. Validar Prazo (Opcional - lógica de negócio do Curso)
        // if (!curso.verificaPrazoAnulacao()) return false;

        // 5. Efetuar a anulação (Alterar estado)
        inscricaoAlvo.setEstado("cancelada");

        // 6. Libertar vaga (Opcional, se tiveres gestão de vagas)
        // curso.incrementarVaga();

        return true;
    }
}