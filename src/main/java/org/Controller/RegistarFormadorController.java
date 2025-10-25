package org.Controller;

import org.Model.Empresa;
import org.Model.Formador;

import java.util.List;

public class RegistarFormadorController {
    private final Empresa empresa;
    private Formador formador;

    public Formador getFormadorEmConstrucao() {
        return formador;
    }

    public RegistarFormadorController(Empresa empresa) {
        this.empresa = empresa;
    }

    public void novoFormador() {
        formador = empresa.novoFormador();
    }

    public void setDados(String nome, String dataNascimento, String cc, String email, String contacto, String areaEspecial) {
        formador.setNome(nome);
        formador.setDataNascimento(dataNascimento);
        formador.setCc(cc);
        formador.setEmail(email);
        formador.setContacto(contacto);
        formador.setAreaEspecial(areaEspecial);
    }

    public boolean registaFormador() {
        return empresa.registaFormador(formador);
    }

    public String getFormadorAsString() {
        return formador.toString();
    }

    public List<Formador> listaFormadores() {
        return empresa.obterListaFormadores();
    }
}