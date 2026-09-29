package br.mack;

public class ValidadorAgente {
    private static int passados = 0;

    public static void main(String[] args) {
        System.out.println("=== INICIANDO VALIDAÇÃO DOS AGENTES ===");

        // Retenta em caso de falha de rede aleatória, para o teste ser determinístico
        esperaExcecao("Teste 1 (Texto: contexto > 500)", new AgenteTexto("GPT-4"),
            "A".repeat(501), FalhaProcessamentoAgenteException.class);

        esperaExcecao("Teste 2 (Imagem: termo sensível)", new AgenteImagem("DALL-E"),
            "Como hackear um sistema?", PromptInadequadoException.class);

        esperaSucesso("Teste 3 (Texto: prompt válido)", new AgenteTexto("GPT-4"), "Explique polimorfismo");
        esperaSucesso("Teste 4 (Imagem: prompt válido)", new AgenteImagem("DALL-E"), "Um gato astronauta");

        System.out.println("\n=== RESULTADO FINAL: " + passados + "/4 Testes Passados ===");
    }

    private static void esperaExcecao(String nome, AgenteIA agente, String input,
                                      Class<? extends Exception> esperada) {
        System.out.print(nome + ": ");
        for (int tentativa = 0; tentativa < 20; tentativa++) {
            try {
                agente.processarRequisicao(input);
                System.out.println("❌ FALHA: nenhuma exceção lançada");
                return;
            } catch (ErroComunicacaoIAException e) {
                continue; // rede instável: tenta de novo
            } catch (Exception e) {
                if (esperada.isInstance(e)) {
                    System.out.println("✅ SUCESSO: " + e.getClass().getSimpleName());
                    passados++;
                } else {
                    System.out.println("❌ FALHA: exceção inesperada " + e.getClass().getSimpleName());
                }
                return;
            }
        }
        System.out.println("❌ FALHA: rede instável demais");
    }

    private static void esperaSucesso(String nome, AgenteIA agente, String input) {
        System.out.print(nome + ": ");
        for (int tentativa = 0; tentativa < 20; tentativa++) {
            try {
                agente.processarRequisicao(input);
                System.out.println("✅ SUCESSO");
                passados++;
                return;
            } catch (ErroComunicacaoIAException e) {
                continue;
            } catch (Exception e) {
                System.out.println("❌ FALHA: " + e.getClass().getSimpleName());
                return;
            }
        }
        System.out.println("❌ FALHA: rede instável demais");
    }
}
