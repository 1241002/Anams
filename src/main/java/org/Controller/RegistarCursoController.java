package org.Controller;

import org.Model.*;
import org.Utils.Data;
import java.util.List;

public class RegistarCursoController {

    private final Empresa empresa;
    private Curso curso;
    private Modulo moduloAtual;

    public RegistarCursoController(Empresa empresa) {
        this.empresa = empresa;
    }

    public void novoCurso() {
        this.curso = new Curso();
    }

    public void setDadosCurso(String titulo, String sigla, TipoCurso tipo,
                              String descricao, Data dataInicio, Data dataFim) {
        curso.setTitulo(titulo);
        curso.setSigla(sigla);
        curso.setTipo(tipo);
        curso.setDescricao(descricao);
        curso.setDataInicio(dataInicio);      // Data
        curso.setDataConclusao(dataFim);      // Data
    }

    public void iniciarNovoModulo() {
        this.moduloAtual = new Modulo();
    }

    public void setDadosModulo(String titulo, int cargaHoraria,
                               Data dataInicio, Data dataConclusao,
                               String horario, Formador formador) {
        moduloAtual.setTitulo(titulo);
        moduloAtual.setCargaHoraria(cargaHoraria);
        moduloAtual.setDataInicio(dataInicio);      // Data
        moduloAtual.setDataConclusao(dataConclusao); // Data
        moduloAtual.setHorario(horario);
        moduloAtual.setFormadorResponsavel(formador);
    }

    public boolean adicionarModuloAtual() {
        String codigo = "MOD-" + System.currentTimeMillis();
        moduloAtual.setCodigo(codigo);
        return curso.adicionarModulo(moduloAtual);
    }

    public boolean registaCurso() {
        if (curso.getModulos().isEmpty()) return false;   // precisa de pelo menos 1 módulo
        empresa.adicionarCurso(curso);
        return true;
    }

    public String getCursoAsString() { return curso.toString(); }

    public List<TipoCurso> getTiposCurso() { return empresa.obterListaTiposCurso(); }
    public List<Formador> getFormadores() { return empresa.obterListaFormadores(); }
    public Curso getCursoEmConstrucao() { return curso; }
}