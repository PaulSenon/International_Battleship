 package launcher;

import java.awt.Dimension;

import javax.swing.JFrame;

import Controler.BattleShipControlerLocal;
import Controler.BattleshipGameControlerModelView;
import Model.BattleshipGameModel;
import Model.BattleshipModel;
import View.BattleshipGUI;
import tools.BattleShipGameConfig;

public class LauncherBattleShipGame {
	
	public static void main(String[] args) {
		// TODO nothing definitive, just some debug config
		
		// setup game config :
			BattleShipGameConfig.newInstance(
					25, // gameGridWidth
					25 // gameGridHeight
			);	
		
		// setup Game
			// TODO
			BattleshipGameModel gameModel = new BattleshipModel();
			BattleshipGameControlerModelView gameController = new BattleShipControlerLocal(gameModel);
		
		// setup Frame
			Dimension dim = new Dimension(850,570);
			JFrame frame = new BattleshipGUI(gameController);	
			frame.setTitle("International Battleship");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocation(400, 10);
			frame.setPreferredSize(dim);
			frame.pack();
			frame.setResizable(true);
			frame.setVisible(true);
	}

}
