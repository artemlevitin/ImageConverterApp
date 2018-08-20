package com.company;

import java.awt.image.BufferedImage;

public class ImageConvert {
    String inputImagePath;
    String outputImagePath;
    String formatName;


    public ImageConvert(String inputImagePath, String outputImagePath) {
        this.inputImagePath = inputImagePath;
        this.outputImagePath = outputImagePath;
        formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
    }
}
