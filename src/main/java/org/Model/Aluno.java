package org.Model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String email;           // ADICIONADO: para identificação única
    private String cc;              // OPCIONAL: mas útil
    private List<Inscricao> inscricoes;
    private List<Turma> turmas;

    // Construtor
    public Aluno() {
        this.inscricoes = new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    // Adiciona inscrição ao aluno
    public void adicionarInscricao(Inscricao inscricao) {
        this.inscricoes.add(inscricao);
    }

    // Adiciona turma ao aluno
    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
    }

    // Devolve turmas do aluno
    public List<Turma> getTurmas() {
        return turmas;
    }

    // Remove inscrição (usado na anulação)
    public void removerInscricao(Inscricao inscricao) {
        this.inscricoes.remove(inscricao);
    }

    // Devolve inscrições do aluno
    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // NOVO: email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // OPCIONAL: CC
    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    // toString para debug/UI
    @Override
    public String toString() {
        return nome + " (" + (email != null ? email : "sem email") + ")";
    }
}