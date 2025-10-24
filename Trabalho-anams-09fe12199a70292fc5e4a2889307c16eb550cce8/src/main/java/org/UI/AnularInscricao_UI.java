package org.UI;

import org.Controller.AnularInscricao_Controller;
import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Empresa;
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AnularInscricao_UI {
    private AnularInscricao_Controller controller;
    private Scanner scanner = new Scanner(System.in);

    public AnularInscricao_UI(Empresa empresa) {
        this.controller = new AnularInscricao_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("Anular Inscrição de Aluno em Curso");
        listInscricoesDoAluno();

        String idCurso = Utils.readLineFromConsole("Digite o ID do curso: ");
        Aluno aluno = new Aluno();
        aluno.setNome(Utils.readLineFromConsole("Digite o nome do aluno: "));

        if (controller.anularInscricao(idCurso, aluno)) {
            System.out.println("Inscrição anulada com sucesso.");
        } else {
            System.out.println("Falha na anulação.");
        }
    }

    private void listInscricoesDoAluno() {
        List<Curso> cursos = controller.listInscricoesDoAluno(new Aluno()); // Corrigido aqui
        System.out.println("Cursos inscritos:");
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso = cursos.get(i);
            System.out.println((i + 1) + ". " + curso.getTitulo());
        }
    }
}