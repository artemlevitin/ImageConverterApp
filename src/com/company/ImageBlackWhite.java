package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageBlackWhite {

    public  ImageBlackWhite(){}

    static void  convert(String inputImagePath,String outputImagePath) throws IOException {

    BufferedImage orginalImage = ImageIO.read(new File(inputImagePath));

    BufferedImage blackAndWhiteImg = new BufferedImage(
            orginalImage.getWidth(), orginalImage.getHeight(),
            BufferedImage.TYPE_BYTE_BINARY);

    Graphics2D graphics = blackAndWhiteImg.createGraphics();
        graphics.drawImage(orginalImage, 0, 0, null);

    ImageIO.write(blackAndWhiteImg, "png", new File(outputImagePath));

    }


}