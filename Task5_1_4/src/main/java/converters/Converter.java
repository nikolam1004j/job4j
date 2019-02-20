package converters;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс для работы с конвертером
 * @author Николай Разилов
 * @since 20.02.2019
 */
public class Converter {

    /**
     * Конвертирует итератор итераторов в обычный итератор
     * @param it Итератор итераторов
     * @return Возвращает обычный итератор
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> carriage = it.next();

            /**
             * Динамический блок. Пропускает все пустые итераторы
             * при инициализации объекта
             */
            {
                skipEmpty();
            }

            /**
             * Рекурсивно пропускает все пустые итераторы
             */
            private void skipEmpty() {
                if (it.hasNext() && !carriage.hasNext()) {
                    carriage = it.next();
                    skipEmpty();
                }
            }

            @Override
            public boolean hasNext() {
                return it.hasNext() || carriage.hasNext();
            }

            @Override
            public Integer next() {
                skipEmpty();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return carriage.next();
            }
        };
    }
}
