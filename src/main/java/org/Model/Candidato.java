package org.Model;

public class Candidato {
    private String nome;
    private String dataNascimento;
    private String habilitacoes;
    private String email;
    private String cc;
    private String genero;
    private Credenciais credenciais;

    // Estado da candidatura (Pendente, Aceite, Rejeitada)
    private int estado;
    private String justificacao; // Para o UC8 (Rejeição)

    public Candidato() {
        this.estado = EstadoMatricula.PENDENTE; // Usa a constante que já tens
    }

    // === Getters e Setters ===
    public void setDados(String nome, String dataNascimento, String habilitacoes, String email, String cc, String genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.habilitacoes = habilitacoes;
        this.email = email;
        this.cc = cc;
        this.genero = genero;
    }

    public void setCredenciais(Credenciais credenciais) {
        this.credenciais = credenciais;
    }

    public boolean validar() {
        // Validação básica conforme diagrama
        return nome != null && !nome.isEmpty() && email != null && !email.isEmpty() && cc != null;
    }

    // Getters necessários para o sistema
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCc() { return cc; }
    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
    public void setJustificacao(String justificacao) { this.justificacao = justificacao; }

    @Override
    public String toString() {
        return String.format("Candidato: %s | CC: %s | Email: %s", nome, cc, email);
    }
}
