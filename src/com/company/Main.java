package com.company;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws Exception {
        String srcFolderPath = System.getProperty("user.dir") + "/Image/";
        String outFolderPath = srcFolderPath + "Convert/";
        double percentResize = 0.5;
        boolean blackWhite = false;


        Menu m =new Menu();
        if(m.allGood){
            srcFolderPath = m.srcFolderPath;
            outFolderPath = m.outFolderPath;
            percentResize = m.percentResize;
            blackWhite = m.blackWhite;
        }




        ExecutorService ex = Executors.newFixedThreadPool(3);



        try {
            File srcFiles = new File(srcFolderPath);
            File outFolder = new File(outFolderPath);
            outFolder.mkdir();


            for( String file_Name : srcFiles.list() ) {

            if(!checkFileIsImage(file_Name))
                    continue;
                if(blackWhite) {
                    ex.execute(new ImageBlackWhite(srcFolderPath + file_Name, outFolderPath + file_Name));
                    ex.execute(new ImageResizer(outFolderPath + file_Name, outFolderPath + file_Name, percentResize));
                }
               else
                   ex.execute( new ImageResizer(srcFolderPath + file_Name, outFolderPath + file_Name, percentResize) );
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
