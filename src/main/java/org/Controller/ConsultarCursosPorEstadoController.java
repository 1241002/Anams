package org.Controller;

import org.Model.Empresa;
import org.Model.Curso;
import java.util.List;

public class ConsultarCursosPorEstadoController {

    private final Empresa empresa;

    public ConsultarCursosPorEstadoController(Empresa empresa) {
        this.empresa = empresa;
    }

    /* devolve os 5 estados possíveis */
    public String[] obterOpcoesEstado() {
        return org.Model.EstadoCurso.NOMES;
    }

    /* cursos REAIS de um estado (0-4) */
    public List<Curso> obterCursosPorEstado(int idx) {
        return empresa.filtrarCursosPorEstado(idx);
    }
}