package auto_salon.Database;

import java.util.ArrayList;
import java.util.List;

import auto_salon.Adminictrator.Administrator;
import auto_salon.Car.Car;
import auto_salon.User.User;
import auto_salon.manager.Manager;
import auto_salon.order.Order;
import auto_salon.order.Order_expectations;

public class Database {

    public static List<Car> cars = new ArrayList<>();
    static{
        cars.add(new Car("BMW", "Red", 10, 2_000_000));
        cars.add(new Car("BMW", "Black", 2, 2_900_000));
        cars.add(new Car("BMW", "White", 2, 1_350_000));
        cars.add(new Car("Lamba", "Black", 4, 15_000_000));
        cars.add(new Car("Lamba", "Red", 4, 8_000_000));
        cars.add(new Car("Lada", "Purple", 2, 200_000_000));
        cars.add(new Car("Lada", "Red", 100, 200_000));
        cars.add(new Car("Lada", "White", 20, 700_000));
        cars.add(new Car("Nissan", "Red", 2, 1_300_000));
        cars.add(new Car("Nissan", "Black", 2, 1_700_000));
    }

    public static List<Manager> managers = new ArrayList<>();
    static {
        managers.add(new Manager("Артём", "Артёмов", 1,  354));
        managers.add(new Manager("А", "А", 2,  123));
    }

    public static List<Administrator> administrators = new ArrayList<>();
    static {
        administrators.add(new Administrator(
                "А", "А", "admin", "admin"));
    }

    public static List<User> users = new ArrayList<>();
    static {
        users.add(new User("B", "B", 465_464));
        users.add(new User("Артём", "Сонов", 4_235_100));
        users.add(new User("A", "A", 1_000_000_000));
    }

    public static List<Order> orders = new ArrayList<>();
    static {
        orders.add(new Order(1, "Srgfb", "skdbvg",
                1_000_000, "ААА", "FFFF"));
    }

    public static List<Order_expectations> order_expectations = new ArrayList<>();
    static{
    }

}
