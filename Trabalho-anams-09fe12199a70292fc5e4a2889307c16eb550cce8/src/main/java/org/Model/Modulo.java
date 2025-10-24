package org.Model;

public class Modulo {
    private String codigo;
    private String titulo;
    private int cargaHoraria;
    private String dataInicio;
    private String dataConclusao;
    private String horario;
    private Formador formadorResponsavel;

    public Modulo() {}

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }
    public String getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(String dataConclusao) { this.dataConclusao = dataConclusao; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public Formador getFormadorResponsavel() { return formadorResponsavel; }
    public void setFormadorResponsavel(Formador formador) { this.formadorResponsavel = formador; }

    @Override
    public String toString() {
        return "Módulo: " + codigo + " | " + titulo + " | CH: " + cargaHoraria +
                " | Início: " + dataInicio + " | Fim: " + dataConclusao +
                " | Horário: " + horario + " | Formador: " + formadorResponsavel.getNome();
    }
}