/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Model;

/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class TipoCurso {
    private String sigla;
    private String descricao;

    private static final String STRING_POR_OMISSAO = "a definir";

    public TipoCurso() {
        this.setSigla(STRING_POR_OMISSAO);
        this.setDescricao(STRING_POR_OMISSAO);
    }

    public TipoCurso(String sigla, String descricao) {
        this.sigla = sigla;
        this.descricao = descricao;
    }

    // === Getters & Setters (mantidos) ===
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // === NOVO: getDesignacao() – usado em UIs e toString do Curso ===
    public String getDesignacao() {
        return descricao; // "designação" = descrição do tipo de curso
    }

    // === Validação local (mantida) ===
    public boolean valida() {
        // por completar: validação real
        return sigla != null && !sigla.trim().isEmpty() &&
                descricao != null && !descricao.trim().isEmpty();
    }


    // === toString melhorado
    @Override
    public String toString() {
        return String.format("TipoCurso [Sigla: %s | Descrição: %s]", sigla, descricao);
    }
}