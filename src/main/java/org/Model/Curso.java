package org.Model;

import org.Utils.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Curso implements Avaliavel {
    private String titulo;
    private String sigla;
    private String descricao;
    private String estado;
    private TipoCurso tipo;
    private Data dataInicio;
    private Data dataFim;
    private int vagas = 20;

    private List<Modulo> modulos;
    private List<Inscricao> inscricoes;
    private Formador responsavel;

    public Curso() {
        this.estado = "A iniciar";
        this.modulos = new ArrayList<>();
        this.inscricoes = new ArrayList<>();
    }

    // === MÉTODOS UC9 / UC10 ===
    public boolean validarVagas() { return vagas > 0; }
    public void decrementarVaga() { if(vagas > 0) vagas--; }
    public void incrementarVaga() { vagas++; }

    public boolean verificaPrazoAnulacao() {
        return !this.estado.equalsIgnoreCase("Concluído");
    }

    // === MÉTODOS UC11 ===
    public boolean temFormador(Formador f) {
        if (this.responsavel != null && this.responsavel.equals(f)) return true;
        for (Modulo m : modulos) {
            if (m.getFormador() != null && m.getFormador().equals(f)) return true;
        }
        return false;
    }

    public boolean temFormador(String nome) {
        for (Modulo m : modulos) {
            if (m.getFormador() != null && m.getFormador().getNome().equalsIgnoreCase(nome)) return true;
        }
        return false;
    }

    // === MÉTODOS UC14 (Cálculo de Notas) ===

    // Calcula a nota final do curso baseada na Média Ponderada dos módulos
    @Override
    public double calcularNota(List<Classificacao> classificacoesAluno) {
        double somaNotasPonderadas = 0.0;
        double somaPonderacoes = 0.0;

        for (Modulo m : this.modulos) {
            // 1. Pede ao módulo a nota que o aluno teve nele (Delegation)
            double notaModulo = m.calcularNota(classificacoesAluno);

            // 2. Obtém o peso do módulo no curso
            double peso = m.getPonderacao();

            // 3. Acumula para a média
            somaNotasPonderadas += (notaModulo * peso);
            somaPonderacoes += peso;
        }

        // Evita divisão por zero se o curso não tiver módulos ou ponderações
        if (somaPonderacoes == 0) return 0.0;

        // Retorna média ponderada
        return somaNotasPonderadas / somaPonderacoes;
    }

    // === GETTERS E SETTERS ===
    public void adicionarInscricao(Inscricao i) { inscricoes.add(i); }
    public List<Inscricao> getInscricoes() { return inscricoes; }

    public boolean adicionarModulo(Modulo m) {
        if (m.valida()) { return modulos.add(m); }
        return false;
    }
    public List<Modulo> getModulos() { return new ArrayList<>(modulos); }

    public void setTitulo(String t) { this.titulo = t; }
    public String getTitulo() { return titulo; }

    public void setSigla(String s) { this.sigla = s; }
    public String getSigla() { return sigla; }

    public void setDescricao(String d) { this.descricao = d; }
    public void setTipo(TipoCurso t) { this.tipo = t; }
    public void setDataInicio(Data d) { this.dataInicio = d; }
    public void setDataFim(Data d) { this.dataFim = d; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setResponsavel(Formador f) { this.responsavel = f; }

    // É boa prática ter o equals para o UC14 funcionar bem nas comparações de listas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(sigla, curso.sigla);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) [%s]", titulo, sigla, estado);
    }
}