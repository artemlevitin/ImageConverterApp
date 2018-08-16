package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageBlackWhite {

void convert() throws IOException {

    File file = new File("C:\\Users\\admin\\IdeaProjects\\ImageConverterApp\\Image\\1.png");
    BufferedImage orginalImage = ImageIO.read(file);

    BufferedImage blackAndWhiteImg = new BufferedImage(
            orginalImage.getWidth(), orginalImage.getHeight(),
            BufferedImage.TYPE_BYTE_BINARY);

    Graphics2D graphics = blackAndWhiteImg.createGraphics();
        graphics.drawImage(orginalImage, 0, 0, null);

    ImageIO.write(blackAndWhiteImg, "png", new File("C:\\Users\\admin\\IdeaProjects\\ImageConverterApp\\Image\\1_c.png"));

    }


}