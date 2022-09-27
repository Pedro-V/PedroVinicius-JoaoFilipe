package jogo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Player {
    private int pontos_vida, pontos_dano;
    private Random gerador = new Random();
    private Room sala_atual;
    private boolean in_combat;

    public Player() {
        pontos_dano = 1;
        pontos_vida = 10;
        in_combat = false;
    }

    // overloading do construtor: Opcionalmente criamos com uma sala inicial
    public Player(Room sala_inicial) {
        pontos_dano = 1;
        pontos_vida = 10;
        sala_atual = sala_inicial;
        in_combat = false;
    }

    public Room getSala_atual() {
        return sala_atual;
    }

    public String printStats() {
        return "Seus pontos de vida: " + pontos_vida + "\u2764\uFE0F";
    }
    public String printStats(Monster monstro) {
        return "Seus pontos de vida: " + pontos_vida + "\u2764\uFE0F" +
                "\t\tPontos de vida do monstro: " + monstro.getPontos_vida() + " \uD83D\uDC7A" + "\n";
    }

    public void entra_combate() {
        if (sala_atual.temMonstro()) {
            Monster monstro = sala_atual.getMonstro();
            if (!monstro.estaVivo())
                System.out.println("O monstro dessa sala já está morto.");
            else {
                System.out.println(ataque(monstro));
                System.out.println(printStats(monstro));
                // Se o monstro estiver vivo, o monstro da sala tenta atacar
                sala_atual.usaAtributo(this);
            }
        } else
            System.out.println("Não há monstros nessa sala.");
    }

    private String ataque(Monster monstro) {
        in_combat = true;
        String resultado = new String();
        System.out.println("Você se prepara para atacar...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
        // possível acerto
        if (gerador.nextBoolean()) {
            // possível crítico
            if (gerador.nextBoolean()) {
                monstro.sofre_dano(pontos_dano * 2);
                resultado = "Seu ataque foi crítico! Ele deu o dobro de dano (" + pontos_dano * 2 + ")";
            } else {
                monstro.sofre_dano(pontos_dano);
                resultado = "Seu ataque acertou o monstro!";
            }
            if (!monstro.estaVivo()) {
                resultado += "\nO seu ataque matou o monstro. \u2620";
                in_combat = false;
            }
        } else
            resultado = "Que pena, seu ataque não acertou o monstro!";
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

    /**
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    public void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ir aonde?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = sala_atual.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Não há porta nessa direção!");
        } else if (in_combat) {
            System.out.println("Nada de fugir. É preciso primeiro derrotar o monstro!");
        } else {
            sala_atual = nextRoom;
            System.out.println(sala_atual.getLongDescription());
            System.out.println(sala_atual.getAttributeDescription());
            if (sala_atual.temMonstro() && sala_atual.atributoEstaAtivo()) {
                in_combat = true;
            }
        }
    }

    /*
     * Método que retorna se o jogador está em combate
     * Um jogador está em combate quando existe um monstro vivo na mesma sala que
     * ele
     */
    public boolean isInCombat() {
        return in_combat;
    }

    public int getPontos_dano() {
        return pontos_dano;
    }

    public int getPontos_vida() {
        return pontos_vida;
    }

}
