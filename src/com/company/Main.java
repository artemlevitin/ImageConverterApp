package com.company;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        String srcFolderPath = System.getProperty("user.dir") + "/Image/";
        String outFolderPath = srcFolderPath + "Convert/";
       // String inputImagePath = "C:/Temp/1.png";
       // String outputImagePath1 = "C:/Temp/1_Fixed.png";
      //  String outputImagePath2 = inputFolder_Name + "1_Smaller.jpg";
        int scaledWidth = 102;
        int scaledHeight = 76;


        try {
            File srcFiles = new File(srcFolderPath);
            File outFolder = new File(outFolderPath);
            outFolder.mkdir();


            for( String file_Name : srcFiles.list() ) {
                // resize to a fixed width (not proportional)
            if(!checkFileIsImage(file_Name))
                     break;
                ImageResizer.resize(srcFolderPath + file_Name, outFolderPath + file_Name , scaledWidth, scaledHeight);

                // resize smaller by 50%
                /*double percent = 0.5;
                ImageResizer.resize(inputImagePath, outputImagePath2, percent);*/
            }


        } catch (IOException ex) {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }
    }

    private static boolean checkFileIsImage(String fName){
        String typesFile[] = new String[] {"jpg","jpeg","png"};

        for (String typeF: typesFile){
            if( fName.toLowerCase().contains(typeF) )
                return true;
        }
                   return false;
    }
}
