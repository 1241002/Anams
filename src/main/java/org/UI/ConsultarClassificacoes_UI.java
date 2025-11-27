package org.UI;

import org.Controller.ConsultarClassificacoes_Controller;
import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Empresa;
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class ConsultarClassificacoes_UI {

    private final ConsultarClassificacoes_Controller controller;

    public ConsultarClassificacoes_UI(Empresa empresa) {
        this.controller = new ConsultarClassificacoes_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n=== Consultar Classificações ===");

        // 1. Simular Login do Aluno
        Aluno aluno = selecionaAluno();
        if (aluno == null) return;
        System.out.println("Aluno autenticado: " + aluno.getNome());

        // 2. Listar Cursos do Aluno
        List<Curso> cursos = controller.obterCursosAluno(aluno.getNome());

        if (cursos.isEmpty()) {
            System.out.println("Não está inscrito em nenhum curso.");
            return;
        }

        System.out.println("\nSelecione o curso para ver as notas:");
        int idx = 0;
        for (Curso c : cursos) {
            System.out.println(++idx + ". " + c.getTitulo() + " (" + c.getSigla() + ")");
        }

        // 3. Selecionar Curso
        int opcao = Utils.IntFromConsole("Opção: ") - 1;
        if (opcao < 0 || opcao >= cursos.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        Curso cursoSelecionado = cursos.get(opcao);

        // 4. Mostrar Pauta
        System.out.println("\n--- PAUTA DE CLASSIFICAÇÕES: " + cursoSelecionado.getTitulo() + " ---");
        List<String> pauta = controller.obterClassificacoes(aluno.getNome(), cursoSelecionado.getSigla());

        for (String linha : pauta) {
            System.out.println(linha);
        }
        System.out.println("------------------------------------------------");
    }

    private Aluno selecionaAluno() {
        List<Aluno> lista = controller.getListaAlunos();
        if (lista.isEmpty()) {
            System.out.println("Sem alunos registados.");
            return null;
        }
        System.out.println("\nIdentifique-se (Aluno):");
        for(int i=0; i<lista.size(); i++) {
            System.out.println((i+1) + ". " + lista.get(i).getNome());
        }
        int op = Utils.IntFromConsole("Nº: ") - 1;
        return (op >= 0 && op < lista.size()) ? lista.get(op) : null;
    }
}