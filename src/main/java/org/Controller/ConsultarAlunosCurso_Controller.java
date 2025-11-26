package org.Controller;

import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Empresa;
import org.Model.Inscricao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultarAlunosCurso_Controller {
    private Empresa empresa;

    // Construtor: recebe a empresa para aceder aos cursos
    public ConsultarAlunosCurso_Controller(Empresa empresa) {
        this.empresa = empresa;
    }

    // Devolve todos os cursos registados na empresa
    public List<Curso> listAvailableCourses() {
        return empresa.getCursos();
    }

    public List<Aluno> obterAlunosDoCurso(Curso curso) {
        List<Aluno> alunos = new ArrayList<>();

        // Percorre todas as inscrições do curso
        for (Inscricao inscricao : curso.getInscricoes()) {
            Aluno aluno = inscricao.getAluno();

            // Evita duplicados
            if (!alunos.contains(aluno)) {
                alunos.add(aluno);
            }
        }

        return alunos;
    }
}