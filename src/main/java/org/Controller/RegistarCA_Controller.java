package org.Controller;

import org.Model.Empresa;
import org.Model.CoordenadorAcademico;
import org.Model.Credenciais;
import org.Utils.Utils;

public class RegistarCA_Controller {
    private final Empresa empresa;
    private CoordenadorAcademico ca;

    //// Construtor: recebe a empresa para criar e registar o CA
    public RegistarCA_Controller(Empresa empresa) { this.empresa = empresa; }

    // Cria um novo Coordenador Académico vazio
    public void novoCA() { ca = empresa.novoCA(); }

    // Define os dados do coordenador (sigla, nome, CC, email, contacto)
    public void setDados(String sigla, String nome, String cc, String email, String contacto) {
        ca.setSigla(sigla);
        ca.setNome(nome);
        ca.setCc(cc);
        ca.setEmail(email);
        ca.setContacto(contacto);
    }

    // Gera login (sigla em minúsculas) e senha (5 chars: 3 letras + 2 dígitos)
    public String geraCredenciais() {
        String login = ca.getSigla().toLowerCase();
        String password = Utils.geraPassword(5, 3, 2);
        ca.setCredenciais(new Credenciais(login, password));
        return login + " / " + password;  // devolve "login / senha"
    }

    // Regista o CA na empresa e envia credenciais por email se OK
    public boolean registaCA() {
        boolean ok = empresa.registaCA(ca);
        if (ok) empresa.enviarCredenciaisPorEmail(ca);
        return ok;
    }

    // Devolve o CA atual como texto (para mostrar na UI)
    public String getCAAsString() { return ca.toString(); }
}