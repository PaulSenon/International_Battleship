package tools;

public class BattleShipGameConfig {
	private static int gameGridWidth;
	private static int gameGridHeight;
	
	private static BattleShipGameConfig instance = null;
	
	public static BattleShipGameConfig newInstance(int gameGridWidth, int gameGridHeight) {
		if (instance == null)
			instance = new BattleShipGameConfig(gameGridWidth, gameGridHeight);
		return instance;
	}
	
	private BattleShipGameConfig(int gameGridWidth, int gameGridHeight) {
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
