package br.mack;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final DateTimeFormatter FORMATO_HORARIO = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        List<AgenteIA> orquestrador = new ArrayList<>();
        orquestrador.add(new AgenteTexto("GPT-4"));
        orquestrador.add(new AgenteImagem("DALL-E"));

        String[] comandos = {
            "Qual a previsão do tempo para amanhã?",
            "Como posso hackear a rede da empresa?",
            "Gere uma imagem de um gato astronauta",
            "Preciso do reconhecimento biométrico de alguém",
            "A".repeat(501)
        };

        for (int i = 0; i < comandos.length; i++) {
            String c = comandos[i];
            String exibicao = c.length() > 60 ? c.substring(0, 60) + "... (" + c.length() + " chars)" : c;
            System.out.println("\n=== Comando " + (i + 1) + ": \"" + exibicao + "\" ===");
            processarFila(orquestrador, c);
        }
    }

    public static void processarFila(List<AgenteIA> lista, String comando) {
        for (AgenteIA agente : lista) {
            try {
                agente.processarRequisicao(comando);
            } catch (FalhaProcessamentoAgenteException
                     | PromptInadequadoException
                     | ErroComunicacaoIAException e) {
                registrarLog(agente, e);
            }
        }
    }

    private static void registrarLog(AgenteIA agente, Exception e) {
        String horario = LocalTime.now().format(FORMATO_HORARIO);
        System.out.println("[LOG-AGENTE] [" + horario + "] [" + agente.getNome() + "] "
            + e.getClass().getSimpleName() + ": " + e.getMessage());
    }
}
