package org.Model;
import java.util.ArrayList;
import java.util.List;

public class RegistoCursos {
    private List<Curso> cursos;

    public RegistoCursos() {
        this.cursos = new ArrayList<>();
    }

    // Factory Methods
    public Curso novoCurso() { return new Curso(); }
    public Modulo novoModulo() { return new Modulo(); }
    public Sessao novaSessao() { return new Sessao(null, 0, ""); }

    // Gestão
    public void addCurso(Curso c) {
        if (validarCurso(c)) {
            this.cursos.add(c);
        }
    }

    public boolean validarCurso(Curso c) {
        for (Curso existente : cursos) {
            if (existente.getSigla().equalsIgnoreCase(c.getSigla())) return false;
        }
        return true;
    }

    public Curso getCurso(String sigla) {
        for (Curso c : cursos) {
            if (c.getSigla().equalsIgnoreCase(sigla)) return c;
        }
        return null;
    }

    public List<Curso> getCursos() { return new ArrayList<>(cursos); }

    // Para UC13
    public List<String> getEstadosPossiveis() {
        List<String> estados = new ArrayList<>();
        estados.add("A iniciar");
        estados.add("Ativo");
        estados.add("Cancelado");
        estados.add("Concluído");
        return estados;
    }

    public List<Curso> getCursosPorEstado(String estado) {
        List<Curso> listaFiltrada = new ArrayList<>();

        for (Curso c : cursos) {
            // Verifica se o curso tem estado e se é igual ao procurado (ex: "A iniciar")
            if (c.getEstado() != null && c.getEstado().equalsIgnoreCase(estado)) {
                listaFiltrada.add(c);
            }
        }
        return listaFiltrada;
    }

    public void registarInscricao(Curso curso, Aluno aluno) {
        // 1. Criar a instância da Inscrição
        Inscricao insc = new Inscricao(aluno, curso);

        // 2. Definir o estado inicial (Requisito IT2: "0-ativa") [cite: 16]
        insc.setEstado("0-ativa");

        // 3. Guardar a inscrição nas listas do Curso e do Aluno
        curso.adicionarInscricao(insc);
        aluno.adicionarInscricao(insc);

        // Opcional: Se quiseres decrementar vaga aqui
        // curso.decrementarVaga();
    }
}