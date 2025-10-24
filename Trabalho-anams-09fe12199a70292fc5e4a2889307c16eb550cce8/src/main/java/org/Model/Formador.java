package org.Model;

import java.util.ArrayList;
import java.util.List;

public class Formador {
    private String nome;
    private List<Inscricao> inscricoes;

    public Formador() {
        this.inscricoes = new ArrayList<>();
    }

    public void adicionarInscricao(Inscricao inscricao) {
        this.inscricoes.add(inscricao);
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}