package org.Controller;

import org.Model.*;
import org.Utils.Utils;
import java.util.List;

public class SubmeterMatriculaController {

    private final Empresa empresa;
    private Matricula matricula;

    // Construtor: recebe a empresa para registar e notificar matrículas
    public SubmeterMatriculaController(Empresa empresa) { this.empresa = empresa; }

    // Cria uma nova matrícula vazia (pronta para preencher)
    public void novaMatricula() { matricula = new Matricula(); }

    // Define os dados do aluno na matrícula (nome, nascimento, habilitações, etc.)
    public void setDados(String nome, String dataNasc, String habilitacoes, String email, String cc, String genero) {
        matricula.setNome(nome);
        matricula.setDataDeNascimento(dataNasc);
        matricula.setHabilitacoesAcademicas(habilitacoes);
        matricula.setEmail(email);
        matricula.setCc(cc);
        matricula.setGenero(genero);
    }

    // Gera login (email em minúsculas) e senha aleatória (6 chars)
    public String gerarCredenciais() {
        String login = matricula.getEmail().toLowerCase();
        String senha = Utils.geraPassword(6, 4, 2);
        matricula.setCredenciais(new Credenciais(login, senha));
        return login + " / " + senha;  // devolve "email / senha"
    }

    // Regista a matrícula, envia credenciais por email e notifica o CA
    public boolean confirmarRegisto() {
        if (matricula == null) return false;
        empresa.registaMatricula(matricula);           // guarda na empresa
        empresa.enviarCredenciaisPorEmail(matricula);  // simula envio
        empresa.notificarCA(matricula);                // simula notificação
        return true;
    }

    // Devolve a matrícula como texto (para mostrar na UI)
    public String getMatriculaAsString() { return matricula.toString(); }
}