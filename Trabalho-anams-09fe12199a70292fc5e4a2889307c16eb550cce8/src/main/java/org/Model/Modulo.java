package org.Model;


import org.Utils.Data;

public class Modulo {
    private String codigo;           // gerado automaticamente
    private String titulo;
    private int cargaHoraria;
    private Data dataInicio;         // tua classe Data
    private Data dataFim;            // tua classe Data
    private String sala;
    private Formador formador;

    public Modulo() {
        this.codigo = "";
    }

    // === Getters & Setters ===
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    public Data getDataInicio() { return dataInicio; }
    public void setDataInicio(Data dataInicio) { this.dataInicio = dataInicio; }

    public Data getDataFim() { return dataFim; }
    public void setDataFim(Data dataFim) { this.dataFim = dataFim; }

    public String getSala() { return sala; }
    public void setSala(String sala) { this.sala = sala; }

    public Formador getFormador() { return formador; }
    public void setFormador(Formador formador) { this.formador = formador; }

    public boolean valida() {
        return titulo != null && !titulo.trim().isEmpty()
                && cargaHoraria > 0
                && dataInicio != null && dataFim != null
                && dataInicio.compareTo(dataFim) <= 0  // usa o teu compareTo
                && sala != null && !sala.trim().isEmpty()
                && formador != null;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %d h | %s a %s | Sala: %s | Formador: %s",
                codigo, titulo, cargaHoraria, dataInicio, dataFim, sala,
                formador != null ? formador.getNome() : "n/a");
    }
}