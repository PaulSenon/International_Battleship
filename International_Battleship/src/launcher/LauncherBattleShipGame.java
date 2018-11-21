package launcher;

import controler.BattleShipControlerLocal;
import controler.BattleshipGameControlerModelView;
import model.BattleshipModel;
import view.BattleshipGUI;
import multiplayer.Client;
import multiplayer.Server;
import tools.BattleShipGameConfig;

import javax.swing.*;
import java.awt.*;

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
		    // TODO VERIF QUE C'EST BIEN LES INTERFACES
            BattleshipModel gameModel = new BattleshipModel();
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
