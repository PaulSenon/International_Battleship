package multiplayer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable{

        private Socket connexion = null;
        private PrintWriter writer = null;
        private BufferedInputStream reader = null;


        private static int count = 0;
        private String name = "Client-";

        public Client(String host, int port){
            name += ++count;
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


                writer = new PrintWriter(connexion.getOutputStream(), true);
                reader = new BufferedInputStream(connexion.getInputStream());


                String commande = "PLAYER";
                writer.write(commande);
                writer.flush();

                System.out.println("Commande " + commande + " envoyée au serveur");

                //On attend la réponse
                String response = read();
                System.out.println("\t * " + name + " : Réponse reçue " + response);

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        writer.write("CLOSE");
        writer.flush();
        writer.close();
    }

    private String read() throws IOException{
        String res = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        res = new String(b, 0, stream);
        return res;
    }


}
