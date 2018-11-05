package launcher;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import Controler.BattleShipControlerLocal;
import Controler.BattleshipGameControlerModelView;
import Model.BattleshipGameModel;
import Model.BattleshipModelObs;
import View.BattleshipGUI;
import tools.BattleShipGameConfig;

public class LauncherBattleShipGame {
	
	public static void main(String[] args) {
		// TODO nothing definitive, just some debug config
		
		// setup game config :
			BattleShipGameConfig.newInstance(
					25, // gameGridWidth
					25, // gameGridHeight
					"BattleshipBoatFactory"
			);	
		
		// setup Game
			BattleshipGameModel gameModel = new BattleshipModelObs();
			BattleshipGameControlerModelView gameController = new BattleShipControlerLocal(gameModel);
			JFrame frame = new BattleshipGUI(gameController);
			((Observable)gameModel).addObserver((Observer)frame);
		
		// setup Frame
			Dimension dim = new Dimension(720,712);	
			frame.setTitle("International Battleship");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocation(400, 10);
			frame.setPreferredSize(dim);
			frame.pack();
			frame.setResizable(true);
			frame.setVisible(true);
	}

}
