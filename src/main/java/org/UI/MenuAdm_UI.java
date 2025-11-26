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
public class MenuAdm_UI
{
    private final Empresa empresa;   // Referência à camada de modelo (empresa)
    private String opcao;           // Opção escolhida pelo utilizador

    // Construtor: recebe a instância da empresa para partilhar dados
    public MenuAdm_UI(Empresa empresa)
    {
        this.empresa = empresa;
    }

    // Executa o menu administrativo em loop até o utilizador escolher "Voltar"
    public void run() throws IOException
    {
        do
        {
            // Cabeçalho do menu
            System.out.println("###### MENU #####\n\n");
            System.out.println("1. Registar Coordenador Académico");

            System.out.println("0. Voltar");

            // Lê a opção do utilizador
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            // Processa a opção 1 – Registar Coordenador Académico
            if( opcao.equals("1") )
            {
                System.out.println("Selecionou a opção: Registar Coordenador Académico");
                RegistarCA_UI ui = new RegistarCA_UI(empresa);
                ui.run();   // Inicia a UI de registo do CA
            }

        }
        while (!opcao.equals("0") );   // Sai do loop apenas ao escolher "0"
    }
}