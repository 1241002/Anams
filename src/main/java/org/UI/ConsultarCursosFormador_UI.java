package org.UI;

import org.Controller.ConsultarCursosFormador_Controller;
import org.Model.Curso;
import org.Model.Empresa;
import org.Model.Formador;
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class ConsultarCursosFormador_UI {
    private final ConsultarCursosFormador_Controller controller;

    public ConsultarCursosFormador_UI(Empresa empresa) {
        this.controller = new ConsultarCursosFormador_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n=== Consultar Cursos (Formador) ===");

        // 1. Simular Login: Selecionar qual formador "eu sou"
        Formador formador = selecionaFormador();
        if (formador == null) return;

        System.out.println("\nFormador autenticado: " + formador.getNome());

        // 2. Obter cursos que leciona (Controller -> Registo -> Curso)
        List<Curso> cursos = controller.getCursosLecciona(formador.getNome());

        if (cursos.isEmpty()) {
            System.out.println("Não lhe estão atribuídos nenhuns cursos ou módulos.");
        } else {
            System.out.println("\n--- Os meus Cursos/Módulos ---");
            int i = 0;
            for (Curso c : cursos) {
                // Mostra o estado para confirmar se está Ativo/Concluído
                System.out.println(++i + ". " + c.getTitulo() + " [" + c.getSigla() + "] - Estado: " + c.getEstado());
            }
        }
    }

    private Formador selecionaFormador() {
        List<Formador> lista = controller.getListaFormadores();
        if (lista.isEmpty()) {
            System.out.println("Não existem formadores registados no sistema.");
            return null;
        }

        System.out.println("\nIdentifique-se (Selecione o Formador):");
        int i = 0;
        for (Formador f : lista) {
            System.out.println(++i + ". " + f.getNome() + " (" + f.getEmail() + ")");
        }

        int opcao = Utils.IntFromConsole("Opção: ") - 1;
        if (opcao >= 0 && opcao < lista.size()) {
            return lista.get(opcao);
        }
        System.out.println("Opção inválida.");
        return null;
    }
}