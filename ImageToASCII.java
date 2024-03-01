import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.io.FileWriter;

public class ImageToASCII {
    public static void main(String[] args) {
        arrayToTextFile("fallen-angel.png", "fallen-angel-ascii");
    }

    //Method to turn every pixel from input image to corresponding brightness level
    public static float[][] pixelBrightness(String fileName){
        int red;
        int green;
        int blue;
        int color;

        try{
            //Load image
            File input = new File(fileName);
            BufferedImage image = ImageIO.read(input);
            //Create 2D Array the size of the image
            float[][] pixels = new float[image.getWidth()][image.getHeight()];
            for(int i=0; i<image.getWidth(); i++){
                for(int j=0; j<image.getHeight(); j++){
                    //Calculating brightness level and writing in array
                    color = image.getRGB(i, j);
                    red   = (color >>> 16) & 0xFF;
                    green = (color >>>  8) & 0xFF;
                    blue  = (color) & 0xFF;

                    pixels[i][j] = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
                }
            }
            return pixels;
        }catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException("Import doesnt work");
        }
    }

    //Method to write corresponding ASCII character for every brightness pixel
    public static void arrayToTextFile(String fileName, String newFileName){
        float[][] pixels = pixelBrightness(fileName);
        try {
            //Create text file
            FileWriter textFile = new FileWriter(newFileName+".txt");
            for(int j=0; j<pixels[0].length; j++){
                for(int i=0; i<pixels.length; i++){
                    //Based on brightness level chosing ASCII character
                    if(pixels[i][j] < 0.1){
                        textFile.write("@");
                    }
                    else if(pixels[i][j] >= 0.1 && pixels[i][j] < 0.2){
                        textFile.write("%");
                    }
                    else if(pixels[i][j] >= 0.2 && pixels[i][j] < 0.3){
                        textFile.write("#");
                    }
                    else if(pixels[i][j] >= 0.3 && pixels[i][j] < 0.4){
                        textFile.write("*");
                    }
                    else if(pixels[i][j] >= 0.4 && pixels[i][j] < 0.5){
                        textFile.write("+");
                    }
                    else if(pixels[i][j] >= 0.5 && pixels[i][j] < 0.6){
                        textFile.write("=");
                    }
                    else if(pixels[i][j] >= 0.6 && pixels[i][j] < 0.7){
                        textFile.write("-");
                    }
                    else if(pixels[i][j] >= 0.7 && pixels[i][j] < 0.8){
                        textFile.write(":");
                    }
                    else if(pixels[i][j] >= 0.8 && pixels[i][j] < 0.9){
                        textFile.write(".");
                    }
                    else{
                        textFile.write(" ");
                    }
                }
                textFile.write("\n");
            }
            textFile.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("The Image couldnt be constructed", e);
        }
    }
}