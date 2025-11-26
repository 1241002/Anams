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
        List<Curso> cursos = controller.listAvailableCourses();
        for (int i = 0; i < cursos.size(); i++) System.out.println((i+1) + ". " + cursos.get(i).getTitulo());
        int i = Integer.parseInt(Utils.readLineFromConsole("Curso nº: ")) - 1;
        List<Aluno> alunos = controller.obterAlunosDoCurso(cursos.get(i));
        for (int j = 0; j < alunos.size(); j++) System.out.println((j+1) + ". " + alunos.get(j).getNome());
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
        for (Curso curso : controller.listAvailableCourses()) {
            if (curso.getSigla().equals(idCurso)) {
                return curso;
            }
        }
        return null;
    }
}