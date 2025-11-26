package org.UI;

import org.Controller.AnularInscricao_Controller;
import org.Model.Empresa;
import org.Model.Inscricao; // Importante: Agora lidamos com Inscrições
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class AnularInscricao_UI {
    private final AnularInscricao_Controller controller;

    public AnularInscricao_UI(Empresa empresa) {
        this.controller = new AnularInscricao_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n=== Anular Inscrição ===");

        // 1. Pedir o nome do aluno primeiro para saber o que listar
        String nomeAluno = Utils.readLineFromConsole("Digite o nome do aluno: ");

        // 2. Listar as inscrições ativas desse aluno
        if (!listInscricoesDoAluno(nomeAluno)) {
            return; // Se não tem inscrições, sai
        }

        // 3. Pedir o ID do curso a anular
        String idCurso = Utils.readLineFromConsole("Digite a Sigla/ID do curso a anular: ");

        // 4. Confirmar
        if (Utils.confirma("Tem a certeza que deseja anular esta inscrição? (S/N)")) {
            // 5. Executar a anulação
            if (controller.anularInscricao(idCurso, nomeAluno)) {
                System.out.println("Inscrição anulada com sucesso.");
            } else {
                System.out.println("Falha na anulação: Curso não encontrado ou prazo expirado.");
            }
        }
    }

    private boolean listInscricoesDoAluno(String nomeAluno) {
        // O Controller devolve a lista de INSCRIÇÕES (não cursos diretos)
        List<Inscricao> inscricoes = controller.listInscricoesDoAluno(nomeAluno);

        if (inscricoes.isEmpty()) {
            System.out.println("Este aluno não tem inscrições ativas registadas.");
            return false;
        }

        System.out.println("\n--- Inscrições Ativas ---");
        for (Inscricao insc : inscricoes) {
            // Acede ao Curso através da Inscrição para mostrar o Título
            System.out.printf("- [%s] %s (Estado: %s)\n",
                    insc.getCurso().getSigla(),
                    insc.getCurso().getTitulo(),
                    insc.getEstado());
        }
        System.out.println();
        return true;
    }
}