package org.UI;

import org.Utils.Utils;
import org.Model.Empresa;
import org.Controller.RegistarFormadorController;
import org.Model.Formador;

public class RegistarFormadorUI {
    private final RegistarFormadorController controller;

    public RegistarFormadorUI(Empresa empresa) {
        this.controller = new RegistarFormadorController(empresa);
    }

    public void run() {
        System.out.println("\n=== Registar Novo Formador ===");

        // 1. Cria um novo formador em branco
        controller.novoFormador();

        // 2. Lê os dados do utilizador
        introduzDados();

        // 3. Mostra apenas os dados introduzidos (sem ID nem credenciais ainda)
        System.out.println("\n--- Dados introduzidos ---");
        System.out.println(dadosIntroduzidosAsString());

        // 4. Confirmação
        if (!Utils.confirma("Confirma o registo? (S/N)")) {
            System.out.println("Registo cancelado.");
            return;
        }

        // 5. Regista → gera ID + credenciais + envia e-mail simulado
        if (controller.registaFormador()) {
            System.out.println("\nFormador registado com sucesso!");
            System.out.println("\n--- Dados finais (com ID e credenciais) ---");
            System.out.println(controller.getFormadorAsString());
            System.out.println("\nCredenciais enviadas por e-mail.");
        } else {
            System.out.println("\nErro: o e-mail já está em uso.");
        }
    }

    private void introduzDados() {
        String nome = Utils.readLineFromConsole("Nome: ");
        String dataNascimento = Utils.readLineFromConsole("Data de nascimento (AAAA-MM-DD): ");
        String cc = Utils.readLineFromConsole("Número de cartão de cidadão: ");
        String email = Utils.readLineFromConsole("Email: ");
        String contacto = Utils.readLineFromConsole("Contacto: ");
        String areaEspecial = Utils.readLineFromConsole("Área de especialização: ");

        controller.setDados(nome, dataNascimento, cc, email, contacto, areaEspecial);
    }

    // Mostra apenas os dados preenchidos pelo utilizador
    private String dadosIntroduzidosAsString() {
        Formador f = controller.getFormadorEmConstrucao();
        return String.format(
                "Nome: %s\n" +
                        "Data de nascimento: %s\n" +
                        "CC: %s\n" +
                        "Email: %s\n" +
                        "Contacto: %s\n" +
                        "Área de especialização: %s\n",
                f.getNome(),
                f.getDataNascimento(),
                f.getCc(),
                f.getEmail(),
                f.getContacto(),
                f.getAreaEspecial()
        );
    }
}