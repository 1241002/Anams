package org.Controller;

import org.Model.Candidato;
import org.Model.Empresa;

public class SubmeterCandidatura_Controller {

    private final Empresa empresa;
    private Candidato candidatoEmEdicao;

    public SubmeterCandidatura_Controller(Empresa empresa) {
        this.empresa = empresa;
    }

    // 1.1: novaCandidatura()
    public void novaCandidatura() {
        this.candidatoEmEdicao = empresa.novaCandidatura();
    }

    // 2.1: setDados(...)
    public void setDados(String nome, String dataNasc, String habilitacoes, String email, String cc, String genero) {
        if (candidatoEmEdicao != null) {
            candidatoEmEdicao.setDados(nome, dataNasc, habilitacoes, email, cc, genero);
        }
    }

    // 3.1: submeterCandidatura()
    public boolean submeterCandidatura() {
        if (candidatoEmEdicao != null) {
            return empresa.registarCandidatura(candidatoEmEdicao);
        }
        return false;
    }

    public String getDadosCandidato() {
        return candidatoEmEdicao != null ? candidatoEmEdicao.toString() : "";
    }
}