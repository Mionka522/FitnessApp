package com.my.app;

import java.io.IOException;
import java.util.LinkedList;

public class JavaProject {
    public static void main(String[] args) throws IOException {
        String mem;
        MembershipManagement mm;
        FileHandler fh = new FileHandler();
        LinkedList<Member> members = FileHandler.readFile();
        int choice;
        System.out.println(members);

        mm = new MembershipManagement();
        choice =mm.getChoice();

        switch (choice) {
            case 1:
                mem = mm.addMembers(members);
                FileHandler.appendFile(mem);
            case 2:
                mm.removeMember(members);
            case 3:
                mm.printMemberInfo(members);
        }

    }
}
