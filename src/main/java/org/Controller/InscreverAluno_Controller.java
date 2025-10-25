package org.Controller;


import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Inscricao;
import org.Model.Empresa;

import java.util.List;

public class InscreverAluno_Controller {
    private Empresa empresa;

    public InscreverAluno_Controller(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean registerInscription(String idCurso, Aluno aluno) {
        Curso curso = empresa.findCursoById(idCurso);
        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return false;
        }

        Inscricao inscricao = new Inscricao(aluno, curso);
        curso.adicionarInscricao(inscricao);
        aluno.adicionarInscricao(inscricao);

        // Simulação de validação
        inscricao.setAprovada(true);

        System.out.println("Inscrição realizada com sucesso.");
        return true;
    }

    public List<Curso> listAvailableCourses() {
        return empresa.getAvailableCourses();
    }
}
