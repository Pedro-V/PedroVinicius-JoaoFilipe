package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jogo.Monster;
import jogo.Player;

public class PlayerTest {
    @Test
    public void testAtaque() {
    }

    @Test
    public void testRecebe_cura_acima_limite() {
        Player jogador = new Player();
        jogador.recebe_cura(12);
        assertEquals(jogador.getPontos_vida(), 10);
    }

    @Test
    public void testRecebe_cura_abaixo_limite() {
        Player jogador = new Player();
        jogador.sofre_dano(8);
        jogador.recebe_cura(5);
        assertEquals(jogador.getPontos_vida(), 7);
    }

    @Test
    public void testSofre_dano() {
        Monster monstro = new Monster();
        Player jogador = new Player();
        int dano_monstro = monstro.getPontos_dano();
        int vida_original = jogador.getPontos_vida();
        jogador.sofre_dano(dano_monstro);
        int vida_apos_dano = jogador.getPontos_vida();
        assertEquals(vida_apos_dano, vida_original - dano_monstro);
    }
}
