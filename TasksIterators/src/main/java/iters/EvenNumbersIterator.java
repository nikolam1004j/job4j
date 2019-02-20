package iters;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс итератора четных чисел
 * @author Николай
 * @since 20.02.2019
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] ints;
    private int carriage;

    /**
     * Создает объект класса итератора четных чисел
     * @param ints исходный массив
     */
    public EvenNumbersIterator(int[] ints) {
        this.ints = ints;
        skipNotEven();
    }

    /**
     * Пропускаем все нечетные числа
     */
    private void skipNotEven() {
        if (carriage < ints.length) {
            if((ints[carriage]& 1) == 1){
                carriage++;
                skipNotEven();
            }
        }
    }

    @Override
    public boolean hasNext() {
        return carriage < ints.length;
    }

    @Override
    public Integer next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        int result = ints[carriage++];
        skipNotEven();
        return result;
    }
}
