package br.mack;

public class AgenteImagem extends AgenteIA {
    private static final String[] TERMOS_SENSIVEIS = {"hackear", "roubar", "biométrico"};

    public AgenteImagem(String nome) {
        super(nome);
    }

    @Override
    public void processarRequisicao(String input)
        throws FalhaProcessamentoAgenteException, PromptInadequadoException, ErroComunicacaoIAException {

        status = "PROCESSING";
        try {
            conectarServidor();

            String minusculo = input.toLowerCase();
            for (String termo : TERMOS_SENSIVEIS) {
                if (minusculo.contains(termo)) {
                    throw new PromptInadequadoException(
                        "Prompt rejeitado: contém termo sensível \"" + termo + "\"."
                    );
                }
            }

            System.out.println("Agente de Imagem [" + nome + "] sintetizando pixels para: " + input);
        } finally {
            status = "IDLE";
        }
    }
}
