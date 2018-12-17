package multiplayer;

import controler.ControllerLocal;
import model.GameModel;
import tools.ProcessedPosition;
import view.GameGUI;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server {

    //On initialise des valeurs par défaut
    private int port = 8080;
    private String host = "127.0.0.1";
    private ServerSocket server = null;
    private boolean isRunning = true;
    private int nbConnect = 0;
    private GameModel gameModel;
    private GameGUI gameGUI;
    private ControllerLocal controller;
    private List<ClientProcessor> clientList;
    private boolean gameStarted = false;

    public Server(){
        try {
            server = new ServerSocket(port,100, InetAddress.getByName(this.host));
            clientList = new ArrayList<>();
            this.gameModel = new GameModel();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Server(String host){
    	this.host = host;
        try {
            server = new ServerSocket(port,100, InetAddress.getByName(this.host));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open(){
        Server serv = this;
        //Toujours dans un thread à part vu qu'il est dans une boucle infinie
        Thread t = new Thread(new Runnable(){

            public void run(){
            	try {
					System.out.println("Serveur ouvert à l'addresse : "+ InetAddress.getLocalHost().getHostAddress());
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            	
                while(isRunning == true ){
                    try {
                        //On attend une connexion d'un client
                        if(nbConnect < 4 && !gameStarted) {
                            Socket client = server.accept();
                            //Une fois reçue, on la traite dans un thread séparé et on compte le nombre de connexion
                            nbConnect++;
                            System.out.println("Connexion cliente reçue.");
                            ClientProcessor cp = new ClientProcessor(client, nbConnect, gameModel,serv);
                            clientList.add(cp);
                            Thread t = new Thread(cp);
                            t.start();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    server = null;
                }
            }
        });
        t.start();
    }

    /**
     * Notify each client that the game should begin
     */
    public void startGame(){
        for (ClientProcessor c :clientList) {
            c.startGame();
        }
    }

    /**
     * Notify each client that the client "form" has done something
     * Then they will update both their model and their GUI
     * @param processedPosition
     * @param from
     */
    public void update(ProcessedPosition processedPosition,int from){
        for (ClientProcessor c :clientList) {
            if(c.getID() != from){
                c.update(processedPosition);
            }
        }
    }

    public void start(){
        for (ClientProcessor c :clientList) {
            c.startGame();

        }
    }

    public void close(){
        isRunning = false;
    }


}
