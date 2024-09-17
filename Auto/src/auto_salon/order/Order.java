package auto_salon.order;

public class Order {
    public int id_order;
    public String mark_car;
    public String color_car;
    public int price_car;
    public String firstname_user;
    public String lastname_user;

    public Order(int id_order, String mark_car, String color, int price_car, String firstname_user, String lastname_user) {
        this.id_order = id_order;
        this.mark_car = mark_car;
        this.color_car = color;
        this.price_car = price_car;
        this.firstname_user = firstname_user;
        this.lastname_user = lastname_user;
    }
}
