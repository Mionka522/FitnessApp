package com.my.app;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import static com.my.app.FileHandler.readFile;

public class MembershipManagement implements Calculator {
    LinkedList<Member> members = readFile();
    int PEOPLE_ID =members.size();
    Scanner in = new Scanner(System.in);
    public MembershipManagement() throws IOException {
    }

    public int getIntInput() {
        // метод вызывается каждый раз, когда любой метод в классе MembershipManagement
        // использует вызов System.out.println() для того,
        // чтобы запросить у пользователя значение int

        int num = in.nextInt();

        return num;

    }
    public void printClubOptions() {
        //вывод информации
        System.out.println("1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Clubs");
        System.out.println("\n");
        System.out.println("Выберете клуб");

    }
    public int getChoice() {
        //Вывод информации о существующих командах

        System.out.println("\nWELCOME TO OZONE FITNESS CENTER\n" +"\n"+
                "================================\n" +"\n"+
                "1) Add Member\n" +
                "2) Remove Member\n" +
                "3) Display Member Information\n" +
                "4) Show All\n" +
                "Please select an option (or Enter -1 to quit):\n" +

                "\n"+"Выберете действие: ");
        int choice = getIntInput();
        return choice;
    }
    public String addMembers(LinkedList<Member> members) {
        //добавить посетителя в LinkedList и возвращает строку,
        // которая в методе аppendFile() будет записана в CSV файл
        this.members = members;
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
        this.members.add(mbr);
        return mem+";"+memberID +";"+name+";"+cal;
    }

    @Override
    public double calculateFees(int clubID) {
        //подсчет оплаты для каждого клуба

        switch (clubID) {
            case 1: return 900;
            case 2: return 950;
            case 3: return 1000;
            default:return -1;
        }
    }
    public void removeMember(LinkedList<Member> members) {
        //удаление пользователя по ID из LinkedList . Этот лист без данного пользователя
        // используется в качестве параметра в методе overwriteFile()
        // для перезаписи на его основе файла

        this.members = members;

        System.out.print("Введите ID удаляемого посетителя или -1 для выхода в меню: ");
        Member finded = null;

        while (true) {
            int memberID = getIntInput();
            if(memberID == -1) {
                System.out.println("Возврат в меню...");
                break;
            }
            for (Member member : this.members) {
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
                this.members.remove(finded);
                System.out.println("Посетитель был удален");
                break;
            }
        }

        }

    public void printMemberInfo(LinkedList<Member> members) {
        this.members = members;

        System.out.print("Введите ID посетителя , о котором хотите получить информацию: ");
        Member finded = null;

        while (true) {
            int memberID = getIntInput();
            for (Member member : this.members) {
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
    public void overwriteFile(LinkedList<Member> members) throws IOException {
        String fileName = "FitnessDOC3.csv";
        String title = "Тип посетителя;Номер;клуба;Имя;Оплата";
        this.members = members;
        File newFileTemp = File.createTempFile("FitnessDOC3", ".temp", new File("D:\\IdeaProjects\\Fitnes"));
        BufferedWriter writerTemp = new BufferedWriter(new FileWriter(newFileTemp));
        File oldF = new File("FitnessDOC3.csv");

        writerTemp.write(title + "\n");
        this.members.stream().forEach(s ->
        {
            try {
                writerTemp.write(s.toStringForFile()+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        writerTemp.close();


        oldF.delete();
        newFileTemp.renameTo(new File(fileName));


    }
    public void showAll() throws IOException {
        LinkedList<Member> showMembers = readFile();
        showMembers.stream().forEach(System.out::println);
    }

}


