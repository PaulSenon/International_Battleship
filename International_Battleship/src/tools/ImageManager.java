package tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {
    private static ImageManager instance = null;
    private static Map<String, BufferedImage> images;
    private static String basePath;
    public static BufferedImage[] ExplosionArray;
    public static BufferedImage[] ExplomissArray;

    public static ImageManager newInstance() throws IOException {
        if (instance == null)
            instance = new ImageManager();
        return instance;
    }

    private ImageManager() throws IOException {
        ImageManager.images = new HashMap<>();
        ImageManager.basePath = defineResourcesBasePath();
        ImageManager.preLoadImages();
        this.setExplosionArray(17);
        this.setExplomissArray(27);
    }

    private void setExplosionArray(int totalFrames) {
        this.ExplosionArray = new BufferedImage[totalFrames];
        for (int i=1; i<this.ExplosionArray.length; i++){
            try {
                BufferedImage gifImage = ImageManager.getImageCopy("explosion/explosion" + i + ".png");
                this.ExplosionArray[i] = gifImage;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setExplomissArray(int totalFrames) {
        this.ExplomissArray = new BufferedImage[totalFrames];
        for (int i=1; i<this.ExplomissArray.length; i++){
            try {
                BufferedImage gifImage = ImageManager.getImageCopy("explomiss/explomiss" + i + ".png");
                this.ExplomissArray[i] = gifImage;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static BufferedImage[] getExplosionArray() {
        return ExplosionArray;
    }

    public static BufferedImage[] getExplomissArray() {
        return ExplomissArray;
    }

    /**
     * Allow to load image before requesting them (preload images in RAM)
     * It's not necessary, if you request an image that's not loaded, it will
     * load it automatically on demand.
     */
    private static void preLoadImages() {
        String[] imageNames = {
                "Aircraft/0.png",
                "Aircraft/1.png",
                "Aircraft/2.png",
                "Aircraft/3.png",
                "Aircraft/4.png",
                "Cruiser/0.png",
                "Cruiser/1.png",
                "Cruiser/2.png",
                "Cruiser/3.png",
                "Submarin/0.png",
                "Submarin/1.png",
                "Submarin/2.png",
                "Torpedo/0.png",
                "Torpedo/1.png",
                "Sentinel/0.png",
                "fog.png"
        };

        for(String name : imageNames){
            try {
                ImageManager.getImageRef(name);
            }catch (IOException e){
                System.out.println("ERROR ImageManager : failed to preload image '"+name+"'");
            }
        }
    }

    /**
     * get a base image
     * (/!\ base image must not be changed, use getImageCopy to work on a safe copy)
     * @param imageName is a path Name to an image (from resources folder)
     * @return BufferedImage ref
     * @throws IOException
     */
    private static BufferedImage getImageRef(String imageName) throws IOException {
        if(ImageManager.images.containsKey(imageName)){
            return ImageManager.images.get(imageName);
        }else{
            String filePath = Paths.get(ImageManager.basePath, imageName).toString();
            BufferedImage image = ImageIO.read(new File(filePath));
            ImageManager.images.put(imageName, image);
            return image;
        }
    }

    /**
     * It get a copy of an image
     * @param imageName is the name of the image
     * @return a BufferedImage (copy from base image)
     * @throws IOException
     */
    public static BufferedImage getImageCopy(String imageName) throws IOException {
        return ImageManager.deepCopy(ImageManager.getImageRef(imageName));
    }

    /**
     * It get an image and rotate it to a desired angle (in degree)
     * @param imageName is the image name you want to get and rotate
     * @param angleDegree is the rotation angle in degree
     * @return resultImage
     */
    public static BufferedImage getImageCopyRotated(String imageName, double angleDegree) throws IOException {
        BufferedImage sourceImage = ImageManager.getImageRef(imageName);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = destImage.createGraphics();

        AffineTransform transform = new AffineTransform();
        transform.rotate(angleDegree / 180 * Math.PI, width / 2 , height / 2);
        g2d.drawRenderedImage(sourceImage, transform);

        g2d.dispose();
        return destImage;
    }

    /**
     * Just copy a image to another, not just the reference
     * @param bufferedImage is the image to copy byte by byte
     * @return BufferedImage is a total new ref, but look the same as input image
     */
    private static BufferedImage deepCopy(BufferedImage bufferedImage) {
        ColorModel cm = bufferedImage.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bufferedImage.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    /**
     * Define the resource folder (depends on how the project has been imported)
     * @return the resources folder path
     * @throws FileNotFoundException
     */
    private static String defineResourcesBasePath() throws FileNotFoundException {
        String[] pathToTry = {
                "International_Battleship/resources/",
                "resources/",
                "resources\\",
                "International_Battleship\\resources\\",
        };

        for(String path : pathToTry){
            if (new File(path).isDirectory()) {
                return path;
            }
        }
        throw new FileNotFoundException();
    }

}
