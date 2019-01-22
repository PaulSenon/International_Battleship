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

    public static BufferedImage tintImage(BufferedImage image, Color color){
        BufferedImage tintedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = tintedImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        float r = (float) color.getRed() / 255;
        float g = (float) color.getGreen() / 255;
        float b = (float) color.getBlue() / 255;
        float a = (float) color.getAlpha() / 255;
        for (int i = 0; i < tintedImage.getWidth(); i++){
            for (int j = 0; j < tintedImage.getHeight(); j++){
                int ax = tintedImage.getColorModel().getAlpha(tintedImage.getRaster().getDataElements(i, j, null));
                int rx = tintedImage.getColorModel().getRed(tintedImage.getRaster().getDataElements(i, j, null));
                int gx = tintedImage.getColorModel().getGreen(tintedImage.getRaster().getDataElements(i, j, null));
                int bx = tintedImage.getColorModel().getBlue(tintedImage.getRaster().getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                ax *= a;
                tintedImage.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx));
            }
        }
        return tintedImage;
    }
    
}


