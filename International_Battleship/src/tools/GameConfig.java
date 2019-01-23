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
	private static Color colorPlayer1;
	private static Color colorPlayer2;
	private static Color colorPlayer3;
	private static Color colorPlayer4;

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
			int numberOfExplomissImages,
			Color colorPlayer1,
			Color colorPlayer2,
			Color colorPlayer3,
			Color colorPlayer4
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
				numberOfExplomissImages,
				colorPlayer1,
				colorPlayer2,
				colorPlayer3,
				colorPlayer4
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
			int numberOfExplomissImages,
			Color colorPlayer1,
			Color colorPlayer2,
			Color colorPlayer3,
			Color colorPlayer4
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
				numberOfExplomissImages,
				colorPlayer1,
				colorPlayer2,
				colorPlayer3,
				colorPlayer4
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
			int numberOfExplomissImages,
			Color colorPlayer1,
			Color colorPlayer2,
			Color colorPlayer3,
			Color colorPlayer4
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
		GameConfig.colorPlayer1 = colorPlayer1;
		GameConfig.colorPlayer2 = colorPlayer2;
		GameConfig.colorPlayer3 = colorPlayer3;
		GameConfig.colorPlayer4 = colorPlayer4;
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
		Color[] colors = new Color[getNbMaxPlayer()];
		colors[0] = colorPlayer1;
		colors[1] = colorPlayer2;
		colors[2] = colorPlayer3;
		colors[3] = colorPlayer4;
		return colors;
	}
}
