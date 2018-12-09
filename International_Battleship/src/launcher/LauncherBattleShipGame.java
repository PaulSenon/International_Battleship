package launcher;

import controler.ControllerLocal;
import controler.ControllerModelViewInterface;
import model.GameModel;
import multiplayer.Client;
import multiplayer.Server;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import tools.ConsoleOutputStream;
import tools.GameConfig;
import tools.ImageManager;
import view.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;

public class LauncherBattleShipGame {

	public static void main(String[] args) throws IOException {
		// TODO nothing definitive, just some debug config

		// setup image manager :
		ImageManager.newInstance();

		// setup game config :
		GameConfig.newInstance(
				25, // gameGridWidth
				25, // gameGridHeight
				20 //maxActionPoint
			);

		JFrame jframe = new JFrame("Menu");
		jframe.setSize(300, 500);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = jframe.getContentPane();
		JPanel jp = new JPanel();
		c.add(jp);


		//Init of console on launcher
		JTextArea textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(250,400));
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.LIGHT_GRAY);
		//TODO fix the scroll and the encodage problem

		//Redirect Standard output to launcher
		PrintStream printStream = new PrintStream(new ConsoleOutputStream(textArea));
		System.setOut(printStream);
		System.setErr(printStream);


		//Bouton pour lancer le solo
		JButton solo = new JButton("Solo");
		solo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launchSolo(jframe);
			}
		});

		//Bouton pour lancer le multijoueur
		JButton multi = new JButton("multi");
		multi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launchMultiplayer(textArea);
			}
		});

		//Bouton pour quitter l'application
		JButton close = new JButton("close");
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		jp.add(solo);
		jp.add(multi);
		jp.add(close);
		jp.add(textArea);

		jframe.setVisible(true);
	}

	/**
	 * Cette fonction permet de lancer la mode multijoueur
	 * Elle instancie le serveur, connecte le premier joueur dessus
	 */
	public static void launchMultiplayer(JTextArea textArea){
		Server server = new Server();
		server.open();
		Thread t = new Thread(new Client("127.0.0.1",8080));
		t.start();
	}

	/**
	 * Cette fonction permet de lancer le mode solo
	 * Elle configure la fenêtre, les joueurs et lance la partie
	 */
	public static void launchSolo(JFrame jframe) {
		GameGUI gameGUI = setupFrame();
		GameModel gameModel = setupGameModel();
		setupGame(gameModel,gameGUI);
		gameGUI.setVisible(true);
		jframe.setVisible(false);//Hide the launcher

	}
	/**
	 * Permet de générer le plateau de jeu
	 */
	public static GameGUI setupFrame(){
		Dimension dim = new Dimension(850,570);
		GameGUI gameGUI = new GameGUI();
		gameGUI.setTitle("International Battleship");
		gameGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameGUI.setLocation(400, 10);
		gameGUI.setPreferredSize(dim);
		gameGUI.pack();
		gameGUI.setResizable(true);
		//gameGUI.setVisible(true);

		return gameGUI;
	}

	/**
	 * Initialise le game model
	 * TODO:Préparer pour pouvoir intialiser soit une partie solo, soit une partie multijoueur
	 * @return GameModel gameModel
	 */
	public static GameModel setupGameModel(){
		GameModel gameModel = new GameModel();
		return gameModel;
	}
	public static void setupGame(GameModel gameModel, GameGUI gameGUI) {
		ControllerModelViewInterface gameController = new ControllerLocal(gameModel,gameGUI);
		gameGUI.initListeners(gameController);
	}

}
