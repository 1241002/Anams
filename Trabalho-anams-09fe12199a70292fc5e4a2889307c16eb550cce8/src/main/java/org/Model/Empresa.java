/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class Empresa
{
    private final List<TipoCurso> lstTiposCurso;
    private final List<CoordenadorAcademico> lstCA;
    private final List<Curso> cursos;
    // Completar
    public Empresa()
    {
        this.lstTiposCurso = new ArrayList<>();
        this.lstCA = new ArrayList<>();
        this.cursos = new ArrayList<>();
    }
  
    public TipoCurso novoTipoCurso()
    {
        return new TipoCurso();
    }
    public boolean especificarTipoCurso(TipoCurso tipoCurso)
    {
        if (this.valida(tipoCurso))
        {
           adicionarTipoCurso(tipoCurso);
           return true;
        }
        return false;
    }
        
    private void adicionarTipoCurso(TipoCurso tipoCurso){
        lstTiposCurso.add(tipoCurso);
    }
    // Validação global
    public boolean valida(TipoCurso tipoCurso)
    {
        boolean resp = false;
        if (tipoCurso.valida())
        {
           // Completar        
           //
           resp = true; 
        }
        return resp;
    }
   
    // Completar com outras funcionalidades
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa: \n");
        sb.append("Lista de tipos de cursos: "+ lstTiposCurso.toString()+"\n");
        return sb.toString();
    }

    /* NOVOS CAMPOS */


    /* -------------------------------------------------- */
    public CoordenadorAcademico novoCA() {
        return new CoordenadorAcademico(); }

    public boolean registaCA(CoordenadorAcademico ca) {
        if (validaCA(ca)) {
            lstCA.add(ca);
            return true;
        }
        return false;
    }

    private boolean validaCA(CoordenadorAcademico ca) {
        if (!ca.valida()) return false;
        /* regra de negócio: sigla única */
        boolean siglaExiste = lstCA.stream()
                .anyMatch(c -> c.getSigla().equalsIgnoreCase(ca.getSigla()));
        return !siglaExiste;
    }

    /* para enviar email (simulado) */
    public void enviarCredenciaisPorEmail(CoordenadorAcademico ca) {
        System.out.println("\n>>> EMAIL ENVIADO para " + ca.getEmail());
        System.out.println(">>> Login: " + ca.getCredenciais().getLogin());
        System.out.println(">>> Password: " + ca.getCredenciais().getPassword());
    }

    // Empresa.java
    public List<TipoCurso> obterListaTiposCurso() {
        return new ArrayList<>(lstTiposCurso);
    }
    public void addCurso(Curso curso) {
        this.cursos.add(curso);
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Curso> getAvailableCourses() {
        return cursos;
    }

    public Curso findCursoById(String idCurso) {
        for (Curso curso : cursos) {
            if (curso.getSigla().equals(idCurso)) {
                return curso;
            }
        }
        return null;
    }

    private final List<Curso> lstCursos = new ArrayList<>();
    private final List<Formador> lstFormadores = new ArrayList<>();
    private int seqModulo = 0;

    public List<Curso> obterListaCursos() { return new ArrayList<>(lstCursos); }
    public void adicionarCurso(Curso c) { lstCursos.add(c); }

    public List<Formador> obterListaFormadores() { return new ArrayList<>(lstFormadores); }
    public void adicionarFormador(Formador f) { lstFormadores.add(f); }

    public String gerarCodigoModulo() { return "MOD" + (++seqModulo); }

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
    private int estado;   // 0-A iniciar, 1-Ativo, ...

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
    /* ---------- consulta por estado ---------- */
    public List<Curso> filtrarCursosPorEstado(int idx) {
        List<Curso> resultado = new ArrayList<>();
        for (Curso c : lstCursos)
            if (c.getEstado() == idx)
                resultado.add(c);
        return resultado;
    }
    private final List<Matricula> lstMatriculas = new ArrayList<>();

    public void registaMatricula(Matricula m) { lstMatriculas.add(m); }

    public void enviarCredenciaisPorEmail(Matricula m) {
        System.out.println("\n>>> EMAIL ENVIADO para " + m.getEmail());
        System.out.println(">>> Login: " + m.getCredenciais().getLogin());
        System.out.println(">>> Senha: " + m.getCredenciais().getPassword());
    }

    public void notificarCA(Matricula m) {
        System.out.println(">>> NOTIFICAÇÃO: Nova matrícula de " + m.getNome() + " (CC: " + m.getCc() + ") pendente de validação.");
    }


    public List<Matricula> filtrarMatriculasPorEstado(int estado) {
        List<Matricula> resultado = new ArrayList<>();
        for (Matricula m : lstMatriculas)
            if (m.getEstado() == estado)
                resultado.add(m);
        return resultado;
    }

    public void atualizarMatricula(Matricula m) {
    }

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
    
    
