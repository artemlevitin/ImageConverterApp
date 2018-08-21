package com.company;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import javax.imageio.ImageIO;

public class ImageResizer extends ImageConvert implements Runnable{

    double percent;

   // Thread t;

    public ImageResizer(String inputImagePath, String outputImagePath, double percent, Semaphore semph){
       super(inputImagePath,outputImagePath, semph) ;
        this.percent = percent;
      //  t = new Thread(this,"ImageResizer_"+inputImagePath.substring(inputImagePath.lastIndexOf("/")+1));
       // t.start();
    }


    public void resize(String inputImagePath,
                              String outputImagePath, int scaledWidth, int scaledHeight) {

        try {
            semph.acquire();

            // reads input image
            BufferedImage inputImage = ImageIO.read(new File(inputImagePath));

            // creates output image
            BufferedImage outputImage = new BufferedImage(scaledWidth,
                    scaledHeight, inputImage.getType());

            // scales the input image to the output image
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();

        /*// extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);*/

            // writes to output file
            ImageIO.write(outputImage, formatName, new File(outputImagePath));

            System.out.println(Thread.currentThread()+ " " + this.toString() + inputImagePath);

        }

    catch(Exception exc){
        System.out.println(exc.getMessage());
        }
    finally {
            semph.release();
        }
    }


    public  void resize(String inputImagePath,
                              String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }

    public void run() {

        try {
            File inputFile = new File(inputImagePath);
            BufferedImage inputImage = ImageIO.read(inputFile);
            int scaledWidth = (int) (inputImage.getWidth() * percent);
            int scaledHeight = (int) (inputImage.getHeight() * percent);
            resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);

    }
    catch(Exception e){
            e.getStackTrace();
        }


    }

}