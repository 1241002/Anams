package org.UI;

import org.Controller.AlterarEstadoCursoController;
import org.Model.Curso;
import org.Model.Empresa;
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class AlterarEstadoCurso_UI {

    private final AlterarEstadoCursoController controller;

    public AlterarEstadoCurso_UI(Empresa empresa) {
        this.controller = new AlterarEstadoCursoController(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n=== Alterar Estado de Curso ===");

        // 1. Listar Cursos
        List<Curso> cursos = controller.getListaCursos();

        if (cursos.isEmpty()) {
            System.out.println("Não existem cursos registados.");
            return;
        }

        System.out.println("Selecione o curso:");
        int i = 0;
        for (Curso c : cursos) {
            System.out.println(++i + ". " + c.getTitulo() + " [" + c.getSigla() + "] - Estado atual: " + c.getEstado());
        }

        int opcao = Utils.IntFromConsole("Opção: ") - 1;
        if (opcao < 0 || opcao >= cursos.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        Curso cursoSelecionado = cursos.get(opcao);

        // 2. Listar Estados Possíveis
        List<String> estados = controller.getEstadosPossiveis();
        System.out.println("\nEscolha o novo estado:");
        int j = 0;
        for (String estado : estados) {
            System.out.println(++j + ". " + estado);
        }

        int opEstado = Utils.IntFromConsole("Opção: ") - 1;
        if (opEstado < 0 || opEstado >= estados.size()) {
            System.out.println("Estado inválido.");
            return;
        }

        String novoEstado = estados.get(opEstado);

        // 3. Confirmar e Alterar
        System.out.println("\nVai alterar o estado de '" + cursoSelecionado.getEstado() + "' para '" + novoEstado + "'.");

        if (Utils.confirma("Confirma a alteração? (S/N)")) {
            if (controller.alterarEstadoCurso(cursoSelecionado.getSigla(), novoEstado)) {
                System.out.println("Estado alterado com sucesso.");
            } else {
                System.out.println("Erro ao alterar estado.");
            }
        }
    }
}