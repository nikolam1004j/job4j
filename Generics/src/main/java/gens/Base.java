package gens;

/**
 * @author Николай Разилов
 * @version 1
 * @since 20.02.2019
 */
public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}