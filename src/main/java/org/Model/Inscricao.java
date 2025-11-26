package org.Model;

import java.time.LocalDate;

public class Inscricao {
    private Aluno aluno;
    private Curso curso;
    private String estado; // Requisito IT2: "0-ativa", "1-cancelada", "2-concluída"
    private LocalDate dataRealizacao;

    public Inscricao(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;
        // Requisito IT2: Por omissão, estado é "0-ativa"
        this.estado = "0-ativa";
        this.dataRealizacao = LocalDate.now();
    }

    // === Getters e Setters ===

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    @Override
    public String toString() {
        return String.format("Inscrição: %s no curso %s [%s]",
                aluno.getNome(), curso.getTitulo(), estado);
    }
}