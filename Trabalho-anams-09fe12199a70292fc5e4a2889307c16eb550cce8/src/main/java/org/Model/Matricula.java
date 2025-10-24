package org.Model;

public class Matricula {
    private String nome;
    private String dataDeNascimento;
    private String habilitacoesAcademicas;
    private String email;
    private String cc;
    private String genero;
    private Credenciais credenciais;

    public Matricula() {}

    /* ---------- getters & setters ---------- */
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDataDeNascimento() { return dataDeNascimento; }
    public void setDataDeNascimento(String data) { this.dataDeNascimento = data; }
    public String getHabilitacoesAcademicas() { return habilitacoesAcademicas; }
    public void setHabilitacoesAcademicas(String h) { this.habilitacoesAcademicas = h; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCc() { return cc; }
    public void setCc(String cc) { this.cc = cc; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public Credenciais getCredenciais() { return credenciais; }
    public void setCredenciais(Credenciais c) { this.credenciais = c; }

    @Override
    public String toString() {
        return String.format("Matrícula: %s | CC: %s | Email: %s", nome, cc, email);
    }
}