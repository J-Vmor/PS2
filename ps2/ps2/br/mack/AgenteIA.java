package br.mack;

public class AgenteIA {
    private static final String[] PALAVRAS_PROIBIDAS = {"hackear", "roubar"};

    public void processarPrompt(String prompt) throws FalhaProcessamentoAgenteException {
        if (prompt == null || prompt.isEmpty()) {
            throw new FalhaProcessamentoAgenteException("O prompt não pode estar vazio.");
        }
        if (prompt.length() > 100) {
            throw new FalhaProcessamentoAgenteException("Prompt muito longo para o modelo atual.");
        }
        System.out.println("Agente processando: " + prompt);
    }

    public void verificarSeguranca(String prompt) throws PromptInadequadoException {
        String promptEmMinusculas = prompt.toLowerCase();

        for (String palavra : PALAVRAS_PROIBIDAS) {
            if (promptEmMinusculas.contains(palavra)) {
                throw new PromptInadequadoException(
                    "Prompt rejeitado: contém termo não permitido \"" + palavra + "\"."
                );
            }
        }
    }

    public void chamarModeloExterno() throws ErroComunicacaoIAException {
        double resultado = Math.random();

        if (resultado > 0.7) {
            throw new ErroComunicacaoIAException(
                "Falha de comunicação com o modelo externo (timeout ou rede instável)."
            );
        }

        System.out.println("Chamada ao modelo externo realizada com sucesso.");
    }
}


