/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Controller;

import org.Model.Empresa;
import org.Model.TipoCurso;
import java.util.List;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
/**
 * Controlador para criar e especificar um novo Tipo de Curso
 */
public class EspecificarTipoCurso_Controller {
    private final Empresa empresa;
    private TipoCurso tipoCurso;

    // Construtor: recebe a empresa para criar e registar tipos de curso
    public EspecificarTipoCurso_Controller(Empresa empresa) {
        this.empresa = empresa;
    }

    // Cria um novo TipoCurso vazio (pronto para preencher)
    public void novoTipoCurso() {
        this.tipoCurso = empresa.novoTipoCurso();
    }

    // Define a sigla e descrição do tipo de curso
    public void setDados(String sigla, String descricao) {
        this.tipoCurso.setSigla(sigla);
        this.tipoCurso.setDescricao(descricao);
    }

    // Devolve a lista completa de tipos de curso já registados
    public List<TipoCurso> listaTiposCurso() {
        return empresa.obterListaTiposCurso();
    }

    // Regista o tipo de curso na empresa (validação interna)
    public boolean especificarTipoCurso() {
        return this.empresa.especificarTipoCurso(this.tipoCurso);
    }

    // Devolve o tipo de curso atual como texto (para mostrar na UI)
    public String getTipoCursoAsString() {
        return this.tipoCurso.toString();
    }
}
