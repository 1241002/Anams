package org.UI;

import org.Controller.SubmeterCandidatura_Controller;
import org.Model.Empresa;
import org.Utils.Utils;
import java.io.IOException;

public class SubmeterCandidatura_UI {

    private final SubmeterCandidatura_Controller ctrl;

    public SubmeterCandidatura_UI(Empresa empresa) {
        ctrl = new SubmeterCandidatura_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n### Submeter Candidatura ###");

        // 1. Iniciar
        ctrl.novaCandidatura();

        // 2. Introduzir Dados
        String nome  = Utils.readLineFromConsole("Nome completo: ");
        String data  = Utils.readLineFromConsole("Data de nascimento (dd-MM-yyyy): ");
        String hab   = Utils.readLineFromConsole("Habilitações académicas: ");
        String email = Utils.readLineFromConsole("Email: ");
        String cc    = Utils.readLineFromConsole("Cartão de cidadão: ");
        String gen   = Utils.readLineFromConsole("Género (M/F/Outro): ");

        ctrl.setDados(nome, data, hab, email, cc, gen);

        // 3. Confirmar
        System.out.println("\nDados da candidatura:");
        System.out.println(ctrl.getDadosCandidato());

        if (Utils.confirma("Confirma a submissão? (S/N)")) {
            // 3.1 Submeter (Regista, Gera Credenciais, Envia Email)
            if (ctrl.submeterCandidatura()) {
                System.out.println("Candidatura submetida com sucesso! Verifique o seu email.");
            } else {
                System.out.println("Erro ao submeter (dados inválidos).");
            }
        }
    }
}