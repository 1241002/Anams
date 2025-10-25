package org.UI;

import org.Utils.Utils;
import org.Model.Empresa;
import org.Controller.RegistarCA_Controller;

import java.io.IOException;

public class RegistarCA_UI {
    private final Empresa empresa;
    private final RegistarCA_Controller controller;

    public RegistarCA_UI(Empresa empresa) {
        this.empresa = empresa;
        controller = new RegistarCA_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n*** Registar Coordenador Académico ***");

        controller.novoCA();
        introduzDados();
        String creds = controller.geraCredenciais();
        apresentaDados(creds);

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (controller.registaCA()) {
                System.out.println("Coordenador registado com sucesso!");
            } else {
                System.out.println("Erro: dados inválidos ou sigla duplicada.");
            }
        }
    }

    private void introduzDados() {
        String sigla  = Utils.readLineFromConsole("Sigla: ");
        String nome   = Utils.readLineFromConsole("Nome completo: ");
        String cc     = Utils.readLineFromConsole("Cartão de cidadão: ");
        String email  = Utils.readLineFromConsole("Email: ");
        String tel    = Utils.readLineFromConsole("Contacto telefónico: ");
        controller.setDados(sigla, nome, cc, email, tel);
    }

    private void apresentaDados(String creds) {
        System.out.println("\nDados do novo CA:");
        System.out.println(controller.getCAAsString());
        System.out.println("Credenciais geradas: " + creds);
    }
}