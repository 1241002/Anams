package org.Model;

import java.util.ArrayList;
import java.util.List;

public class Formador {

    private String nome;
    private String dataNascimento;
    private String cc;
    private String email;
    private String contacto;
    private String areaEspecial;
    private String identificadorUnico;
    private Credenciais credenciais;

    private final List<Inscricao> inscricoes;

    public Formador() {
        inscricoes = new ArrayList<>();
        identificadorUnico = "";
        credenciais = new Credenciais("", "");
    }

    // ---------- UC3 – setters ----------
    public void setNome(String nome) { this.nome = nome; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setCc(String cc) { this.cc = cc; }
    public void setEmail(String email) { this.email = email; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public void setAreaEspecial(String areaEspecial) { this.areaEspecial = areaEspecial; }
    public void setIdentificadorUnico(String identificadorUnico) { this.identificadorUnico = identificadorUnico; }
    public void setCredenciais(Credenciais credenciais) { this.credenciais = credenciais; }

    // ---------- UC3 – getters ----------
    public String getNome() { return nome; }
    public String getDataNascimento() { return dataNascimento; }
    public String getCc() { return cc; }
    public String getEmail() { return email; }
    public String getContacto() { return contacto; }
    public String getAreaEspecial() { return areaEspecial; }
    public String getIdentificadorUnico() { return identificadorUnico; }
    public Credenciais getCredenciais() { return credenciais; }

    // ---------- original – mantido ----------
    public void adicionarInscricao(Inscricao inscricao) { inscricoes.add(inscricao); }
    public List<Inscricao> getInscricoes() { return inscricoes; }

    @Override
    public String toString() {
        return  "Nome: " + nome + "\n" +
                "Data de nascimento: " + dataNascimento + "\n" +
                "CC: " + cc + "\n" +
                "Email: " + email + "\n" +
                "Contacto: " + contacto + "\n" +
                "Área de especialização: " + areaEspecial + "\n" +
                "Identificador único: " + identificadorUnico + "\n" +
                "Credenciais: " + (credenciais != null ? credenciais.toString() : "n/a") + "\n";
    }
}