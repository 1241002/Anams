package org.UI;

import org.Controller.SubmeterMatriculaController;
import org.Model.Empresa;
import org.Utils.Utils;
import java.io.IOException;

public class SubmeterMatricula_UI {

    private final SubmeterMatriculaController ctrl;

    public SubmeterMatricula_UI(Empresa empresa) {
        ctrl = new SubmeterMatriculaController(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n### Submeter Matrícula ###");

        ctrl.novaMatricula();

        String nome  = Utils.readLineFromConsole("Nome completo: ");
        String data  = Utils.readLineFromConsole("Data de nascimento (dd-MM-yyyy): ");
        String hab   = Utils.readLineFromConsole("Habilitações académicas: ");
        String email = Utils.readLineFromConsole("Email: ");
        String cc    = Utils.readLineFromConsole("Cartão de cidadão: ");
        String gen   = Utils.readLineFromConsole("Género (M/F/Outro): ");

        ctrl.setDados(nome, data, hab, email, cc, gen);

        String creds = ctrl.gerarCredenciais();

        System.out.println("\nDados da matrícula:");
        System.out.println(ctrl.getMatriculaAsString());
        System.out.println("Credenciais geradas: " + creds);

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (ctrl.confirmarRegisto()) {
                System.out.println("Matrícula registada com sucesso!\nFoi enviado email com as credenciais.");
            } else {
                System.out.println("Erro ao registar matrícula.");
            }
        }
    }
}