package maps;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class MapsCollisionTest {

    /**
     * Создаются четыре объекта, логически разных.
     * Однако имеют одинаковый хэш-код. Получаем коллизию,
     * означающую, что данные расположились в одном сегменте
     * карты.
     */
    @Test
    public void collisionTest1() {
        MapsCollision collision = new MapsCollision();
        MapsCollision.A a = collision.new A(1, 2, 0);
        MapsCollision.A b = collision.new A(1, 1, 1);
        MapsCollision.A c = collision.new A(0, 1, 2);
        MapsCollision.A d = collision.new A(0, 0, 3);

        assertThat(a.hashCode(), is(b.hashCode()));
        assertThat(b.hashCode(), is(c.hashCode()));
        assertThat(c.hashCode(), is(d.hashCode()));
        assertThat(a, not(b));
        assertThat(b, not(c));
        assertThat(c, not(d));
    }

}