package org.Model;

import org.Utils.Data;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String titulo;
    private String sigla;
    private String descricao;

    // === UC4: CAMPOS OBRIGATÓRIOS ===
    private TipoCurso tipo;
    private Data dataInicio;
    private Data dataFim;
    private final List<Modulo> modulos;  // <-- FALTAVA!

    // === Outros ===
    private List<Inscricao> inscricoes;
    private List<Turma> turmas;

    public Curso() {
        this.inscricoes = new ArrayList<>();
        this.modulos = new ArrayList<>();     // <-- AGORA EXISTE
        this.turmas = new ArrayList<>();
    }

    // === Getters & Setters (UC4) ===
    public TipoCurso getTipo() { return tipo; }
    public void setTipo(TipoCurso tipo) { this.tipo = tipo; }

    public Data getDataInicio() { return dataInicio; }
    public void setDataInicio(Data dataInicio) { this.dataInicio = dataInicio; }

    public Data getDataFim() { return dataFim; }
    public void setDataFim(Data dataFim) { this.dataFim = dataFim; }

    public List<Modulo> getModulos() { return new ArrayList<>(modulos); }

    public boolean adicionarModulo(Modulo modulo) {
        if (modulo != null && modulo.valida()) {
            return modulos.add(modulo);
        }
        return false;
    }

    // === Outros ===
    public void adicionarInscricao(Inscricao inscricao) {
        this.inscricoes.add(inscricao);
    }

    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
    }

    public List<Turma> getTurmas() { return turmas; }
    public List<Inscricao> getInscricoes() { return inscricoes; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public void removerInscricao(Inscricao inscricao) {
        this.inscricoes.remove(inscricao);
    }

    // === toString CORRIGIDO ===
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
}