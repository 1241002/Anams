/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class Empresa {

    private final List<TipoCurso> lstTiposCurso = new ArrayList<>();
    private final List<CoordenadorAcademico> lstCA = new ArrayList<>();
    private final List<Curso> cursos = new ArrayList<>();
    private final List<Curso> lstCursos = new ArrayList<>();
    private final List<Formador> lstFormadores = new ArrayList<>();
    private final List<Matricula> lstMatriculas = new ArrayList<>();
    private int seqModulo = 0;

    public Empresa() {}

    /* ---------- TIPO CURSO ---------- */
    public TipoCurso novoTipoCurso() { return new TipoCurso(); }
    public boolean especificarTipoCurso(TipoCurso tipoCurso) {
        if (this.valida(tipoCurso)) { adicionarTipoCurso(tipoCurso); return true; }
        return false;
    }
    private void adicionarTipoCurso(TipoCurso tipoCurso) { lstTiposCurso.add(tipoCurso); }
    public boolean valida(TipoCurso tipoCurso) { return tipoCurso.valida(); }
    public List<TipoCurso> obterListaTiposCurso() { return new ArrayList<>(lstTiposCurso); }

    /* ---------- COORDENADOR ACADÉMICO ---------- */
    public CoordenadorAcademico novoCA() { return new CoordenadorAcademico(); }
    public boolean registaCA(CoordenadorAcademico ca) {
        if (!ca.valida()) return false;
        boolean siglaExiste = lstCA.stream()
                .anyMatch(c -> c.getSigla().equalsIgnoreCase(ca.getSigla()));
        if (!siglaExiste) { lstCA.add(ca); return true; }
        return false;
    }
    public void enviarCredenciaisPorEmail(CoordenadorAcademico ca) {
        System.out.println("\n>>> EMAIL ENVIADO para " + ca.getEmail());
        System.out.println(">>> Login: " + ca.getCredenciais().getLogin());
        System.out.println(">>> Password: " + ca.getCredenciais().getPassword());
    }

    /* ---------- CURSO ---------- */
    public void addCurso(Curso curso) { this.cursos.add(curso); }
    public List<Curso> getCursos() { return cursos; }
    public List<Curso> getAvailableCourses() { return cursos; }
    public Curso findCursoById(String idCurso) {
        for (Curso curso : cursos) { if (curso.getSigla().equals(idCurso)) return curso; }
        return null;
    }
    public List<Curso> obterListaCursos() { return new ArrayList<>(lstCursos); }
    public void adicionarCurso(Curso c) { lstCursos.add(c); }

    /* ---------- FORMADOR ---------- */
    public Formador novoFormador() { return new Formador(); }
    public boolean registaFormador(Formador f) {
        if (f == null) return false;
        lstFormadores.add(f);
        return true;
    }
    public List<Formador> obterListaFormadores() { return new ArrayList<>(lstFormadores); }

    /* ---------- MÓDULO ---------- */
    public String gerarCodigoModulo() {
        int temp = ++seqModulo;
        return "MOD" + temp;
    }
    public boolean existeSobreposicaoHorario(Formador f, String horario) {
        for (Curso c : lstCursos)
            for (Modulo m : c.getModulos())
                if (m.getFormadorResponsavel().equals(f) &&
                        m.getHorario().equalsIgnoreCase(horario))
                    return true;
        return false;
    }
    public boolean registaModulo(Curso curso, Modulo modulo) {
        if (curso == null || modulo == null) return false;
        curso.adicionarModulo(modulo);
        return true;
    }

    /* ---------- ESTADO CURSO / CONSULTA ---------- */
    public List<Curso> filtrarCursosPorEstado(int idx) {
        List<Curso> res = new ArrayList<>();
        for (Curso c : lstCursos) if (c.getEstado() == idx) res.add(c);
        return res;
    }

    /* ---------- MATRÍCULA ---------- */
    public void registaMatricula(Matricula m) { lstMatriculas.add(m); }
    public void atualizarMatricula(Matricula m) { /* memória – já está actualizado */ }
    public void enviarCredenciaisPorEmail(Matricula m) {
        System.out.println("\n>>> EMAIL ENVIADO para " + m.getEmail());
        System.out.println(">>> Login: " + m.getCredenciais().getLogin());
        System.out.println(">>> Senha: " + m.getCredenciais().getPassword());
    }
    public void notificarCA(Matricula m) {
        System.out.println("\n>>> NOTIFICAÇÃO: Nova matrícula de " + m.getNome() + " (CC: " + m.getCc() + ") pendente de validação.");
    }
    public List<Matricula> filtrarMatriculasPorEstado(int estado) {
        List<Matricula> res = new ArrayList<>();
        for (Matricula m : lstMatriculas) if (m.getEstado() == estado) res.add(m);
        return res;
    }

    /* ---------- toString ---------- */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa:\n");
        sb.append("Lista de tipos de cursos: ").append(lstTiposCurso.toString()).append("\n");
        return sb.toString();
    }
    /* ---------- NOTIFICAÇÃO DE MATRÍCULA ---------- */
    public void enviarNotificacao(Matricula m, String estado) {
        System.out.println("\n>>> EMAIL ENVIADO para " + m.getEmail());
        if ("REJEITADA".equals(estado))
            System.out.println(">>> A sua matrícula foi REJEITADA.");
        else
            System.out.println(">>> A sua matrícula foi ACEITE.");
        if (m.getJustificacao() != null)
            System.out.println(">>> Justificativo: " + m.getJustificacao());
    }
}