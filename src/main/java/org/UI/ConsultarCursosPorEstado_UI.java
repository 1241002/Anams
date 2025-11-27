package org.UI;

import org.Controller.ConsultarCursosPorEstadoController;
import org.Model.Curso;
import org.Model.Empresa;
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class ConsultarCursosPorEstado_UI {

    private final ConsultarCursosPorEstadoController ctrl;

    public ConsultarCursosPorEstado_UI(Empresa empresa) {
        ctrl = new ConsultarCursosPorEstadoController(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n### Consultar Cursos por Estado ###");

        // 1. Obter a lista de estados do Controller
        List<String> estados = ctrl.obterEstados();

        if (estados.isEmpty()) {
            System.out.println("Não existem estados definidos no sistema.");
            return;
        }

        // 2. Apresentar e Selecionar
        System.out.println("Selecione o estado:");
        int i = 0;
        for (String e : estados) {
            System.out.println(++i + ". " + e);
        }

        int escolha = Utils.IntFromConsole("Opção: ") - 1;

        if (escolha < 0 || escolha >= estados.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        // Recupera o NOME do estado (ex: "A iniciar")
        String estadoSelecionado = estados.get(escolha);

        // 3. Pesquisar Cursos por esse estado
        List<Curso> cursos = ctrl.obterCursosPorEstado(estadoSelecionado);

        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso encontrado no estado: " + estadoSelecionado);
        } else {
            System.out.println("\nCursos em '" + estadoSelecionado + "':");
            for (Curso c : cursos) {
                System.out.println(" - " + c.toString());
            }
        }

        System.out.println("\nOperação concluída.");
    }
}