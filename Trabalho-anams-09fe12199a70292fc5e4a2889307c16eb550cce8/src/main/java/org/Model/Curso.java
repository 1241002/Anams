package org.Model;

import org.Utils.Data;  // tua classe Data
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String titulo;
    private String sigla;
    private String descricao;
    private List<Inscricao> inscricoes;
    private List<Turma> turmas;

    public Curso() {
        this.inscricoes = new ArrayList<>();
        this.modulos = new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    public void adicionarInscricao(Inscricao inscricao) {
        this.inscricoes.add(inscricao);
    }
    public void removerInscricao(Inscricao inscricao) {
    }

    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSigla() {
        return sigla;
    }

    // === toString melhorado (funciona com Data.toString()) ===
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Curso: ").append(titulo).append(" (").append(sigla).append(")\n");
        sb.append("Tipo: ").append(tipo != null ? tipo.getDesignacao() : "n/a").append("\n");
        sb.append("Descrição: ").append(descricao).append("\n");
        sb.append("Período: ").append(dataInicio).append(" a ").append(dataFim).append("\n");
        sb.append("Módulos (").append(modulos.size()).append("):\n");
        for (Modulo m : modulos) {
            sb.append("  - ").append(m).append("\n");
        }
        sb.append("Inscrições: ").append(inscricoes.size()).append(" aluno(s)\n");
        return sb.toString();
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
