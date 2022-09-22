package java;
import java.util.Random;

public class Player {
    private int pontos_vida, pontos_dano;
    private Random gerador = new Random();
    private Room sala_atual;

    public Player() {
        pontos_dano = 1;
        pontos_vida = 10;
    }

    public void ataque(Monster monstro) {
        // possível acerto
        if (gerador.nextBoolean()) {
            // possível crítico
            if (gerador.nextBoolean()) 
                monstro.sofre_dano(pontos_dano * 2);
            else
                monstro.sofre_dano(pontos_dano);
        }
        return;
    }

    // Método que subtrai o dano sofrido dos pontos de vida do monstro
    public void sofre_dano(int dano_sofrido){
        pontos_vida -= dano_sofrido;
    }

    public void recebe_cura(int quant_cura) {
        pontos_vida += quant_cura;
    }
    
}
