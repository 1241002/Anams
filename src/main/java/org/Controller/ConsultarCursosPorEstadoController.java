package org.Controller;

import org.Model.Curso;
import org.Model.Empresa;
import org.Model.RegistoCursos;

import java.util.List;

public class ConsultarCursosPorEstadoController {

    private final RegistoCursos registoCursos;

    public ConsultarCursosPorEstadoController(Empresa empresa) {
        // 1.1.1: reg = getRegistoCursos()
        this.registoCursos = empresa.getRegistoCursos();
    }

    // 1.1: obterEstados() -> Devolve a lista de Strings (ex: "A iniciar", "Ativo")
    public List<String> obterEstados() {
        // 1.1.2: listaEstados = obterEstados() (no diagrama)
        // No RegistoCursos chamámos getEstadosPossiveis()
        return registoCursos.getEstadosPossiveis();
    }

    // 2.1: obterCursosPorEstado(estado)
    public List<Curso> obterCursosPorEstado(String estado) {
        // 2.1.1: listaC = obterCursosPorEstado(estado)
        return registoCursos.getCursosPorEstado(estado);
    }
}