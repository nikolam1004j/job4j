package maps;

public class Car {
    private String name;
    private String model;
    private double price;
    private int maxSpeed;

    public Car(String name, String model, double price, int maxSpeed) {
        this.name = name;
        this.model = model;
        this.price = price;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = true;
        if (obj != this) {
            if (obj instanceof Car) {
                Car car = (Car) obj;
                result = this.name.equals(car.name)
                        && this.model.equals(car.model)
                        && this.price == car.price
                        && this.maxSpeed == car.maxSpeed;
            } else {
                result = false;
            }
        }
        return result;
    }
}