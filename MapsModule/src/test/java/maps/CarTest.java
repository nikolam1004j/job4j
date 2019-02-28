package maps;

import org.junit.Assert;
import org.junit.Test;

public class CarTest {

    @Test
    public void testCar() {
        Car car1 = new Car("BMW", "M3", 25000.0, 250);
        Car car2 = new Car("BMW", "M3", 25000.0, 250);
        Car car3 = new Car("BMW", "M3", 25000.0, 250);

        //Рефлексивность
        Assert.assertEquals(car1, car1);
        Assert.assertEquals(car2, car2);
        Assert.assertEquals(car3, car3);
        //Симметричность
        Assert.assertEquals(car1, car2);
        Assert.assertEquals(car2, car1);
        //Транзитивность
        Assert.assertEquals(car1, car2);
        Assert.assertEquals(car2, car3);
        Assert.assertEquals(car1, car3);
        //Непротиворечивость
        Assert.assertEquals(car1, car2);
        Assert.assertEquals(car1, car2);
        Assert.assertEquals(car1, car2);
        Assert.assertEquals(car2, car3);
        Assert.assertEquals(car2, car3);
        Assert.assertEquals(car2, car3);
        //Null
        Assert.assertFalse(car1.equals(null));
        Assert.assertFalse(car2.equals(null));
        Assert.assertFalse(car3.equals(null));
    }
}