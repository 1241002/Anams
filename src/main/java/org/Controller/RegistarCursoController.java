package org.Controller;

import org.Model.*;
import org.Utils.Data;
import java.util.List;

public class RegistarCursoController {

    private final Empresa empresa;
    private Curso curso;
    private Modulo moduloAtual;

    // Construtor: recebe a empresa para criar e registar cursos
    public RegistarCursoController(Empresa empresa) {
        this.empresa = empresa;
    }

    // Cria um novo curso vazio (pronto para preencher)
    public void novoCurso() {
        this.curso = new Curso();
    }

    // Define os dados principais do curso (título, sigla, tipo, descrição, datas)
    public void setDadosCurso(String titulo, String sigla, TipoCurso tipo,
                              String descricao, Data dataInicio, Data dataFim) {
        curso.setTitulo(titulo);
        curso.setSigla(sigla);
        curso.setTipo(tipo);
        curso.setDescricao(descricao);
        curso.setDataInicio(dataInicio);
        curso.setDataConclusao(dataFim);
    }

    // Inicia a criação de um novo módulo (limpa o anterior)
    public void iniciarNovoModulo() {
        this.moduloAtual = new Modulo();
    }

    // Define os dados do módulo atual (título, carga, datas, horário, formador)
    public void setDadosModulo(String titulo, int cargaHoraria,
                               Data dataInicio, Data dataConclusao,
                               String horario, Formador formador) {
        moduloAtual.setTitulo(titulo);
        moduloAtual.setCargaHoraria(cargaHoraria);
        moduloAtual.setDataInicio(dataInicio);
        moduloAtual.setDataConclusao(dataConclusao);
        moduloAtual.setHorario(horario);
        moduloAtual.setFormadorResponsavel(formador);
    }

    // Gera código automático e adiciona o módulo atual ao curso
    public boolean adicionarModuloAtual() {
        String codigo = "MOD-" + System.currentTimeMillis();
        moduloAtual.setCodigo(codigo);
        return curso.adicionarModulo(moduloAtual);
    }

    // Regista o curso na empresa (exige pelo menos 1 módulo)
    public boolean registaCurso() {
        if (curso.getModulos().isEmpty()) return false;
        empresa.adicionarCurso(curso);
        return true;
    }

    // Devolve o curso completo como texto (para mostrar na UI)
    public String getCursoAsString() { return curso.toString(); }

    // Devolve a lista de tipos de curso disponíveis
    public List<TipoCurso> getTiposCurso() { return empresa.obterListaTiposCurso(); }

    // Devolve a lista de formadores disponíveis
    public List<Formador> getFormadores() { return empresa.obterListaFormadores(); }

    // Devolve o curso em construção (para UI parcial)
    public Curso getCursoEmConstrucao() { return curso; }
}