package org.Controller;

import org.Model.Curso;
import org.Model.Empresa;
import org.Model.Formador;
import org.Model.RegistoCursos;

import java.util.ArrayList;
import java.util.List;

public class ConsultarCursosFormador_Controller {

    private final Empresa empresa;
    private final RegistoCursos registoCursos;

    public ConsultarCursosFormador_Controller(Empresa empresa) {
        this.empresa = empresa;
        // Obtém o acesso ao Registo através da Empresa (Facade)
        this.registoCursos = empresa.getRegistoCursos();
    }

    // Método conforme o diagrama: getCursosLecciona
    public List<Curso> getCursosLecciona(String nomeFormador) {
        // 1. Obter o objeto Formador (Simulação de Sessão/Login)
        Formador formador = empresa.getFormadorPorNome(nomeFormador);

        if (formador == null) {
            return new ArrayList<>();
        }

        // 2. Delegar a pesquisa ao RegistoCursos (High Cohesion)
        // O Registo vai usar o método c.temFormador(f) conforme o diagrama
        return registoCursos.getCursosDoFormador(formador);
    }

    // Método auxiliar para a UI listar formadores (Simulação de Login)
    public List<Formador> getListaFormadores() {
        return empresa.obterListaFormadores();
    }
}