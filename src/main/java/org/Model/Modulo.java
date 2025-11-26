package org.Model;
import org.Utils.Data;
import java.util.ArrayList;
import java.util.List;

public class Modulo implements Avaliavel {
    private String titulo;
    private int cargaHoraria;
    private double ponderacao; // IT2
    private Formador formador;
    private List<Sessao> sessoes; // IT2

    public Modulo() {
        this.sessoes = new ArrayList<>();
    }

    // Setters básicos (mantém os teus ou usa estes)
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getTitulo() { return titulo; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public void setFormador(Formador formador) { this.formador = formador; }
    public Formador getFormador() { return formador; }

    // === IT2 NOVOS ===
    public void setPonderacao(double ponderacao) { this.ponderacao = ponderacao; }
    public double getPonderacao() { return ponderacao; }

    public void addSessao(Sessao s) { this.sessoes.add(s); }
    public List<Sessao> getSessoes() { return new ArrayList<>(sessoes); }

    public boolean valida() {
        // Requisito: Mínimo 3 sessões
        if (sessoes.size() < 3) return false;
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
        return String.format(" - %s (%.1f%%) [%d sessões]", titulo, ponderacao, sessoes.size());
    }
}