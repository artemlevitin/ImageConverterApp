package com.company;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        String inputImagePath = "C:/Temp/1.png";
        String outputImagePath1 = "C:/Temp/1_Fixed.png";
        String outputImagePath2 = "C:/Temp/1_Smaller.jpg";


        try {
            // resize to a fixed width (not proportional)
            int scaledWidth = 102;
            int scaledHeight = 76;
            ImageResizer.resize(inputImagePath, outputImagePath1, scaledWidth, scaledHeight);

            // resize smaller by 50%
            double percent = 0.5;
            ImageResizer.resize(inputImagePath, outputImagePath2, percent);



        } catch (IOException ex) {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }
    }

}
