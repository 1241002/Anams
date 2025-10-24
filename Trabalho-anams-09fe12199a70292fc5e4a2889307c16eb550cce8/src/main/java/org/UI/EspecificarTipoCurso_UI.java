/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.UI;

import org.Utils.Utils;
import org.Model.Empresa;
import org.Controller.EspecificarTipoCurso_Controller;
import java.util.List;
import org.Model.TipoCurso;

/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
// EspecificarTipoCurso_UI.java
import java.util.List;

public class EspecificarTipoCurso_UI {
    private Empresa empresa;
    private EspecificarTipoCurso_Controller controller;

    public EspecificarTipoCurso_UI(Empresa empresa) {
        this.empresa = empresa;
        controller = new EspecificarTipoCurso_Controller(empresa);
    }

    public void run() {
        System.out.println("\nNovo Tipo de Curso:");
        controller.novoTipoCurso();

        introduzDados();

        System.out.println("Tipos de Curso existentes:");
        listaTiposCurso();

        apresentaDados();

        if (Utils.confirma("Confirma os dados? (S/N)")) {
            if (controller.especificarTipoCurso()) {
                System.out.println("Dados do tipo de curso guardados com sucesso.");
            } else {
                System.out.println("Não foi possível guardar os dados do tipo de curso.");
            }
        }
    }

    private void introduzDados() {
        String sigla = Utils.readLineFromConsole("Introduza a sigla: ");
        String descricao = Utils.readLineFromConsole("Introduza uma descrição: ");
        controller.setDados(sigla, descricao);
    }

    private void listaTiposCurso() {
        List<TipoCurso> tiposCurso = controller.listaTiposCurso();
        for (TipoCurso tc : tiposCurso) {
            System.out.println(tc);
        }
    }

    private void apresentaDados() {
        System.out.println("\nTipo de curso:\n" + controller.getTipoCursoAsString());
    }
}
