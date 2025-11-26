package org.UI;

import org.Controller.RegistarCursoController;
import org.Model.*;
import org.Utils.Data;
import org.Utils.Utils;

import java.util.List;

public class RegistarCursoUI {
    private final RegistarCursoController controller;

    public RegistarCursoUI(Empresa empresa) {
        this.controller = new RegistarCursoController(empresa);
    }

    public void run() {
        System.out.println("\n=== Registar Novo Curso (IT2) ===");

        controller.novoCurso();
        introduzDadosCurso();

        System.out.println("\n--- Dados do Curso ---");
        System.out.println(controller.getCursoAsString());

        // Loop para adicionar módulos
        do {
            if (!Utils.confirma("Pretende adicionar um módulo? (S/N)")) {
                break;
            }
            adicionarModulo();
        } while (true);

        if (Utils.confirma("\nConfirma o registo final do curso? (S/N)")) {
            if (controller.registaCurso()) {
                System.out.println("\nCurso registado com sucesso!");
                System.out.println(controller.getCursoAsString());
            } else {
                System.out.println("\nErro: O curso precisa de pelo menos 1 módulo válido.");
            }
        } else {
            System.out.println("Registo cancelado.");
        }
    }

    private void introduzDadosCurso() {
        String titulo = Utils.readLineFromConsole("Título do curso: ");
        String sigla = Utils.readLineFromConsole("Sigla: ");
        TipoCurso tipo = selecionaTipoCurso();
        String descricao = Utils.readLineFromConsole("Descrição: ");
        Data dataInicio = Utils.readDataFromConsole("Data de início (dd-MM-aaaa): ");
        Data dataFim = Utils.readDataFromConsole("Data de término (dd-MM-aaaa): ");

        controller.setDadosCurso(titulo, sigla, tipo, descricao, dataInicio, dataFim);
    }

    private void adicionarModulo() {
        System.out.println("\n>> Adicionar Módulo");
        controller.iniciarNovoModulo();

        // 1. Dados Básicos do Módulo
        String titulo = Utils.readLineFromConsole("Título do módulo: ");
        int carga = Utils.IntFromConsole("Carga horária (horas): ");
        // Requisito IT2: Ponderação
        int ponderacao = Utils.IntFromConsole("Ponderação na nota final (0-100%): ");

        Formador formador = selecionaFormador();

        // Atualizado para aceitar os 4 argumentos corretos
        controller.setDadosModulo(titulo, carga, (double)ponderacao, formador);

        // 2. Adicionar Sessões (Requisito IT2: Mínimo 3)
        System.out.println(">> Definir Sessões (Mínimo 3 obrigatórias)");
        int contadorSessoes = 0;
        do {
            System.out.println("Sessão #" + (contadorSessoes + 1));
            adicionarSessao();
            contadorSessoes++;

            if (contadorSessoes >= 3) {
                if (!Utils.confirma("Adicionar mais sessões? (S/N)")) break;
            }
        } while (true);

        // 3. Guardar Módulo
        if (controller.adicionarModuloAtual()) {
            System.out.println("Módulo adicionado com sucesso.");
        } else {
            System.out.println("Erro: Módulo inválido (verifique se tem 3 sessões).");
        }
    }

    private void adicionarSessao() {
        Data data = Utils.readDataFromConsole("Data da sessão: ");
        int duracao = Utils.IntFromConsole("Duração (horas): ");
        String sala = Utils.readLineFromConsole("Sala: ");

        controller.adicionarSessao(data, duracao, sala);
    }

    private TipoCurso selecionaTipoCurso() {
        List<TipoCurso> tipos = controller.getTiposCurso();
        if (tipos.isEmpty()) {
            System.out.println("Nenhum tipo de curso disponível.");
            return null;
        }
        Utils.apresentaLista(tipos, "\nTipos de Curso Disponíveis:");
        int opcao = Utils.IntFromConsole("Escolha o tipo (número): ");
        if (opcao < 1 || opcao > tipos.size()) return tipos.get(0);
        return tipos.get(opcao - 1);
    }

    private Formador selecionaFormador() {
        List<Formador> formadores = controller.getFormadores();
        if (formadores.isEmpty()) {
            System.out.println("Nenhum formador disponível.");
            return null;
        }
        Utils.apresentaLista(formadores, "\nFormadores Disponíveis:");
        int opcao = Utils.IntFromConsole("Escolha o formador (número): ");
        if (opcao < 1 || opcao > formadores.size()) return formadores.get(0);
        return formadores.get(opcao - 1);
    }
}