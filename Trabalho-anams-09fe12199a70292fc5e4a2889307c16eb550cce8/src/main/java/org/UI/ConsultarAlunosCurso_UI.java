package org.UI;

import org.Controller.ConsultarAlunosCurso_Controller;
import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Empresa;
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsultarAlunosCurso_UI {
    private ConsultarAlunosCurso_Controller controller;
    private Scanner scanner = new Scanner(System.in);

    public ConsultarAlunosCurso_UI(Empresa empresa) {
        this.controller = new ConsultarAlunosCurso_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("Consultar Alunos de um Curso");
        listAvailableCourses();

        String idCurso = Utils.readLineFromConsole("Digite o ID do curso: ");
        Curso curso = findCourseById(idCurso);

        if (curso != null) {
            List<Aluno> alunos = controller.obterAlunosDoCurso(curso);
            System.out.println("Alunos inscritos no curso:");
            for (int i = 0; i < alunos.size(); i++) {
                Aluno aluno = alunos.get(i);
                System.out.println((i + 1) + ". " + aluno.getNome());
            }
        } else {
            System.out.println("Curso não encontrado.");
        }
    }

    private void listAvailableCourses() {
        List<Curso> cursos = controller.listAvailableCourses();
        System.out.println("Cursos disponíveis:");
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso = cursos.get(i);
            System.out.println((i + 1) + ". " + curso.getTitulo());
        }
    }

    private Curso findCourseById(String idCurso) {
        // Este método deve ser implementado para encontrar um curso pelo seu ID
        // Por exemplo:
        for (Curso curso : controller.listAvailableCourses()) {
            if (curso.getSigla().equals(idCurso)) {
                return curso;
            }
        }
        return null;
    }
}