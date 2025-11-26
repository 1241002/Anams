package org.Controller;

import org.Model.Formador;
import org.Model.Curso;
import org.Model.Empresa;
import org.Model.RegistoCursos;

import java.util.ArrayList;
import java.util.List;

public class ConsultarCursosFormador_Controller {
    private final RegistoCursos registoCursos;

    public ConsultarCursosFormador_Controller(Empresa empresa) {
        // 1. Obtém o acesso ao Registo através da Empresa
        this.registoCursos = empresa.getRegistoCursos();
    }

    public List<Curso> obterCursosDoFormador(Formador formador) {
        List<Curso> cursosDoFormador = new ArrayList<>();

        // 2. Pede a lista completa ao Registo
        List<Curso> todosCursos = registoCursos.getCursos();

        // 3. Filtra usando a lógica correta (Information Expert no Curso)
        for (Curso c : todosCursos) {
            // O método temFormador foi adicionado à classe Curso na atualização anterior
            if (c.temFormador(formador.getNome())) {
                cursosDoFormador.add(c);
            }
        }

        return cursosDoFormador;
    }

    // Se precisares de obter por ID (caso a UI envie apenas o ID/Nome)
    public List<Curso> getCursosLeciona(String nomeFormador) {
        List<Curso> resultado = new ArrayList<>();
        for (Curso c : registoCursos.getCursos()) {
            if (c.temFormador(nomeFormador)) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}