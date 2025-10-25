package org.Model;

public class CoordenadorAcademico {
    private String sigla;
    private String nome;
    private String cc;
    private String email;
    private String contacto;
    private Credenciais credenciais;

    public CoordenadorAcademico() { }

    /* setters – usados pelo controller */
    public void setSigla(String sigla) { this.sigla = sigla; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCc(String cc) { this.cc = cc; }
    public void setEmail(String email) { this.email = email; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public void setCredenciais(Credenciais c) { this.credenciais = c; }

    /* getters – usados na UI */
    public String getSigla() { return sigla; }
    public String getNome() { return nome; }
    public String getCc() { return cc; }
    public String getEmail() { return email; }
    public String getContacto() { return contacto; }
    public Credenciais getCredenciais() { return credenciais; }

    /* validação local */
    public boolean valida() {
        return sigla != null && !sigla.trim().isEmpty() &&
                nome != null && !nome.trim().isEmpty() &&
                cc != null && !cc.trim().isEmpty() &&
                email != null && email.contains("@") &&
                contacto != null && !contacto.trim().isEmpty();
    }

    @Override
    public String toString() {
        return String.format("Sigla: %s\nNome: %s\nCC: %s\nEmail: %s\nContacto: %s\n%s",
                sigla, nome, cc, email, contacto, credenciais);
    }
}