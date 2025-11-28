package org.Controller;

import org.Model.Candidato;
import org.Model.Empresa;
import org.Model.EstadoCandidatura;
import java.util.List;

public class ValidarCandidatura_Controller {

    private final Empresa empresa;

    public ValidarCandidatura_Controller(Empresa empresa) {
        this.empresa = empresa;
    }

    // 1. Obter lista de pendentes
    public List<Candidato> getCandidaturasPendentes() {
        return empresa.getCandidaturasPendentes();
    }

    // 2. Registar a decisão (Aceitar)
    public void aceitarCandidatura(Candidato candidato) {
        // "Candidatura aceite"
        empresa.registarDecisao(candidato, EstadoCandidatura.ACEITE, "Candidatura aceite. Bem-vindo!");
    }

    // 3. Registar a decisão (Rejeitar)
    public void rejeitarCandidatura(Candidato candidato, String motivo) {
        empresa.registarDecisao(candidato, EstadoCandidatura.REJEITADA, motivo);
    }
}