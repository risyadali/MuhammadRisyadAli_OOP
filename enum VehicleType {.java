enum VehicleType {
    CAR,
    MOTORCYCLE,
    TRUCK
}

class Vehicle {
    Private String brand;
    Private int year;
    Private VehicleType type;
    Private double price;

    public Vehicle(String brand, int year, VehicleType type, double price) {
        this.brand = brand;
        this.year = year;
        this.type = type;
        this.price = price;
    }

    public void showDetail() {
        System.out.println("Brand: " + brand);
        System.out.println("Year: " + Year);
        System.out.println("Type: " + Type);
        System.out.println("Price: " + Price);
    }

    public double getPrice() {
        return price;
    }

}

Class Customer {
    Private String name;
    Private Vehicle vehicle;

    public Costumer(String name, Vehicle vehicle){
        this.name = name;
        this.vehicle = vehicle;
    }

    public double getTotalPrice() {
        return vehicle.getPrice();
    }

    public void showDetail(){
        System.out.println("Costumer Name:"+name);
        System.out.println("Vehicle Details:");
        vehicle.showDetail();
        System.out.println("Total Price:" + getTotalPrice());

    }
}

public class Main {
    public static void main(String[] args){
        Vehicle vehicleSupraBapak = new Vehicle("Honda Supra", 1998, VehicleType.MOTORCYCLE, 3000);
        Vehicle vehicleKalcer = new Vehicle("VW Beetle", 1998, VehicleType.CAR, 200000);
        Vehicle vehicleGuede = new Vehicle("Isuzu Giga", 2011, VehicleType.TRUCK, 300000);

        Costumer costumer1 = new Costumer("Budi Santoso", vehicleSupraBapak);
        Costumer costumer2 = new Costumer("Sari Melati", vehicleKalcer);
        Costumer costumer3 = new Costumer("Joko Widodo", vehicleGuede);

        System.out.println("=== Costumer 1 information ===");
        costumer1.showDetail();
        System.out.println();

        System.out.println("=== Costumer 2 information ===");
        costumer2.showDetail();
        System.out.println();

        System.out.println("=== Costumer 3 information ===");
        costumer3.showDetail();

    }
}