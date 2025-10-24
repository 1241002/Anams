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
    private String email;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() { return nome + " (" + email + ")"; }
}