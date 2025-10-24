package org.Model;

import org.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Empresa {

    private final List<TipoCurso> lstTiposCurso;
    private final List<CoordenadorAcademico> lstCA;
    private final List<Curso> cursos;
    private final List<Formador> lstFormadores;

    public Empresa() {
        this.lstTiposCurso = new ArrayList<>();
        this.lstCA = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.lstFormadores = new ArrayList<>();
    }

    // ==================== UC3: REGISTAR FORMADOR ====================

    public Formador novoFormador() {
        return new Formador();
    }

    public void gerarIdentificadorECredenciais(Formador formador) {
        // Identificador único
        String identificadorUnico = "FOR-" + String.valueOf(System.currentTimeMillis());
        formador.setIdentificadorUnico(identificadorUnico);

        // Login: primeira parte do email (antes do @)
        String login = formador.getEmail().split("@")[0];
        // Password: 6 caracteres aleatórios (4 letras + 2 dígitos)
        String password = Utils.geraPassword(6, 4, 2);

        formador.setCredenciais(new Credenciais(login, password));

        // Simulação de envio de e-mail
        System.out.println("\n>>> EMAIL ENVIADO para " + formador.getEmail());
        System.out.println(">>> Login: " + login);
        System.out.println(">>> Password: " + password);
    }

    public boolean registaFormador(Formador formador) {
        // por completar: validação completa (campos obrigatórios, formato de email, etc.)
        if (emailJaExiste(formador.getEmail())) {
            return false;
        }

        gerarIdentificadorECredenciais(formador);
        lstFormadores.add(formador);
        return true;
    }

    private boolean emailJaExiste(String email) {
        return lstFormadores.stream()
                .anyMatch(f -> f.getEmail().equalsIgnoreCase(email));
    }

    public List<Formador> obterListaFormadores() {
        return new ArrayList<>(lstFormadores);
    }

    // ==================== OUTROS MÉTODOS (mantidos inalterados) ====================

    public TipoCurso novoTipoCurso() {
        return new TipoCurso();
    }

    public boolean especificarTipoCurso(TipoCurso tipoCurso) {
        if (this.valida(tipoCurso)) {
            adicionarTipoCurso(tipoCurso);
            return true;
        }
        return false;
    }

    private void adicionarTipoCurso(TipoCurso tipoCurso) {
        lstTiposCurso.add(tipoCurso);
    }

    public boolean valida(TipoCurso tipoCurso) {
        boolean resp = false;
        if (tipoCurso.valida()) {
            // por completar: regras de negócio adicionais
            resp = true;
        }
        return resp;
    }

    public CoordenadorAcademico novoCA() {
        return new CoordenadorAcademico();
    }

    public boolean registaCA(CoordenadorAcademico ca) {
        if (validaCA(ca)) {
            lstCA.add(ca);
            return true;
        }
        return false;
    }

    private boolean validaCA(CoordenadorAcademico ca) {
        if (!ca.valida()) return false;
        // regra de negócio: sigla única
        boolean siglaExiste = lstCA.stream()
                .anyMatch(c -> c.getSigla().equalsIgnoreCase(ca.getSigla()));
        return !siglaExiste;
    }

    public void enviarCredenciaisPorEmail(CoordenadorAcademico ca) {
        System.out.println("\n>>> EMAIL ENVIADO para " + ca.getEmail());
        System.out.println(">>> Login: " + ca.getCredenciais().getLogin());
        System.out.println(">>> Password: " + ca.getCredenciais().getPassword());
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa: \n");
        sb.append("Lista de tipos de cursos: ").append(lstTiposCurso).append("\n");
        return sb.toString();
    }
}