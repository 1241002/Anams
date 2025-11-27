package org.Controller;

import org.Model.*;
import org.Utils.Data;
import java.util.List;

public class AdicionarModuloController {

    private final Empresa empresa;
    private final RegistoCursos registoCursos;

    // Variáveis de estado para guardar o contexto durante a execução
    private Curso cursoSelecionado;
    private Modulo moduloEmConstrucao;

    public AdicionarModuloController(Empresa empresa) {
        this.empresa = empresa;
        // Obtém o registo através da Empresa (Facade)
        this.registoCursos = empresa.getRegistoCursos();
    }

    // Passo 1: Listar Cursos (Filtrado por estado "A iniciar")
    public List<Curso> obterListaCursos() {
        return registoCursos.getCursosPorEstado("A iniciar");
    }

    // Passo 2: Selecionar Curso
    public boolean selecionarCurso(String sigla) {
        this.cursoSelecionado = registoCursos.getCurso(sigla);
        return this.cursoSelecionado != null;
    }

    // Passo 3: Criar Módulo (Factory no Registo)
    public void criarModulo(String titulo, int cargaHoraria, Data dataInicio,
                            Data dataFim, String horario, Formador formador, double ponderacao) {

        // Factory Method: O Registo cria o objeto
        this.moduloEmConstrucao = registoCursos.novoModulo();

        // Define os dados
        this.moduloEmConstrucao.setTitulo(titulo);
        this.moduloEmConstrucao.setCargaHoraria(cargaHoraria);
        this.moduloEmConstrucao.setDataInicio(dataInicio);
        this.moduloEmConstrucao.setDataConclusao(dataFim);
        this.moduloEmConstrucao.setHorario(horario);
        this.moduloEmConstrucao.setFormador(formador);
        this.moduloEmConstrucao.setPonderacao(ponderacao);

        // Gera código automático
        this.moduloEmConstrucao.setCodigo(empresa.gerarCodigoModulo());
    }

    // Passo 4: Adicionar Sessão
    public void adicionarSessao(Data data, int duracao, String localizacao) {
        if (this.moduloEmConstrucao != null) {
            // Factory Method para sessão
            Sessao sessao = registoCursos.novaSessao();

            sessao.setDados(data, duracao, localizacao);

            this.moduloEmConstrucao.addSessao(sessao);
        }
    }

    // Passo 5: Confirmar e Gravar
    public boolean confirmarRegisto() {
        // Verifica se temos curso e módulo instanciados
        if (cursoSelecionado != null && moduloEmConstrucao != null) {

            // 1. Validar o módulo (ex: verificar se tem as 3 sessões obrigatórias)
            if (moduloEmConstrucao.valida()) {
                // 2. Adicionar o módulo ao curso
                return cursoSelecionado.adicionarModulo(moduloEmConstrucao);
            }
        }
        return false;
    }

    // Auxiliares para a UI
    public List<Formador> obterFormadores() {
        return empresa.obterListaFormadores();
    }

    public String getDadosModulo() {
        return moduloEmConstrucao != null ? moduloEmConstrucao.toString() : "";
    }
}