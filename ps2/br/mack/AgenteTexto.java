package br.mack;

public class AgenteTexto extends AgenteIA {
    private static final int LIMITE_CONTEXTO = 500;

    public AgenteTexto(String nome) {
        super(nome);
    }

    @Override
    public void processarRequisicao(String input)
        throws FalhaProcessamentoAgenteException, PromptInadequadoException, ErroComunicacaoIAException {

        status = "PROCESSING";
        try {
            conectarServidor();

            if (input.length() > LIMITE_CONTEXTO) {
                throw new FalhaProcessamentoAgenteException(
                    "Estouro de contexto: " + input.length() + " caracteres (máximo " + LIMITE_CONTEXTO + ")."
                );
            }

            System.out.println("Agente de Texto [" + nome + "] gerando resposta para: " + input);
        } finally {
            status = "IDLE";
        }
    }
}
