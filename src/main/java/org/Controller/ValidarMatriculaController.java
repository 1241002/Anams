package org.Controller;

import org.Model.Aluno;
import org.Model.Empresa;
import org.Model.Matricula;
import java.util.List;

public class ValidarMatriculaController {

    private final Empresa empresa;
    private Matricula candidaturaSelecionada;

    // Construtor
    public ValidarMatriculaController(Empresa empresa) {
        this.empresa = empresa;
    }

    // Devolve matrículas PENDENTES
    public List<Matricula> obterCandidaturasPendentes() {
        return empresa.filtrarMatriculasPorEstado(org.Model.EstadoMatricula.PENDENTE);
    }

    // Seleciona uma candidatura
    public void selecionarCandidatura(int idx) {
        List<Matricula> lista = obterCandidaturasPendentes();
        if (idx >= 0 && idx < lista.size()) candidaturaSelecionada = lista.get(idx);
    }

    // ACEITA: cria Aluno real + guarda na empresa
    public void aceitar() {
        if (candidaturaSelecionada == null) return;

        // 1. Cria o Aluno real
        Aluno aluno = new Aluno();
        aluno.setNome(candidaturaSelecionada.getNome());
        aluno.setEmail(candidaturaSelecionada.getEmail());
        aluno.setCc(candidaturaSelecionada.getCc());
        // (podes adicionar mais campos se quiseres)

        // 2. Guarda o aluno na empresa
        empresa.addAluno(aluno);

        // 3. Atualiza estado da matrícula
        candidaturaSelecionada.setEstado(org.Model.EstadoMatricula.ACEITE);
        empresa.atualizarMatricula(candidaturaSelecionada);

        // 4. Notifica o aluno
        empresa.enviarNotificacao(candidaturaSelecionada, "ACEITE");
    }

    // Rejeita com justificativa
    public void rejeitar(String justificacao) {
        if (candidaturaSelecionada == null) return;
        candidaturaSelecionada.setEstado(org.Model.EstadoMatricula.REJEITADA);
        candidaturaSelecionada.setJustificacao(justificacao);
        empresa.atualizarMatricula(candidaturaSelecionada);
        empresa.enviarNotificacao(candidaturaSelecionada, "REJEITADA");
    }

    // Mostra dados da candidatura
    public String getCandidaturaAsString() {
        return candidaturaSelecionada != null ? candidaturaSelecionada.toString() : "Nenhuma selecionada";
    }
}