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

    // Construtor: inicializa listas e valores padrão
    public Formador() {
        inscricoes = new ArrayList<>();
        identificadorUnico = "";
        credenciais = new Credenciais("", "");
    }

    // ---------- UC3 – setters ----------
    // Define o nome do formador
    public void setNome(String nome) { this.nome = nome; }

    // Define a data de nascimento (formato String)
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    // Define o número do Cartão de Cidadão
    public void setCc(String cc) { this.cc = cc; }

    // Define o email do formador
    public void setEmail(String email) { this.email = email; }

    // Define o contacto telefónico
    public void setContacto(String contacto) { this.contacto = contacto; }

    // Define a área de especialização
    public void setAreaEspecial(String areaEspecial) { this.areaEspecial = areaEspecial; }

    // Define o identificador único (ex: FOR-123)
    public void setIdentificadorUnico(String identificadorUnico) { this.identificadorUnico = identificadorUnico; }

    // Define as credenciais de acesso (login + senha)
    public void setCredenciais(Credenciais credenciais) { this.credenciais = credenciais; }

    // ---------- UC3 – getters ----------
    // Devolve o nome
    public String getNome() { return nome; }

    // Devolve a data de nascimento
    public String getDataNascimento() { return dataNascimento; }

    // Devolve o CC
    public String getCc() { return cc; }

    // Devolve o email
    public String getEmail() { return email; }

    // Devolve o contacto
    public String getContacto() { return contacto; }

    // Devolve a área de especialização
    public String getAreaEspecial() { return areaEspecial; }

    // Devolve o identificador único
    public String getIdentificadorUnico() { return identificadorUnico; }

    // Devolve as credenciais
    public Credenciais getCredenciais() { return credenciais; }

    // ---------- original – mantido ----------
    // Adiciona uma inscrição (curso que o formador leciona)
    public void adicionarInscricao(Inscricao inscricao) { inscricoes.add(inscricao); }

    // Devolve a lista de inscrições do formador
    public List<Inscricao> getInscricoes() { return inscricoes; }

    // Devolve todos os dados do formador em texto formatado
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