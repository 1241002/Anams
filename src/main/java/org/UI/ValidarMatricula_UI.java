package org.UI;

import org.Controller.ValidarMatriculaController;
import org.Model.Matricula;
import org.Utils.Utils;
import java.io.IOException;
import java.util.List;

/**
 * Interface de utilizador para validação ou rejeição de matrículas pendentes.
 * Permite ao Coordenador Académico analisar candidaturas, tomar decisão
 * e registar justificativa em caso de rejeição.
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class ValidarMatricula_UI {

    private final ValidarMatriculaController ctrl;   // Controlador que gere a lógica de validação

    /**
     * Construtor: recebe a instância da empresa e cria o controlador associado.
     *
     * @param empresa Instância partilhada do modelo (Empresa)
     */
    public ValidarMatricula_UI(org.Model.Empresa empresa) {
        ctrl = new ValidarMatriculaController(empresa);
    }

    /**
     * Executa o fluxo completo de validação de uma matrícula:
     * - Lista candidaturas pendentes
     * - Permite seleção
     * - Mostra detalhes
     * - Aceita ou rejeita com justificativa
     *
     * @throws IOException Em caso de erro de leitura do input
     */
    public void run() throws IOException {
        System.out.println("\n### Validar / Rejeitar Matrícula ###");

        // 1. Obtém e verifica se existem candidaturas pendentes
        List<Matricula> pendentes = ctrl.obterCandidaturasPendentes();
        if (pendentes.isEmpty()) {
            System.out.println("Nenhuma candidatura pendente.");
            return;
        }

        // 2. Lista todas as candidaturas com número sequencial
        System.out.println("\nCandidaturas pendentes:");
        int idx = 0;
        for (Matricula m : pendentes) {
            System.out.println(++idx + ". " + m.getNome() + " (CC: " + m.getCc() + ")");
        }

        // 3. Solicita seleção de candidatura
        int escolha = Integer.parseInt(Utils.readLineFromConsole("Escolha uma candidatura (nº): ")) - 1;
        if (escolha < 0 || escolha >= pendentes.size()) {
            System.out.println("Seleção inválida. Operação cancelada.");
            return;
        }
        ctrl.selecionarCandidatura(escolha);

        // 4. Mostra detalhes completos da candidatura selecionada
        System.out.println("\nDados da candidatura selecionada:");
        System.out.println(ctrl.getCandidaturaAsString());

        // 5. Pergunta a decisão: Aceitar ou Rejeitar
        String decisao = Utils.readLineFromConsole("Deseja ACEITAR (A) ou REJEITAR (R)? ").trim().toUpperCase();

        if (decisao.equals("A")) {
            // Aceita a candidatura
            ctrl.aceitar();
            System.out.println("Candidatura ACEITE com sucesso. Notificação enviada ao aluno.");
        }
        else if (decisao.equals("R")) {
            // Rejeita com justificativa
            String just = Utils.readLineFromConsole("Justificativo da rejeição: ");
            ctrl.rejeitar(just);
            System.out.println("Candidatura REJEITADA. Notificação com justificativa enviada.");
        }
        else {
            System.out.println("Opção inválida. Operação cancelada.");
        }
    }
}