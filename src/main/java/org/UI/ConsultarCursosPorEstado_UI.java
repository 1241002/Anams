package org.UI;

import org.Controller.ConsultarCursosPorEstadoController;
import org.Model.Curso;
import org.Utils.Utils;
import java.io.IOException;
import java.util.List;

public class ConsultarCursosPorEstado_UI {

    private final ConsultarCursosPorEstadoController ctrl;

    public ConsultarCursosPorEstado_UI(org.Model.Empresa empresa) {
        ctrl = new ConsultarCursosPorEstadoController(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n### Consultar Cursos por Estado ###");

        String[] estados = ctrl.obterOpcoesEstado();
        int i = 0;
        for (String e : estados) System.out.println(++i + ". " + e);

        int escolha = Integer.parseInt(Utils.readLineFromConsole("Selecione o estado (nº): ")) - 1;
        List<Curso> cursos = ctrl.obterCursosPorEstado(escolha);

        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso encontrado para esse estado.");
            return;
        }

        System.out.println("\nCursos encontrados:");
        for (Curso c : cursos) System.out.println(" - " + c);
        System.out.println("\nOperação concluída com sucesso.");
    }
}