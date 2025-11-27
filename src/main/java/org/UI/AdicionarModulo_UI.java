package org.UI;

import org.Controller.AdicionarModuloController;
import org.Model.*;
import org.Utils.Utils;
import org.Utils.Data;
import java.io.IOException;
import java.util.List;

public class AdicionarModulo_UI {

    private final AdicionarModuloController ctrl;

    public AdicionarModulo_UI(Empresa empresa) {
        ctrl = new AdicionarModuloController(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n### Adicionar Módulo a Curso ###");

        // 1. Escolher Curso
        List<Curso> cursos = ctrl.obterListaCursos();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso disponível.");
            return;
        }

        int idx = 0;
        for (Curso c : cursos) System.out.println(++idx + ". " + c);

        int escCurso = Utils.IntFromConsole("Escolha o curso (nº): ") - 1;
        if (escCurso < 0 || escCurso >= cursos.size()) return;

        // Seleciona usando a SIGLA (conforme o diagrama pede)
        ctrl.selecionarCurso(cursos.get(escCurso).getSigla());

        // 2. Dados do Módulo
        String titulo = Utils.readLineFromConsole("Título do módulo: ");
        int ch = Utils.IntFromConsole("Carga horária: ");
        // === NOVO CAMPO ===
        double ponderacao = Double.parseDouble(Utils.readLineFromConsole("Ponderação (0-1 ou %): "));

        Data dataI = Utils.readDataFromConsole("Data início (dd-MM-yyyy): ");
        Data dataF = Utils.readDataFromConsole("Data fim (dd-MM-yyyy): ");
        String hora = Utils.readLineFromConsole("Horário (ex: Seg 14h-16h): ");

        // 3. Escolher Formador
        List<Formador> formadores = ctrl.obterFormadores();
        int fIdx = 0;
        for (Formador f : formadores) System.out.println(++fIdx + ". " + f.getNome());

        int escForm = Utils.IntFromConsole("Escolha o formador (nº): ") - 1;
        Formador formador = (escForm >= 0 && escForm < formadores.size()) ? formadores.get(escForm) : null;

        // Cria o módulo em memória
        ctrl.criarModulo(titulo, ch, dataI, dataF, hora, formador, ponderacao);

        // 4. Adicionar Sessões
        System.out.println("\n>> Adicionar Sessões");
        do {
            Data dSessao = Utils.readDataFromConsole("Data da sessão: ");
            int duracao = Utils.IntFromConsole("Duração (h): ");
            String sala = Utils.readLineFromConsole("Sala: ");

            ctrl.adicionarSessao(dSessao, duracao, sala);

        } while (Utils.confirma("Adicionar outra sessão? (S/N)"));

        // 5. Mostrar resumo e Confirmar
        System.out.println("\nDados do módulo:\n" + ctrl.getDadosModulo());

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (ctrl.confirmarRegisto())
                System.out.println("Módulo registado com sucesso!");
            else
                System.out.println("Erro ao registar módulo.");
        }
    }
}