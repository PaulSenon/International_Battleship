package tools;

public class BattleShipGameConfig {
	private static int gameGridWidth;
	private static int gameGridHeight;
	private static String factoryName;
	
	private static BattleShipGameConfig instance = null;
	
	public static BattleShipGameConfig newInstance(int gameGridWidth, int gameGridHeight,String factoryName) {
		if (instance == null)
			instance = new BattleShipGameConfig(gameGridWidth, gameGridHeight,factoryName);
		return instance;
	}
	
	private BattleShipGameConfig(int gameGridWidth, int gameGridHeight,String factoryName) {
		this.gameGridWidth = gameGridWidth;
		this.gameGridHeight = gameGridHeight;
		this.factoryName = factoryName;
	}
	
	public static int getGameGridWidth() {
		return gameGridWidth;
	}
	public static int getGameGridHeight() {
		return gameGridHeight;
	}
	
	
	
}
