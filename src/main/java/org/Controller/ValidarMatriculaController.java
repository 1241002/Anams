package org.Controller;

import org.Model.Empresa;
import org.Model.Matricula;
import java.util.List;

public class ValidarMatriculaController {

    private final Empresa empresa;
    private Matricula candidaturaSelecionada;

    public ValidarMatriculaController(Empresa empresa) { this.empresa = empresa; }

    /* devolve matrículas em estado PENDENTE */
    public List<Matricula> obterCandidaturasPendentes() {
        return empresa.filtrarMatriculasPorEstado(org.Model.EstadoMatricula.PENDENTE);
    }

    public void selecionarCandidatura(int idx) {
        List<Matricula> lista = obterCandidaturasPendentes();
        if (idx >= 0 && idx < lista.size()) candidaturaSelecionada = lista.get(idx);
    }

    public void aceitar() {
        if (candidaturaSelecionada == null) return;
        candidaturaSelecionada.setEstado(org.Model.EstadoMatricula.ACEITE);
        empresa.atualizarMatricula(candidaturaSelecionada);
        empresa.enviarNotificacao(candidaturaSelecionada, "ACEITE");
    }

    public void rejeitar(String justificacao) {
        if (candidaturaSelecionada == null) return;
        candidaturaSelecionada.setEstado(org.Model.EstadoMatricula.REJEITADA);
        candidaturaSelecionada.setJustificacao(justificacao);
        empresa.atualizarMatricula(candidaturaSelecionada);
        empresa.enviarNotificacao(candidaturaSelecionada, "REJEITADA");
    }

    public String getCandidaturaAsString() {
        return candidaturaSelecionada.toString();
    }
}