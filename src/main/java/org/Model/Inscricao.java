package org.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inscricao {
    private Aluno aluno;
    private Curso curso;
    private String estado; // "0-ativa", "1-cancelada"
    private LocalDate dataRealizacao;
    private List<Classificacao> classificacoes;

    public Inscricao(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;
        this.estado = "0-ativa";
        this.dataRealizacao = LocalDate.now();
        this.classificacoes = new ArrayList<>();
    }

    public void setEstado(String estado) { this.estado = estado; }
    public String getEstado() { return estado; }

    public Aluno getAluno() { return aluno; }
    public Curso getCurso() { return curso; }

    public void addClassificacao(Classificacao c) {
        if (this.classificacoes == null) {
            this.classificacoes = new ArrayList<>();
        }
        this.classificacoes.add(c);
    }
    public List<Classificacao> getClassificacoes() {
        return classificacoes;
    }

    @Override
    public String toString() {
        return "Inscricao de " + aluno.getNome() + " em " + curso.getSigla();
    }
}