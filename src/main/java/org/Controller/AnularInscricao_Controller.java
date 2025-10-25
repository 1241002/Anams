package org.Controller;

import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Inscricao;
import org.Model.Empresa;

import java.util.List;
import java.util.stream.Collectors;

public class AnularInscricao_Controller {
    private Empresa empresa;

    // Construtor: recebe a empresa para aceder aos cursos e dados
    public AnularInscricao_Controller(Empresa empresa) {
        this.empresa = empresa;
    }

    // Anula a inscrição do aluno num curso (pelo ID/sigla do curso)
    public boolean anularInscricao(String idCurso, Aluno aluno) {
        Curso curso = empresa.findCursoById(idCurso);
        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return false;
        }

        Inscricao inscricao = findInscricaoByIdCurso(aluno, idCurso);
        if (inscricao == null) {
            System.out.println("Inscrição não encontrada.");
            return false;
        }

        curso.removerInscricao(inscricao);
        aluno.removerInscricao(inscricao);

        System.out.println("Inscrição anulada com sucesso.");
        return true;
    }

    // Procura a inscrição do aluno num curso específico (pelo ID do curso)
    private Inscricao findInscricaoByIdCurso(Aluno aluno, String idCurso) {
        for (Inscricao inscricao : aluno.getInscricoes()) {
            if (inscricao.getCurso().getSigla().equals(idCurso)) {
                return inscricao;
            }
        }
        return null;
    }

    // Devolve a lista de cursos em que o aluno está inscrito (sem duplicados)
    public List<Curso> listInscricoesDoAluno(Aluno aluno) {
        return aluno.getInscricoes().stream()
                .map(Inscricao::getCurso)
                .distinct()
                .collect(Collectors.toList());
    }
}