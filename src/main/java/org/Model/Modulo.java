package org.Model;

import org.Utils.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Modulo implements Avaliavel {
    private String titulo;
    private String codigo;
    private int cargaHoraria;
    private double ponderacao; // Ponderação adicionada
    private Formador formador;

    // Dados de agendamento
    private Data dataInicio;
    private Data dataConclusao;
    private String horario;

    private List<Sessao> sessoes;

    public Modulo() {
        this.sessoes = new ArrayList<>();
    }

    public Modulo(String titulo) {
        this();
        this.titulo = titulo;
    }

    // === LÓGICA DE VALIDAÇÃO (CORRIGIDA) ===
    public boolean valida() {
        // Regra de Negócio: Tem de ter título E pelo menos 3 sessões
        if (titulo == null || titulo.trim().isEmpty()) return false;

        // Validação das 3 sessões obrigatórias
        if (sessoes.size() < 3) return false;

        return true;
    }

    // === GETTERS E SETTERS ===
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getTitulo() { return titulo; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getCodigo() { return codigo; }

    public void setCargaHoraria(int ch) { this.cargaHoraria = ch; }
    public int getCargaHoraria() { return cargaHoraria; }

    public void setPonderacao(double p) { this.ponderacao = p; }
    public double getPonderacao() { return ponderacao; }

    public void setFormador(Formador f) { this.formador = f; }
    public void setFormadorResponsavel(Formador f) { this.formador = f; } // Compatibilidade
    public Formador getFormador() { return formador; }
    public Formador getFormadorResponsavel() { return formador; }

    public void setDataInicio(Data d) { this.dataInicio = d; }
    public Data getDataInicio() { return dataInicio; }

    public void setDataConclusao(Data d) { this.dataConclusao = d; }
    public Data getDataConclusao() { return dataConclusao; }

    public void setHorario(String h) { this.horario = h; }
    public String getHorario() { return horario; }

    // === GESTÃO DE SESSÕES ===
    public void addSessao(Sessao s) { this.sessoes.add(s); }
    public List<Sessao> getSessoes() { return new ArrayList<>(sessoes); }

    // === INTERFACE AVALIAVEL ===
    @Override
    public double calcularNota(List<Classificacao> classificacoes) {
        if (classificacoes == null || classificacoes.isEmpty()) return 0.0;
        for (Classificacao c : classificacoes) {
            if (c.getModulo() != null && c.getModulo().equals(this)) {
                return c.getNota();
            }
        }
        return 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modulo modulo = (Modulo) o;
        return Objects.equals(titulo, modulo.titulo) && Objects.equals(codigo, modulo.codigo);
    }

    @Override
    public String toString() {
        return String.format("Módulo: %s [%s] - %d h (Peso: %.0f%%)",
                titulo, (codigo != null ? codigo : "N/A"), cargaHoraria, ponderacao);
    }
}