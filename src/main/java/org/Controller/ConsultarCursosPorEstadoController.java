package org.Controller;

import org.Model.Empresa;
import org.Model.Curso;
import java.util.List;

public class ConsultarCursosPorEstadoController {

    private final Empresa empresa;

    // Construtor: recebe a empresa para aceder aos cursos
    public ConsultarCursosPorEstadoController(Empresa empresa) {
        this.empresa = empresa;
    }

    // Devolve os 5 nomes dos estados de curso (ex: "Planeado", "Aberto", ...)
    public String[] obterOpcoesEstado() {
        return org.Model.EstadoCurso.NOMES;
    }

    // Devolve a lista de cursos reais num estado específico (índice 0 a 4)
    public List<Curso> obterCursosPorEstado(int idx) {
        return empresa.filtrarCursosPorEstado(idx);
    }
}