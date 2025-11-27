package org.Model;

import java.util.List;

public interface Avaliavel {
    // Recebe a lista de todas as classificações do aluno e calcula a nota relevante
    double calcularNota(List<Classificacao> classificacoes);
}