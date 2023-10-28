package com.my.app;

import java.io.IOException;
import java.util.LinkedList;

public class JavaProject {
    public static void main(String[] args) throws IOException {
        menu();
    }
    public static void menu() throws IOException {
        String newMember;
        MembershipManagement mm;
        LinkedList<Member> members = FileHandler.readFile();
        mm = new MembershipManagement();


        switch (mm.getChoice()) {
            case 1:
                newMember = mm.addMembers(members); //метод добавления формирования String newMember из полученной информации
                FileHandler.appendFile(newMember);//добавление newMember в файл FitnessDOC3.csv
                members.stream().forEach(System.out::println);

                menu();//возврат к выбору действия

            case 2:
                mm.removeMember(members); //удалить посетителя из LinkedList
                mm.overwriteFile(members); //перезаписать файл CSV на основе обновленного LinkedList
                menu(); //возврат в меню
            case 3:
                mm.printMemberInfo(members); //вывести информацию о конкретном посетителе
                menu();//возврат в меню

            case 4:
                mm.showAll(); //показать всех в файле CSV
                menu();//возврат в меню
        }

    }
}
