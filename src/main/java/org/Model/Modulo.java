package org.Model;

import org.Utils.Data;
import java.util.ArrayList;
import java.util.List;

public class Modulo implements Avaliavel {
    private String titulo;
    private String codigo;          // Faltava este
    private int cargaHoraria;
    private double ponderacao;
    private Formador formador;

    // Novos campos para corrigir o erro do Controller
    private Data dataInicio;        // Faltava este
    private Data dataConclusao;     // Faltava este
    private String horario;         // Faltava este

    private List<Sessao> sessoes;

    public Modulo() {
        this.sessoes = new ArrayList<>();
    }

    // === SETTERS QUE FALTAVAM (Corrigem o erro) ===

    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }
    public Data getDataInicio() { return dataInicio; }

    public void setDataConclusao(Data dataConclusao) {
        this.dataConclusao = dataConclusao;
    }
    public Data getDataConclusao() { return dataConclusao; }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    public String getHorario() { return horario; }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getCodigo() { return codigo; }

    // O Controller chama "setFormadorResponsavel", mas o atributo é "formador"
    // Criamos este método para compatibilidade
    public void setFormadorResponsavel(Formador formador) {
        this.formador = formador;
    }
    public Formador getFormadorResponsavel() { return formador; }


    // === OUTROS MÉTODOS JÁ EXISTENTES ===

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getTitulo() { return titulo; }

    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public int getCargaHoraria() { return cargaHoraria; }

    public void setFormador(Formador formador) { this.formador = formador; }
    public Formador getFormador() { return formador; }

    public void setPonderacao(double ponderacao) { this.ponderacao = ponderacao; }
    public double getPonderacao() { return ponderacao; }

    public void addSessao(Sessao s) { this.sessoes.add(s); }
    public List<Sessao> getSessoes() { return new ArrayList<>(sessoes); }

    public boolean valida() {
        // Validação mínima: tem título e (opcionalmente) sessões
        // Podes ajustar a regra das 3 sessões aqui se necessário
        return titulo != null && !titulo.isEmpty();
    }

    @Override
    public double calcularNota(List<Classificacao> classificacoes) {
        if(classificacoes == null) return 0;
        for(Classificacao c : classificacoes) {
            if(c.getModulo().equals(this)) return c.getNota();
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Módulo: %s [%s] - %d h", titulo, codigo != null ? codigo : "N/A", cargaHoraria);
    }
}