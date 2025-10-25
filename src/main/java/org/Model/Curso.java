package org.Model;

import java.util.ArrayList;
import java.util.List;

public class Curso {

    private String titulo;
    private String sigla;
    private String descricao;
    private TipoCurso tipo;
    private int estado;                     // 0-A iniciar, 1-Ativo, ...
    private org.Utils.Data dataInicio;      // CORRIGIDO: Data
    private org.Utils.Data dataConclusao;   // CORRIGIDO: Data
    private final List<Modulo> modulos;
    private final List<Inscricao> inscricoes;

    public Curso() {
        this.modulos = new ArrayList<>();
        this.inscricoes = new ArrayList<>();
        this.estado = 0;        // padrão: A iniciar
    }

    /* ---------- getters & setters ---------- */
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public TipoCurso getTipo() { return tipo; }
    public void setTipo(TipoCurso tipo) { this.tipo = tipo; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }

    public org.Utils.Data getDataInicio() { return dataInicio; }
    public void setDataInicio(org.Utils.Data dataInicio) { this.dataInicio = dataInicio; }

    public org.Utils.Data getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(org.Utils.Data dataConclusao) { this.dataConclusao = dataConclusao; }

    public List<Modulo> getModulos() { return new ArrayList<>(modulos); }

    public boolean adicionarModulo(Modulo modulo) {
        if (modulo != null) { modulos.add(modulo); return true; }
        return false;
    }

    public List<Inscricao> getInscricoes() { return inscricoes; }

    public void adicionarInscricao(Inscricao inscricao) { inscricoes.add(inscricao); }
    public void removerInscricao(Inscricao inscricao) { inscricoes.remove(inscricao); }

    @Override
    public String toString() {
        return titulo + " (" + sigla + ") - Estado: " + estado;
    }
}