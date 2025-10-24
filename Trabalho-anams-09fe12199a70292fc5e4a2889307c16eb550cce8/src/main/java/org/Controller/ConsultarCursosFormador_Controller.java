package org.Controller;

import org.Model.Formador;
import org.Model.Curso;
import org.Model.Empresa;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultarCursosFormador_Controller {
    private Empresa empresa;

    public ConsultarCursosFormador_Controller(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Curso> obterCursosDoFormador(Formador formador) {
        return empresa.getCursos().stream()
                .filter(curso -> curso.getInscricoes().stream()
                        .anyMatch(inscricao -> inscricao.getFormador().equals(formador)))
                .collect(Collectors.toList());
    }
}