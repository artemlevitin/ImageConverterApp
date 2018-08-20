package com.company;

import java.util.Scanner;

public class Menu {

    String srcFolderPath;
    String outFolderPath;
    float percentResize;
    boolean blackWhite;
    boolean allGood = false;

    public Menu(){
        allGood = getData();
    }
    private boolean getData() {

        Scanner scn = new Scanner(System.in);

        System.out.println("Type please path to sourse folder");
        srcFolderPath = scn.nextLine() + "\\";

        System.out.println("Type please path to output folder");
        outFolderPath = scn.nextLine()+ "\\";

        System.out.println("What is percent Resize for new image(0,1 is 10%) ?");
        percentResize = scn.nextFloat();

        System.out.println("Would you like to make Black and White image type Y");
        blackWhite = (Character.toUpperCase(scn.next().charAt(0)) == 'Y')  ? true : false;

        System.out.println("sourse folder" + srcFolderPath +"\n" + "output folder" + outFolderPath + "\n" + "Scale:" + percentResize + ", BlackWhite -"+ blackWhite);

        System.out.println("That is correct type Y");

        if(Character.toUpperCase(scn.next().charAt(0)) == 'Y')
            return true;
        else
            return false;

    }

}
