package org.Controller;

import org.Model.Curso;
import org.Model.Empresa;
import org.Model.Formador;
import org.Model.Inscricao;
import org.Model.RegistoCursos;

import java.util.ArrayList;
import java.util.List;

public class ConsultarAlunosCurso_Controller {

    private final Empresa empresa;
    private final RegistoCursos registoCursos;

    public ConsultarAlunosCurso_Controller(Empresa empresa) {
        this.empresa = empresa;
        // Acesso ao RegistoCursos através da Empresa (Facade)
        this.registoCursos = empresa.getRegistoCursos();
    }

    // Passo 1: Obter lista de cursos atribuídos ao formador (Reutiliza lógica do UC11)
    public List<Curso> getCursosDoFormador(String nomeFormador) {
        // 1. Obter objeto formador
        Formador formador = empresa.getFormadorPorNome(nomeFormador);
        if (formador == null) return new ArrayList<>();

        // 2. Delegar pesquisa ao RegistoCursos
        return registoCursos.getCursosDoFormador(formador);
    }

    // Passo 2: Obter dados dos alunos inscritos no curso selecionado
    public List<String> getAlunosDoCurso(String siglaCurso) {
        List<String> infoAlunos = new ArrayList<>();

        // 1. Obter o Curso pelo ID
        Curso curso = registoCursos.getCurso(siglaCurso);

        if (curso != null) {
            // 2. Iterar sobre as inscrições do curso (Information Expert)
            for (Inscricao insc : curso.getInscricoes()) {
                // Formatar string: "Nome do Aluno [Estado da Inscrição]"
                String info = String.format("%s [%s]",
                        insc.getAluno().getNome(),
                        insc.getEstado());

                infoAlunos.add(info);
            }
        }
        return infoAlunos;
    }

    // Auxiliar para UI (Simulação de Login)
    public List<Formador> getListaFormadores() {
        return empresa.obterListaFormadores();
    }
}