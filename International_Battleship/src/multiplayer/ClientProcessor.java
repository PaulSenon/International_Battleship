/**
 * Je suis une classe du serveur et non du client
 */
package multiplayer;

import model.GameModel;
import model.Player;
import model.PlayerInterface;
import tools.ProcessedPosition;
import tools.ProcessedProps;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ClientProcessor implements Runnable{

	private Socket sock;
	private ObjectOutputStream writer = null;
	private ObjectInputStream reader = null;
	private int idPlayer = 1;
	private GameModel gameModel;
	private Server server;

	public ClientProcessor(Socket pSock, int nbConnect, GameModel gameModel, Runnable runnable){
		this.sock = pSock;
	}

	public ClientProcessor(Socket pSock,int idPlayer){
		this.sock = pSock;
		this.idPlayer = idPlayer;
	}
	public ClientProcessor(Socket pSock, int idPlayer, GameModel gameModel, Server server){
		this.sock = pSock;
		this.idPlayer = idPlayer;
		this.gameModel = gameModel;
		this.server = server;
	}

	//Le traitement lancé dans un thread séparé
	public void run(){
		try {
			System.err.println("Lancement du traitement de la connexion cliente");
			writer = new ObjectOutputStream(sock.getOutputStream());
			reader = new ObjectInputStream(sock.getInputStream());
			boolean closeConnexion = false;


			//Create the player
			gameModel.createPlayer(idPlayer);

			//Tant que la connexion est active, on traite les demandes
			while(!sock.isClosed()){




				//On attend la demande du client
				System.out.println("J'attends une commande client");
				Object response = read();

				System.out.println("Raw response : " + response);

				//On traite la demande du client
				if(response instanceof Player){
					System.out.println(((Player)response).toString());
				}
				if(response instanceof String){
					switch((String)response){
						case "close":
							closeConnexion = true;
							break;
						case "getPlayer":
							System.out.println("Je suis dans la fonction getPlayer");
							writer.writeObject(new Player(idPlayer, "Player"+idPlayer,"Port"+idPlayer));
							writer.flush();
							break;
						case "endOfTurn":
							diffuseMessage((String)response);
							break;
						default:
							diffuseMessage((String)response);
					}

				}else if(response instanceof ProcessedPosition){
					ProcessedPosition pp = (ProcessedPosition)response;
					diffuse(pp);
				}else if(response instanceof ProcessedProps) {
					ProcessedProps pp = (ProcessedProps) response;
					diffuse(pp);
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

	private void diffuseMessage(String msg) {
		server.updateMessage(msg,idPlayer);
	}

	//La méthode que nous utilisons pour lire les réponses
	private Object read() throws IOException, ClassNotFoundException {
		Object response;
		response =  reader.readObject();
		return response;
	}

	/**
	 * Diffuse the processedPosition to the server which should update all the
	 * client expect the one attache to this clientProcessor
	 * @param processedPosition
	 */
	public void diffuse(ProcessedPosition processedPosition){
		server.update(processedPosition,idPlayer);
	}

	/**
	 * Diffuse the processedPosition to the server which should update all the
	 * client expect the one attache to this clientProcessor
	 * @param processedProps
	 */
	public void diffuse(ProcessedProps processedProps){
		server.update(processedProps,idPlayer);
	}

	public int getID(){
		return this.idPlayer;
	}

	/**
	 * This method update the model and the client view after receive the order from the server
	 * @param processedPosition
	 */
	public void update(ProcessedPosition processedPosition){
		try {
			writer.writeObject(processedPosition);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//TODO faire pour des Object
	/**
	 * This method update the model and the client view after receive the order from the server
	 * @param processedProps
	 */
	public void update(ProcessedProps processedProps){
		try {
			writer.writeObject(processedProps);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method update the model and the client view after receive the order from the server
	 * @param msg
	 */
	public void sendMessage(String msg){
		try {
			writer.writeObject(msg);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startGame(List<PlayerInterface> players) {
		try {
		    //Send the player of the client
            writer.writeObject(players.get(idPlayer-1));
            writer.flush();
			writer.writeObject("start");
			writer.flush();
			writer.writeObject(players);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
