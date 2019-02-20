package iters;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для двумерного массива.
 * @author Николай Разилов.
 * @since 20.02.2019
 */
public class MatrixIterator implements Iterator<Integer> {
    private int[][]matrix;
    private int i, j;

    /**
     * Создает итератор для двумерного массива.
     * @param matrix Исходный двумерный массив.
     */
    public MatrixIterator(int[][] matrix) {
        this.matrix = matrix;
        skipEmpty();
    }

    /**
     * Пропускаем пустые массивы и переходим на следующий массив
     * при окончании работы с предыдущим
     */
    private void skipEmpty() {
        if (i < matrix.length && matrix[i].length == j) {
            i++;
            j = 0;
            skipEmpty();
        }
    }

    @Override
    public boolean hasNext() {
        return i < matrix.length && j < matrix[i].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int res = matrix[i][j++];
        skipEmpty();
        return res;
    }
}
