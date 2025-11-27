package org.Model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String email;
    private String cc;
    private List<Inscricao> inscricoes;

    // REMOVIDO: private List<Turma> turmas;

    public Aluno() {
        this.inscricoes = new ArrayList<>();
        // REMOVIDO: this.turmas = new ArrayList<>();
    }

    public void adicionarInscricao(Inscricao inscricao) {
        this.inscricoes.add(inscricao);
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    // Método auxiliar para os controllers (UC10)
    public List<Inscricao> getInscricoesAtivas() {
        List<Inscricao> ativas = new ArrayList<>();
        for(Inscricao i : inscricoes) {
            if ("0-ativa".equalsIgnoreCase(i.getEstado())) {
                ativas.add(i);
            }
        }
        return ativas;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCc() { return cc; }
    public void setCc(String cc) { this.cc = cc; }

    @Override
    public String toString() {
        return nome + " (" + (email != null ? email : "sem email") + ")";
    }
}