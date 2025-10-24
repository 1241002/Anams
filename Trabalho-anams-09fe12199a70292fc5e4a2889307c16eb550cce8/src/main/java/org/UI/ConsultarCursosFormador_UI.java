package org.UI;

import org.Controller.ConsultarCursosFormador_Controller;
import org.Model.Curso;
import org.Model.Formador;
import org.Model.Empresa;
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsultarCursosFormador_UI {
    private ConsultarCursosFormador_Controller controller;
    private Scanner scanner = new Scanner(System.in);

    public ConsultarCursosFormador_UI(Empresa empresa) {
        this.controller = new ConsultarCursosFormador_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("Consultar Cursos Atribuídos a um Formador");

        String nomeFormador = Utils.readLineFromConsole("Digite o nome do formador: ");
        Formador formador = new Formador();
        formador.setNome(nomeFormador);

        List<Curso> cursos = controller.obterCursosDoFormador(formador);
        System.out.println("Cursos atribuídos ao formador:");
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso = cursos.get(i);
            System.out.println((i + 1) + ". " + curso.getTitulo());
        }
    }
}