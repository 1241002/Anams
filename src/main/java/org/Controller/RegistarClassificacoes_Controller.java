package org.Controller;

import org.Model.*;
import java.util.ArrayList;
import java.util.List;

public class RegistarClassificacoes_Controller {

    private final Empresa empresa;
    private final RegistoCursos registoCursos;

    public RegistarClassificacoes_Controller(Empresa empresa) {
        this.empresa = empresa;
        this.registoCursos = empresa.getRegistoCursos();
    }

    // Passo 1: Obter lista de cursos do formador (Reutiliza lógica do UC11)
    public List<Curso> getCursosFormador(String nomeFormador) {
        Formador f = empresa.getFormadorPorNome(nomeFormador);
        if (f == null) return new ArrayList<>();

        return registoCursos.getCursosDoFormador(f);
    }

    // Passo 2: Obter apenas os módulos deste curso que este formador leciona
    public List<Modulo> getModulosPeloFormador(String siglaCurso, String nomeFormador) {
        Curso curso = registoCursos.getCurso(siglaCurso);
        Formador formador = empresa.getFormadorPorNome(nomeFormador);

        if (curso == null || formador == null) return new ArrayList<>();

        // Delega no RegistoCursos a lógica de filtrar módulos (High Cohesion)
        return registoCursos.getModulosPorFormador(curso, formador);
    }

    // Passo 3: Obter alunos inscritos no curso (para serem avaliados no módulo)
    public List<Inscricao> getAlunosParaAvaliar(String siglaCurso) {
        Curso curso = registoCursos.getCurso(siglaCurso);
        if (curso == null) return new ArrayList<>();

        // Filtra apenas inscrições ativas
        List<Inscricao> ativos = new ArrayList<>();
        for (Inscricao i : curso.getInscricoes()) {
            if ("0-ativa".equalsIgnoreCase(i.getEstado())) {
                ativos.add(i);
            }
        }
        return ativos;
    }

    // Passo 4: Registar a nota
    public boolean registarNota(Modulo modulo, Inscricao inscricao, double nota) {
        // Delega a regra de negócio (validar 0-20 e criar objeto) no Registo
        return registoCursos.registarNota(modulo, inscricao, nota);
    }

    // Auxiliar para a UI (Login)
    public List<Formador> getListaFormadores() {
        return empresa.obterListaFormadores();
    }
}