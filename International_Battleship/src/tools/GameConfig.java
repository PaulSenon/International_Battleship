package tools;

import model.BoatType;

public class GameConfig {
	private static int gameGridWidth;
	private static int gameGridHeight;
	private static int maxActionPoint;
	private static int portSize;
	private static int nbMaxPlayer;
	private static String[] players;
	private static BoatType[] fleet;
	
	private static GameConfig instance = null;
	
	public static GameConfig newInstance(
			int gameGridWidth,
			int gameGridHeight,
			int maxActionPoint,
			int portSize,
			int nbMaxPlayer,
			String[] players,
			BoatType[] fleet
	) {
		if (instance == null){
			return forceNewInstance(
					gameGridWidth,
					gameGridHeight,
					maxActionPoint,
					portSize,
					nbMaxPlayer,
					players,
					fleet
			);
		}
		return instance;
	}

	public static GameConfig forceNewInstance(
			int gameGridWidth,
			int gameGridHeight,
			int maxActionPoint,
			int portSize,
			int nbMaxPlayer,
			String[] players,
			BoatType[] fleet
	){
		instance = new GameConfig(
				gameGridWidth,
				gameGridHeight,
				maxActionPoint,
				portSize,
				nbMaxPlayer,
				players,
				fleet
		);
		return instance;
	}
	
	private GameConfig(
			int gameGridWidth,
			int gameGridHeight,
			int maxActionPoint,
			int portSize,
			int nbMaxPlayer,
			String[] players,
			BoatType[] fleet
	) {
		GameConfig.gameGridWidth = gameGridWidth;
		GameConfig.gameGridHeight = gameGridHeight;
		GameConfig.maxActionPoint = maxActionPoint;
		GameConfig.portSize = portSize;
		GameConfig.nbMaxPlayer = nbMaxPlayer;
		GameConfig.players = players;
		GameConfig.fleet = fleet;
	}
	
	public static int getGameGridWidth() {
		return gameGridWidth;
	}
	public static int getGameGridHeight() {
		return gameGridHeight;
	}
	public static int getMaxActionPoint() {
	    return maxActionPoint;
    }
    public static int getPortSize(){
		return portSize;
	}
	public static int getNbMaxPlayer(){
		return nbMaxPlayer;
	}
	public static String[] getPlayers() {
		return players;
	}
	public static BoatType[] getFleet() {
		return fleet;
	}
}
