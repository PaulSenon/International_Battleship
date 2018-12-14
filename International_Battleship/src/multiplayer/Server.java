package multiplayer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    //On initialise des valeurs par défaut
    private int port = 8080;
    private String host = "127.0.0.1";
    private ServerSocket server = null;
    private boolean isRunning = true;
    private int nbConnect = 0;

    public Server(){
        try {
            server = new ServerSocket(port,100, InetAddress.getByName(this.host));
            System.out.println("je suis ouvert batard");
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
        //Toujours dans un thread à part vu qu'il est dans une boucle infinie
        Thread t = new Thread(new Runnable(){

            public void run(){
            	try {
					System.out.println("Serveur ouvert à l'addresse : "+ InetAddress.getLocalHost().getHostAddress());
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            	
                while(isRunning == true && nbConnect < 4){
                    try {
                        //On attend une connexion d'un client
                        Socket client = server.accept();
                        //Une fois reçue, on la traite dans un thread séparé et on compte le nombre de connexion
                        nbConnect++;
                        System.out.println("Connexion cliente reçue.");
                        Thread t = new Thread(new ClientProcessor(client,nbConnect));
                        t.start();
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

    public void close(){
        isRunning = false;
    }


}
