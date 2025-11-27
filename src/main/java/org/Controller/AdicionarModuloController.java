package org.Controller;

import org.Model.*;
import org.Utils.Data;
import java.util.List;

public class AdicionarModuloController {

    private final Empresa empresa;
    private Curso cursoSelecionado;
    private Modulo modulo;

    // Construtor: recebe a empresa para aceder aos dados
    public AdicionarModuloController(Empresa empresa) { this.empresa = empresa; }

    // Devolve a lista completa de cursos da empresa
    public List<Curso> obterCursos() { return empresa.obterListaCursos(); }

    // Devolve a lista completa de formadores
    public List<Formador> obterFormadores() { return empresa.obterListaFormadores(); }

    // Seleciona um curso da lista pelo índice (0-based)
    public void selecionarCurso(int idx) {
        List<Curso> c = obterCursos();
        if (idx >= 0 && idx < c.size()) cursoSelecionado = c.get(idx);
    }

    // Cria um novo módulo com os dados fornecidos
    public void criarModulo(String titulo, int ch, Data dataI, Data dataF,
                            String horario, Formador formador) {
        modulo = new Modulo();
        modulo.setTitulo(titulo);
        modulo.setCargaHoraria(ch);
        modulo.setDataInicio(dataI);
        modulo.setDataConclusao(dataF);
        modulo.setHorario(horario);
        modulo.setFormadorResponsavel(formador);
        modulo.setCodigo(empresa.gerarCodigoModulo());
    }

    // Verifica se o formador não tem conflito de horário
    public boolean dadosOk() {
        return !empresa.existeSobreposicaoHorario(modulo.getFormadorResponsavel(), modulo.getHorario());
    }

    // Regista o módulo no curso selecionado
    public boolean confirmarRegisto() {
        if (cursoSelecionado == null || modulo == null) return false;
        return empresa.registaModulo(cursoSelecionado, modulo);
    }

    // Devolve a representação em texto do módulo criado
    public String getDadosModulo() { return modulo.toString(); }
}