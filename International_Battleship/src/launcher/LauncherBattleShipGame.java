package launcher;

import controler.ControllerLocal;
import controler.ControllerModelViewInterface;
import model.BoatType;
import model.GameModel;
import model.ProcessedPropsManager;
import multiplayer.Client;
import multiplayer.Server;
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

		// setup game config :
		GameConfig.newInstance(
				40, // gameGridWidth
				40, // gameGridHeight
				20, // maxActionPoint
                9, // portSize
                4, // nbMaxPlayer
				new String[]{
						"José",
						"Théodule",
						"Yvonne",
						"Titouan"
				},
				new BoatType[]{
						BoatType.Cruiser,
						BoatType.Submarine,
						BoatType.AircraftCarrier,
						BoatType.Sentinel,
						BoatType.TorpedoBoat
				},
				new Color(255, 247, 0, 255).getRGB(), //Color of edging
				16, //Number of explosion images
				8, //Number of explomiss images
				new Color(255,0,0,255), //Color of Player1
				new Color(0,255,0,255), //Color of Player2
				new Color(100,100,255,255), //Color of Player3
				new Color(255,100,255,255) //Color of Player4
		);

		// setup image manager :
				ImageManager.newInstance();
// setup processedPropsManager :
			ProcessedPropsManager.newInstance();

		final JFrame jframe = new JFrame("Menu");
		jframe.setPreferredSize(new Dimension(1130, 800));
		jframe.setMinimumSize(new Dimension(1130, 800));
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = jframe.getContentPane();
		c.setLayout(new BorderLayout(10,0));
		final JLabel img = new JLabel(new ImageIcon(ImageManager.getImageCopy("background.png")));
		c.add(img, BorderLayout.CENTER);
		final JPanel jp = new JPanel();
		jp.setBackground(new Color(0,2,7));
		c.add(jp, BorderLayout.SOUTH);


		//Init of console on launcher
		JTextArea textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(800,1000));
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.LIGHT_GRAY);
		//TODO fix the scroll and the encodage problem

		//Redirect Standard output to launcher
		PrintStream printStream = new PrintStream(new ConsoleOutputStream(textArea));
		final JEditorPane editText = new JEditorPane();


		//Bouton pour lancer le solo
		JButton solo = new JButton("Solo");
		solo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					launchSolo(jframe);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		//Bouton pour lancer le multijoueur
		JButton multi = new JButton("Multi");
		multi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launchMultiplayer(editText,jp);
			}
		});

		editText.setText("127.0.0.1");
		JButton join = new JButton("Join");
		join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				joinMultiplayer(editText);
			}
		});



		//Bouton pour quitter l'application
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});



		jp.add(solo);
		jp.add(multi);
		jp.add(join);
		jp.add(editText);
		jp.add(close);

		jframe.setVisible(true);
	}

	/**
	 * Cette fonction permet de lancer la mode multijoueur
	 * Elle instancie le serveur, connecte le premier joueur dessus
	 */
	public static void launchMultiplayer(JEditorPane editText,JPanel jp){
		final Server server = new Server(editText.getText());
		server.open();

		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				server.startGame();
			}
		});

		jp.add(start);
		jp.revalidate();
		jp.repaint();

	}

	/**
	 * To join a game
	 */
	private static void joinMultiplayer(JEditorPane editText) {
		Thread t = new Thread(new Client(editText.getText(),8080));
		t.start();
	}

	/**
	 * Cette fonction permet de lancer le mode solo
	 * Elle configure la fenêtre, les joueurs et lance la partie
	 */
	public static void launchSolo(JFrame jframe) throws IOException {
		GameGUI gameGUI = setupFrame();
		GameModel gameModel = setupGameModel();
		setupGame(gameModel,gameGUI);
		gameGUI.setVisible(true);
		jframe.setVisible(false);//Hide the launcher

	}
	/**
	 * Permet de générer le plateau de jeu
	 */
	public static GameGUI setupFrame() throws IOException {
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
