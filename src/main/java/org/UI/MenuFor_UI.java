package org.UI;

import java.io.IOException;

import org.Utils.Utils;
import org.Model.Empresa;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuFor_UI
{
    private final Empresa empresa;   // Referência à camada de modelo (empresa)
    private String opcao;           // Opção escolhida pelo utilizador

    // Construtor: recebe a instância da empresa para partilhar dados entre UIs
    public MenuFor_UI(Empresa empresa)
    {
        this.empresa = empresa;
    }

    // Executa o menu do formador em loop até o utilizador escolher "Voltar"
    public void run() throws IOException
    {
        do
        {
            // Cabeçalho do menu
            System.out.println("###### MENU #####\n\n");
            System.out.println("1. Consultar lista de cursos (responsável)");
            System.out.println("2. Consultar lista de alunos de um curso");
            System.out.println("3. Registar formador");  // UC3 – Registo de novo formador

            System.out.println("0. Voltar");

            // Lê a opção do utilizador
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            // Processa a opção 1 – Consultar cursos em que é responsável
            if (opcao.equals("1"))
            {
                ConsultarCursosPorEstado_UI ui = new ConsultarCursosPorEstado_UI(empresa);
                ui.run();   // Inicia a UI de consulta de cursos por estado
                System.out.println("Selecionou a opção: Consultar lista de cursos (responsável)");
            }
            // Processa a opção 2 – Consultar alunos de um curso
            else if (opcao.equals("2"))
            {
                ConsultarAlunosCurso_UI ui = new ConsultarAlunosCurso_UI(empresa);
                ui.run();   // Inicia a UI de consulta de alunos por curso
                System.out.println("Selecionou a opção: Consultar lista de alunos de um curso");
            }
            // Processa a opção 3 – Registar novo formador (UC3)
            else if (opcao.equals("3"))
            {
                RegistarFormadorUI ui = new RegistarFormadorUI(empresa);
                ui.run();   // Inicia a UI de registo de formador
            }

        }
        while (!opcao.equals("0"));   // Sai do loop apenas ao escolher "0"
    }
}