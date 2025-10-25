package org.UI;


import org.Controller.InscreverAluno_Controller;
import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Empresa;
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class InscreverAluno_UI {
    private InscreverAluno_Controller controller;
    private Scanner scanner = new Scanner(System.in);

    public InscreverAluno_UI(Empresa empresa) {
        this.controller = new InscreverAluno_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("Inscrever Aluno em Curso");
        listAvailableCourses();

        String idCurso = Utils.readLineFromConsole("Digite o ID do curso: ");
        Aluno aluno = new Aluno();
        aluno.setNome(Utils.readLineFromConsole("Digite o nome do aluno: "));

        if (controller.registerInscription(idCurso, aluno)) {
            System.out.println("Inscrição bem-sucedida.");
        } else {
            System.out.println("Falha na inscrição.");
        }
    }

    private void listAvailableCourses() {
        List<Curso> cursos = controller.listAvailableCourses();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso disponível.");
            return;
        }
        int idx = 0;
        for (Curso c : cursos) System.out.println(++idx + ". " + c.getTitulo() + " [" + c.getSigla() + "]");
        int esc = Integer.parseInt(Utils.readLineFromConsole("Escolha o curso (nº): ")) - 1;
        String idCurso = cursos.get(esc).getSigla();
    }
}
