package org.UI;

import java.io.IOException;
import org.Utils.Utils;
import org.Model.Empresa;

public class MenuCA_UI
{
    private final Empresa empresa;
    private String opcao;

    public MenuCA_UI(Empresa empresa)
    {
        this.empresa = empresa;
    }

    public void run() throws IOException
    {
        do
        {
            System.out.println("\n###### MENU COORDENADOR ACADÉMICO #####\n");
            System.out.println("1. Especificar tipo de curso");
            System.out.println("2. Registar formador");
            System.out.println("3. Registar curso");
            System.out.println("4. Adicionar módulo a um curso");
            System.out.println("5. Tomar decisão sobre candidatura"); // UC8
            System.out.println("6. Consultar cursos por estado");     // UC6 (Faltava esta linha!)
            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1"))
            {
                EspecificarTipoCurso_UI ui = new EspecificarTipoCurso_UI(empresa);
                ui.run();
            }
            else if (opcao.equals("2"))
            {
                RegistarFormadorUI ui = new RegistarFormadorUI(empresa);
                ui.run();
            }
            else if (opcao.equals("3"))
            {
                RegistarCursoUI ui = new RegistarCursoUI(empresa);
                ui.run();
            }
            else if (opcao.equals("4"))
            {
                AdicionarModulo_UI ui = new AdicionarModulo_UI(empresa);
                ui.run();
            }
            else if (opcao.equals("5"))
            {
                // CORRETO: Usa a nova UI de Validação de Candidatura (Candidato -> Aluno)
                ValidarCandidatura_UI ui = new ValidarCandidatura_UI(empresa);
                ui.run();
            }
            else if (opcao.equals("6"))
            {
                // CORRETO: Usa a UI de Consulta por Estado
                ConsultarCursosPorEstado_UI ui = new ConsultarCursosPorEstado_UI(empresa);
                ui.run();
            }

        }
        while (!opcao.equals("0"));
    }
}