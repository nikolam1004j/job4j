package lists;

/**
 * Класс связанного списка с цикличностью.
 * @param <T> Тип данных.
 */
public class BadList<T> {

    public boolean hasCycle(Node<T> first) {
        boolean result = false;
        if (first != null) {
            if(first.next == first) {
                result = true;
            } else if(first.next.next != null) {
                Node<T> hear = first;
                Node<T> tor = first.next.next;
                while (hear!=null || tor!=null) {
                    if(hear == tor) {
                        result = true;
                        break;
                    } else {
                        tor = tor.next;
                        hear = hear.next != null ? hear.next.next : hear.next;
                    }
                }
            }
        }
        return result;
    }
}