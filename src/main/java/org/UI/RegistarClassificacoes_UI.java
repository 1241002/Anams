package org.UI;

import org.Controller.RegistarClassificacoes_Controller;
import org.Model.*;
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class RegistarClassificacoes_UI {

    private final RegistarClassificacoes_Controller controller;

    public RegistarClassificacoes_UI(Empresa empresa) {
        this.controller = new RegistarClassificacoes_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n=== Registar Classificações (Notas) ===");

        // 1. Login do Formador
        Formador formador = selecionaFormador();
        if (formador == null) return;
        System.out.println("Autenticado como: " + formador.getNome());

        // 2. Selecionar Curso
        Curso curso = selecionaCurso(formador.getNome());
        if (curso == null) return;

        // 3. Selecionar Módulo (apenas os deste formador neste curso)
        Modulo modulo = selecionaModulo(curso.getSigla(), formador.getNome());
        if (modulo == null) return;

        // 4. Listar Alunos e Atribuir Notas
        lancarNotas(curso, modulo);
    }

    private void lancarNotas(Curso curso, Modulo modulo) {
        List<Inscricao> inscricoes = controller.getAlunosParaAvaliar(curso.getSigla());

        if (inscricoes.isEmpty()) {
            System.out.println("Não existem alunos ativos neste curso para avaliar.");
            return;
        }

        System.out.println("\n--- Lançar notas para o módulo: " + modulo.getTitulo() + " ---");

        boolean continuar = true;
        while (continuar) {
            // Listar alunos
            System.out.println("\nSelecione o aluno para avaliar:");
            for (int i = 0; i < inscricoes.size(); i++) {
                System.out.println((i + 1) + ". " + inscricoes.get(i).getAluno().getNome());
            }
            System.out.println("0. Terminar registo de notas");

            int opcao = Utils.IntFromConsole("Opção: ");

            if (opcao == 0) {
                continuar = false;
            } else if (opcao > 0 && opcao <= inscricoes.size()) {
                Inscricao inscricaoSelecionada = inscricoes.get(opcao - 1);

                // Pedir nota
                System.out.print("Nota para " + inscricaoSelecionada.getAluno().getNome() + ": ");
                double nota = Double.parseDouble(Utils.readLineFromConsole("")); // Simplificado

                // Registar
                if (controller.registarNota(modulo, inscricaoSelecionada, nota)) {
                    System.out.println("Nota registada com sucesso.");
                } else {
                    System.out.println("Erro: Nota inválida (deve ser entre 0 e 20).");
                }
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    private Formador selecionaFormador() {
        List<Formador> lista = controller.getListaFormadores();
        if (lista.isEmpty()) {
            System.out.println("Sem formadores registados.");
            return null;
        }
        Utils.apresentaLista(lista, "Selecione o Formador:");
        int op = Utils.IntFromConsole("Nº: ") - 1;
        return (op >= 0 && op < lista.size()) ? lista.get(op) : null;
    }

    private Curso selecionaCurso(String nomeFormador) {
        List<Curso> cursos = controller.getCursosFormador(nomeFormador);
        if (cursos.isEmpty()) {
            System.out.println("Este formador não tem cursos atribuídos.");
            return null;
        }
        System.out.println("\nCursos disponíveis:");
        for(int i=0; i<cursos.size(); i++)
            System.out.println((i+1) + ". " + cursos.get(i).getTitulo());

        int op = Utils.IntFromConsole("Escolha o curso: ") - 1;
        return (op >= 0 && op < cursos.size()) ? cursos.get(op) : null;
    }

    private Modulo selecionaModulo(String siglaCurso, String nomeFormador) {
        List<Modulo> modulos = controller.getModulosPeloFormador(siglaCurso, nomeFormador);
        if (modulos.isEmpty()) {
            System.out.println("Não é responsável por nenhum módulo neste curso.");
            return null;
        }
        System.out.println("\nMódulos sob sua responsabilidade:");
        for(int i=0; i<modulos.size(); i++)
            System.out.println((i+1) + ". " + modulos.get(i).getTitulo());

        int op = Utils.IntFromConsole("Escolha o módulo: ") - 1;
        return (op >= 0 && op < modulos.size()) ? modulos.get(op) : null;
    }
}
