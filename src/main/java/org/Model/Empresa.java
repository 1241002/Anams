package org.Model;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

    // === ESTRUTURAS ANTIGAS (Mantidas para compatibilidade) ===
    private final List<TipoCurso> lstTiposCurso;
    private final List<CoordenadorAcademico> lstCA;
    private final List<Formador> lstFormadores;
    private final List<Matricula> lstMatriculas;

    // === NOVOS REGISTOS (Para a nova lógica) ===
    private final RegistoCursos registoCursos;
    private final RegistoAlunos registoAlunos;

    public Empresa() {
        this.lstTiposCurso = new ArrayList<>();
        this.lstCA = new ArrayList<>();
        this.lstFormadores = new ArrayList<>();
        this.lstMatriculas = new ArrayList<>();

        this.registoCursos = new RegistoCursos();
        this.registoAlunos = new RegistoAlunos();
    }

    // === ACESSO AOS REGISTOS (Usado pelos novos Controllers) ===
    public RegistoCursos getRegistoCursos() {
        return registoCursos;
    }

    public RegistoAlunos getRegistoAlunos() {
        return registoAlunos;
    }

    // === MÉTODOS DE ALUNOS (Ponte) ===
    public void addAluno(Aluno a) {
        registoAlunos.addAluno(a);
    }

    public List<Aluno> getAlunos() {
        return registoAlunos.getAlunos();
    }

    // === MÉTODOS DE CURSOS (Ponte) ===
    public List<Curso> getCursos() {
        return registoCursos.getCursos();
    }

    public List<Curso> obterListaCursos() { // Alias para compatibilidade
        return registoCursos.getCursos();
    }

    public void addCurso(Curso c) {
        registoCursos.addCurso(c);
    }

    public String gerarCodigoModulo() {
        return "MOD-" + (System.currentTimeMillis() % 10000);
    }

    public boolean existeSobreposicaoHorario(Formador f, String horario) {
        return false; // Simplificado para não dar erro
    }

    public boolean registaModulo(Curso c, Modulo m) {
        return c.adicionarModulo(m);
    }

    // === MÉTODOS DE MATRÍCULAS (Usado pelo ValidarMatriculaController) ===
    public void registaMatricula(Matricula m) {
        lstMatriculas.add(m);
    }

    public List<Matricula> filtrarMatriculasPorEstado(int estado) {
        List<Matricula> lista = new ArrayList<>();
        for (Matricula m : lstMatriculas) {
            if (m.getEstado() == estado) {
                lista.add(m);
            }
        }
        return lista;
    }

    public void atualizarMatricula(Matricula m) {
        // Em memória já está atualizado por referência
    }

    public void enviarNotificacao(Matricula m, String msg) {
        System.out.println("Notificação enviada para " + m.getEmail() + ": " + msg);
    }

    public void notificarCA(Matricula m) {
        System.out.println("CA notificado sobre: " + m.getNome());
    }

    // === MÉTODOS DE FORMADORES ===
    public Formador novoFormador() { return new Formador(); }

    public boolean registaFormador(Formador f) {
        if (!lstFormadores.contains(f)) {
            lstFormadores.add(f);
            return true;
        }
        return false;
    }

    public List<Formador> obterListaFormadores() { return new ArrayList<>(lstFormadores); }

    public Formador getFormadorPorNome(String nome) {
        for(Formador f : lstFormadores) {
            if(f.getNome().equalsIgnoreCase(nome)) return f;
        }
        return null;
    }

    // === MÉTODOS DE TIPOS DE CURSO ===
    public TipoCurso novoTipoCurso() { return new TipoCurso(); }

    public boolean especificarTipoCurso(TipoCurso tc) {
        if (tc.valida()) { lstTiposCurso.add(tc); return true; }
        return false;
    }

    public List<TipoCurso> obterListaTiposCurso() { return new ArrayList<>(lstTiposCurso); }

    // === MÉTODOS DE CA ===
    public CoordenadorAcademico novoCA() { return new CoordenadorAcademico(); }

    public boolean registaCA(CoordenadorAcademico ca) {
        if (ca.valida()) { lstCA.add(ca); return true; }
        return false;
    }

    public void enviarCredenciaisPorEmail(Object destinatario) {
        System.out.println("Credenciais enviadas.");
    }
    public List<Curso> filtrarCursosPorEstado(int indice) {
        // 1. Validar se o índice é válido
        if (EstadoCurso.NOMES == null || indice < 0 || indice >= EstadoCurso.NOMES.length) {
            return new ArrayList<>();
        }

        // 2. Converter o índice (ex: 0) para Texto (ex: "A iniciar")
        String nomeEstado = EstadoCurso.NOMES[indice];

        // 3. Delegar a pesquisa ao RegistoCursos
        return registoCursos.getCursosPorEstado(nomeEstado);
    }
}