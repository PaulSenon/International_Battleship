package tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFilter {
    public static BufferedImage edgingColoration(BufferedImage image){
        //Parse all the pixel and set edging color in yellow
        for(int x = 0; x < image.getWidth() - 1; x++) {
            image = heightParse(image,x);
            //image = heightParseDegeu(image,x);
        }
        for(int x = image.getWidth() - 1; x > 0; x--) {
            image = heightParse(image,x);
        }
        for(int y = 0; y < image.getHeight() - 1; y++) {
            image = widthParse(image,y);
        }
        for(int y = image.getHeight() - 1; y > 0; y--) {
            image = widthParse(image,y);
        }
        return image;
    }

    public static BufferedImage heightParseDegeu (BufferedImage image, int x) {
        int yellow = new Color(255, 215, 0, 127).getRGB();
        for (int y = 0; y < image.getHeight() - 1; y++) {
            if (image.getRGB(x, y) == 0) {
                image.setRGB(x, y, yellow);
            }
        }
        return image;
    }

    public static BufferedImage heightParse (BufferedImage image, int x) {
        int pixelChanged = 0;
        boolean afterThePicture = false;
        int yellow = new Color(255, 215, 0, 127).getRGB();
        for (int y = 0; y < image.getHeight() - 1; y++) {
            if (image.getRGB(x, y) != 0) {
                afterThePicture = true;
            }
            if (image.getRGB(x, y) == 0 && image.getRGB(x,y) != yellow && afterThePicture == true) {
                image.setRGB(x, y, yellow);
                pixelChanged++;
            }
            if (pixelChanged >= 20)
                break;
        }
        return image;
    }

    public static BufferedImage widthParse (BufferedImage image, int y) {
        int pixelChanged = 0;
        boolean afterThePicture = false;
        int yellow = new Color(255, 215, 0, 127).getRGB();
        for (int x = 0; x < image.getWidth() - 1; x++) {
            if (image.getRGB(x, y) != 0) {
                afterThePicture = true;
            }
            if (image.getRGB(x, y) == 0 && image.getRGB(x,y) != yellow && afterThePicture == true) {
                image.setRGB(x, y, yellow);
                pixelChanged++;
            }
            if (pixelChanged >= 20)
                break;
        }
        return image;
    }
}


