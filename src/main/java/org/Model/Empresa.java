package org.Model;

import org.Utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
    // === Listas Legadas (IT1) - Mantidas para compatibilidade ===
    private final List<TipoCurso> lstTiposCurso;
    private final List<CoordenadorAcademico> lstCA;
    private final List<Formador> lstFormadores;

    // === Novos Registos (IT2) - A Estrutura Nova ===
    private final RegistoCursos registoCursos;
    private final RegistoAlunos registoAlunos;

    public Empresa() {
        // Inicializa as listas antigas
        this.lstTiposCurso = new ArrayList<>();
        this.lstCA = new ArrayList<>();
        this.lstFormadores = new ArrayList<>();

        // Inicializa os novos Registos (Fundamental!)
        this.registoCursos = new RegistoCursos();
        this.registoAlunos = new RegistoAlunos();
    }

    // =============================================================
    // === MÉTODOS QUE FALTAVAM (Resolvem o teu erro) ===
    // =============================================================

    public RegistoCursos getRegistoCursos() {
        return registoCursos;
    }

    public RegistoAlunos getRegistoAlunos() {
        return registoAlunos;
    }

    // =============================================================
    // === MÉTODOS DE COMPATIBILIDADE / FACADE ===
    // =============================================================

    // Delegar nos registos para não partir código antigo
    public void addCurso(Curso c) {
        registoCursos.addCurso(c);
    }

    public List<Curso> getCursos() {
        return registoCursos.getCursos();
    }

    public List<Curso> getAvailableCourses() {
        return registoCursos.getCursosPorEstado("A iniciar");
    }

    public Curso findCursoById(String id) {
        return registoCursos.getCurso(id);
    }

    // === MÉTODOS ANTIGOS (UC1, UC2, UC3) - MANTIDOS IGUAIS ===

    public TipoCurso novoTipoCurso() { return new TipoCurso(); }
    public boolean especificarTipoCurso(TipoCurso tc) {
        if (tc.valida()) { lstTiposCurso.add(tc); return true; }
        return false;
    }
    public List<TipoCurso> obterListaTiposCurso() { return new ArrayList<>(lstTiposCurso); }

    public Formador novoFormador() { return new Formador(); }
    public boolean registaFormador(Formador f) { lstFormadores.add(f); return true; }
    public List<Formador> obterListaFormadores() { return new ArrayList<>(lstFormadores); }

    public CoordenadorAcademico novoCA() { return new CoordenadorAcademico(); }
    public boolean registaCA(CoordenadorAcademico ca) {
        if(ca.valida()){lstCA.add(ca); return true;}
        return false;
    }
    public void enviarCredenciaisPorEmail(CoordenadorAcademico ca) {
        System.out.println("Email enviado para: " + ca.getEmail());
    }
}