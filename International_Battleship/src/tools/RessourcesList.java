package tools;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class RessourcesList {
    private BufferedImage imageArray[];
    private BufferedImage imageArrayMissed[];
    private int totalFrames = 17;
    private int totalFramesMissed = 26;

    private BufferedImage gifImage;

    public RessourcesList(){
        imageArray = new BufferedImage[totalFrames];
        for (int i=1; i<imageArray.length; i++){
            try {
                gifImage = ImageManager.getImageCopy("explosion/explosion" + i + ".png");
                imageArray[i] = gifImage;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imageArrayMissed = new BufferedImage[totalFramesMissed];
        for (int i = 1; i < imageArrayMissed.length; i++) {
            try {
                gifImage = ImageManager.getImageCopy("explomiss/explomiss" + i + ".png");
                imageArrayMissed[i] = gifImage;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public BufferedImage[] getImageArray() {
        return imageArray;
    }

    public BufferedImage[] getImageArrayMissed() {
        return imageArrayMissed;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public int getTotalFramesMissed() {
        return totalFramesMissed;
    }
    
}
