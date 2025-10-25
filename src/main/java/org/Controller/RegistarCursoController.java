package org.Controller;

import org.Model.*;
import org.Utils.Utils;
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

    // === CORRIGIDO: Usa Data em vez de LocalDate ===
    public void setDadosCurso(String titulo, String sigla, TipoCurso tipo,
                              String descricao, Data dataInicio, Data dataFim) {
        curso.setTitulo(titulo);
        curso.setSigla(sigla);
        curso.setTipo(tipo);
        curso.setDescricao(descricao);
        curso.setDataInicio(dataInicio);
        curso.setDataFim(dataFim);
    }

    public void iniciarNovoModulo() {
        this.moduloAtual = new Modulo();
    }

    // === CORRIGIDO: Usa Data em vez de LocalDate ===
    public void setDadosModulo(String titulo, int cargaHoraria,
                               Data dataInicio, Data dataFim,
                               String sala, Formador formador) {
        moduloAtual.setTitulo(titulo);
        moduloAtual.setCargaHoraria(cargaHoraria);
        moduloAtual.setDataInicio(dataInicio);
        moduloAtual.setDataFim(dataFim);
        moduloAtual.setSala(sala);
        moduloAtual.setFormador(formador);
    }

    public boolean adicionarModuloAtual() {
        // Gera código automático: MOD- + timestamp
        String codigo = "MOD-" + System.currentTimeMillis();
        moduloAtual.setCodigo(codigo);

        return curso.adicionarModulo(moduloAtual);
    }

    public boolean registaCurso() {
        // por completar: validação global do curso
        if (curso.getModulos().isEmpty()) {
            return false; // curso precisa de pelo menos 1 módulo
        }
        empresa.addCurso(curso);
        return true;
    }

    public String getCursoAsString() {
        return curso.toString();
    }

    public List<TipoCurso> getTiposCurso() {
        return empresa.obterListaTiposCurso();
    }

    public List<Formador> getFormadores() {
        return empresa.obterListaFormadores();
    }

    public Curso getCursoEmConstrucao() {
        return curso;
    }
}