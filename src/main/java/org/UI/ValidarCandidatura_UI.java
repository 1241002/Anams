package org.UI;

import org.Controller.ValidarCandidatura_Controller;
import org.Model.Candidato;
import org.Model.Empresa;
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class ValidarCandidatura_UI {

    private final ValidarCandidatura_Controller ctrl;

    public ValidarCandidatura_UI(Empresa empresa) {
        this.ctrl = new ValidarCandidatura_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n### Validar Candidaturas Pendentes ###");

        // 1. Listar Pendentes
        List<Candidato> pendentes = ctrl.getCandidaturasPendentes();

        if (pendentes.isEmpty()) {
            System.out.println("Não existem candidaturas pendentes.");
            return;
        }

        System.out.println("Selecione a candidatura:");
        for (int i = 0; i < pendentes.size(); i++) {
            System.out.println((i + 1) + ". " + pendentes.get(i).toString());
        }

        // 2. Selecionar
        int opcao = Utils.IntFromConsole("Opção: ") - 1;
        if (opcao < 0 || opcao >= pendentes.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        Candidato candidatoSelecionado = pendentes.get(opcao);

        // 3. Tomar Decisão
        System.out.println("\nCandidato: " + candidatoSelecionado.getNome());
        String decisao = Utils.readLineFromConsole("Decisão: (A)ceitar ou (R)ejeitar? ").toUpperCase();

        if (decisao.equals("A")) {
            if (Utils.confirma("Tem a certeza que quer ACEITAR? O aluno será criado automaticamente. (S/N)")) {
                ctrl.aceitarCandidatura(candidatoSelecionado);
                System.out.println("Candidatura aceite com sucesso!");
            }
        } else if (decisao.equals("R")) {
            String motivo = Utils.readLineFromConsole("Indique o motivo da rejeição: ");
            if (Utils.confirma("Tem a certeza que quer REJEITAR? (S/N)")) {
                ctrl.rejeitarCandidatura(candidatoSelecionado, motivo);
                System.out.println("Candidatura rejeitada.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }
}