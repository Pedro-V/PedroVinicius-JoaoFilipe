package java;
import java.util.Random;

public class Monster {
    private int pontos_vida, pontos_dano;
    private Random gerador = new Random();

    // Todos monstros começam com 3 de HP e 1 de dano;
    public Monster() {
        this.pontos_dano = 1;
        this.pontos_vida = 3;
    }

    // Overloading do construtor: Se uma string for informada, é assumido
    // que se trata de um boss
    public Monster(String is_boss) {
        pontos_dano = 2;
        pontos_vida = 8;
    }

    // Ataca um jogador, com chance de errar
    public void ataque(Player jogador) {
        if(gerador.nextBoolean()) {
            jogador.sofre_dano(pontos_dano);
        }
        return;
    }

    // Método que subtrai o dano sofrido dos pontos de vida do monstro
    public void sofre_dano(int dano_sofrido){
        pontos_vida -= dano_sofrido;
    }
}
