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

    public Aluno getAluno(String nome) {
        for (Aluno a : alunos) {
            if (a.getNome() != null && a.getNome().equalsIgnoreCase(nome)) {
                return a;
            }
        }
        // Para efeitos de teste, se não encontrar, cria um (Simulação)
        // Num sistema real, retornaria null.
        Aluno novo = new Aluno();
        novo.setNome(nome);
        novo.setEmail(nome + "@email.com");
        this.alunos.add(novo);
        return novo;
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }
}