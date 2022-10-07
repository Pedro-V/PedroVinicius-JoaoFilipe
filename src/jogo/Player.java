package jogo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Player {
    private int pontos_vida, pontos_dano;
    private Random gerador = new Random();
    private boolean in_combat;
    private boolean possuiBolaFogo;

    public Player() {
        pontos_dano = 1;
        pontos_vida = 10;
        in_combat = false;
        possuiBolaFogo = false;
    }

    public void printStats() {
        System.out.println("Seus pontos de vida: " + pontos_vida + "\u2764\uFE0F");
    }

    public void printStats(Monster monstro) {
        System.out.println("Seus pontos de vida: " + pontos_vida + "\u2764\uFE0F" +
                "\t\tPontos de vida do monstro: " + monstro.getPontos_vida() + " \uD83D\uDC7A" + "\n");
    }

    public void entra_combate(Room sala_atual, boolean usoFeitico) {
        if (sala_atual.temMonstro()) {
            Monster monstro = sala_atual.getMonstro();
            if (!monstro.estaVivo())
                System.out.println(sala_atual.getAttributeDescription());
            else {
                System.out.println(ataque(sala_atual, usoFeitico));
                printStats(monstro);
                // Se o monstro estiver vivo, o monstro da sala tenta atacar
                sala_atual.ataqueMonstro(this);
            }
        } else
            System.out.println("Não há monstros nessa sala.");
    }

    private String ataque(Room sala_atual, boolean usoFeitico) {
        Monster monstro = sala_atual.getMonstro();
        int chance = gerador.nextInt(0, 5);
        in_combat = true;
        String resultado = new String();
        if (usoFeitico) {
            if (!possuiBolaFogo) {
                return "Você não possui o feitiço de bola de fogo ou ele já foi utilizado!";
            } else {
                int dano_bola_fogo = Attribute.BOLAFOGO.getValor_associado();
                monstro.sofre_dano(dano_bola_fogo);
                resultado = "Seu feitiço de bola de fogo acertou o monstro! Ele deu " + dano_bola_fogo + " de dano";
                possuiBolaFogo = false;
            }
        }
        // possível acerto
        else if (chance <= 3) { // 80% de chance de acertar
            // possível crítico
            if (chance == 1) { // 20% de chance de dar critico (considerando que está atacando)
                monstro.sofre_dano(pontos_dano * 2);
                resultado = "Seu ataque foi crítico! Ele deu o dobro de dano (" + pontos_dano * 2 + ")";
            } else {
                monstro.sofre_dano(pontos_dano);
                resultado = "Seu ataque acertou o monstro!";
            }
            if (!monstro.estaVivo()) {
                resultado += "\nO seu ataque matou o monstro. \u2620";
                in_combat = false;
                // Caso se tratar duma sala com o feitiço de bola de fogo
                if (sala_atual.getAtributo().equals(Attribute.BOLAFOGO)) {
                    possuiBolaFogo = true;
                    resultado += "\nVocê ganhou o feitiço Bola de Fogo, para utilizá-lo use o comando 'feitico' quando enfrentar o próximo monstro.";
                }
            }
        } else {
            resultado = "Que pena, seu ataque não acertou o monstro!";
        }

        System.out.println("Você se prepara para atacar...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
        return resultado;
    }

    // Método que subtrai o dano sofrido dos pontos de vida do monstro
    public void sofre_dano(int dano_sofrido) {
        pontos_vida -= dano_sofrido;
    }

    public void recebe_cura(int quant_cura) {
        pontos_vida += quant_cura;
        if (pontos_vida > 10)
            // limitamos a quantidade máxima de HP a 10
            pontos_vida = 10;
    }
    
    public void setIn_combat(boolean in_combat) {
        this.in_combat = in_combat;
    }

    public int getPontos_dano() {
        return pontos_dano;
    }

    public int getPontos_vida() {
        return pontos_vida;
    }

    public boolean isInCombat() {
        return in_combat;
    }

    public boolean getPossuiBolaFogo() {
        return possuiBolaFogo;
    }
}
