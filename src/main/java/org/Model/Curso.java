package org.Model;

import org.Utils.Data;
import java.util.ArrayList;
import java.util.List;

public class Curso implements Avaliavel {
    private String titulo;
    private String sigla;
    private String descricao;
    private String estado;
    private TipoCurso tipo;
    private Data dataInicio;
    private Data dataFim;
    private int vagas = 20;

    private List<Modulo> modulos;
    private List<Inscricao> inscricoes;
    private Formador responsavel;

    public Curso() {
        this.estado = "A iniciar";
        this.modulos = new ArrayList<>();
        this.inscricoes = new ArrayList<>();
    }

    // === MÉTODOS UC9 / UC10 ===
    public boolean validarVagas() { return vagas > 0; }
    public void decrementarVaga() { if(vagas > 0) vagas--; }
    public void incrementarVaga() { vagas++; }

    public boolean verificaPrazoAnulacao() {
        return !this.estado.equalsIgnoreCase("Concluído");
    }

    // === MÉTODOS UC11 ===
    public boolean temFormador(Formador f) {
        if (this.responsavel != null && this.responsavel.equals(f)) return true;
        for (Modulo m : modulos) {
            if (m.getFormador() != null && m.getFormador().equals(f)) return true;
        }
        return false;
    }

    public boolean temFormador(String nome) {
        for (Modulo m : modulos) {
            if (m.getFormador() != null && m.getFormador().getNome().equalsIgnoreCase(nome)) return true;
        }
        return false;
    }

    // === GETTERS E SETTERS ===
    public void adicionarInscricao(Inscricao i) { inscricoes.add(i); }
    public List<Inscricao> getInscricoes() { return inscricoes; }

    public boolean adicionarModulo(Modulo m) {
        if (m.valida()) { return modulos.add(m); }
        return false;
    }
    public List<Modulo> getModulos() { return new ArrayList<>(modulos); }

    public void setTitulo(String t) { this.titulo = t; }
    public String getTitulo() { return titulo; }

    public void setSigla(String s) { this.sigla = s; }
    public String getSigla() { return sigla; }

    public void setDescricao(String d) { this.descricao = d; }
    public void setTipo(TipoCurso t) { this.tipo = t; }
    public void setDataInicio(Data d) { this.dataInicio = d; }
    public void setDataFim(Data d) { this.dataFim = d; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setResponsavel(Formador f) { this.responsavel = f; }

    @Override
    public double calcularNota(List<Classificacao> classificacoesAluno) { return 0.0; }

    @Override
    public String toString() {
        return String.format("%s (%s) [%s]", titulo, sigla, estado);
    }
}