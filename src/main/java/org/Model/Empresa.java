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
/**
 * Classe central que guarda todos os dados da empresa em memória
 */
public class Empresa {

    private final List<TipoCurso> lstTiposCurso = new ArrayList<>();
    private final List<CoordenadorAcademico> lstCA = new ArrayList<>();
    private final List<Curso> cursos = new ArrayList<>();
    private final List<Curso> lstCursos = new ArrayList<>();
    private final List<Formador> lstFormadores = new ArrayList<>();
    private final List<Matricula> lstMatriculas = new ArrayList<>();
    private final List<Aluno> alunos = new ArrayList<>();
    private int seqModulo = 0;

    // Construtor vazio – inicializa listas
    public Empresa() {}

    /* ---------- TIPO CURSO ---------- */
    // Cria um novo tipo de curso vazio
    public TipoCurso novoTipoCurso() { return new TipoCurso(); }

    // Regista um tipo de curso se for válido
    public boolean especificarTipoCurso(TipoCurso tipoCurso) {
        if (this.valida(tipoCurso)) { adicionarTipoCurso(tipoCurso); return true; }
        return false;
    }

    // Adiciona o tipo de curso à lista
    private void adicionarTipoCurso(TipoCurso tipoCurso) { lstTiposCurso.add(tipoCurso); }

    // Valida o tipo de curso usando o seu próprio método
    public boolean valida(TipoCurso tipoCurso) { return tipoCurso.valida(); }

    // Devolve cópia da lista de tipos de curso
    public List<TipoCurso> obterListaTiposCurso() { return new ArrayList<>(lstTiposCurso); }

    /* ---------- COORDENADOR ACADÉMICO ---------- */
    // Cria um novo coordenador vazio
    public CoordenadorAcademico novoCA() { return new CoordenadorAcademico(); }

    // Regista CA se válido e sigla não existir
    public boolean registaCA(CoordenadorAcademico ca) {
        if (!ca.valida()) return false;
        boolean siglaExiste = lstCA.stream()
                .anyMatch(c -> c.getSigla().equalsIgnoreCase(ca.getSigla()));
        if (!siglaExiste) { lstCA.add(ca); return true; }
        return false;
    }

    // Simula envio de credenciais por email
    public void enviarCredenciaisPorEmail(CoordenadorAcademico ca) {
        System.out.println("\n>>> EMAIL ENVIADO para " + ca.getEmail());
        System.out.println(">>> Login: " + ca.getCredenciais().getLogin());
        System.out.println(">>> Password: " + ca.getCredenciais().getPassword());
    }

    /* ---------- CURSO ---------- */
    // Adiciona curso à lista principal
    public void addCurso(Curso curso) { this.cursos.add(curso); }

    // Devolve lista de todos os cursos
    public List<Curso> getCursos() { return cursos; }

    // Devolve cursos disponíveis (mesma lista)
    public List<Curso> getAvailableCourses() { return cursos; }

    // Procura curso pela sigla
    public Curso findCursoById(String idCurso) {
        for (Curso curso : cursos) { if (curso.getSigla().equals(idCurso)) return curso; }
        return null;
    }

    // Devolve cópia da lista de cursos registados
    public List<Curso> obterListaCursos() { return new ArrayList<>(lstCursos); }

    // Adiciona curso à lista secundária (UI)
    public void adicionarCurso(Curso c) { lstCursos.add(c); }

    /* ---------- FORMADOR ---------- */
    // Cria um novo formador vazio
    public Formador novoFormador() { return new Formador(); }

    // Regista formador (validação simples)
    public boolean registaFormador(Formador f) {
        if (f == null) return false;
        lstFormadores.add(f);
        return true;
    }

    // Devolve cópia da lista de formadores
    public List<Formador> obterListaFormadores() { return new ArrayList<>(lstFormadores); }

    /* ---------- MÓDULO ---------- */
    // Gera código sequencial: MOD1, MOD2, ...
    public String gerarCodigoModulo() {
        int temp = ++seqModulo;
        return "MOD" + temp;
    }

    // Verifica se formador tem conflito de horário
    public boolean existeSobreposicaoHorario(Formador f, String horario) {
        for (Curso c : lstCursos)
            for (Modulo m : c.getModulos())
                if (m.getFormadorResponsavel().equals(f) &&
                        m.getHorario().equalsIgnoreCase(horario))
                    return true;
        return false;
    }

    // Adiciona módulo a um curso
    public boolean registaModulo(Curso curso, Modulo modulo) {
        if (curso == null || modulo == null) return false;
        curso.adicionarModulo(modulo);
        return true;
    }

    /* ---------- ESTADO CURSO / CONSULTA ---------- */
    // Filtra cursos por estado (índice 0 a 4)
    public List<Curso> filtrarCursosPorEstado(int idx) {
        List<Curso> res = new ArrayList<>();
        for (Curso c : lstCursos) if (c.getEstado() == idx) res.add(c);
        return res;
    }

    /* ---------- MATRÍCULA ---------- */
    // Regista nova matrícula na lista
    public void registaMatricula(Matricula m) { lstMatriculas.add(m); }

    // Atualiza matrícula (em memória, já está atualizada)
    public void atualizarMatricula(Matricula m) { /* memória – já está actualizado */ }

    // Simula envio de credenciais ao aluno
    public void enviarCredenciaisPorEmail(Matricula m) {
        System.out.println("\n>>> EMAIL ENVIADO para " + m.getEmail());
        System.out.println(">>> Login: " + m.getCredenciais().getLogin());
        System.out.println(">>> Senha: " + m.getCredenciais().getPassword());
    }

    // Simula notificação ao CA sobre nova matrícula
    public void notificarCA(Matricula m) {
        System.out.println("\n>>> NOTIFICAÇÃO: Nova matrícula de " + m.getNome() + " (CC: " + m.getCc() + ") pendente de validação.");
    }

    // Filtra matrículas por estado (PENDENTE, ACEITE, etc.)
    public List<Matricula> filtrarMatriculasPorEstado(int estado) {
        List<Matricula> res = new ArrayList<>();
        for (Matricula m : lstMatriculas) if (m.getEstado() == estado) res.add(m);
        return res;
    }

    // Adiciona aluno (ex: após aceitar matrícula)
    public void addAluno(Aluno aluno) {
        if (aluno != null && !alunos.contains(aluno)) {
            alunos.add(aluno);
        }
    }

    // Devolve todos os alunos
    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }

    public Aluno buscarAlunoPorEmail(String email) {
        for (Aluno a : alunos) {
            if (a.getEmail() != null && a.getEmail().equalsIgnoreCase(email)) {
                return a;
            }
        }
        return null;
    }

    /* ---------- toString ---------- */
    // Mostra resumo da empresa (tipos de curso)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa:\n");
        sb.append("Lista de tipos de cursos: ").append(lstTiposCurso.toString()).append("\n");
        return sb.toString();
    }

    /* ---------- NOTIFICAÇÃO DE MATRÍCULA ---------- */
    // Envia email ao aluno: ACEITE ou REJEITADA (com justificativa)
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