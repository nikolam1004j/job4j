package threads;

public class User {
    private int id;
    private int amount;
    private int hashCode;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != this) {
            if (obj instanceof User) {
                User u = (User) obj;
                result = this.id == u.id;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        if(hashCode == 0) {
            int result = 17;
            result = result * 31 + this.id;
            hashCode = result;
        }
        return hashCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
