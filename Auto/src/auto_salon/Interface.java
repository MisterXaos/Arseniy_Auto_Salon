package auto_salon;

import auto_salon.Database.Logic;

import java.util.Scanner;

import static auto_salon.Database.Logic.*;

public class Interface {
    public static String Interfaces(){
        Scanner scanner = new Scanner(System.in);
        String pr;
        while (true){
            System.out.println("1. Посмотреть доступные авто");
            System.out.println("2. Оформить заказ");
            System.out.println("3. Принять заказ");
            System.out.println("4.Выйти из программы");
            pr = scanner.next();
            switch (pr){
                case "1":{
                    check_Cars();
                    break;
                    //Посмотреть доступные авто
                }case "2":{
                    creat_Order();
                    break;
                    //Оформить заказ
                }case "3":{
                    accepting_order();
                    break;
                    //Принять заказ
                }case "4":{
                    //Выйти из программы
                    System.out.println("Вы выходите из программы, подтверждаете выход? y/n");
                    String ex ;
                    while (true){
                        ex =scanner.next();
                        if(ex.equals("y")){
                            System.out.println("Приходите ещё, мы всегда рады вам! ");
                            System.exit(0);
                        } else if (ex.equals("n")) {
                            break;
                        }else{
                            System.out.println("Вы ввели неподходящую информацию");
                        }
                    }
                    break;
                } default:{
                    System.out.println("Вы ввели нетот пункт");
                }
            }
        }
    }
}
