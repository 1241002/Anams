package org.Controller;

import org.Model.*;
import java.util.List;

public class AdicionarModuloController {

    private final Empresa empresa;
    private Curso cursoSelecionado;
    private Modulo modulo;

    public AdicionarModuloController(Empresa empresa) { this.empresa = empresa; }

    public List<Curso> obterCursos() { return empresa.obterListaCursos(); }
    public List<Formador> obterFormadores() { return empresa.obterListaFormadores(); }

    public void selecionarCurso(int idx) {
        List<Curso> c = obterCursos();
        if (idx >= 0 && idx < c.size()) cursoSelecionado = c.get(idx);
    }

    public void criarModulo(String titulo, int ch, String dataI, String dataF,
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

    /* ---- única verificação: sobreposição de horário ---- */
    public boolean dadosOk() {
        return !empresa.existeSobreposicaoHorario(modulo.getFormadorResponsavel(), modulo.getHorario());
    }

    public boolean confirmarRegisto() {
        if (cursoSelecionado == null || modulo == null) return false;
        return empresa.registaModulo(cursoSelecionado, modulo);
    }

    public String getDadosModulo() { return modulo.toString(); }
}