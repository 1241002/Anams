package org.Model;

public class Inscricao {
    private Aluno aluno;
    private Formador formador;
    private Curso curso;
    private boolean aprovada;

    public Inscricao(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.formador = formador;
        this.curso = curso;
        this.aprovada = false;
    }

    public Aluno getAluno() {
        return aluno;
    }
    public Formador getFormador() {
        return formador;
    }

    public Curso getCurso() {
        return curso;
    }

    public boolean isAprovada() {
        return aprovada;
    }

    public void setAprovada(boolean aprovada) {
        this.aprovada = aprovada;
    }
}