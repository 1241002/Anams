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

    public void addAluno(Aluno a) {
        this.alunos.add(a);
    }

    // Este é o ÚNICO método getAluno que deves ter
    public Aluno getAluno(String nome) {
        for (Aluno a : alunos) {
            if (a.getNome() != null && a.getNome().equalsIgnoreCase(nome)) {
                return a;
            }
        }
        // Retorna null se não encontrar (Correção para o UC10 funcionar bem)
        return null;
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }
}