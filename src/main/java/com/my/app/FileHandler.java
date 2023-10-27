package com.my.app;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class FileHandler {

    public static LinkedList<Member> members;

    static {
        try {
            members = readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FileHandler() throws IOException {
    }

    public static LinkedList<Member> readFile() throws IOException {

       LinkedList<Member> members = new LinkedList<>();

       Path path = Path.of("src","main","resources", "FitnessDOC3.csv");
       Files.lines(path)
               .skip(1)
               .map(line -> {
                   String[] fields = line.split(";");
                   return new Member(fields[0], Integer.parseInt(fields[1]), fields[2], Double.parseDouble(fields[3]));
               }).forEach(members::add);

       return members;

   }



    public static void appendFile(String mem){
        try {
            FileWriter writer = new FileWriter("FitnessDOC3.temp", true);

            writer.write(mem +"\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Возникла ошибка во время записи, проверьте данные.");
        }

    }
      public void overWriteFile(LinkedList<Member> m){

      }
}
