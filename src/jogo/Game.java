/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game. Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game. It also evaluates and
 * executes the commands that the parser returns.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
package jogo;

import java.util.concurrent.TimeUnit;

public class Game {
    private Parser parser;
    // private Room currentRoom;
    // Caso queiramos generalizar o jogo para N jogadores, poderíamos implementar
    // uma ArrayList aqui
    private Player jogador;
    private Room sala_atual;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Salas criadas com seus respectivos atributos.
     */
    private void createRooms() {
        Room entrada, grandeHall, sotao, biblioteca, enfermaria, porao, jardim, saladeJantar, igrejaAntiga;

        // create the rooms
        entrada = new Room("na entrada da masmorra");
        grandeHall = new Room("no saguão do castelo");
        sotao = new Room("no sotão empoeirado", Attribute.ARMADILHA);
        biblioteca = new Room("na biblioteca antiga", Attribute.MONSTER);
        enfermaria = new Room("na enfermaria", Attribute.CURA);
        porao = new Room("no porão do castelo", Attribute.BOLAFOGO);
        jardim = new Room("no jardim de estátuas", Attribute.CURA);
        saladeJantar = new Room("na grande sala de jantar");
        igrejaAntiga = new Room("na igreja mal assombrada", Attribute.CHEFE);

        // Saídas disponíveis de cada sala.
        entrada.setExit("leste", grandeHall);

        grandeHall.setExit("norte", biblioteca);
        grandeHall.setExit("leste", saladeJantar);

        biblioteca.setExit("norte", sotao);
        biblioteca.setExit("leste", jardim);
        biblioteca.setExit("sul", grandeHall);

        saladeJantar.setExit("oeste", grandeHall);
        saladeJantar.setExit("leste", enfermaria);
        saladeJantar.setExit("sul", porao);

        porao.setExit("norte", saladeJantar);

        enfermaria.setExit("norte", igrejaAntiga);
        enfermaria.setExit("oeste", saladeJantar);

        sotao.setExit("sul", biblioteca);

        jardim.setExit("oeste", biblioteca);

        igrejaAntiga.setExit("sul", enfermaria);

        jogador = new Player();

        sala_atual = entrada;
    }

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
        } else if (jogador.isInCombat()) {
            System.out.println("Nada de fugir. É preciso primeiro derrotar o monstro!");
        } else {
            sala_atual = nextRoom;
            System.out.println(sala_atual.getLongDescription());
            sala_atual.usaAtributo(jogador);
        }
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command) || checaJogoFinalizado();
        }
        System.out.println("Obrigado por jogar! Até.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Bem vindo ao Castelo Poxim");
        System.out.println("Castelo Poxim é um jogo room-to-room de aventura baseado no terminal");
        System.out.println("Digite '" + CommandWord.HELP + "' se precisar de ajuda.");
        System.out.println();
        System.out.println(sala_atual.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("Não entendi o que você quis dizer...");
                break;

            case HELP:
                printHelp();
                break;

            case ATTACK:
                jogador.entra_combate(sala_atual, false);
                break;

            case FEITICO:
                jogador.entra_combate(sala_atual, true);
                break;
            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    public boolean checaJogoFinalizado() {
        if (sala_atual.getAtributo() == Attribute.CHEFE && !sala_atual.atributoEstaAtivo()) {
            printaInfoVitoria(); // Assim que o boss (chefe) morrer o jogo é finalizado.
            return true;
        } else if (jogador.getPontos_vida() == 0) {
            System.out.println("Você morreu  \u2620 \u2620! Boa sorte na próxima vez.");
            return true; // A outra forma de finalizar o jogo é com o player morrendo.
        }
        return false;
    }
    
    private void printaInfoVitoria() {
        System.out.println("Uma luz azul brilha no corpo morto do chefe dos monstros...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("Você encontra a safira que tanto procurava, parabéns!");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("                         `-+sdmhmMMhyhNMMddm`");
        System.out.println("                      .smdy+:`  `-smmNy/    :h\\");
        System.out.println("                    -yNs.          mmhmo.    Md");
        System.out.println("                  -hNo`           oM- .oNh:  :s");
        System.out.println("                :dm+`            .Ns    `/dmo.d");
        System.out.println("               +Mo`              hN        yMMM");
        System.out.println("             `sN/              /dMmsssoo++///NM");
        System.out.println("            `hN-             +md/sM.        hNM");
        System.out.println("           .dm.            +mh:  .Ns      .dm+M");
        System.out.println("           +My          .oNy-     sM.    :Nd.+M");
        System.out.println("           +MM+     .:/sNd-       .Ns   sNo  sM");
        System.out.println("          `+MyMyhdddysmMoydmhs+:smdsM.+Nh-   hN");
        System.out.println("          `+M.MM:`   -Mo           hMNm/     md");
        System.out.println("          `+M  hm.   dm`         `+Nmdm+     My");
        System.out.println("          `/M:  md` /M/        -sNy:  -yNs. .Mo");
        System.out.println("           `Nh   Nh`Nh      :smd+.      .sNyoM/");
        System.out.println("            -mh:mh:MmNss:+sdNds:-::///++oosyN-");
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("Você está perdido, sozinho e caminha pelo castelo.");
        System.out.println();
        System.out.println("Seus comandos são:");
        parser.showCommands(jogador.getPossuiBolaFogo());
        System.out.println();
        System.out.println("Sua direção:\n" + sala_atual.getLongDescription() + "\n");
        System.out.println("Seu status de vida:" + jogador.getPontos_vida() + "");
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Sair da onde?");
            return false;
        } else {
            return true; // sinaliza que queremos sair
        }
    }
}