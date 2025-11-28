package org.UI;

import org.Controller.ConsultarAlunosCurso_Controller;
import org.Model.Curso;
import org.Model.Empresa;
import org.Model.Formador;
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class ConsultarAlunosCurso_UI {
    private final ConsultarAlunosCurso_Controller controller;

    public ConsultarAlunosCurso_UI(Empresa empresa) {
        this.controller = new ConsultarAlunosCurso_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n=== Consultar Alunos de um Curso ===");

        // 1. Simulação de Login (Escolher Formador)
        Formador formador = selecionaFormador();
        if (formador == null) return;

        System.out.println("Formador autenticado: " + formador.getNome());

        // 2. Listar cursos deste formador
        List<Curso> cursos = controller.getCursosDoFormador(formador.getNome());

        if (cursos.isEmpty()) {
            System.out.println("Não tem cursos atribuídos.");
            return;
        }

        System.out.println("\nSelecione o curso:");
        int i = 0;
        for (Curso c : cursos) {
            System.out.println(++i + ". " + c.getTitulo() + " [" + c.getSigla() + "]");
        }

        // 3. Selecionar Curso
        int opcao = Utils.IntFromConsole("Opção: ") - 1;
        if (opcao < 0 || opcao >= cursos.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        Curso cursoSelecionado = cursos.get(opcao);

        // 4. Mostrar Alunos
        List<String> alunosInfo = controller.getAlunosDoCurso(cursoSelecionado.getSigla());

        if (alunosInfo.isEmpty()) {
            System.out.println("\nEste curso ainda não tem alunos inscritos.");
        } else {
            System.out.println("\n--- Lista de Alunos em " + cursoSelecionado.getSigla() + " ---");
            for (String info : alunosInfo) {
                System.out.println("- " + info);
            }
        }
    }

    private Formador selecionaFormador() {
        List<Formador> lista = controller.getListaFormadores();
        if (lista.isEmpty()) {
            System.out.println("Não existem formadores no sistema.");
            return null;
        }

        // === CORREÇÃO AQUI ===
        System.out.println("\nSelecione o Formador:");
        int i = 0;
        for(Formador f : lista) {
            System.out.println(++i + ". " + f.getNome());
        }
        int op = Utils.IntFromConsole("Opção: ") - 1;

        if (op >= 0 && op < lista.size()) return lista.get(op);

        System.out.println("Opção inválida.");
        return null;
    }
}