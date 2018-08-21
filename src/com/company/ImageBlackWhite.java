package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class ImageBlackWhite extends ImageConvert implements Runnable  {


   //Thread t ;

    public  ImageBlackWhite(String inputImagePath,String outputImagePath,Semaphore smph){
        super(inputImagePath, outputImagePath, smph);
       // t = new Thread(this,"ImageResizer_"+inputImagePath.substring(inputImagePath.lastIndexOf("/")+1));
       // t.start();
    }



    static void  convert(String inputImagePath,String outputImagePath) throws IOException {

    BufferedImage orginalImage = ImageIO.read(new File(inputImagePath));

    BufferedImage blackAndWhiteImg = new BufferedImage(
            orginalImage.getWidth(), orginalImage.getHeight(),
            BufferedImage.TYPE_BYTE_BINARY);

    Graphics2D graphics = blackAndWhiteImg.createGraphics();
        graphics.drawImage(orginalImage, 0, 0, null);

    ImageIO.write(blackAndWhiteImg, "png", new File(outputImagePath));


    }


    @Override
    public void run() {
        try {
            semph.acquire();
            BufferedImage orginalImage = ImageIO.read(new File(inputImagePath));

            BufferedImage blackAndWhiteImg = new BufferedImage(
                    orginalImage.getWidth(), orginalImage.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY);

            Graphics2D graphics = blackAndWhiteImg.createGraphics();
            graphics.drawImage(orginalImage, 0, 0, null);

            ImageIO.write(blackAndWhiteImg, formatName, new File(outputImagePath));
            System.out.println(Thread.currentThread()+ " " + this.toString() + inputImagePath);
        }
        catch(Exception exc){
            System.out.println(exc.getMessage());
        }
        finally {
            semph.release();
        }
    }
}