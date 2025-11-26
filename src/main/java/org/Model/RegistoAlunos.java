package org.Model;

import java.util.ArrayList;
import java.util.List;

public class RegistoAlunos {
    private List<Aluno> alunos;

    public RegistoAlunos() {
        this.alunos = new ArrayList<>();
    }

    public Aluno novoAluno() {
        return new Aluno();
    }

    public void addAluno(Aluno aluno) {
        // Aqui podias validar se já existe
        this.alunos.add(aluno);
    }

    // Método usado pelo Controller para encontrar o aluno
    public Aluno getAluno(String nome) {
        for (Aluno a : alunos) {
            if (a.getNome() != null && a.getNome().equalsIgnoreCase(nome)) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }
}