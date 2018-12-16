package multiplayer;

import controler.ControllerClient;
import controler.ControllerLocal;
import model.GameModel;
import model.Player;
import tools.ProcessedPosition;
import view.GameGUI;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

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

        try {
            writer = new ObjectOutputStream(connexion.getOutputStream());
            reader = new ObjectInputStream(connexion.getInputStream());

            
            //Player player = new Player("Joueur 1","France");
            
            System.out.println("J'envoie une demande de création de joueur");
            writer.writeObject("getPlayer");
            writer.flush();

            System.out.println("Commande getPlayer envoyée au serveur");

            //On attend la réponse
            Object answer = read();
            if(answer instanceof Player){
            	this.player = (Player)answer;
            	System.out.println(this.player.toString());
            }else if(answer instanceof String){
                if(((String)answer).equals("start")){
                    controller = new ControllerClient(new GameModel(),new GameGUI(),this);
                }
            	System.out.println("Client recois : "+(String)answer);
            }else if(answer instanceof ProcessedPosition){
                controller.update((ProcessedPosition)answer);
            }






        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
