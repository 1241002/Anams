package org.UI;

import org.Controller.ValidarMatriculaController;
import org.Model.Matricula;
import org.Utils.Utils;
import java.io.IOException;
import java.util.List;

public class ValidarMatricula_UI {

    private final ValidarMatriculaController ctrl;

    public ValidarMatricula_UI(org.Model.Empresa empresa) {
        ctrl = new ValidarMatriculaController(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n### Validar / Rejeitar Matrícula ###");

        List<Matricula> pendentes = ctrl.obterCandidaturasPendentes();
        if (pendentes.isEmpty()) {
            System.out.println("Nenhuma candidatura pendente.");
            return;
        }

        int idx = 0;
        for (Matricula m : pendentes) System.out.println(++idx + ". " + m);

        int escolha = Integer.parseInt(Utils.readLineFromConsole("Escolha uma candidatura (nº): ")) - 1;
        ctrl.selecionarCandidatura(escolha);

        System.out.println("\nDados da candidatura:");
        System.out.println(ctrl.getCandidaturaAsString());

        String decisao = Utils.readLineFromConsole("Deseja ACEITAR (A) ou REJEITAR (R)? ").trim().toUpperCase();

        if (decisao.equals("A")) {
            ctrl.aceitar();
            System.out.println("Candidatura ACEITE. Notificação enviada.");
        } else if (decisao.equals("R")) {
            String just = Utils.readLineFromConsole("Justificativo da rejeição: ");
            ctrl.rejeitar(just);
            System.out.println("Candidatura REJEITADA. Notificação enviada.");
        } else {
            System.out.println("Opção inválida. Operação cancelada.");
        }
    }
}