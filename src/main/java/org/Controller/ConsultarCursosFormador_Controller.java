package org.Controller;

import org.Model.Formador;
import org.Model.Curso;
import org.Model.Empresa;
import org.Model.Inscricao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultarCursosFormador_Controller {
    private Empresa empresa;

    // Construtor: recebe a empresa para aceder aos cursos
    public ConsultarCursosFormador_Controller(Empresa empresa) {
        this.empresa = empresa;
    }

    // Devolve os cursos onde o formador está associado
    public List<Curso> obterCursosDoFormador(Formador formador) {
        List<Curso> cursosDoFormador = new ArrayList<>();

        for (Curso curso : empresa.getCursos()) {
            for (Inscricao inscricao : curso.getInscricoes()) {
                if (inscricao.getFormador().equals(formador)) {
                    if (!cursosDoFormador.contains(curso)) {
                        cursosDoFormador.add(curso);
                    }
                    break; // já encontrou, não precisa ver mais inscrições
                }
            }
        }
        return cursosDoFormador;
    }
}