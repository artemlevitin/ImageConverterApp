package com.company;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import javax.imageio.ImageIO;

public class ImageResizer extends ImageConvert implements Runnable{

    double percent;


    public ImageResizer(String inputImagePath, String outputImagePath, double percent, Semaphore semph) throws InterruptedException {
       super(inputImagePath,outputImagePath, semph) ;
        this.percent = percent;

    }




    public void run() {

        try {

            File inputFile = new File(inputImagePath);
            BufferedImage inputImage = ImageIO.read(inputFile);
            int scaledWidth = (int) (inputImage.getWidth() * percent);
            int scaledHeight = (int) (inputImage.getHeight() * percent);


            // creates output image
            BufferedImage outputImage = new BufferedImage(scaledWidth,
                    scaledHeight, inputImage.getType());

            // scales the input image to the output image
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();


            // writes to output file
            ImageIO.write(outputImage, formatName, new File(outputImagePath));

            System.out.println(Thread.currentThread().getName()+ " " + this.toString() + " " +  inputImagePath);


        }
    catch(Exception e){
            e.getStackTrace();
        }
    finally {
            semph.release();
        }

    }

}