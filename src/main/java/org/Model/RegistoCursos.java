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

    public boolean addCurso(Curso c) {
        // 1. Valida se a sigla é única
        if (validarCurso(c)) {
            this.cursos.add(c);
            return true; // Sucesso
        }
        return false; // Falha (já existe)
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
    public boolean validarCurso(Curso c) {
        for (Curso existente : cursos) {
            if (existente.getSigla().equalsIgnoreCase(c.getSigla())) {
                return false; // Erro: Já existe!
            }
        }
        return true;
    }

    // === UC5: VALIDAÇÃO DE SESSÕES (Conflito de Sala/Data) ===

    public boolean validarSessao(Sessao novaSessao) {
        // Percorre TODOS os cursos registados
        for (Curso c : cursos) {
            // Percorre TODOS os módulos de cada curso
            for (Modulo m : c.getModulos()) {
                // Percorre TODAS as sessões já marcadas
                for (Sessao sExistente : m.getSessoes()) {

                    // Verifica se é no mesmo DIA
                    boolean mesmoDia = sExistente.getData().equals(novaSessao.getData());

                    // Verifica se é na mesma SALA
                    boolean mesmaSala = sExistente.getSala().equalsIgnoreCase(novaSessao.getSala());

                    if (mesmoDia && mesmaSala) {
                        System.out.println("ERRO: Conflito detetado! A sala " + novaSessao.getSala() +
                                " já está ocupada no dia " + novaSessao.getData());
                        return false; // Inválido (Sobreposição)
                    }
                }
            }
        }
        return true; // Válido (Sem conflitos)
    }
}
