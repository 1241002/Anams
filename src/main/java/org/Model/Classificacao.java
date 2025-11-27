package org.Model;

public class Classificacao {
    private Inscricao inscricao;
    private Modulo modulo;
    private double nota; // 0 a 20

    // Construtor
    public Classificacao(Inscricao inscricao, Modulo modulo, double nota) {
        this.inscricao = inscricao;
        this.modulo = modulo;
        this.nota = nota;
    }

    // Getters
    public double getNota() { return nota; }
    public Modulo getModulo() { return modulo; }
    public Inscricao getInscricao() { return inscricao; }

    // Setters (se necessário)
    public void setNota(double nota) { this.nota = nota; }
}