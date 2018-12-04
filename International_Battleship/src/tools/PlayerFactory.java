package tools;

import model.Player;

public class PlayerFactory{
    public static Player newPlayer(String name) {
        return (Player) Introspection.newInstance (name);
    }
}
