package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class ImageBlackWhite extends ImageConvert implements Runnable  {


   //Thread t ;

    public  ImageBlackWhite(String inputImagePath,String outputImagePath,Semaphore smph) throws InterruptedException {
        super(inputImagePath, outputImagePath, smph);

    }






    @Override
    public void run() {
        try {

            BufferedImage orginalImage = ImageIO.read(new File(inputImagePath));

            BufferedImage blackAndWhiteImg = new BufferedImage(
                    orginalImage.getWidth(), orginalImage.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY);

            Graphics2D graphics = blackAndWhiteImg.createGraphics();
            graphics.drawImage(orginalImage, 0, 0, null);

            ImageIO.write(blackAndWhiteImg, formatName, new File(outputImagePath));
            System.out.println(Thread.currentThread().getName() + " " + this.toString() + inputImagePath);
        }
        catch(Exception exc){
            System.out.println(exc.getMessage());
        }
        finally {
            semph.release();
        }
    }
}