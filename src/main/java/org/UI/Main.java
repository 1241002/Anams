package org.UI;

import org.Model.*;
import org.Utils.Data;

/**
 * Classe de arranque da aplicação com Bootstrap de Dados
 */
public class Main {

    public static void main(String[] args) {
        try {
            // 1. Construção da empresa
            Empresa empresa = new Empresa();

            // 2. Carregar dados fictícios para teste (Bootstrap)
            carregarDadosExemplo(empresa);

            // 3. Iniciar o Menu
            MenuInicial_UI uiMenu = new MenuInicial_UI(empresa);
            uiMenu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método auxiliar para povoar o sistema com dados iniciais.
     * Cobre TODOS os cenários para os UCs 1 a 15.
     */
    private static void carregarDadosExemplo(Empresa empresa) {
        System.out.println(">>> A carregar dados de exemplo...");

        // === 1. TIPOS DE CURSO e ATORES ===
        TipoCurso tc1 = new TipoCurso("INF", "Informática");
        TipoCurso tc2 = new TipoCurso("GES", "Gestão");
        empresa.especificarTipoCurso(tc1);
        empresa.especificarTipoCurso(tc2);

        // Formadores
        Formador f1 = new Formador();
        f1.setNome("João Silva");
        f1.setEmail("joao@email.com");
        empresa.registaFormador(f1);

        Formador f2 = new Formador();
        f2.setNome("Maria Santos");
        f2.setEmail("maria@email.com");
        empresa.registaFormador(f2);

        // Coordenador
        CoordenadorAcademico ca = new CoordenadorAcademico();
        ca.setNome("Admin Coordenador");
        ca.setEmail("admin@isep.ipp.pt");
        ca.setSigla("ADM");
        empresa.registaCA(ca);

        // === 2. CURSOS (Vários Estados para UC6 e UC13) ===

        // Curso 1: "A iniciar" (Ideal para UC5-Adicionar Módulos e UC9-Inscrição)
        Curso c1 = empresa.getRegistoCursos().novoCurso();
        c1.setTitulo("Engenharia de Software");
        c1.setSigla("LEI");
        c1.setTipo(tc1);
        c1.setEstado("A iniciar");
        c1.setDataInicio(new Data(2024, 1, 10));
        c1.setDataFim(new Data(2024, 6, 20));
        c1.setResponsavel(f1);

        Modulo m1 = empresa.getRegistoCursos().novoModulo();
        m1.setTitulo("Programação Java");
        m1.setCargaHoraria(50);
        m1.setPonderacao(60);
        m1.setFormador(f1);
        m1.addSessao(new Sessao(new Data(2024, 1, 12), 2, "B203"));
        m1.addSessao(new Sessao(new Data(2024, 1, 14), 2, "B203"));
        m1.addSessao(new Sessao(new Data(2024, 1, 16), 2, "B203"));
        c1.adicionarModulo(m1);
        empresa.getRegistoCursos().addCurso(c1);

        // Curso 2: "Ativo" (Ideal para UC14-Notas, UC15-Lançar, UC10-Anular)
        Curso c2 = empresa.getRegistoCursos().novoCurso();
        c2.setTitulo("Redes de Computadores");
        c2.setSigla("LRC");
        c2.setTipo(tc1);
        c2.setEstado("Ativo");
        c2.setResponsavel(f2);

        Modulo m2 = empresa.getRegistoCursos().novoModulo();
        m2.setTitulo("Fundamentos de Redes");
        m2.setCargaHoraria(40);
        m2.setPonderacao(100);
        m2.setFormador(f2);
        m2.addSessao(new Sessao(new Data(2023, 9, 1), 4, "Lab 1"));
        m2.addSessao(new Sessao(new Data(2023, 9, 2), 4, "Lab 1"));
        m2.addSessao(new Sessao(new Data(2023, 9, 3), 4, "Lab 1"));
        c2.adicionarModulo(m2);
        empresa.getRegistoCursos().addCurso(c2);

        // Curso 3: "Concluído" (Para testar filtros do UC6)
        Curso c3 = empresa.getRegistoCursos().novoCurso();
        c3.setTitulo("Gestão de Projetos");
        c3.setSigla("GPROJ");
        c3.setTipo(tc2);
        c3.setEstado("Concluído");
        c3.setResponsavel(f1);
        empresa.getRegistoCursos().addCurso(c3);

        // === 3. CANDIDATURAS (Para UC8 - Validar) ===
        Candidato cand1 = empresa.novaCandidatura();
        cand1.setDados("Carlos Candidato", "2000-01-01", "12º Ano", "carlos@mail.com", "99999999", "M");
        empresa.registarCandidatura(cand1);
        // O Carlos fica no estado PENDENTE para tu o aceitares no UC8

        // === 4. ALUNOS JÁ ACEITES (Para UC9, UC10, UC14) ===
        Aluno a1 = new Aluno();
        a1.setNome("Ana Pereira");
        a1.setEmail("ana@aluno.ipp.pt");
        a1.setCc("12345678");
        empresa.getRegistoAlunos().addAluno(a1);

        Aluno a2 = new Aluno();
        a2.setNome("Bruno Costa");
        a2.setEmail("bruno@aluno.ipp.pt");
        a2.setCc("87654321");
        empresa.getRegistoAlunos().addAluno(a2);

        // === 5. INSCRIÇÕES E NOTAS ===
        // Bruno está inscrito em Redes (Ativo) e já tem nota
        empresa.getRegistoCursos().registarInscricao(c2, a2);
        Inscricao inscBruno = a2.getInscricoes().get(0);
        empresa.getRegistoCursos().registarNota(m2, inscBruno, 16.5);

        // Ana também está inscrita em Redes mas ainda não tem nota (para o Formador lançar no UC15)
        empresa.getRegistoCursos().registarInscricao(c2, a1);

        System.out.println(">>> DADOS CARREGADOS:");
        System.out.println("  - Candidato Pendente: 'Carlos Candidato' (Teste UC8)");
        System.out.println("  - Curso A Iniciar: 'LEI' (Teste UC5, UC9)");
        System.out.println("  - Curso Ativo: 'LRC' (Teste UC10, UC14, UC15)");
        System.out.println("  - Aluno com Nota: 'Bruno Costa' (16.5)");
        System.out.println("  - Aluno sem Nota: 'Ana Pereira' (Teste UC15)\n");
    }
}