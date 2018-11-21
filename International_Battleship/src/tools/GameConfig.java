package tools;

public class GameConfig {
	private static int gameGridWidth;
	private static int gameGridHeight;
	
	private static GameConfig instance = null;
	
	public static GameConfig newInstance(int gameGridWidth, int gameGridHeight) {
		if (instance == null)
			instance = new GameConfig(gameGridWidth, gameGridHeight);
		return instance;
	}
	
	private GameConfig(int gameGridWidth, int gameGridHeight) {
		this.gameGridWidth = gameGridWidth;
		this.gameGridHeight = gameGridHeight;
	}
	
	public static int getGameGridWidth() {
		return gameGridWidth;
	}
	public static int getGameGridHeight() {
		return gameGridHeight;
	}
	
	
	
}
