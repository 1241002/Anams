package org.Controller;

import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Empresa;
import org.Model.Inscricao;
import org.Model.RegistoAlunos;
import org.Model.RegistoCursos;

import java.util.ArrayList;
import java.util.List;

public class AnularInscricao_Controller {
    private final RegistoAlunos registoAlunos;
    private final RegistoCursos registoCursos;

    public AnularInscricao_Controller(Empresa empresa) {
        this.registoAlunos = empresa.getRegistoAlunos();
        this.registoCursos = empresa.getRegistoCursos();
    }

    // Passo 1 da UI: Obter inscrições para listar
    public List<Inscricao> listInscricoesDoAluno(String nomeAluno) {
        Aluno aluno = registoAlunos.getAluno(nomeAluno);
        if (aluno == null) return new ArrayList<>();

        List<Inscricao> ativas = new ArrayList<>();
        for (Inscricao i : aluno.getInscricoes()) {
            // Filtra apenas as ativas para mostrar na UI
            if ("0-ativa".equalsIgnoreCase(i.getEstado())) {
                ativas.add(i);
            }
        }
        return ativas;
    }

    // Passo 2 da UI: Executar anulação
    public boolean anularInscricao(String idCurso, String nomeAluno) {
        // Recuperar objetos do domínio
        Aluno aluno = registoAlunos.getAluno(nomeAluno);
        Curso curso = registoCursos.getCurso(idCurso);

        if (aluno == null || curso == null) {
            return false;
        }

        // Delegar a regra de negócio no RegistoCursos
        return registoCursos.anularInscricao(curso, aluno);
    }
}