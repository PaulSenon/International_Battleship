package tools;

import java.awt.Color;

import model.BoatType;

public class GameConfig {
	private static int gameGridWidth;
	private static int gameGridHeight;
	private static int maxActionPoint;
	private static int portSize;
	private static int nbMaxPlayer;
	private static String[] players;
	private static BoatType[] fleet;
	private static int edgingColor;
	private static int numberOfExplosionImages;
	private static int numberOfExplomissImages;

	private static GameConfig instance = null;
	
	public static GameConfig newInstance(
			int gameGridWidth,
			int gameGridHeight,
			int maxActionPoint,
			int portSize,
			int nbMaxPlayer,
			String[] players,
			BoatType[] fleet,
			int edgingColor,
			int numberOfExplosionImages,
			int numberOfExplomissImages
	) {
		if (instance == null){
			return forceNewInstance(
				gameGridWidth,
				gameGridHeight,
				maxActionPoint,
				portSize,
				nbMaxPlayer,
				players,
				fleet,
				edgingColor,
				numberOfExplosionImages,
				numberOfExplomissImages
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
			BoatType[] fleet,
			int edgingColor,
			int numberOfExplosionImages,
			int numberOfExplomissImages
	){
		instance = new GameConfig(
				gameGridWidth,
				gameGridHeight,
				maxActionPoint,
				portSize,
				nbMaxPlayer,
				players,
				fleet,
				edgingColor,
				numberOfExplosionImages,
				numberOfExplomissImages
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
			BoatType[] fleet,
			int edgingColor,
			int numberOfExplosionImages,
			int numberOfExplomissImages
	) {
		GameConfig.gameGridWidth = gameGridWidth;
		GameConfig.gameGridHeight = gameGridHeight;
		GameConfig.maxActionPoint = maxActionPoint;
		GameConfig.portSize = portSize;
		GameConfig.nbMaxPlayer = nbMaxPlayer;
		GameConfig.players = players;
		GameConfig.fleet = fleet;
		GameConfig.edgingColor = edgingColor;
		GameConfig.numberOfExplosionImages = numberOfExplosionImages;
		GameConfig.numberOfExplomissImages = numberOfExplomissImages;
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
	public static int getEdgingColor() { return edgingColor;}
	public static int getNumberOfExplosionImages() {return numberOfExplosionImages;}
	public static int getNumberOfExplomissImages() {return numberOfExplomissImages;}
	public static Color[] getColors(){
		//A REVOIR: FAIT EN DUR POUR TEST
		Color[] colors = new Color[4];
		colors[0] = new Color(53,212,53,204);
		colors[1] = new Color(121,53,55,204);
		colors[2] = new Color(53,55,212,204);
		colors[3] = new Color(233,137,11,204);
		return colors;
	}
}
