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

        System.out.print("Введите номер: ");
        int num = in.nextInt();

        return num;

    }
    public void printClubOptions() {
        System.out.println("1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Clubs");

    }
    public int getChoice() {

        System.out.println("WELCOME TO OZONE FITNESS CENTER\n" +
                "================================\n" +
                "1) Add Member\n" +
                "2) Remove Member\n" +
                "3) Display Member Information\n" +
                "Please select an option (or Enter -1 to quit):\n");
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

        while (!members.contains(memberID =getIntInput() )) {
            System.out.print("Такого посетителя нет, введите ID удаляемого посетителя ещё раз: ");
        }
        System.out.println ("Вы ввели ID " + memberID);

        int finalMemberID = memberID;
        Member del = members.stream()
                .filter(d ->(d.getMemberID() == finalMemberID))
                .findFirst()
                .orElse(null);

        System.out.println ("Пользователь" + del.toString() + "был удален");
        members.remove(del);
            }

    public void printMemberInfo(LinkedList<Member> members) {
        int memberID;
        System.out.print("Для получения информации о посетителе введите его ID: ");

        while (!members.contains(memberID =getIntInput() )) {
            System.out.print("Такого посетителя нет, введите ID посетителя ещё раз: ");
        }
        System.out.println ("Вы ввели ID " + memberID);

        int finalMemberID = memberID;
        Member info = members.stream()
                .filter(d ->(d.getMemberID() == finalMemberID))
                .findFirst()
                .orElse(null);
        System.out.print(info.toString());
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


