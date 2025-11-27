package org.UI;

import java.io.IOException;

import org.Utils.Utils;
import org.Model.Empresa;

public class MenuAlu_UI
{
    private final Empresa empresa;
    private String opcao;

    public MenuAlu_UI(Empresa empresa)
    {
        this.empresa = empresa;
    }

    public void run() throws IOException
    {
        do
        {
            System.out.println("\n###### MENU ALUNO #####\n");
            System.out.println("1. Fazer inscrição num curso");
            System.out.println("2. Anular inscrição num curso");
            System.out.println("3. Consultar Classificações"); // === NOVA OPÇÃO (UC14) ===
            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            // Opção 1 – Fazer inscrição (UC9)
            if( opcao.equals("1") )
            {
                InscreverAluno_UI ui = new InscreverAluno_UI(empresa);
                ui.run();
            }
            // Opção 2 – Anular inscrição (UC10)
            else if( opcao.equals("2") )
            {
                AnularInscricao_UI ui = new AnularInscricao_UI(empresa);
                ui.run();
            }
            // Opção 3 – Consultar Classificações (UC14)
            else if( opcao.equals("3") )
            {
                // Chama a nova UI que criámos
                ConsultarClassificacoes_UI ui = new ConsultarClassificacoes_UI(empresa);
                ui.run();
            }

        }
        while (!opcao.equals("0") );
    }
}