package br.mack;

public class ModuloConexao {

    private ModuloConexao() { }

    public static void validarLink() throws ErroComunicacaoIAException {
        if (Math.random() > 0.8) {
            throw new ErroComunicacaoIAException(
                "Falha de comunicação com a GPU (timeout ou rede instável)."
            );
        }
    }
}
