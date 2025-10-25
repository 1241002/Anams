package org.UI;

import org.Controller.RegistarCursoController;
import org.Model.*;
import org.Utils.Utils;
import org.Utils.Data;
import java.io.IOException;
import java.util.List;

/**
 * Interface de utilizador para registo de um curso com múltiplos módulos.
 * Permite ao Coordenador Académico criar um curso completo com dados gerais
 * e adicionar módulos com formadores associados.
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class RegistarCursoUI {

    private final RegistarCursoController ctrl;   // Controlador que gere a lógica de registo

    /**
     * Construtor: recebe a instância da empresa e cria o controlador associado.
     *
     * @param empresa Instância partilhada do modelo (Empresa)
     */
    public RegistarCursoUI(Empresa empresa) {
        ctrl = new RegistarCursoController(empresa);
    }

    /**
     * Executa o fluxo completo de registo de curso:
     * - Dados gerais do curso
     * - Adição de um ou mais módulos
     * - Confirmação e registo final
     *
     * @throws IOException Em caso de erro de leitura do input
     */
    public void run() throws IOException {
        System.out.println("\n### Registar Curso com Módulos ###");

        // 1. Inicia um novo curso em construção
        ctrl.novoCurso();

        // 2. Lê os dados gerais do curso
        String titulo = Utils.readLineFromConsole("Título do curso: ");
        String sigla = Utils.readLineFromConsole("Sigla: ");
        String descricao = Utils.readLineFromConsole("Descrição: ");

        // 3. Seleciona o tipo de curso da lista disponível
        List<TipoCurso> tipos = ctrl.getTiposCurso();
        if (tipos.isEmpty()) {
            System.out.println("Erro: Nenhum tipo de curso registado. Registe primeiro um tipo.");
            return;
        }
        int idx = 0;
        for (TipoCurso t : tipos) System.out.println(++idx + ". " + t.getDescricao());
        int escTipo = Integer.parseInt(Utils.readLineFromConsole("Tipo de curso (nº): ")) - 1;
        if (escTipo < 0 || escTipo >= tipos.size()) {
            System.out.println("Seleção inválida.");
            return;
        }
        TipoCurso tipo = tipos.get(escTipo);

        // 4. Lê as datas do curso
        Data dataI = Utils.readDataFromConsole("Data início (dd-MM-yyyy): ");
        Data dataF = Utils.readDataFromConsole("Data fim (dd-MM-yyyy): ");
        ctrl.setDadosCurso(titulo, sigla, tipo, descricao, dataI, dataF);

        // 5. Ciclo para adicionar módulos (um ou mais)
        boolean adicionarMais = true;
        while (adicionarMais) {
            ctrl.iniciarNovoModulo();   // Prepara novo módulo vazio

            String titMod = Utils.readLineFromConsole("Título do módulo: ");
            int ch = Integer.parseInt(Utils.readLineFromConsole("Carga horária: "));
            Data modI = Utils.readDataFromConsole("Data início do módulo (dd-MM-yyyy): ");
            Data modF = Utils.readDataFromConsole("Data fim do módulo (dd-MM-yyyy): ");
            String hora = Utils.readLineFromConsole("Horário (ex: Seg 14h-16h): ");

            // Seleciona formador
            List<Formador> formadores = ctrl.getFormadores();
            if (formadores.isEmpty()) {
                System.out.println("Aviso: Nenhum formador disponível. Módulo não será adicionado.");
                break;
            }
            int fIdx = 0;
            for (Formador f : formadores) System.out.println(++fIdx + ". " + f.getNome() + " (" + f.getAreaEspecial() + ")");
            int escForm = Integer.parseInt(Utils.readLineFromConsole("Formador (nº): ")) - 1;
            if (escForm < 0 || escForm >= formadores.size()) {
                System.out.println("Seleção inválida. Módulo não adicionado.");
                continue;
            }
            Formador formador = formadores.get(escForm);

            // Define dados do módulo e adiciona ao curso
            ctrl.setDadosModulo(titMod, ch, modI, modF, hora, formador);
            if (ctrl.adicionarModuloAtual()) {
                System.out.println("Módulo adicionado com sucesso.");
            } else {
                System.out.println("Erro ao adicionar módulo.");
            }

            // Pergunta se quer adicionar outro
            adicionarMais = Utils.confirma("Adicionar outro módulo? (S/N)");
        }

        // 6. Mostra resumo do curso antes de confirmar
        System.out.println("\nDados do curso:\n" + ctrl.getCursoAsString());

        // 7. Confirmação final do registo
        if (Utils.confirma("Confirma o registo do curso? (S/N)")) {
            if (ctrl.registaCurso()) {
                System.out.println("Curso registado com sucesso!");
            } else {
                System.out.println("Erro: o curso precisa de pelo menos 1 módulo.");
            }
        } else {
            System.out.println("Registo cancelado.");
        }
    }
}