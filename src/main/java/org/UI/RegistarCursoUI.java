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
        System.out.println("\n=== Registar Novo Curso ===");

        controller.novoCurso();
        introduzDadosCurso();

        System.out.println("\n--- Dados do Curso ---");
        System.out.println(dadosCursoAsString());

        // Loop para adicionar módulos
        do {
            if (!Utils.confirma("Pretende adicionar módulos? (S/N)")) {
                break;
            }
            adicionarModulo();
        } while (true);

        if (Utils.confirma("\nConfirma o registo do curso? (S/N)")) {
            if (controller.registaCurso()) {
                System.out.println("\nCurso registado com sucesso!");
                System.out.println("\n--- Curso Final ---");
                System.out.println(controller.getCursoAsString());
            } else {
                System.out.println("\nErro: o curso precisa de pelo menos 1 módulo.");
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

    private TipoCurso selecionaTipoCurso() {
        List<TipoCurso> tipos = controller.getTiposCurso();
        if (tipos.isEmpty()) {
            System.out.println("Nenhum tipo de curso disponível.");
            return null;
        }

        // Usa Utils.apresentaLista() → já formata com números e "0 - Cancelar"
        Utils.apresentaLista(tipos, "\nTipos de Curso Disponíveis:");

        int opcao = Utils.IntFromConsole("Escolha o tipo (número): ");
        if (opcao < 1 || opcao > tipos.size()) {
            System.out.println("Opção inválida. Usando o primeiro disponível.");
            return tipos.get(0);
        }
        return tipos.get(opcao - 1);
    }

    private void adicionarModulo() {
        controller.iniciarNovoModulo();

        String titulo = Utils.readLineFromConsole("Título do módulo: ");
        int carga = Utils.IntFromConsole("Carga horária (horas): ");
        Data dataInicio = Utils.readDataFromConsole("Data de início do módulo (dd-MM-aaaa): ");
        Data dataFim = Utils.readDataFromConsole("Data de término do módulo (dd-MM-aaaa): ");
        String sala = Utils.readLineFromConsole("Sala: ");
        Formador formador = selecionaFormador();

        controller.setDadosModulo(titulo, carga, dataInicio, dataFim, sala, formador);

        if (controller.adicionarModuloAtual()) {
            System.out.println("Módulo adicionado com sucesso.");
        } else {
            System.out.println("Erro: dados do módulo inválidos.");
        }
    }

    private Formador selecionaFormador() {
        List<Formador> formadores = controller.getFormadores();
        if (formadores.isEmpty()) {
            System.out.println("Nenhum formador disponível.");
            return null;
        }

        Utils.apresentaLista(formadores, "\nFormadores Disponíveis:");

        int opcao = Utils.IntFromConsole("Escolha o formador (número): ");
        if (opcao < 1 || opcao > formadores.size()) {
            System.out.println("Opção inválida. Usando o primeiro disponível.");
            return formadores.get(0);
        }
        return formadores.get(opcao - 1);
    }

    private String dadosCursoAsString() {
        Curso c = controller.getCursoEmConstrucao();
        return String.format(
                "Título: %s\n" +
                        "Sigla: %s\n" +
                        "Tipo: %s\n" +
                        "Descrição: %s\n" +
                        "Período: %s a %s\n",
                c.getTitulo(),
                c.getSigla(),
                c.getTipo() != null ? c.getTipo().getDesignacao() : "n/a",
                c.getDescricao(),
                c.getDataInicio(),
                c.getDataFim()
        );
    }
}