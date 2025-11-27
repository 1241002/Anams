/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.UI;

import java.io.IOException;

import org.Utils.Utils;
import org.Model.Empresa;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuCad_UI
{
    private final Empresa empresa;   // Referência à camada de modelo (empresa)
    private String opcao;           // Opção escolhida pelo utilizador

    // Construtor: recebe a instância da empresa para partilhar dados
    public MenuCad_UI(Empresa empresa)
    {
        this.empresa = empresa;
    }

    // Executa o menu do candidato em loop até o utilizador escolher "Voltar"
    public void run() throws IOException
    {
        do
        {
            // Cabeçalho do menu
            System.out.println("###### MENU #####\n\n");
            System.out.println("1. Fazer matrícula na Instituição");

            System.out.println("0. Voltar");

            // Lê a opção do utilizador
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            // Processa a opção 1 – Fazer matrícula na instituição
            if (opcao.equals("1"))
            {
                System.out.println("Selecionou a opção: Fazer matrícula na Instituição");
                SubmeterMatricula_UI ui = new SubmeterMatricula_UI(empresa);
                ui.run();   // Inicia a UI de submissão de matrícula
            }

        }
        while (!opcao.equals("0"));   // Sai do loop apenas ao escolher "0"
    }
}