package org.Controller;

import org.Model.*;
import org.Utils.Utils;
import java.util.List;

public class SubmeterMatriculaController {

    private final Empresa empresa;
    private Matricula matricula;

    public SubmeterMatriculaController(Empresa empresa) { this.empresa = empresa; }

    public void novaMatricula() { matricula = new Matricula(); }

    public void setDados(String nome, String dataNasc, String habilitacoes, String email, String cc, String genero) {
        matricula.setNome(nome);
        matricula.setDataDeNascimento(dataNasc);
        matricula.setHabilitacoesAcademicas(habilitacoes);
        matricula.setEmail(email);
        matricula.setCc(cc);
        matricula.setGenero(genero);
    }

    public String gerarCredenciais() {
        String login = matricula.getEmail().toLowerCase();
        String senha = Utils.geraPassword(6, 4, 2);   // 6 chars: 4 letras + 2 dígitos
        matricula.setCredenciais(new Credenciais(login, senha));
        return login + " / " + senha;
    }

    public boolean confirmarRegisto() {
        if (matricula == null) return false;
        empresa.registaMatricula(matricula);           // guarda em memória
        empresa.enviarCredenciaisPorEmail(matricula);  // simula email
        empresa.notificarCA(matricula);                // simula notificação
        return true;
    }

    public String getMatriculaAsString() { return matricula.toString(); }
}