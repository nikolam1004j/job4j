package maps;

public class Car {
    private String name;
    private String model;
    private double price;
    private int maxSpeed;
    private int hashCode;

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

    @Override
    public int hashCode() {
        if (hashCode == 0) {
            int result = 17;
            result = result * 31 + (name == null ? 0 : name.hashCode());
            result = result * 31 + (model == null ? 0 : model.hashCode());
            long fromDouble = Double.doubleToLongBits(result);
            result = result * 31 + (int) (fromDouble ^ (fromDouble >>> 32));
            result = result * 31 + maxSpeed;
            hashCode = result;
        }
        return hashCode;
    }
}