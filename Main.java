package com.khamurai.labb2;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Create scanner to read user input
        Scanner sc = new Scanner(System.in);
        String input = "";
        System.out.println("Enter a search term:");
        input = sc.nextLine();
        //Get base directory
        File directory = new File(".");
        //Get list of files in base directory
        File[] files = directory.listFiles();
        for(File f : files) {
            printFileInfo(f, input);
        }
        sc.close();
    }

    public static void printFileInfo(File file, String searchTerm) {
        if(!file.canRead()) {
            //If file can't be read, print error message and return
            System.err.println("Could not access " + file.getName() + " at: " +
                    file.getAbsolutePath());
            return;
        }

        if(file.isDirectory()) {
            //If file is a directory, get all folders and files and iterate through them
            File[] directoryFiles = file.listFiles();
            for(File f : directoryFiles) {
                printFileInfo(f, searchTerm);
            }
        } else if(file.isFile()){
            //If file, read file information
            try {
                Scanner fileReader = new Scanner(file);
                while(fileReader.hasNextLine()) {
                    //Compare file contents with search term
                    if(fileReader.nextLine().contains(searchTerm)) {
                        //If match, print file path
                        System.out.println(file.getAbsolutePath());
                    }
                }
                fileReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
