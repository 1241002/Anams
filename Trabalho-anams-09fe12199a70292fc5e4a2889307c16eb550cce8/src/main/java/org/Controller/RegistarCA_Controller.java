package org.Controller;

import org.Model.Empresa;
import org.Model.CoordenadorAcademico;
import org.Model.Credenciais;
import org.Utils.Utils;
public class RegistarCA_Controller {
    private final Empresa empresa;
    private CoordenadorAcademico ca;

    public RegistarCA_Controller(Empresa empresa) { this.empresa = empresa; }

    public void novoCA() { ca = empresa.novoCA(); }

    public void setDados(String sigla, String nome, String cc, String email, String contacto) {
        ca.setSigla(sigla);
        ca.setNome(nome);
        ca.setCc(cc);
        ca.setEmail(email);
        ca.setContacto(contacto);
    }

    public String geraCredenciais() {
        String login = ca.getSigla().toLowerCase();
        String password = Utils.geraPassword(5, 3, 2);   // novo helper
        ca.setCredenciais(new Credenciais(login, password));
        return login + " / " + password;
    }

    public boolean registaCA() {
        boolean ok = empresa.registaCA(ca);
        if (ok) empresa.enviarCredenciaisPorEmail(ca);
        return ok;
    }

    public String getCAAsString() { return ca.toString(); }
}