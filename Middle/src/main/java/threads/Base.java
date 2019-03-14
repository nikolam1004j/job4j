package threads;

/**
 * Класс модели Base.
 * @author Николай Разилов
 * @since 14.03.2019
 */
public class Base {
    private int id;
    private int version;
    private int hashCode;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != this) {
            if (obj instanceof Base) {
                Base base = (Base) obj;
                result = this.id == base.id
                        && this.version == base.version;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        if (hashCode == 0) {
            int result = 17;
            result = result * 31 + id;
            result = result * 31 + version;
            hashCode = result;
        }
        return hashCode;
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }
}