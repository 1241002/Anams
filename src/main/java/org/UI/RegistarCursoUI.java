package org.UI;

import org.Controller.RegistarCursoController;
import org.Model.*;
import org.Utils.Utils;
import org.Utils.Data;
import java.io.IOException;
import java.util.List;

public class RegistarCursoUI {

    private final RegistarCursoController ctrl;

    public RegistarCursoUI(Empresa empresa) {
        ctrl = new RegistarCursoController(empresa);
    }

    public void run() throws IOException {
        System.out.println("\n### Registar Curso com Módulos ###");

        ctrl.novoCurso();

        String titulo = Utils.readLineFromConsole("Título do curso: ");
        String sigla = Utils.readLineFromConsole("Sigla: ");
        String descricao = Utils.readLineFromConsole("Descrição: ");

        List<TipoCurso> tipos = ctrl.getTiposCurso();
        int idx = 0; for (TipoCurso t : tipos) System.out.println(++idx + ". " + t);
        int escTipo = Integer.parseInt(Utils.readLineFromConsole("Tipo de curso (nº): ")) - 1;
        TipoCurso tipo = tipos.get(escTipo);

        Data dataI = Utils.readDataFromConsole("Data início (dd-MM-yyyy): ");
        Data dataF = Utils.readDataFromConsole("Data fim (dd-MM-yyyy): ");
        ctrl.setDadosCurso(titulo, sigla, tipo, descricao, dataI, dataF);

        boolean adicionarMais = true;
        while (adicionarMais) {
            ctrl.iniciarNovoModulo();

            String titMod = Utils.readLineFromConsole("Título do módulo: ");
            int ch = Integer.parseInt(Utils.readLineFromConsole("Carga horária: "));
            Data modI = Utils.readDataFromConsole("Data início do módulo (dd-MM-yyyy): ");
            Data modF = Utils.readDataFromConsole("Data fim do módulo (dd-MM-yyyy): ");
            String hora = Utils.readLineFromConsole("Horário (ex: Seg 14h-16h): ");

            List<Formador> formadores = ctrl.getFormadores();
            int fIdx = 0; for (Formador f : formadores) System.out.println(++fIdx + ". " + f);
            int escForm = Integer.parseInt(Utils.readLineFromConsole("Formador (nº): ")) - 1;
            Formador formador = formadores.get(escForm);

            ctrl.setDadosModulo(titMod, ch, modI, modF, hora, formador);
            ctrl.adicionarModuloAtual();

            adicionarMais = Utils.confirma("Adicionar outro módulo? (S/N)");
        }

        System.out.println("\nDados do curso:\n" + ctrl.getCursoAsString());

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (ctrl.registaCurso()) {
                System.out.println("Curso registado com sucesso!");
            } else {
                System.out.println("Erro: curso precisa de pelo menos 1 módulo.");
            }
        }
    }
}