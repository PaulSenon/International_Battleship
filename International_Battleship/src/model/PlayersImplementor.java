package model;

import tools.BoatFactory;
import tools.Coord;
import tools.GameConfig;
import tools.UniqueIdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayersImplementor implements PlayersImplementorInterface {

    private List<PlayerInterface> players;

    public PlayersImplementor(String[] playerNames) throws Exception {
        if(playerNames.length > GameConfig.getNbMaxPlayer()){
            throw new Exception("PlayersImplementor: Too many players");
        }

        this.players = new ArrayList<>();
        this.generatePlayers(playerNames);
    }

    public PlayersImplementor(){
        this.players = new ArrayList<>();
    }

    public PlayersImplementor(List<PlayerInterface> players){
        this.players = players;
    }

    private void generatePlayers(String[] playerNames){
        PlayerInterface player;
        for(String name : playerNames){
            // TODO port names are generated here, maybe not so clean...
            player = new Player(UniqueIdGenerator.getNextId(), name, name+"-port");
            player.generateFleet(GameConfig.getFleet());
            this.players.add(player);
        }
    }

//    private List<Coord> generatePort(Coord origin, int radius){
//        List<Coord> port = new ArrayList<>();
//        int halfPortSize = GameConfig.getPortSize();
//        origin = origin.sub(new Coord(halfPortSize, halfPortSize));
//        Coord coord;
//
//        for(int i=0; i<GameConfig.getPortSize(); i++){
//            for(int j=0; j<GameConfig.getPortSize(); j++){
//                coord = origin.add(new Coord(i,j));
//                if(this.isInGrid(coord)){
//                    port.add(coord);
//                }
//            }
//        }
//    }

    private boolean isInGrid(Coord coord){
        if(
                coord.getX()>=GameConfig.getGameGridWidth()
                || coord.getX()<0
                || coord.getY()>=GameConfig.getGameGridHeight()
                || coord.getY()<0
        ){
            return false;
        }
        return true;
    }

    /**
     *
     * @param coord the coord to test
     * @return player ID if it's a port
     * @throws Exception when not a port
     */
    private int getPlayerIdIfPort(Coord coord) throws Exception {
        for(PlayerInterface player : this.players){
            if(player.isInPort(coord)){
                return player.getId();
            }
        }
        throw new Exception("Not a port");
    }

    public List<PlayerInterface> getPlayers() {
        return players;
    }

    @Override
    public void undoLastMove(PlayerInterface currentPlayer) {
        currentPlayer.undoLastAction();
    }

	@Override
	public PlayerInterface findById(int idPlayer) {
		for (PlayerInterface playerInterface : players) {
			if(playerInterface.getId() == idPlayer){
				return playerInterface;
			}
		}
		return null;
	}

	@Override
	public int remainsPlayers() {
		int nbPlayers = 0;
		for (PlayerInterface playerInterface : players) {
			if (!playerInterface.isEliminate()) {
				nbPlayers++;
			}
		}
		return nbPlayers;
	}

	@Override
	public int idWinner() {
		int nbPlayers = 0;
		int idPlayer = -1;
		for (PlayerInterface playerInterface : players) {
			if (!playerInterface.isEliminate()) {
				nbPlayers++;
				idPlayer = playerInterface.getId();
			}
		}
		if (nbPlayers != 1) {
			idPlayer = -1;
		}
		return idPlayer;
	}

    @Override
    public PlayerInterface createPlayer(int idPlayer) {
        Player p = new Player(idPlayer,"Player"+idPlayer,"Port"+idPlayer);
        p.generateFleet(GameConfig.getFleet());
        players.add(p);
        return p;
    }
}
