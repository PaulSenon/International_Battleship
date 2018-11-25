package launcher;

import controler.ControllerLocal;
import controler.ControllerModelViewInterface;
import model.GameModel;
import multiplayer.Client;
import multiplayer.Server;
import tools.GameConfig;
import view.GameGUI;

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
			GameConfig.newInstance(
					25, // gameGridWidth
					25 // gameGridHeight
			);

		// setup Frame
			Dimension dim = new Dimension(850,570);
			GameGUI gameGUI = new GameGUI(); // TODO use GameGUIInterface
			gameGUI.setTitle("International Battleship");
			gameGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gameGUI.setLocation(400, 10);
			gameGUI.setPreferredSize(dim);
			gameGUI.pack();
			gameGUI.setResizable(true);
			gameGUI.setVisible(true);

		// setup Game
		    // TODO VERIF QUE C'EST BIEN LES INTERFACES
            GameModel gameModel = new GameModel();
            ControllerModelViewInterface gameController = new ControllerLocal(gameModel, gameGUI);
            gameGUI.initListeners(gameController);

	}

}
