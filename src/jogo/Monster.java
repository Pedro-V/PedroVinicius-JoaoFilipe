package jogo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Monster {
    private int pontos_vida, pontos_dano;
    private Random gerador = new Random();

    // Todos monstros começam com 3 de HP e 1 de dano;
    public Monster() {
        this.pontos_dano = 1;
        this.pontos_vida = 3;
    }

    /**
     * Overloading do construtor: Se um boolean for informado, é assumido
     * que se trata de um boss
     * 
     * @param is_boos: Booleano representando se está instanciando um chefe
     */
    public Monster(boolean is_boss) {
        pontos_dano = 2;
        pontos_vida = 8;
    }

    // Ataca um jogador, com chance de errar
    public String ataque(Player jogador) {
        System.out.println("O monstro se prepara para atacar...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
        if (gerador.nextBoolean()) {
            jogador.sofre_dano(pontos_dano);
            return "O ataque do monstro te acertou! \uD83D\uDE28";
        }
        return "Você teve sorte. O monstro errou o ataque!";
    }

    // Método que subtrai o dano sofrido dos pontos de vida do monstro
    public void sofre_dano(int dano_sofrido) {
        pontos_vida -= dano_sofrido;
    }

    public int getPontos_dano() {
        return pontos_dano;
    }

    public boolean estaVivo() {
        return pontos_vida > 0;
    }

    public int getPontos_vida() {
        if (pontos_vida < 0) {
            return pontos_vida = 0;
        }
        return pontos_vida;
    }
}
