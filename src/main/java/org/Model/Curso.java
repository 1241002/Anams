package org.Model;
import org.Utils.Data;
import java.util.ArrayList;
import java.util.List;

public class Curso implements Avaliavel {
    private String titulo;
    private String sigla;
    private String descricao;
    private String estado; // IT2: "A iniciar", "Ativo", etc.
    private TipoCurso tipo;
    private Data dataInicio;
    private Data dataFim;
    private List<Modulo> modulos;
    private List<Inscricao> inscricoes; // Necessário para UC11/12

    public Curso() {
        this.estado = "A iniciar";
        this.modulos = new ArrayList<>();
        this.inscricoes = new ArrayList<>();
    }

    // Getters/Setters básicos
    public void setTitulo(String t) { this.titulo = t; }
    public String getTitulo() { return titulo; }
    public void setSigla(String s) { this.sigla = s; }
    public String getSigla() { return sigla; }
    public void setDescricao(String d) { this.descricao = d; }
    public void setTipo(TipoCurso t) { this.tipo = t; }
    public void setDataInicio(Data d) { this.dataInicio = d; }
    public void setDataFim(Data d) { this.dataFim = d; }

    // === IT2 NOVOS ===
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public boolean adicionarModulo(Modulo m) {
        if (m.valida()) { return modulos.add(m); }
        return false;
    }
    public List<Modulo> getModulos() { return new ArrayList<>(modulos); }

    // Métodos de Inscrição (para UC11/12)
    public List<Inscricao> getInscricoes() { return inscricoes; }
    public void adicionarInscricao(Inscricao i) { inscricoes.add(i); }

    // Validação de Formador (UC11)
    public boolean temFormador(String nomeFormador) {
        for(Modulo m : modulos) {
            if(m.getFormador() != null && m.getFormador().getNome().equalsIgnoreCase(nomeFormador))
                return true;
        }
        return false;
    }

    @Override
    public double calcularNota(List<Classificacao> classificacoesAluno) {
        // Lógica de cálculo (não crítica para UC4/13, mas necessária para compilar Interface)
        return 0.0;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - [%s]", titulo, sigla, estado);
    }
}   