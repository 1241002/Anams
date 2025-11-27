package org.Model;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

    // === ESTRUTURAS ANTIGAS (Mantidas para compatibilidade) ===
    private final List<TipoCurso> lstTiposCurso;
    private final List<CoordenadorAcademico> lstCA;
    private final List<Formador> lstFormadores;
    // private final List<Matricula> lstMatriculas; // Podes remover esta se apagaste a classe Matricula

    // === NOVOS REGISTOS (Para a nova lógica UC9-UC15) ===
    private final RegistoCursos registoCursos;
    private final RegistoAlunos registoAlunos;

    // === ESTRUTURAS PARA UC7 e UC8 (Candidaturas) ===
    private final List<Candidato> candidatos;
    private final ServiceEmail serviceEmail;

    // === CONSTRUTOR ÚNICO (Inicializa TUDO aqui) ===
    public Empresa() {
        // Inicialização das listas legadas
        this.lstTiposCurso = new ArrayList<>();
        this.lstCA = new ArrayList<>();
        this.lstFormadores = new ArrayList<>();
        // this.lstMatriculas = new ArrayList<>();

        // Inicialização dos novos registos
        this.registoCursos = new RegistoCursos();
        this.registoAlunos = new RegistoAlunos();

        // Inicialização UC7/UC8
        this.candidatos = new ArrayList<>();
        this.serviceEmail = new ServiceEmail();
    }

    // ============================================================
    // === MÉTODOS DOS NOVOS REGISTOS (UC9, 10, 11, 12, 15) ===
    // ============================================================
    public RegistoCursos getRegistoCursos() {
        return registoCursos;
    }

    public RegistoAlunos getRegistoAlunos() {
        return registoAlunos;
    }

    // Ponte para Alunos
    public void addAluno(Aluno a) {
        registoAlunos.addAluno(a);
    }

    public List<Aluno> getAlunos() {
        return registoAlunos.getAlunos();
    }

    // Ponte para Cursos
    public List<Curso> getCursos() {
        return registoCursos.getCursos();
    }

    public List<Curso> obterListaCursos() {
        return registoCursos.getCursos();
    }

    public void addCurso(Curso c) {
        registoCursos.addCurso(c);
    }

    // ============================================================
    // === MÉTODOS DO UC7 e UC8 (Candidaturas) ===
    // ============================================================

    // UC7: Criar nova candidatura vazia
    public Candidato novaCandidatura() {
        return new Candidato();
    }

    // UC7: Registar candidatura, gerar credenciais e enviar email
    public boolean registarCandidatura(Candidato candidato) {
        if (!candidato.validar()) {
            return false;
        }

        // Gera credenciais
        String login = candidato.getEmail().toLowerCase();
        String password = "senha" + (candidatos.size() + 1);
        Credenciais creds = new Credenciais(login, password);

        candidato.setCredenciais(creds);

        // Envia email simulado
        serviceEmail.sendEmail(candidato.getEmail(), "Bem-vindo! Credenciais: " + creds.toString());

        // Guarda na lista
        candidatos.add(candidato);

        return true;
    }

    // UC8: Obter candidaturas pendentes
    public List<Candidato> getCandidaturasPendentes() {
        List<Candidato> pendentes = new ArrayList<>();
        for (Candidato c : candidatos) {
            if (c.getEstado() == EstadoMatricula.PENDENTE) {
                pendentes.add(c);
            }
        }
        return pendentes;
    }

    // UC8: Registar Decisão (Aceitar/Rejeitar)
    public void registarDecisao(Candidato candidato, int estadoDecisao, String justificacao) {
        // 1. Atualizar o estado do candidato
        candidato.setEstado(estadoDecisao);
        candidato.setJustificacao(justificacao);

        // 2. Lógica Crítica: Se for ACEITE, criar o Aluno
        if (estadoDecisao == EstadoMatricula.ACEITE) {
            // Criar novo aluno
            Aluno novoAluno = registoAlunos.novoAluno();

            // Copiar dados do Candidato para o Aluno
            novoAluno.setNome(candidato.getNome());
            novoAluno.setEmail(candidato.getEmail());
            novoAluno.setCc(candidato.getCc());

            // Adicionar ao Registo de Alunos (Faltava isto!)
            registoAlunos.addAluno(novoAluno);

            System.out.println("[Sistema] Novo aluno criado e registado: " + novoAluno.getNome());
        }

        // 3. Enviar notificação
        String msg = (estadoDecisao == EstadoMatricula.ACEITE)
                ? "Parabéns! A sua candidatura foi ACEITE. Já é aluno."
                : "Candidatura REJEITADA. Motivo: " + justificacao;

        serviceEmail.sendEmail(candidato.getEmail(), msg);

    }

    // ============================================================
    // === MÉTODOS LEGADOS / AUXILIARES (Compatibilidade) ===
    // ============================================================

    public String gerarCodigoModulo() {
        return "MOD-" + (System.currentTimeMillis() % 10000);
    }

    public boolean registaModulo(Curso c, Modulo m) {
        return c.adicionarModulo(m);
    }

    public Formador novoFormador() { return new Formador(); }

    public boolean registaFormador(Formador f) {
        if (!lstFormadores.contains(f)) {
            lstFormadores.add(f);
            return true;
        }
        return false;
    }

    public List<Formador> obterListaFormadores() { return new ArrayList<>(lstFormadores); }

    public Formador getFormadorPorNome(String nome) {
        for(Formador f : lstFormadores) {
            if(f.getNome().equalsIgnoreCase(nome)) return f;
        }
        return null;
    }

    public TipoCurso novoTipoCurso() { return new TipoCurso(); }

    public boolean especificarTipoCurso(TipoCurso tc) {
        if (tc.valida()) { lstTiposCurso.add(tc); return true; }
        return false;
    }

    public List<TipoCurso> obterListaTiposCurso() { return new ArrayList<>(lstTiposCurso); }

    public CoordenadorAcademico novoCA() { return new CoordenadorAcademico(); }

    public boolean registaCA(CoordenadorAcademico ca) {
        if (ca.valida()) { lstCA.add(ca); return true; }
        return false;
    }

    public void enviarCredenciaisPorEmail(Object destinatario) {
        System.out.println("Credenciais enviadas.");
    }

    // UC6: Filtrar Cursos por Estado (Ponte)
    public List<Curso> filtrarCursosPorEstado(int indice) {
        if (EstadoCurso.NOMES == null || indice < 0 || indice >= EstadoCurso.NOMES.length) {
            return new ArrayList<>();
        }
        String nomeEstado = EstadoCurso.NOMES[indice];
        return registoCursos.getCursosPorEstado(nomeEstado);
    }
}