package org.Model;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private List<Aluno> alunos;

    public Turma() {
        this.alunos = new ArrayList<>();
    }

    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
}