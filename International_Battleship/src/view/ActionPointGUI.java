package view;

import tools.GameConfig;
import tools.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActionPointGUI extends JPanel{

    private ImageIcon torpedoUsed;
    private ImageIcon torpedoLoad;
    List<JLabel> listOfAP;
    String counterPA;

    /**
     * __CONSTRUCTOR__
     */
    public ActionPointGUI(){
        this.torpedoUsed= new ImageIcon(ImageManager.getImageCopy("PA/PAUsed.png"));
        this.torpedoLoad= new ImageIcon(ImageManager.getImageCopy("PA/PALoad.png"));
        this.setLayout(new GridLayout(20,1));
        this.setBackground(new Color(-16777216));
        this.listOfAP = new ArrayList<>();
        for(int i = 0; i < GameConfig.getMaxActionPoint(); i++) {
            BufferedImage torpedoImageForPA = ImageManager.getImageCopy("PA/PAUsed.png");
            listOfAP.add(new JLabel(new ImageIcon(torpedoImageForPA)));
            this.add(listOfAP.get(i));
        }
    }

    /**
     * Method which allow to repain the Action point Panel
     * @param nbActionPoint
     * @throws IOException
     */
    protected void setNbAP(int nbActionPoint) {
        System.out.println("Vous avez " + nbActionPoint + " point d'action disponible(s)\n");
        //Add Player Name
        for (int i = 0; i < nbActionPoint; i++) {
            this.listOfAP.get(i).setIcon(this.torpedoLoad);
        }
        for (int y = nbActionPoint; y < GameConfig.getMaxActionPoint(); y++){
            this.listOfAP.get(y).setIcon(this.torpedoUsed);
        }
        this.counterPA = nbActionPoint+"/"+GameConfig.getMaxActionPoint();
    }

    /**
     * When the player haven't enough Action Point, all action are disable
     */
    public void disableAction() {
        this.add(new JLabel("Point d'action insuffisant pour effectuer cette action"),BorderLayout.NORTH);
    }

    public String getCounterPA() {
        return counterPA;
    }

}
