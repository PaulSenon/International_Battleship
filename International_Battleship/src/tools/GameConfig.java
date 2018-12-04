package tools;

public class GameConfig {
	private static int gameGridWidth;
	private static int gameGridHeight;
	private static int maxActionPoint;
	
	private static GameConfig instance = null;
	
	public static GameConfig newInstance(int gameGridWidth, int gameGridHeight, int maxActionPoint) {
		if (instance == null)
			instance = new GameConfig(gameGridWidth, gameGridHeight, maxActionPoint);
		return instance;
	}
	
	private GameConfig(int gameGridWidth, int gameGridHeight, int maxActionPoint) {
		this.gameGridWidth = gameGridWidth;
		this.gameGridHeight = gameGridHeight;
		this.maxActionPoint = maxActionPoint;
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
	
	
}
