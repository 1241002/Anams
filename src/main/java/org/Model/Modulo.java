package org.Model;

import org.Utils.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects; // Necessário para o equals

public class Modulo implements Avaliavel {
    private String titulo;
    private String codigo;
    private int cargaHoraria;
    private double ponderacao;
    private Formador formador;

    // Dados de agendamento (UC4/UC5)
    private Data dataInicio;
    private Data dataConclusao;
    private String horario;

    private List<Sessao> sessoes;

    public Modulo() {
        this.sessoes = new ArrayList<>();
    }

    // Construtor auxiliar (útil para testes)
    public Modulo(String titulo) {
        this();
        this.titulo = titulo;
    }

    // === SETTERS E GETTERS ===

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getTitulo() { return titulo; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getCodigo() { return codigo; }

    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public int getCargaHoraria() { return cargaHoraria; }

    public void setPonderacao(double ponderacao) { this.ponderacao = ponderacao; }
    public double getPonderacao() { return ponderacao; }

    // Compatibilidade com Controller
    public void setFormadorResponsavel(Formador formador) { this.formador = formador; }
    public void setFormador(Formador formador) { this.formador = formador; }
    public Formador getFormador() { return formador; }
    public Formador getFormadorResponsavel() { return formador; }

    public void setDataInicio(Data dataInicio) { this.dataInicio = dataInicio; }
    public Data getDataInicio() { return dataInicio; }

    public void setDataConclusao(Data dataConclusao) { this.dataConclusao = dataConclusao; }
    public Data getDataConclusao() { return dataConclusao; }

    public void setHorario(String horario) { this.horario = horario; }
    public String getHorario() { return horario; }

    // === GESTÃO DE SESSÕES ===
    public void addSessao(Sessao s) { this.sessoes.add(s); }
    public List<Sessao> getSessoes() { return new ArrayList<>(sessoes); }

    // === LÓGICA DE NEGÓCIO ===

    public boolean valida() {
        // Um módulo é válido se tiver título e pelo menos uma sessão (regra básica)
        // O Controller do UC5 pode aplicar regras mais estritas (ex: min 3 sessões)
        return titulo != null && !titulo.isEmpty();
    }

    // === MÉTODO CRUCIAL PARA O UC14 (NOVO) ===
    // Garante que o sistema sabe comparar se dois módulos são o mesmo
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modulo modulo = (Modulo) o;
        // Compara pelo código (se existir) ou pelo título
        return Objects.equals(codigo, modulo.codigo) &&
                Objects.equals(titulo, modulo.titulo);
    }

    // === INTERFACE AVALIAVEL (UC14) ===
    @Override
    public double calcularNota(List<Classificacao> classificacoes) {
        if (classificacoes == null || classificacoes.isEmpty()) {
            return 0.0;
        }

        // Procura a nota deste módulo específico na lista do aluno
        for (Classificacao c : classificacoes) {
            // Aqui o .equals() que adicionámos acima é fundamental!
            if (c.getModulo() != null && c.getModulo().equals(this)) {
                return c.getNota();
            }
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return String.format("Módulo: %s [%s] - %d h (Ponderação: %.0f%%)",
                titulo,
                (codigo != null ? codigo : "N/A"),
                cargaHoraria,
                ponderacao * 100); // Mostra ponderação em percentagem
    }
}