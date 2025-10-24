package org.Controller;

import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Empresa;
import org.Model.Inscricao;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultarAlunosCurso_Controller {
    private Empresa empresa;

    public ConsultarAlunosCurso_Controller(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Curso> listAvailableCourses() {
        return empresa.getCursos();
    }

    public List<Aluno> obterAlunosDoCurso(Curso c) {
        return empresa.getCursos().stream()
                .flatMap(curso -> c.getInscricoes().stream()
                        .map(inscricao -> inscricao.getAluno()))
                .distinct()
                .collect(Collectors.toList());
    }
}