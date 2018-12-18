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
    JPanel APJpan;

    /**
     * __CONSTRUCTOR__
     */
    public ActionPointGUI(){
        this.torpedoUsed= new ImageIcon(ImageManager.getImageCopy("PA/torpedoUsed.png"));
        this.torpedoLoad= new ImageIcon(ImageManager.getImageCopy("PA/torpedoLoad.png"));
        this.APJpan = new JPanel(new GridLayout(2,10));
        this.setLayout(new BorderLayout(5,5));
        //this.add(new JLabel(""),BorderLayout.EAST);
        this.add(this.APJpan,BorderLayout.CENTER);
        this.listOfAP = new ArrayList<>();
        for(int i = 0; i < GameConfig.getMaxActionPoint(); i++) {
            BufferedImage torpedoImageForPA = ImageManager.getImageCopy("PA/torpedoUsed.png");
            listOfAP.add(new JLabel(new ImageIcon(torpedoImageForPA)));
            this.APJpan.add(listOfAP.get(i));
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
    }

    /**
     * When the player haven't enough Action Point, all action are disable
     */
    public void disableAction() {
        this.add(new JLabel("Point d'action insuffisant pour effectuer cette action"),BorderLayout.NORTH);
    }
}
