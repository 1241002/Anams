package org.Model;

import org.Utils.Data;

public class Sessao {
    private Data data;
    private int duracaoHoras;
    private String sala;

    // Construtor
    public Sessao(Data data, int duracaoHoras, String sala) {
        this.data = data;
        this.duracaoHoras = duracaoHoras;
        this.sala = sala;
    }

    public void setDados(Data data, int duracao, String sala) {
        this.data = data;
        this.duracaoHoras = duracao;
        this.sala = sala;
    }

    // Getters
    public Data getData() { return data; }
    public int getDuracaoHoras() { return duracaoHoras; }
    public String getSala() { return sala; }

    @Override
    public String toString() {
        return "Data: " + data + " | Duração: " + duracaoHoras + "h | Sala: " + sala;
    }
}