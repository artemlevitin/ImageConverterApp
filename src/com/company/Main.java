package com.company;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws Exception {

        String srcFolderPath = System.getProperty("user.dir") + "/Image/";
        String outFolderPath = srcFolderPath + "Convert/";

        ExecutorService ex = Executors.newFixedThreadPool(3);

        // resize smaller by 50%
        double percent = 0.5;

        try {
            File srcFiles = new File(srcFolderPath);
            File outFolder = new File(outFolderPath);
            outFolder.mkdir();


            for( String file_Name : srcFiles.list() ) {
                // resize to a fixed width (not proportional)
            if(!checkFileIsImage(file_Name))
                     break;
               //  ImageResizer.resize(srcFolderPath + file_Name, outFolderPath + file_Name , scaledWidth, scaledHeight);
               // ImageBlackWhite.convert(srcFolderPath + file_Name, outFolderPath + file_Name);
               // ImageResizer.resize(srcFolderPath + file_Name, outFolderPath + file_Name , percent);
                ex.execute( new ImageBlackWhite(srcFolderPath + file_Name, outFolderPath + file_Name));
                ex.execute( new ImageResizer(srcFolderPath + file_Name, outFolderPath + file_Name, percent) );
            }
            ex.shutdown();
        } catch (Exception exc) {
            System.out.println("Error resizing the image.");
            exc.printStackTrace();
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
