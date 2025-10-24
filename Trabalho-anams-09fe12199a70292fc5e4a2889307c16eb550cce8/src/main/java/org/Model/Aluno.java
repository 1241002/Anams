package org.Model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private List<Inscricao> inscricoes;
    private List<Turma> turmas;

    public Aluno() {
        this.inscricoes = new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    public void adicionarInscricao(Inscricao inscricao) {
        this.inscricoes.add(inscricao);
    }
    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void removerInscricao(Inscricao inscricao) {
        this.inscricoes.remove(inscricao);
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
