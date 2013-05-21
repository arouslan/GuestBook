package com.abushaev.GuestBook.GuestBook;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {

    protected static String getMessage(String[] args) {
        String st = "";
        for (int i = 1; i < args.length; i++) {
            st = st + " " + args[i];
        }
        return st;
    }

    public static void main(String[] args) {

        GuestBookDBController dbgb = new GuestBookDBController("SA", "");
        Scanner scn = new Scanner(System.in);
        String[] str;
        String st;


        try {
            while (true) {
                System.out.println("");
                System.out.println("wwwwwwwwwwwwwwwww ГОСТЕВАЯ КНИГА wwwwwwwwwwwwwwwwwwwwwwwwwww");
                System.out.println("для ввода записи используте команду ADD [текст сообщения]");
                System.out.println("для вывода всех записей используте команду LIST");
                System.out.println("для выхода команда EXIT");
                System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
                System.out.println("");

                System.out.print("введите команду:");
                str = scn.nextLine().split(" ");
                System.out.println("");


                switch (str[0].toUpperCase()) {
                    case "ADD": {
                        System.out.println("=========  Добавление записи =====================");
                        st = getMessage(str);
                        dbgb.addRecord(st);
                        System.out.println("Запись : '" + st + "' добавлена!");

                        break;
                    }
                    case "LIST": {
                        System.out.println("=========  Содержимое гостевой книги =====================");
                        for (Record r : dbgb.getRecords()) {
                            System.out.println(r.getID() + "   " + r.getDateStringFull() + "   " + r.getMessage());
                        }
                        break;
                    }
                    case "EXIT": {
                        System.out.println("Выход");
                        return;
                    }
                    default: {
                        System.out.println("Команда '" + str[0] + "' не распознана");
                    }
                }

            }


        } catch (Exception e) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dbgb.close();
            } catch (Exception e) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
            }

        }


    }
}
