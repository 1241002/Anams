package org.UI;

import java.io.IOException;

import org.Utils.Utils;
import org.Model.Empresa;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuAlu_UI
{
    private final Empresa empresa;   // Referência à camada de modelo (empresa)
    private String opcao;           // Opção escolhida pelo utilizador

    // Construtor: recebe a instância da empresa para partilhar dados
    public MenuAlu_UI(Empresa empresa)
    {
        this.empresa = empresa;
    }

    // Executa o menu do aluno em loop até o utilizador escolher "Voltar"
    public void run() throws IOException
    {
        do
        {
            // Cabeçalho do menu
            System.out.println("###### MENU #####\n\n");
            System.out.println("1. Fazer inscrição num curso");
            System.out.println("2. Anular inscrição num curso");
            System.out.println("0. Voltar");

            // Lê a opção do utilizador
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            // Processa a opção 1 – Fazer inscrição num curso
            if( opcao.equals("1") )
            {
                InscreverAluno_UI ui = new InscreverAluno_UI(empresa);
                ui.run();   // Inicia a UI de inscrição no curso
                System.out.println("Selecionou a opção: Fazer inscrição num curso");
            }
            // Processa a opção 2 – Anular inscrição num curso
            else if( opcao.equals("2") )
            {
                AnularInscricao_UI ui = new AnularInscricao_UI(empresa);
                ui.run();   // Inicia a UI de anulação de inscrição
                System.out.println("Selecionou a opção: Anular inscrição num curso");
            }

        }
        while (!opcao.equals("0") );   // Sai do loop apenas ao escolher "0"
    }
}