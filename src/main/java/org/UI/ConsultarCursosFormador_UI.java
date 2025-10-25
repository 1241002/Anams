package org.UI;

import org.Controller.ConsultarCursosFormador_Controller;
import org.Model.Curso;
import org.Model.Formador;
import org.Model.Empresa;
import org.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class ConsultarCursosFormador_UI {
    private final ConsultarCursosFormador_Controller controller;
    private final Empresa empresa;

    public ConsultarCursosFormador_UI(Empresa empresa) {
        this.empresa = empresa;
        this.controller = new ConsultarCursosFormador_Controller(empresa);
    }

    public void run() throws IOException {
        System.out.println("Consultar Cursos Atribuídos a um Formador");
        List<Formador> formadores = empresa.obterListaFormadores();
        for (int i = 0; i < formadores.size(); i++) System.out.println((i+1) + ". " + formadores.get(i).getNome());
        int i = Integer.parseInt(Utils.readLineFromConsole("Formador nº: ")) - 1;
        List<Curso> cursos = controller.obterCursosDoFormador(formadores.get(i));
        for (int j = 0; j < cursos.size(); j++) System.out.println((j+1) + ". " + cursos.get(j).getTitulo());
    }
}