package org.UI;

import org.Controller.AdicionarModuloController;
import org.Model.*;
import org.Utils.Utils;
import org.Utils.Data;
import java.io.IOException;
import java.util.List;

public class AdicionarModulo_UI {

    private final AdicionarModuloController ctrl;

    // Construtor: recebe a empresa e cria o controlador
    public AdicionarModulo_UI(Empresa empresa) {
        ctrl = new AdicionarModuloController(empresa);
    }

    // Executa o fluxo completo de adicionar um módulo a um curso
    public void run() throws IOException {
        System.out.println("\n### Adicionar Módulo a Curso ###");

        // Mostra todos os cursos disponíveis
        List<Curso> cursos = ctrl.obterCursos();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso disponível.");
            return;
        }

        // Lista cursos com número
        int idx = 0;
        for (Curso c : cursos) System.out.println(++idx + ". " + c);

        // Lê escolha do usuário e seleciona o curso
        int escCurso = Integer.parseInt(Utils.readLineFromConsole("Escolha o curso (nº): ")) - 1;
        ctrl.selecionarCurso(escCurso);

        // Lê dados do módulo
        String titulo = Utils.readLineFromConsole("Título do módulo: ");
        int ch      = Integer.parseInt(Utils.readLineFromConsole("Carga horária: "));
        Data dataI = Utils.readDataFromConsole("Data início (dd-MM-yyyy): ");
        Data dataF = Utils.readDataFromConsole("Data fim (dd-MM-yyyy): ");
        String hora = Utils.readLineFromConsole("Horário (ex: Seg 14h-16h): ");

        // Mostra formadores disponíveis
        List<Formador> formadores = ctrl.obterFormadores();
        int fIdx = 0;
        for (Formador f : formadores) System.out.println(++fIdx + ". " + f);

        // Lê escolha do formador
        int escForm = Integer.parseInt(Utils.readLineFromConsole("Escolha o formador (nº): ")) - 1;
        Formador formador = formadores.get(escForm);

        // Cria o módulo com os dados
        ctrl.criarModulo(titulo, ch, dataI, dataF, hora, formador);

        // Verifica conflito de horário
        if (!ctrl.dadosOk()) {
            System.out.println("Horário sobrepõe-se a outro módulo do mesmo formador.");
            return;
        }

        // Mostra resumo do módulo
        System.out.println("\nDados do módulo:\n" + ctrl.getDadosModulo());

        // Confirma e regista
        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (ctrl.confirmarRegisto())
                System.out.println("Módulo registado com sucesso!");
            else
                System.out.println("Erro ao registar módulo.");
        }
    }
}