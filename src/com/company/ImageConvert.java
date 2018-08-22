package com.company;

import java.util.concurrent.Semaphore;

public class ImageConvert {
    String inputImagePath;
    String outputImagePath;
    String formatName;
    Semaphore semph;


    public ImageConvert(String inputImagePath, String outputImagePath, Semaphore semph) throws InterruptedException {
        this.inputImagePath = inputImagePath;
        this.outputImagePath = outputImagePath;
        formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
        this.semph = semph;
        semph.acquire();
    }
}
