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
    private Empresa empresa; // Adicionada referência à empresa para buscar alunos

    public InscreverAluno_UI(Empresa empresa) {
        this.empresa = empresa; // Guardar a referência da empresa
        this.controller = new InscreverAluno_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n=== Inscrever Aluno em Curso ===");

        // 1. Seleciona o aluno (CORRIGIDO)
        Aluno aluno = selecionaAluno();
        if (aluno == null) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("Aluno selecionado: " + aluno.getNome());

        // 2. Lista cursos disponíveis (CORRIGIDO)
        List<Curso> cursos = controller.listAvailableCourses();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso disponível para inscrição.");
            return;
        }

        System.out.println("\nCursos disponíveis:");
        int idx = 0;
        for (Curso c : cursos) {
            System.out.println(++idx + ". " + c.getTitulo() + " [" + c.getSigla() + "]");
        }

        // 3. Escolhe curso
        int esc = Integer.parseInt(Utils.readLineFromConsole("Escolha o curso (nº): ")) - 1;
        if (esc < 0 || esc >= cursos.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        String idCurso = cursos.get(esc).getSigla();

        // 4. Regista a inscrição (CORRIGIDO)
        if (controller.registerInscription(idCurso, aluno)) {
            System.out.println("Inscrição bem-sucedida.");
        } else {
            System.out.println("Falha na inscrição (verifique se o curso existe ou se o aluno já está inscrito).");
        }
    }

    // Método auxiliar adicionado (baseado em AnularInscricao_UI)
    private Aluno selecionaAluno() {
        List<Aluno> alunos = empresa.getAlunos(); // Busca alunos da Empresa
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno (com matrícula validada) registado no sistema.");
            return null;
        }

        System.out.println("\nSelecione o aluno:");
        for (int i = 0; i < alunos.size(); i++) {
            Aluno a = alunos.get(i);
            System.out.println((i + 1) + ". " + a.getNome() + " (" + a.getEmail() + ")");
        }

        int op = Utils.IntFromConsole("Número do aluno: ") - 1;
        if (op >= 0 && op < alunos.size()) {
            return alunos.get(op);
        }

        System.out.println("Opção inválida.");
        return null;
    }

    /* O método listAvailableCourses original foi removido pois a sua lógica
       foi integrada no run() e não pedia o ID do curso, apenas o listava. */
}