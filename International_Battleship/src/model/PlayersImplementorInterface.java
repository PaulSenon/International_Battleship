package model;

import java.util.List;

public interface PlayersImplementorInterface {

    List<PlayerInterface> getPlayers();

    void undoLastMove(PlayerInterface currentPlayer);

    PlayerInterface findById(int idPlayer);
    
    int remainsPlayers();
    
    int idWinner();
    
}
