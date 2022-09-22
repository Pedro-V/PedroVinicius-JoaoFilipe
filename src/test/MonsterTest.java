package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jogo.Monster;
import jogo.Player;

public class MonsterTest {
    @Test
    public void testAtaque() {
        Monster monstro = new Monster();
        Player jogador = new Player();
        monstro.ataque(jogador);
    }

    @Test
    public void testSofre_dano() {
        Monster monstro = new Monster();
        Player jogador = new Player();
        int dano_jogador = jogador.getPontos_dano();
        int vida_monstro_original = monstro.getPontos_vida();
        monstro.sofre_dano(dano_jogador);
        int vida_monstro_apos_dano = monstro.getPontos_vida();
        assertEquals(vida_monstro_apos_dano, vida_monstro_original - dano_jogador);
    }
}
