package jogo;
import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private Attribute atributo;
    private Monster monstro;
    private boolean atributo_finalizado;



    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        atributo = Attribute.VAZIA;
        exits = new HashMap<>();
    }

    public Room(String description, Attribute atributo) 
    {
        this.description = description;
        this.atributo = atributo;
        ativaAtributo();
        
        exits = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Ativa o atributo relacionado a instância de uma sala.
     */
    private void ativaAtributo() {
        switch (atributo) {
            case MONSTER:
                monstro = new Monster();
                atributo_finalizado = false;
                break;
            case CHEFE:
                monstro = new Monster(true);
                atributo_finalizado = false;
                break;
            default:
                atributo_finalizado = false;
                break;
        }
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "Você está " + description + ".\n" + getExitString();
    }

    public String getAttributeDescription() {
        return atributo.getDescricao();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Saídas:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public boolean atributoFinalizado() {
        return atributo_finalizado;
    }

    public boolean temMonstro() {
        return atributo.equals(Attribute.MONSTER) || atributo.equals(Attribute.CHEFE);
    }

    /**
     * 
     * @return O objeto monstro, se a sala tiver um monstro. Caso contrário, null;
     */
    public Monster getMonstro() {
        if (temMonstro()){
            return monstro;
        }
        return null;
    }
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}