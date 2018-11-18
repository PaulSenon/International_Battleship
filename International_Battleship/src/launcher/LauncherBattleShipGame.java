package launcher;

import java.awt.Dimension;

import javax.swing.JFrame;

import Controler.BattleShipControlerLocal;
import Controler.BattleshipGameControlerModelView;
import View.BattleshipGUI;
import multiplayer.Client;
import multiplayer.Server;
import tools.BattleShipGameConfig;

public class LauncherBattleShipGame {
	
	public static void main(String[] args) {
		// TODO nothing definitive, just some debug config

		//Test de la connexion du server en localhost avant le lancement du jeu
		Server server = new Server();
		server.open();
		Thread t = new Thread(new Client("127.0.0.1",8080));
		t.start();


		// setup game config :
			BattleShipGameConfig.newInstance(
					25, // gameGridWidth
					25 // gameGridHeight
			);	
		
		// setup Game
			// TODO
			//BattleshipGameModel gameModel = //...;
			//BattleshipGameControlerModelView gameController = new BattleShipControlerLocal(/*gameModel*/);
		
		// setup Frame
			Dimension dim = new Dimension(720,712);
			JFrame frame = new BattleshipGUI();	
			frame.setTitle("International Battleship");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocation(400, 10);
			frame.setPreferredSize(dim);
			frame.pack();
			frame.setResizable(true);
			frame.setVisible(true);
	}

}
