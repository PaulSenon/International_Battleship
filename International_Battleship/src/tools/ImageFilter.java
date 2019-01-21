package tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFilter {

    /**
     * Parse all the pixel and set edging color in yellow
     * @param image
     * @return
     */
    public static BufferedImage edgingColoration(BufferedImage image){
        double maxPixelChanged = image.getHeight() * 0.05;
        int yellow = GameConfig.getEdgingColor();
        for(int x = 0; x < image.getWidth() - 1; x++) {
            int pixelChanged = 0;
            boolean afterThePicture = false;
            for (int y = 0; y < image.getHeight() - 1; y++) {
                Color color = new Color(image.getRGB(x, y), true);
                int alpha = color.getAlpha();
                if (alpha != 0) {
                    afterThePicture = true;
                }
                if (alpha == 0 && afterThePicture == true) {
                    image.setRGB(x, y, yellow);
                    pixelChanged++;
                }
                if (pixelChanged >= maxPixelChanged)
                    break;
            }
        }
        for(int x = 0; x < image.getWidth() - 1; x++) {
            int pixelChanged = 0;
            boolean afterThePicture = false;
            for (int y = image.getHeight() - 1 ; y > 0; y--) {
                Color color = new Color(image.getRGB(x, y), true);
                int alpha = color.getAlpha();
                if (alpha != 0) {
                    afterThePicture = true;
                }
                if (alpha == 0 && afterThePicture == true) {
                    image.setRGB(x, y, yellow);
                    pixelChanged++;
                }
                if (pixelChanged >= maxPixelChanged)
                    break;
            }
        }
        for(int y = 0; y < image.getHeight() - 1; y++) {
            int pixelChanged = 0;
            boolean afterThePicture = false;
            for (int x = 0; x < image.getWidth() - 1; x++) {
                Color color = new Color(image.getRGB(x, y), true);
                int alpha = color.getAlpha();
                if (alpha != 0) {
                    afterThePicture = true;
                }
                if (alpha == 0 && afterThePicture == true) {
                    image.setRGB(x, y, yellow);
                    pixelChanged++;
                }
                if (pixelChanged >= maxPixelChanged)
                    break;
            }
        }
        for(int y = image.getHeight() - 1; y > 0; y--) {
            int pixelChanged = 0;
            boolean afterThePicture = false;
            for (int x = image.getWidth() - 1; x > 0; x--) {
                Color color = new Color(image.getRGB(x, y), true);
                int alpha = color.getAlpha();
                if (alpha != 0) {
                    afterThePicture = true;
                }
                if (alpha == 0 && afterThePicture == true) {
                    image.setRGB(x, y, yellow);
                    pixelChanged++;
                }
                if (pixelChanged >= maxPixelChanged)
                    break;
            }
        }
        return image;
    }

}


