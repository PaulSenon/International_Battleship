package model;

import java.util.List;

public interface PlayersImplementorInterface {

    List<PlayerInterface> getPlayers();

    void undoLastMove(PlayerInterface currentPlayer);

    PlayerInterface createPlayer(int idPlayer);
}
