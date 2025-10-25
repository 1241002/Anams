package org.UI;

import java.io.IOException;

import org.Utils.Utils;
import org.Model.Empresa;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuCA_UI
{
    private final Empresa empresa;   // Referência à camada de modelo (empresa)
    private String opcao;           // Opção escolhida pelo utilizador

    // Construtor: recebe a instância da empresa para partilhar dados entre UIs
    public MenuCA_UI(Empresa empresa)
    {
        this.empresa = empresa;
    }

    // Executa o menu do Coordenador Académico em loop até o utilizador escolher "Voltar"
    public void run() throws IOException
    {
        do
        {
            // Cabeçalho do menu
            System.out.println("###### MENU #####\n\n");
            System.out.println("1. Especificar tipo de curso");
            System.out.println("2. Registar formador");
            System.out.println("3. Registar curso");
            System.out.println("4. Adicionar módulo a um curso");
            System.out.println("5. Tomar decisão sobre candidatura");

            System.out.println("0. Voltar");

            // Lê a opção do utilizador
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            // Processa a opção 1 – Especificar tipo de curso
            if (opcao.equals("1"))
            {
                EspecificarTipoCurso_UI ui = new EspecificarTipoCurso_UI(empresa);
                ui.run();   // Inicia a UI de especificação de tipo de curso
            }
            // Processa a opção 2 – Registar formador
            else if (opcao.equals("2"))
            {
                System.out.println("Selecionou a opção: Registar formador");
                RegistarFormadorUI ui = new RegistarFormadorUI(empresa);
                ui.run();   // Inicia a UI de registo de formador
            }
            // Processa a opção 3 – Registar curso
            else if (opcao.equals("3"))
            {
                System.out.println("Selecionou a opção: Registar curso");
                RegistarCursoUI ui = new RegistarCursoUI(empresa);
                ui.run();   // Inicia a UI de registo de curso
            }
            // Processa a opção 4 – Adicionar módulo a um curso
            else if (opcao.equals("4"))
            {
                System.out.println("Selecionou a opção: Adicionar módulo a um curso");
                AdicionarModulo_UI ui = new AdicionarModulo_UI(empresa);
                ui.run();   // Inicia a UI de adição de módulo
            }
            // Processa a opção 5 – Tomar decisão sobre candidatura
            else if (opcao.equals("5"))
            {
                System.out.println("Selecionou a opção: Tomar decisão sobre candidatura");
                ValidarMatricula_UI ui = new ValidarMatricula_UI(empresa);
                ui.run();   // Inicia a UI de validação de matrícula
            }
            // Processa a opção 6 – Consultar cursos por estado
            else if (opcao.equals("6"))
            {
                ConsultarCursosPorEstado_UI ui = new ConsultarCursosPorEstado_UI(empresa);
                ui.run();   // Inicia a UI de consulta de cursos por estado
            }

        }
        while (!opcao.equals("0"));   // Sai do loop apenas ao escolher "0"
    }
}