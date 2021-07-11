import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.*;

interface User {
    public void user_menu();

    public void user_details();

    public void print_reward();
}

public class Main {
    private static App app;

    public static void main(String[] args) {
        app = new App();
    }
}

/*

================================================ COMPANY CLASS ============================================================

 */

class Company {

    private static Restaurant rest_list[] = new Restaurant[5];
    private static Customer cust_list[] = new Customer[5];
    private static HashMap<Integer, Food_item>[] list = new HashMap[5];
    private static float transaction_amount = 0;
    private static float total_delivery_charge = 0;
    Scanner sc = new Scanner(System.in);
    private App app;

    public Company() {
        rest_list[0] = new Authentic("Shah", "Delhi");
        rest_list[1] = new Restaurant("Ravi's", "Delhi");
        rest_list[2] = new Authentic("The Chinese", "Mumbai");
        rest_list[3] = new Fast_food("Wang's", "Pune");
        rest_list[4] = new Restaurant("Paradise", "Mumbai");

        cust_list[0] = new Elite("Ram", "Delhi");
        cust_list[1] = new Elite("Sam", "Mumbai");
        cust_list[2] = new Special("Tim", "Pune");
        cust_list[3] = new Customer("Kim", "Delhi");
        cust_list[4] = new Customer("Jim", "Pune");
    }

    public static void list_of_rest() {
        System.out.println("Choose Restaurant");
        for (int i = 0; i < rest_list.length; i++) {
            System.out.print("    " + (i + 1) + ") " + rest_list[i].getName());
            if (rest_list[i].getCategory() != null)
                System.out.println(" (" + rest_list[i].getCategory() + ")");
            else System.out.println("");
        }
    }

    public static Restaurant get_restaurant(int num) {
        return rest_list[num - 1];
    }

    public static float getTransaction_amount() {
        return transaction_amount;
    }

    public static void setTransaction_amount(float transaction_amount) {
        Company.transaction_amount = transaction_amount;
    }

    public static float getTotal_delivery_charge() {
        return total_delivery_charge;
    }

    public static void setTotal_delivery_charge(float total_delivery_charge) {
        Company.total_delivery_charge = total_delivery_charge;
    }

    public Restaurant rest_user() {
        System.out.println("Choose Restaurant");
        for (int i = 0; i < rest_list.length; i++) {
            System.out.println("    " + (i + 1) + ") " + rest_list[i].getName());
        }
        int option = sc.nextInt();
        return rest_list[option - 1];
    }

    public Customer cust_user() {
        System.out.println("Choose Customer");
        for (int i = 0; i < cust_list.length; i++) {
            System.out.print("    " + (i + 1) + ") " + cust_list[i].getName());
            if (cust_list[i].getCategory() != null)
                System.out.println(" (" + cust_list[i].getCategory() + ")");
            else
                System.out.println("");
        }
        int option = sc.nextInt();
        return cust_list[option - 1];
    }

    public Restaurant get_rest() {
        for (int i = 0; i < rest_list.length; i++) {
            System.out.println((i + 1) + ") " + rest_list[i].getName());
        }
        int option = sc.nextInt();
        return rest_list[option - 1];
    }

    public Customer get_cust() {
        for (int i = 0; i < cust_list.length; i++) {
            System.out.println((i + 1) + ") " + cust_list[i].getName());
        }
        int option = sc.nextInt();
        return cust_list[option - 1];
    }

    public void user_details() {
        System.out.println("Total company balance - " + transaction_amount);
        System.out.println("Total delivery charge - " + total_delivery_charge);
    }
}

/*

===================================================== APP CLASS ============================================================

 */

class App {
    private Company zotato;

    public App() {
        zotato = new Company();
        show_menu();
    }

    public void show_menu() {
        System.out.println("Welcome to Zotato:");
        System.out.println("    1) Enter as Restaurant Owner");
        System.out.println("    2) Enter as Customer");
        System.out.println("    3) Check User Details");
        System.out.println("    4) Company Account details");
        System.out.println("    5) Exit");
        Scanner sc = new Scanner(System.in);
        int options = sc.nextInt();
        if (options == 1) {
            User rest_owner = zotato.rest_user();
            rest_owner.user_menu();
            show_menu();
        }
        if (options == 2) {
            User cust_owner = zotato.cust_user();
            cust_owner.user_menu();
            show_menu();
        } else if (options == 3) {
            System.out.println("1) Customer List");
            System.out.println("2) Restaurant List");
            int choose = sc.nextInt();
            if (choose == 1) {
                User a = zotato.get_cust();
                a.user_details();
            } else {
                User a = zotato.get_rest();
                a.user_details();
            }
            show_menu();
        } else if (options == 4) {
            zotato.user_details();
            show_menu();
        } else {
            System.exit(0);
        }
    }
}

/*

================================================ RESTAURANT CLASS ============================================================

 */

class Restaurant implements User {
    Scanner sc = new Scanner(System.in);
    private String name;
    private String category;
    private int reward;
    private String address;
    private int orders_taken;
    private Company zotato;

    private HashMap<Integer, Food_item> list_food = new HashMap<>();

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public void print_reward() { System.out.println("Reward points claimed: " + reward); }

    @Override
    public void user_details() {
        System.out.println(this.name + " " + this.address + " " + this.orders_taken);
    }

    @Override
    public void user_menu() {
        System.out.println("Welcome " + this.name);
        System.out.println("    1) Add Item");
        System.out.println("    2) Edit Item");
        System.out.println("    3) Print Rewards");
        System.out.println("    4) Exit");
        int option = sc.nextInt();
        if (option == 1) {
            add_items();
            user_menu();
        } else if (option == 2) {
            edit_items();
            user_menu();
        } else if (option == 3) {
            print_reward();
            user_menu();
        } else {
            System.out.print("");
        }
    }

    protected void add_items() {
        System.out.println("Enter food item details");
        System.out.print("Food name ");
        String n = sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Item price ");
        float price = sc.nextFloat();
        System.out.print("Item quantity ");
        int quantity = sc.nextInt();
        System.out.print("Item category ");
        String c = sc.nextLine();
        String category = sc.nextLine();
        System.out.print("Offer ");
        int offer = sc.nextInt();
        Food_item food = new Food_item(name, price, quantity, category, offer);
        list_food.put(food.getId(), food);
        System.out.print(food.getId() + " -> ");
        System.out.print(this.name + " | ");
        System.out.print(food.getName() + " | ");
        System.out.print(food.getPrice() + " | ");
        System.out.print(food.getQuantity() + " | ");
        System.out.print(food.getDiscount() + "% off | ");
        System.out.println(food.getCategory() + " ");
    }

    protected void edit_items() {
        System.out.println("Choose item by code");
        if (!list_food.isEmpty()) {
            Iterator it = list_food.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry obj = (Entry) it.next();
                Food_item food = (Food_item) obj.getValue();
                System.out.print(food.getId() + " -> ");
                System.out.print(this.name + " | ");
                System.out.print(food.getName() + " | ");
                System.out.print(food.getPrice() + " | ");
                System.out.print(food.getQuantity() + " | ");
                System.out.print(food.getDiscount() + "% off | ");
                System.out.println(food.getCategory() + " ");
            }
        }
        if (!list_food.isEmpty()) {
            int option = sc.nextInt();
            System.out.println("Choose an attribute to edit");
            System.out.println("    1) Name");
            System.out.println("    2) Price");
            System.out.println("    3) Quantity");
            System.out.println("    4) Category");
            System.out.println("    5) Offer");
            int o = sc.nextInt();
            if (o == 1) {
                System.out.println("Enter the new name");
                String n = sc.nextLine();
                String name = sc.nextLine();
                list_food.get(option).setName(name);
            } else if (o == 2) {
                System.out.println("Enter the new price");
                float price = sc.nextFloat();
                list_food.get(option).setPrice(price);
            } else if (o == 3) {
                System.out.println("Enter the new quantity");
                int quantity = sc.nextInt();
                list_food.get(option).setQuantity(quantity);
            } else if (o == 4) {
                System.out.println("Enter the new category");
                String n = sc.nextLine();
                String category = sc.nextLine();
                list_food.get(option).setCategory(category);
            } else {
                System.out.println("Enter the new offer");
                int offer = sc.nextInt();
                list_food.get(option).setDiscount(offer);
            }
            Food_item food = list_food.get(option);
            System.out.print(food.getId() + " -> ");
            System.out.print(this.name + " | ");
            System.out.print(food.getName() + " | ");
            System.out.print(food.getPrice() + " | ");
            System.out.print(food.getQuantity() + " | ");
            System.out.print(food.getDiscount() + "% off | ");
            System.out.println(food.getCategory() + " ");
        } else {
            System.out.println("------ no items in this restaurant -------");
        }
    }

    public int add_reward(float price) {
        int val = 5 * (((int) price) / 100);
        reward += val;
        return val;
    }

    public String getCategory() { return category; }

    public float apply_disc(float price) { return price; }

    public String getName() { return name; }

    public int getOrders_taken() { return orders_taken; }

    public void setOrders_taken(int orders_taken) { this.orders_taken = orders_taken; }

    public int getReward() { return reward; }

    public void setReward(int reward) { this.reward = reward; }

    public String getAddress() { return address; }

    public HashMap<Integer, Food_item> getList_food() { return list_food; }
}

/*

================================================== AUTHENTIC CLASS ========================================================

 */

class Authentic extends Restaurant {
    private final String category = "Authentic";
    private String name;
    private String address;
    private int ov_disc;
    private int additional_ov_disc;
    private Scanner sc = new Scanner(System.in);

    public Authentic(String name, String address) {
        super(name, address);
    }

    @Override
    public int add_reward(float price) {
        int val = 25 * (((int) price) / 200);
        val += this.getReward();
        this.setReward(val);
        return val;
    }

    @Override
    public void user_menu() {
        System.out.println("Welcome " + this.getName());
        System.out.println("    1) Add Item");
        System.out.println("    2) Edit Item");
        System.out.println("    3) Print Rewards");
        System.out.println("    4) Discount on bill value");
        System.out.println("    5) Exit");
        int option = sc.nextInt();
        if (option == 1) {
            add_items();
            user_menu();
        } else if (option == 2) {
            edit_items();
            user_menu();
        } else if (option == 3) {
            print_reward();
            user_menu();
        } else if (option == 4) {
            overall_discount();
            user_menu();
        } else {
            System.out.print("");
        }
    }

    @Override
    public float apply_disc(float price) {
        float val = (float) ((1.0 * ov_disc) * price);
        val = val / 100;
        price = price - val;
        if (price > 100) price = price - additional_ov_disc;
        return price;
    }

    public void overall_discount() {
        System.out.print("Offer on bill value - ");
        int val = sc.nextInt();
        this.ov_disc = val;
        additional_ov_disc = 50;
    }

    public String getCategory() { return category; }
}

/*

=================================================== FAST-FOOD CLASS =======================================================

 */

class Fast_food extends Restaurant {
    private final String category = "Fast Food";
    private String name;
    private int overall_discount;
    private String address;
    private Scanner sc = new Scanner(System.in);


    public Fast_food(String name, String address) {
        super(name, address);
    }

    @Override
    public int add_reward(float price) {
        int val = 10 * (((int) price) / 150);
        val += this.getReward();
        this.setReward(val);
        return val;
    }

    @Override
    public void user_menu() {
        System.out.println("Welcome " + this.getName());
        System.out.println("    1) Add Item");
        System.out.println("    2) Edit Item");
        System.out.println("    3) Print Rewards");
        System.out.println("    4) Discount on bill value");
        System.out.println("    5) Exit");
        int option = sc.nextInt();
        if (option == 1) {
            add_items();
            user_menu();
        } else if (option == 2) {
            edit_items();
            user_menu();
        } else if (option == 3) {
            print_reward();
            user_menu();
        } else if (option == 4) {
            overall_discount();
            user_menu();
        } else {
            System.out.print("");
        }
    }

    @Override
    public float apply_disc(float price) {
        float val = (float) ((1.0 * overall_discount) * price);
        val = val / 100;
        price = price - val;
        return price;
    }

    public void overall_discount() {
        System.out.print("Offer on bill value - ");
        int val = sc.nextInt();
        this.overall_discount = val;
    }

    public String getCategory() { return category; }
}

/*

=================================================== CUSTOMER CLASS =========================================================

 */

class Customer implements User {

    private String name;
    private String address;
    private int reward;
    private Restaurant restaurant;
    private float wallet = 1000;
    private String category;
    private ArrayList<Integer> cart_quantity = new ArrayList<>();
    private ArrayList<Food_item> cart_item = new ArrayList<>();
    private ArrayList<Integer> cart_id = new ArrayList<>();
    private String[] recent_orders = new String[10];
    private int counter;
    private Scanner sc = new Scanner(System.in);

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
        this.counter = 0;
    }

    @Override
    public void user_menu() {
        System.out.println("Welcome " + this.name);
        System.out.println("    1) Select Restaurant");
        System.out.println("    2) Checkout Cart");
        System.out.println("    3) Reward won");
        System.out.println("    4) Print recent orders");
        System.out.println("    5) Exit");
        int option = sc.nextInt();
        if (option == 1) {
            show_list();
            user_menu();
        } else if (option == 2) {
            check_out();
            user_menu();
        } else if (option == 3) {
            print_reward();
            user_menu();
        } else if (option == 4) {
            recent_orders();
            user_menu();
        } else {
            System.out.print("");
        }
    }


    public void recent_orders() {
        if (counter < 10) {
            for (int i = 0; i < counter; i++) {
                System.out.println((i + 1) + ") " + recent_orders[i]);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                System.out.println((i + 1) + ") " + recent_orders[i]);
            }
        }
    }

    public void check_out() {
        if (!cart_quantity.isEmpty()) {
            Iterator it = cart_quantity.iterator();
            Iterator it2 = cart_item.iterator();
            float price = 0;
            int qu = 0;
            System.out.println("Items in cart -");
            while (it.hasNext()) {
                Food_item food = (Food_item) it2.next();
                int q = (int) it.next();
                qu += q;
                System.out.print(food.getId() + " " + restaurant.getName() + " - " + food.getName());
                System.out.println(" - " + food.getPrice() + " - " + q + " - " + food.getDiscount() + "% off");
                price += apply_discount(food, q, restaurant);
            }
            price += apply_delivery();
            if (price < wallet + reward) {
                Iterator ii = cart_quantity.iterator();
                Iterator ii2 = cart_item.iterator();
                while (ii.hasNext()) {
                    Food_item food = (Food_item) ii2.next();
                    int q = (int) ii.next();
                    String str = "";
                    str += "Bought: " + food.getName() + ", quantity: " + q + " from " + restaurant.getName();
                    str += " for Rs " + apply_discount(food, q, restaurant) + ", delivery charge " + apply_delivery();
                    recent_orders[counter%10]=str;
                    counter++;
                    Food_item new_food = food;
                    new_food.setQuantity(food.getQuantity()-q);
                    int id = food.getId();
                    restaurant.getList_food().remove(food.getId());
                    restaurant.getList_food().put(id , new_food);
                }
                if (reward > price) {
                    reward -= price;
                    price = 0;
                } else {
                    price -= reward;
                    reward = 0;
                }
                wallet -= price;
                float one_per = (float) (0.01 * price);
                one_per += Company.getTransaction_amount();
                Company.setTransaction_amount(one_per);
                float delivery_charge = apply_delivery();
                delivery_charge += Company.getTotal_delivery_charge();
                Company.setTotal_delivery_charge(delivery_charge);
                reward += restaurant.add_reward(price);
                int order_taken = restaurant.getOrders_taken();
                restaurant.setOrders_taken(order_taken + 1);
                System.out.println("Delivery charge - " + apply_delivery() + "/-");
                System.out.println("Total Order Value - INR " + price + "/-");
                System.out.println("    1) Proceed to checkout");
                int num = sc.nextInt();
                System.out.println(qu + " item(s) bought for INR " + price + "/-");
                cart_id.clear();
                cart_item.clear();
                cart_quantity.clear();
            } else {
                System.out.println("Delivery charge - " + apply_delivery() + "/-");
                System.out.println("Total Order Value - INR " + price + "/-");
                System.out.println("Insufficient Balance! Add items again");
                Iterator i = cart_quantity.iterator();
                Iterator i2 = cart_item.iterator();
                ArrayList<Integer> del = new ArrayList<>();
                System.out.println("Choose items to delete:");
                int idx =0;
                while (i.hasNext()) {
                    Food_item food = (Food_item) i2.next();
                    int q = (int) i.next();
                    System.out.print(food.getId() + " " + restaurant.getName() + " - " + food.getName());
                    System.out.println(" - " + food.getPrice() + " - " + q + " - " + food.getDiscount() + "% off");
                    System.out.println("    1) Keep it");
                    System.out.println("    2) Delete it");
                    int num = sc.nextInt();
                    if(num == 2){
                        del.add(idx);
                    }else idx++;
                }
                for(int x=0;x<del.size();x++){
                    int idxx = del.get(x);
                    cart_item.remove(idxx);
                    cart_quantity.remove(idxx);
                    cart_id.remove(idxx);
                }
                check_out();
            }
        }
    }

    public void delete_some(){
        Iterator it = cart_quantity.iterator();
        Iterator it2 = cart_item.iterator();
        ArrayList<Integer> del = new ArrayList<>();
        System.out.println("Choose items to delete:");
        int idx =0;
        while (it.hasNext()) {
            Food_item food = (Food_item) it2.next();
            int q = (int) it.next();
            System.out.print(food.getId() + " " + restaurant.getName() + " - " + food.getName());
            System.out.println(" - " + food.getPrice() + " - " + q + " - " + food.getDiscount() + "% off");
            System.out.println("    1) Keep it");
            System.out.println("    2) Delete it");
            int num = sc.nextInt();
            if(num == 2){
                cart_item.remove(idx);
                cart_quantity.remove(idx);
                cart_id.remove(idx);
            }else idx++;
        }
        check_out();
    }

    public void show_list(){
        Company.list_of_rest();
        int option = sc.nextInt();
        restaurant = Company.get_restaurant(option);
        list_rest();
    }

    public void list_rest() {
        HashMap<Integer, Food_item> list = restaurant.getList_food();
        if (!list.isEmpty()) {
            System.out.println("Choose item by code: ");
            Iterator it = list.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry obj = (Entry) it.next();
                Food_item food = (Food_item) obj.getValue();
                System.out.print(food.getId() + " -> ");
                System.out.print(restaurant.getName() + " | ");
                System.out.print(food.getName() + " | ");
                System.out.print(food.getPrice() + " | ");
                System.out.print(food.getQuantity() + " | ");
                System.out.print(food.getDiscount() + "% off | ");
                System.out.println(food.getCategory() + " ");
            }
            int item = sc.nextInt();
            System.out.println("Enter item quantity");
            int quantity = sc.nextInt();
            Food_item food = list.get(item);
            cart_item.add(food);
            cart_id.add(food.getId());
            cart_quantity.add(quantity);
            System.out.println("Items added");
            System.out.println("1) Add more items");
            System.out.println("2) Exit");
            int num = sc.nextInt();
            if(num == 1) list_rest();
            else System.out.print("");
        } else {
            System.out.println("Restaurant doesn't have items");
        }
    }

    public float apply_delivery() { return 40; }

    @Override
    public void print_reward() {
        System.out.println("Total Reward - " + reward);
    }

    @Override
    public void user_details() {
        System.out.println(this.name + " " + this.address + " " + this.wallet);
    }

    public float apply_discount(Food_item food, int quantity, Restaurant restaurant) {
        float price = food.getPrice() * quantity;
        int food_dis = food.getDiscount();
        float vall = (float) ((0.01 * food_dis) * price);
        price -= vall;
        float val = restaurant.apply_disc(price);
        return val;
    }

    public String getCategory() { return category; }

    public String getName() { return name; }

    public float getWallet() { return wallet; }

    public String getAddress() { return address; }
}

class Elite extends Customer {
    private final String category = "Elite";
    private String name;
    private String address;

    public Elite(String name, String address) {
        super(name, address);
    }

    @Override
    public float apply_delivery() {
        return 0;
    }

    @Override
    public float apply_discount(Food_item food, int q, Restaurant restaurant) {
        float price = food.getPrice() * q;
        int food_dis = food.getDiscount();
        float vall = (float) ((0.01 * food_dis) * price);
        price -= vall;
        float val = restaurant.apply_disc(price);
        if (val > 200) val = val - 50;
        return val;
    }

    @Override
    public String getCategory() { return category; }

    @Override
    public void user_details() {
        System.out.println(this.getName() + " (" + this.category + ") " + this.getAddress() + " " + this.getWallet());
    }
}

class Special extends Customer {
    private final String category = "Special";
    private String address;
    private String name;

    public Special(String name, String address) {
        super(name, address);
    }

    @Override
    public float apply_delivery() { return 20; }

    @Override
    public float apply_discount(Food_item food, int q, Restaurant restaurant) {
        float price = food.getPrice() * q;
        int food_dis = food.getDiscount();
        float vall = (float) ((0.01 * food_dis) * price);
        price -= vall;
        float val = restaurant.apply_disc(price);
        if (val > 200) val = val - 25;
        return val;
    }

    @Override
    public String getCategory() { return category; }

    @Override
    public void user_details() {
        System.out.println(this.getName() + " (" + this.category + ") " + this.getAddress() + " " + this.getWallet());
    }
}

/*

=================================================== FOOD CLASS ============================================================

 */

class Food_item {
    private static int id_generator = 1;
    private final int id;
    private String name;
    private String category;
    private float price;
    private int quantity;
    private int discount;

    public Food_item(String name, float price, int quantity, String category, int discount) {
        this.id = id_generator;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.category = category;
        id_generator++;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public float getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }

    public int getDiscount() { return discount; }

    public void setDiscount(int discount) { this.discount = discount; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }
}
