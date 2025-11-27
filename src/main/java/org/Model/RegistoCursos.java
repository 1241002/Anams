package org.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistoCursos {
    private List<Curso> cursos;

    public RegistoCursos() {
        this.cursos = new ArrayList<>();
    }

    // === MÉTODOS GERAIS (Factory & Getters) ===
    public Curso novoCurso() {
        return new Curso();
    }

    public Modulo novoModulo() {
        return new Modulo();
    }

    public Sessao novaSessao() {
        return new Sessao(null, 0, "");
    }

    public void addCurso(Curso c) {
        this.cursos.add(c);
    }

    public List<Curso> getCursos() {
        return new ArrayList<>(cursos);
    }

    public Curso getCurso(String sigla) {
        for (Curso c : cursos) {
            if (c.getSigla() != null && c.getSigla().equalsIgnoreCase(sigla)) return c;
        }
        return null;
    }

    // === UC9: INSCREVER ALUNO ===
    public List<Curso> getCursosPorEstado(String estado) {
        List<Curso> lista = new ArrayList<>();
        for (Curso c : cursos) {
            if (c.getEstado() != null && c.getEstado().equalsIgnoreCase(estado)) {
                lista.add(c);
            }
        }
        return lista;
    }

    public boolean registarInscricao(Curso curso, Aluno aluno) {
        if (curso.validarVagas()) {
            Inscricao insc = new Inscricao(aluno, curso);
            insc.setEstado("0-ativa");

            curso.adicionarInscricao(insc);
            aluno.adicionarInscricao(insc);

            curso.decrementarVaga();
            return true;
        }
        return false;
    }

    // === UC10: ANULAR INSCRIÇÃO ===
    public boolean anularInscricao(Curso curso, Aluno aluno) {
        Inscricao alvo = null;
        for (Inscricao i : aluno.getInscricoes()) {
            if (i.getCurso().equals(curso) && "0-ativa".equalsIgnoreCase(i.getEstado())) {
                alvo = i;
                break;
            }
        }

        if (alvo == null) return false;

        if (curso.verificaPrazoAnulacao()) {
            alvo.setEstado("1-cancelada");
            curso.incrementarVaga();
            return true;
        }
        return false;
    }

    // === UC11: CONSULTAR CURSOS (FORMADOR) ===
    // Este é um dos métodos que te faltava!
    public List<Curso> getCursosDoFormador(Formador formador) {
        List<Curso> lista = new ArrayList<>();
        for (Curso c : cursos) {
            // Requer que Curso tenha o método temFormador(Formador)
            if (c.temFormador(formador)) {
                lista.add(c);
            }
        }
        return lista;
    }

    // === UC15: REGISTAR CLASSIFICAÇÕES ===
    // Estes são os outros métodos que te faltavam!
    public List<Modulo> getModulosPorFormador(Curso curso, Formador formador) {
        List<Modulo> lista = new ArrayList<>();
        for (Modulo m : curso.getModulos()) {
            if (m.getFormador() != null && m.getFormador().equals(formador)) {
                lista.add(m);
            }
        }
        return lista;
    }

    public boolean registarNota(Modulo modulo, Inscricao inscricao, double nota) {
        // Se a nota for inválida, devolve falso
        if (nota < 0 || nota > 20) return false;

        // Cria a classificação e associa
        Classificacao c = new Classificacao(inscricao, modulo, nota);
        inscricao.addClassificacao(c);

        return true; //
    }

    public List<String> getEstadosPossiveis() {
        List<String> lista = new ArrayList<>();
        // Vai buscar os nomes à classe constante EstadoCurso
        if (EstadoCurso.NOMES != null) {
            for (String s : EstadoCurso.NOMES) {
                lista.add(s);
            }
        }
        return lista;
    }
}
