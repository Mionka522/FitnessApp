package com.my.app;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement implements Calculator {
    LinkedList<Member> members = FileHandler.readFile();
    int PEOPLE_ID =members.size();
    Scanner in = new Scanner(System.in);
    public MembershipManagement() throws IOException {
    }

    public int getIntInput() {

        int num = in.nextInt();

        return num;

    }
    public void printClubOptions() {
        System.out.println("1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Clubs");
        System.out.println("\n");
        System.out.println("Выберете клуб");

    }
    public int getChoice() {

        System.out.println("\nWELCOME TO OZONE FITNESS CENTER\n" +"\n"+
                "================================\n" +"\n"+
                "1) Add Member\n" +
                "2) Remove Member\n" +
                "3) Display Member Information\n" +
                "Please select an option (or Enter -1 to quit):\n" +
                "\n"+"Выберете действие: ");
        int choice = getIntInput();
        return choice;
    }
    public String addMembers(LinkedList<Member> members) {
        int club;
        String name;
        String mem;

        printClubOptions();



        System.out.print("Введите номер клуба: ");
        club= in.nextInt();

        System.out.println("Введите информацию о новом посетителе");

        System.out.print("Введите Имя: ");
        name = in.next();

        System.out.print("Введите тип посетителя: ");
        mem = in.next();


        int memberID= ++PEOPLE_ID;
        Member mbr;
        double cal = calculateFees(club);
        mbr = new Member(mem,memberID,name,cal);
        members.add(mbr);
        return mem+";"+memberID +";"+name+";"+cal;
    }

    @Override
    public double calculateFees(int clubID) {

        switch (clubID) {
            case 1: return 900;
            case 2: return 950;
            case 3: return 1000;
            default:return -1;
        }
    }
    public void removeMember(LinkedList<Member> members) {
        int memberID;
        System.out.print("Введите ID удаляемого посетителя: ");
        Member finded = null;
        while (finded == null)
        for(Member member : members) {
            if (member.getMemberID() == getIntInput()) {
                finded = member;
                break;
            } else {
                System.out.print("Такого посетителя нет, введите ID удаляемого посетителя ещё раз: ");
            }
        }
            System.out.println("Вы ввели ID " + finded.getMemberID());
        members.remove(finded);
        System.out.println("Посетитель был удален");
        }

    public void printMemberInfo(LinkedList<Member> members) {

        System.out.print("Введите ID посетителя , о котором хотите получить информацию: ");
        Member finded = null;

        while (true) {
            int memberID = getIntInput();
            for (Member member : members) {
                if (member.getMemberID() == memberID) {
                    finded = member;
                    break;
                }
            }
            if (finded == null) {
                System.out.print("Такого посетителя нет, введите ID удаляемого посетителя ещё раз: ");

            }

            if (finded != null) {
                System.out.println("Вы ввели ID " + finded.getMemberID());
                System.out.println(finded.toString());
                break;
            }
        }
    }
    public void overwriteFile(LinkedList<Member> m) throws IOException {
        String n = "FitnessDOC3.csv";
        File newFile = File.createTempFile("FitnessDOC3", ".temp", new File("D:\\IdeaProjects\\Fitnes\\src\\main\\resources"));
        FileWriter writer = new FileWriter(newFile, true);

       File file = Path.of("src","main","resources", "FitnessDOC3.temp").toFile();
       File oldF = new File("FitnessDOC3.csv");

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(oldF));
            String line = reader.readLine();

            while (line != null) {
                FileHandler.appendFile(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        oldF.delete();


       file.renameTo(new File(n));


    }

}


