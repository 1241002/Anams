// AnularInscricao_UI.java
package org.UI;

import org.Controller.AnularInscricao_Controller;
import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Empresa;
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class AnularInscricao_UI {
    private final AnularInscricao_Controller controller;
    private final Empresa empresa;

    // Construtor
    public AnularInscricao_UI(Empresa empresa) {
        this.empresa = empresa;
        this.controller = new AnularInscricao_Controller(empresa);
    }

    // Fluxo principal
    public void run() throws IOException {
        System.out.println("\n=== Anular Inscrição de Aluno em Curso ===");

        // 1. Seleciona o aluno
        Aluno aluno = selecionaAluno();
        if (aluno == null) return;

        // 2. Mostra cursos inscritos
        List<Curso> cursos = controller.listInscricoesDoAluno(aluno);
        if (cursos.isEmpty()) {
            System.out.println("Este aluno não está inscrito em nenhum curso.");
            return;
        }

        // 3. Lista cursos com número
        System.out.println("\nCursos inscritos:");
        for (int i = 0; i < cursos.size(); i++) {
            Curso c = cursos.get(i);
            System.out.println((i + 1) + ". " + c.getTitulo() + " [" + c.getSigla() + "]");
        }

        // 4. Escolhe curso
        int escolha = Utils.IntFromConsole("Escolha o curso a anular (número): ") - 1;
        if (escolha < 0 || escolha >= cursos.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        String idCurso = cursos.get(escolha).getSigla();

        // 5. Confirma
        if (!Utils.confirma("Tem a certeza que quer anular esta inscrição? (S/N)")) {
            System.out.println("Operação cancelada.");
            return;
        }

        // 6. Anula
        if (controller.anularInscricao(idCurso, aluno)) {
            System.out.println("Inscrição anulada com sucesso!");
        } else {
            System.out.println("Falha ao anular a inscrição.");
        }
    }

    // Seleciona aluno da lista completa
    private Aluno selecionaAluno() {
        List<Aluno> alunos = empresa.getAlunos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno registado no sistema.");
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
}