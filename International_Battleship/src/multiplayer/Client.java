package multiplayer;

import controler.ControllerClient;
import model.GameModel;
import model.Player;
import model.PlayerInterface;
import tools.ProcessedPosition;
import tools.ProcessedProps;
import view.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class Client implements Runnable{

    private Socket connexion = null;
    private ObjectOutputStream writer = null;
    private ObjectInputStream reader = null;


    private String name = "Client";
    private Player player;
    private ControllerClient controller;
    
    
    public Client(String host, int port){
        try {
            connexion = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        boolean isStarted = false;
        try {
            writer = new ObjectOutputStream(connexion.getOutputStream());
            reader = new ObjectInputStream(connexion.getInputStream());

            
            //Player player = new Player("Joueur 1","France");
            
            System.out.println("J'envoie une demande de création de joueur");
            writer.writeObject("getPlayer");
            writer.flush();

            System.out.println("Commande getPlayer envoyée au serveur");

            //On attend la réponse

            while(!isStarted) {
                Object answer = read();
                if (answer instanceof Player) {
                    this.player = (Player) answer;
                } else if (answer instanceof String) {
                    if (((String) answer).equals("start")) {
                        isStarted = true;
                        Object player = read();
                        if (player instanceof List) {
                            List<PlayerInterface> players = (List<PlayerInterface>) player;
                            Dimension dim = new Dimension(850, 570);
                            GameGUI gameGUI = new GameGUI();
                            gameGUI.setTitle("International Battleship");
                            gameGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            gameGUI.setLocation(400, 10);
                            gameGUI.setPreferredSize(dim);
                            gameGUI.pack();
                            gameGUI.setResizable(true);
                            GameModel gm = new GameModel(players);
                            gm.setClientPlayer(this.player);
                            controller = new ControllerClient(gm, gameGUI, this);
                            gameGUI.initListeners(controller);
                            gameGUI.setVisible(true);
                        }
                    }
                }
            }

            while(true){
                Object answer = read();
                if (answer instanceof ProcessedPosition) {
                    ProcessedPosition pp = (ProcessedPosition) answer;
                    controller.update(pp);
                }else if (answer instanceof ProcessedProps) {
                    ProcessedProps pp = (ProcessedProps) answer;
                    controller.update(pp);

                }else if(answer instanceof String){
                    switch ((String)answer){
                        case "endOfTurn":
                            controller.setupEndTurn();
                            break;
                    }
                }
            }






        } catch (IOException e1) {
            e1.printStackTrace();
            isStarted = true;
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isStarted = true;
		}

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        try {
            writer.flush();
            //writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Object read() throws IOException, ClassNotFoundException{
        Object res;
		res = reader.readObject();
		
        return res;
    }


    public void sendProcessedPosition(ProcessedPosition processedPosition) {
        try {

            writer.writeObject(processedPosition);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endOfTurn(){
        try {
            writer.writeObject("endOfTurn");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendProcessedProps(List<ProcessedProps> processedProps) {
        for (ProcessedProps pp : processedProps) {
            try {
                writer.writeObject(pp);
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
