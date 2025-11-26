package org.Controller;

import org.Model.Empresa;
import org.Model.RegistoCursos;
import org.Model.Curso;
import java.util.List;

public class AlterarEstadoCursoController {
    private final RegistoCursos registoCursos;

    public AlterarEstadoCursoController(Empresa empresa) {
        this.registoCursos = empresa.getRegistoCursos();
    }

    public List<Curso> getListaCursos() {
        return registoCursos.getCursos();
    }

    public List<String> getEstadosPossiveis() {
        return registoCursos.getEstadosPossiveis();
    }

    public boolean alterarEstadoCurso(String siglaCurso, String novoEstado) {
        Curso c = registoCursos.getCurso(siglaCurso);
        if (c != null) {
            // Aqui podias ter validarTransicao() no Registo
            c.setEstado(novoEstado);
            return true;
        }
        return false;
    }
}