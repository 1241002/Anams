package org.Controller;

import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Empresa;
import org.Model.RegistoCursos;
import org.Model.RegistoAlunos; // Se precisares de validar aluno

import java.util.List;

public class InscreverAluno_Controller {
    private final Empresa empresa;
    private final RegistoCursos registoCursos;

    public InscreverAluno_Controller(Empresa empresa) {
        this.empresa = empresa;
        // Obtém o acesso ao Registo (conforme o Diagrama de Sequência)
        this.registoCursos = empresa.getRegistoCursos();
    }

    public List<Curso> getCursosAIniciar() {
        // Pede ao Registo a lista filtrada
        return registoCursos.getCursosPorEstado("A iniciar");
    }

    // Mantive o nome do método que tinhas, mas a lógica interna mudou
    public boolean registerInscription(String idCurso, Aluno aluno) {
        // 1. Pedir o curso ao Registo (não à Empresa)
        Curso curso = registoCursos.getCurso(idCurso);

        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return false;
        }

        // 2. Validar se há vagas (usando o método do Curso)
        // Nota: Tens de ter o método validarVagas() ou verificar capacidade no Curso
        // if (!curso.temVagas()) return false;

        // 3. O Registo trata da inscrição (High Cohesion)
        registoCursos.registarInscricao(curso, aluno);

        return true;
    }

    // Método auxiliar para manter compatibilidade com a tua UI atual
    public List<Curso> listAvailableCourses() {
        return getCursosAIniciar();
    }
}