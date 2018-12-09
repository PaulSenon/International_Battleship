/**
 * Je suis une classe du serveur et non du client
 */
package multiplayer;

import model.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientProcessor implements Runnable{

	private Socket sock;
	private ObjectOutputStream writer = null;
	private ObjectInputStream reader = null;
	private int idPlayer = 1;

	public ClientProcessor(Socket pSock){
		this.sock = pSock;
	}

	public ClientProcessor(Socket pSock,int idPlayer){
		this.sock = pSock;
		this.idPlayer = idPlayer;
	}

	//Le traitement lancé dans un thread séparé
	public void run(){
		try {
			System.err.println("Lancement du traitement de la connexion cliente");
			writer = new ObjectOutputStream(sock.getOutputStream());
			reader = new ObjectInputStream(sock.getInputStream());
			boolean closeConnexion = false;
			//Tant que la connexion est active, on traite les demandes
			while(!sock.isClosed()){




				//On attend la demande du client
				System.out.println("J'attends une commande client");
				Object response = read();
		
				//On traite la demande du client
				String className = response.getClass().getName();
				if(response instanceof Player){
					System.out.println(((Player)response).toString());
				}
				if(response instanceof String){
					switch((String)response){
					case "getPlayer":
						System.out.println("Je suis dans la fonction getPlayer");
						writer.writeObject(new Player(1, "Player"+idPlayer,"Port"+idPlayer));
						writer.flush();
						break;
					case "close":
						closeConnexion = true;
					}

				}


				if(closeConnexion){
					System.err.println("COMMANDE CLOSE DETECTEE ! ");
					writer = null;
					reader = null;
					sock.close();
					break;
				}

			}
		}catch(SocketException e){
			System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} 

	//La méthode que nous utilisons pour lire les réponses
	private Object read() throws IOException, ClassNotFoundException {
		Object response;
		response =  reader.readObject();
		return response;
	}

}
