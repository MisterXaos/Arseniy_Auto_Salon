package auto_salon.Database;

import auto_salon.Adminictrator.Administrator;
import auto_salon.Car.Car;
import auto_salon.User.User;
import auto_salon.manager.Manager;
import auto_salon.order.Order;
import auto_salon.order.Order_expectations;

import java.util.Arrays;
import java.util.Scanner;

import static auto_salon.Database.Database.*;
import static auto_salon.proverka.ppp;

public class Logic {

    static Scanner scanner = new Scanner(System.in);

    public static int id_order = 1;

    public String first_name;
    public String last_name;


    public static void check_Cars() {

        for (Car car : cars) {
            System.out.println("Марка: " + car.mark + " Цвет: " + car.color +
                    " Количество " + car.quantity + " цена: " + car.price);
        }
    }

    public static void creat_Order() {

        String[] info_user = check_Account().split(" ");

        String first_name = info_user[0];
        String last_name = info_user[1];

        boolean availability = false; //наличие

        System.out.println("Вот список всех доступных авто: ");
        check_Cars();
        System.out.println("Задаём ордер на покупку: ");

        System.out.println("Введите марку машины ");
        String mark_auto = scanner.next();
        System.out.println("Введите цвет машины ");
        String color_auto = scanner.next();

        for (Car car : cars) {
            if (mark_auto.equals(car.mark) && color_auto.equals(car.color)) {
                order_expectations.add(new Order_expectations(id_order, mark_auto,
                        color_auto, car.price, first_name, last_name));
                availability = true;
            }
        }

        if (!availability) {
            order_expectations.add(new Order_expectations(id_order, mark_auto,
                    color_auto, 0, first_name, last_name));
        }

        System.out.println("Ваш ордер: " + id_order + " Марка выбраной машины " +
                mark_auto + " Цвет выбранной машины: " + color_auto + "  \" Цена по договору \" " +
                " Ваши контактные данные: " + first_name + " " + last_name);

        id_order++;
    }

    public static void accepting_order() {
        int pin;
        boolean check_Manager = false;
        boolean exit =false;
        while (exit == false){
            System.out.println("Для входа введите пинкод менеджера: ");
            pin = scanner.nextInt();
            for (Manager manager : managers) {
                if (pin == manager.pin) {
                    check_Manager = true;
                    if (check_Manager == true) {
                        System.out.println("Здравсвуйте менеджер, id: " + manager.id + " Имя, фамилия: " + manager.firstname + " " + manager.lastname);
                        if (check_order_expectations()==true && !(order_expectations.size()<=0)) {
                            System.out.println("Машина ваша, поздравляю с покупкой!");
                            break;
                        } else{
                            System.out.println("Ошибка, заказ имеет ошибки");
                            job_administrator();
                        }
                        exit =true;
                    }
                }
                if(check_Manager == false){
                    System.out.println("Вы ввели несуществующий пинкод менеджера, повторите ещё раз");
                }
            }
        }
    }


    private static boolean check_order_expectations() {

        System.out.println("Введите номер заказа: ");
        int order_number_processed = scanner.nextInt();

        boolean check_availability = false;

        for (Order_expectations orderExpectation : order_expectations) {
            if (order_number_processed == orderExpectation.id_order) {
                for (Car car : cars) {
                    if (orderExpectation.mark_car.equals(car.mark) && orderExpectation.color_car.equals(car.color)) {
                        check_availability = solvency_check(orderExpectation, order_number_processed);//true
                    }
                }
            }
        }
        return check_availability;
    }

    private static boolean solvency_check(Order_expectations orderExpectation, int id_order_to_delete) {

        System.out.println("Повторите данные покупателя, для индетификации: ");

        String f_name_for_purchase = scanner.next();
        String l_name_for_purchase = scanner.next();

        boolean solvency_check_true = false;
        boolean exit = false;

        for (User user : users) {
            if (f_name_for_purchase.equals(user.firstname) && l_name_for_purchase.equals(user.lastname)) {
                for (Car car : cars) {
                    if(orderExpectation.mark_car.equals(car.mark)){
                        if (user.check >= car.price) {
                            while (exit == false) {
                                System.out.println("хотите оформить покупку авто? y/n");
                                String regist_A = scanner.next();
                                if (regist_A.equals("y")) {

                                    user.check -=orderExpectation.price_car;//снятие денег со счёта
                                    System.out.println("Прибыль с данной сделки составила: +" + orderExpectation.price_car + " ДЕНЕГ");
                                    solvency_check_true = true;
                                    orders.add(orderExpectation);
                                    exit =true;
                                    break;
                                } else if (regist_A.equals("n")) {
                                    break;
                                } else {
                                    System.out.println("Вы ввели не то, повторите ещё раз, y/n");
                                }
                            }
                        } else {
                            System.out.println("У вас недостаточно средств");
                        }
                    }

                }
            }
        }
        return solvency_check_true;
    }


    private static void job_administrator() {
        System.out.println("Введите логин: ");
        String login = scanner.next();
        System.out.println("Введите пароль: ");
        String password = scanner.next();
        for (Administrator administrator : administrators) {
            if (login.equals(administrator.login) && password.equals(administrator.password)) {
                System.out.println("Администратор " + administrator.firstname + " " + administrator.lastname);
                System.out.println("Какую функцию вы выбирите для работы с ордером? ");
                String working_with_a_oreder;

                while (true) {

                    System.out.println("Удаление (d) или Изменение (ch) ");
                    working_with_a_oreder = scanner.next();

                    if (working_with_a_oreder.equals("d")) {

                        System.out.println("Введите id ордера, который нужно удалить");
                        int id_order_to_delete = scanner.nextInt();

                        order_expectations.remove(id_order_to_delete-1);
                        break;

                    } else if (working_with_a_oreder.equals("ch")) {

                        System.out.println("Эта функция находится в разработке, обратитесь к ней позже");

                        //Изменение опредлеённых параметров в временном ордере

//                        System.out.println("Введите id ордера, который нужно изменить");
//                        int id_order_to_delete = scanner.nextInt();
//                        System.out.println("Какой параметр нужно поменять, Машину(car) или Цвет(col)");
//                        while (true){
//                            String parameter_change = scanner.next();
//                            if(parameter_change.equals("col")){
//                                order_expectations.get(id_order_to_delete-1).s
//                            }
//                        }

                    }
                }
            }
        }

    }




    private static String check_Account() {

        String firstname = null;
        String lastname  = null;
        boolean proverit = false;
        int kolvo_proverok = 0;

        while (true) {

            if (!(kolvo_proverok >= 3)) {

                System.out.println("Введите имя: ");
                firstname = scanner.next();
                System.out.println("Введите фамилию: ");
                lastname = scanner.next();

                for (User user : users) {
                    if (firstname.equals(user.firstname) && lastname.equals(user.lastname)) {
                        proverit = true;
                        break;
                    }
                }

                if (!(proverit)) {
                    System.out.println("хотите зарегистрирвоать аккаунт? y/n");
                    while (true) {
                        String regist_A = scanner.next();
                        if (regist_A.equals("y")) {
                            registration_Account();
                            proverit = true;
                            break;
                        } else if (regist_A.equals("n")) {
                            break;
                        } else {
                            System.out.println("Вы ввели не то, повторите ещё раз, y/n");

                        }
                    }
                } else {
                    System.out.println("Здравствуйте " + firstname + " " + lastname);
                    String fullname = firstname + " " + lastname;
                    return fullname;
                }
            } else if (kolvo_proverok >= 3) {
                System.out.println("Вы истратели все попытки входа");
                System.exit(0);
            }
            kolvo_proverok++;
        }
    }
    private static void registration_Account () {

        System.out.println("Введите имя: ");
        String first_n = scanner.next();
        System.out.println("Введите фамилию: ");
        String last_n = scanner.next();
        double check_A = Math.random() * (5_000_000 - 300_000) + 300_000;

        users.add(new User(first_n, last_n, check_A));

    }
}

