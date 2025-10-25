package org.UI;

import org.Controller.AdicionarModuloController;
import org.Model.*;
import org.Utils.Utils;
import java.io.IOException;
import java.util.List;

public class AdicionarModulo_UI {

    private final AdicionarModuloController ctrl;

    public AdicionarModulo_UI(Empresa empresa) {
        ctrl = new AdicionarModuloController(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n### Adicionar Módulo a Curso ###");

        List<Curso> cursos = ctrl.obterCursos();
        if (cursos.isEmpty()) { System.out.println("Nenhum curso disponível."); return; }

        int idx = 0; for (Curso c : cursos) System.out.println(++idx + ". " + c);
        int escCurso = Integer.parseInt(Utils.readLineFromConsole("Escolha o curso (nº): ")) - 1;
        ctrl.selecionarCurso(escCurso);

        String titulo = Utils.readLineFromConsole("Título do módulo: ");
        int ch      = Integer.parseInt(Utils.readLineFromConsole("Carga horária: "));
        String dataI= Utils.readLineFromConsole("Data início (dd-MM-yyyy): ");
        String dataF= Utils.readLineFromConsole("Data fim (dd-MM-yyyy): ");
        String hora = Utils.readLineFromConsole("Horário (ex: Seg 14h-16h): ");

        List<Formador> formadores = ctrl.obterFormadores();
        int fIdx = 0; for (Formador f : formadores) System.out.println(++fIdx + ". " + f);
        int escForm = Integer.parseInt(Utils.readLineFromConsole("Escolha o formador (nº): ")) - 1;
        Formador formador = formadores.get(escForm);

        ctrl.criarModulo(titulo, ch, dataI, dataF, hora, formador);

        if (!ctrl.dadosOk()) {
            System.out.println("Horário sobrepõe-se a outro módulo do mesmo formador.");
            return;
        }

        System.out.println("\nDados do módulo:\n" + ctrl.getDadosModulo());
        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (ctrl.confirmarRegisto()) System.out.println("Módulo registado com sucesso!");
            else System.out.println("Erro ao registar módulo.");
        }
    }
}