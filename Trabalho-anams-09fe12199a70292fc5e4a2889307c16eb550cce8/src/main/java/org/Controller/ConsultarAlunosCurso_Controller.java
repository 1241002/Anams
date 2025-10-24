package org.Controller;

import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Empresa;
import org.Model.Turma;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultarAlunosCurso_Controller {
    private Empresa empresa;

    public ConsultarAlunosCurso_Controller(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Aluno> obterAlunosDoCurso(Curso curso) {
        return empresa.getCursos().stream()
                .filter(c -> c.getInscricoes().stream()
                        .anyMatch(inscricao -> inscricao.getCurso().equals(curso)))
                .flatMap(curso -> curso.getInscricoes().stream()
                        .map(inscricao -> inscricao.getAluno()))
                .collect(Collectors.toList());
    }
}