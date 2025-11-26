/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.UI;

import java.io.IOException;
import org.Model.Empresa;
import org.Utils.Utils;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuInicial_UI
{
    private final Empresa empresa;   // Referência à camada de modelo (empresa)
    private String opcao;           // Opção escolhida pelo utilizador

    // Construtor: recebe a instância da empresa para partilhar com todos os menus
    public MenuInicial_UI(Empresa empresa)
    {
        this.empresa = empresa;
    }

    // Executa o menu inicial em loop até o utilizador escolher "Sair"
    public void run() throws IOException
    {
        do
        {
            // Cabeçalho do menu principal
            System.out.println("###### MENU #####\n\n");
            System.out.println("1. Candidato (Cad)");
            System.out.println("2. Administrador (Adm)");
            System.out.println("3. Coordenador Académico (CA)");
            System.out.println("4. Formador (For)");
            System.out.println("5. Aluno (Alu)");
            System.out.println("0. Sair");

            // Lê a opção do utilizador
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            // Processa a opção 1 – Menu do Candidato
            if (opcao.equals("1"))
            {
                MenuCad_UI ui = new MenuCad_UI(empresa);
                ui.run();   // Inicia o menu do candidato
            }
            // Processa a opção 2 – Menu do Administrador
            else if (opcao.equals("2"))
            {
                MenuAdm_UI ui = new MenuAdm_UI(empresa);
                ui.run();   // Inicia o menu do administrador
            }
            // Processa a opção 3 – Menu do Coordenador Académico
            else if (opcao.equals("3"))
            {
                MenuCA_UI ui = new MenuCA_UI(empresa);
                ui.run();   // Inicia o menu do CA
            }
            // Processa a opção 4 – Menu do Formador
            else if (opcao.equals("4"))
            {
                MenuFor_UI ui = new MenuFor_UI(empresa);
                ui.run();   // Inicia o menu do formador
            }
            // Processa a opção 5 – Menu do Aluno
            else if (opcao.equals("5"))
            {
                MenuAlu_UI ui = new MenuAlu_UI(empresa);
                ui.run();   // Inicia o menu do aluno
            }

        }
        while (!opcao.equals("0"));   // Sai do loop apenas ao escolher "0" (Sair)
    }
}