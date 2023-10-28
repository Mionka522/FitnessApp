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
        // Чтение из файла и перенос информации в LinkedList

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



    public static void appendFile(String newMember){
        // Запись нового посетителя в файл FitnessDOC3.csv
        try {

            FileWriter writer = new FileWriter("FitnessDOC3.csv", false);
            writer.write(newMember+"\n");
            writer.close();

            System.out.println("Добавлен "+newMember);

        } catch (IOException e) {
            System.out.println("Возникла ошибка во время записи, проверьте данные.");
        }

    }

}
