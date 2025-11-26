package org.Controller;

import org.Model.Empresa;
import org.Model.Formador;

import java.util.List;

public class RegistarFormadorController {
    private final Empresa empresa;
    private Formador formador;

    // Devolve o formador que está a ser criado (para UI mostrar dados parciais)
    public Formador getFormadorEmConstrucao() {
        return formador;
    }

    // Construtor: recebe a empresa para criar e registar formadores
    public RegistarFormadorController(Empresa empresa) {
        this.empresa = empresa;
    }

    // Cria um novo formador vazio (pronto para preencher)
    public void novoFormador() {
        formador = empresa.novoFormador();
    }

    // Define todos os dados do formador (nome, nascimento, CC, email, contacto, área)
    public void setDados(String nome, String dataNascimento, String cc, String email, String contacto, String areaEspecial) {
        formador.setNome(nome);
        formador.setDataNascimento(dataNascimento);
        formador.setCc(cc);
        formador.setEmail(email);
        formador.setContacto(contacto);
        formador.setAreaEspecial(areaEspecial);
    }

    // Regista o formador na empresa
    public boolean registaFormador() {
        // Gera ID único FOR-0001, FOR-0002, ...
        int seq = empresa.obterListaFormadores().size() + 1;
        String id = String.format("FOR-%04d", seq);
        formador.setIdentificadorUnico(id);
        return empresa.registaFormador(formador);
    }

    // Devolve o formador como texto (para mostrar na UI)
    public String getFormadorAsString() {
        return formador.toString();
    }

    // Devolve a lista completa de formadores já registados
    public List<Formador> listaFormadores() {
        return empresa.obterListaFormadores();
    }
}