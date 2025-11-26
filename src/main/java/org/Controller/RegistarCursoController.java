package org.Controller;

import org.Model.*;
import org.Utils.Data;
import java.util.List;

public class RegistarCursoController {
    private final Empresa empresa;
    private final RegistoCursos registoCursos;
    private Curso curso;
    private Modulo moduloAtual;

    public RegistarCursoController(Empresa empresa) {
        this.empresa = empresa;
        this.registoCursos = empresa.getRegistoCursos(); // Pega o Registo!
    }

    public void novoCurso() {
        this.curso = registoCursos.novoCurso();
    }

    public void setDadosCurso(String titulo, String sigla, TipoCurso tipo, String descricao, Data ini, Data fim) {
        curso.setTitulo(titulo);
        curso.setSigla(sigla);
        curso.setTipo(tipo);
        curso.setDescricao(descricao);
        curso.setDataInicio(ini);
        curso.setDataFim(fim);
    }

    public void iniciarNovoModulo() {
        this.moduloAtual = registoCursos.novoModulo();
    }

    public void setDadosModulo(String titulo, int carga, double ponderacao, Formador formador) {
        moduloAtual.setTitulo(titulo);
        moduloAtual.setCargaHoraria(carga);
        moduloAtual.setPonderacao(ponderacao);
        moduloAtual.setFormador(formador);
    }

    // Novo método para adicionar sessões (Requisito Mínimo 3)
    public void adicionarSessao(Data data, int duracao, String sala) {
        Sessao s = registoCursos.novaSessao();
        s.setDados(data, duracao, sala);
        moduloAtual.addSessao(s);
    }

    public boolean adicionarModuloAtual() {
        // O módulo valida-se a si próprio (verifica se tem 3 sessões)
        return curso.adicionarModulo(moduloAtual);
    }

    public boolean registaCurso() {
        if (curso.getModulos().isEmpty()) return false;
        registoCursos.addCurso(curso); // Guarda no Registo
        return true;
    }

    // Listas auxiliares vêm da Empresa (decisão Facade para estas listas)
    public List<TipoCurso> getTiposCurso() { return empresa.obterListaTiposCurso(); }
    public List<Formador> getFormadores() { return empresa.obterListaFormadores(); }

    public Curso getCursoEmConstrucao() { return curso; }
    public String getCursoAsString() { return curso.toString(); }
}