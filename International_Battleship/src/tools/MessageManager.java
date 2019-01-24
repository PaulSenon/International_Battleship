package tools;

import java.util.HashMap;
import java.util.Map;

public class MessageManager {

    private static MessageManager instance = null;
    private static Map<String, MessageDisplayInterface> displays;

    public static MessageManager newInstance() {
        instance = new  MessageManager();
        return instance;
    }

    public static MessageManager getInstance() {
        if (instance == null){
            return newInstance();
        }
        return instance;
    }

    public static void addDisplay(String displayName, MessageDisplayInterface display){
        displays.put(displayName, display);
    }

    public static void removeDisplay(String displayName){
        displays.remove(displayName);
    }

    public static void displayMessage(String displayName, String message){
        MessageDisplayInterface messageDisplay = displays.get(displayName);
        if(messageDisplay != null){
            messageDisplay.displayMessage(message);
        }
    }
    public static void displayMessageConsole(String displayName, String message){
        MessageDisplayInterface messageDisplay = displays.get(displayName);
        if(messageDisplay != null){
            messageDisplay.displayMessageConsole(message);
        }
    }
    public static void displayMessagePopUp(String displayName, String message){
        MessageDisplayInterface messageDisplay = displays.get(displayName);
        if(messageDisplay != null){
            messageDisplay.displayMessagePopUp(message);
        }
    }

    public static void broadcastMessage(String message){
        for(Map.Entry<String, MessageDisplayInterface> entry : displays.entrySet()){
            entry.getValue().displayMessage(message);
        }
    }
    public static void broadcastMessageConsole(String message){
        for(Map.Entry<String, MessageDisplayInterface> entry : displays.entrySet()){
            entry.getValue().displayMessageConsole(message);
        }
    }
    public static void broadcastMessagePopUp(String message){
        for(Map.Entry<String, MessageDisplayInterface> entry : displays.entrySet()){
            entry.getValue().displayMessagePopUp(message);
        }
    }

    private MessageManager(){
        displays = new HashMap<>();
    }

}
