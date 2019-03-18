package main;

class Stm {
    public static int print(String s) {
        return 0;
    }
}

class Papa{
    static {
        System.out.println("PAPA static block");
    }
    private static int stint = Stm.print("PAPA static field stint created");
    private int local = Stm.print("PAPA local field local created");
    {
        System.out.println("PAPA non-static block");
    }
    public Papa() {
        System.out.println("Constructor Papa");
    }
}

class Son extends Papa{
    static {
        System.out.println("SON static block");
    }
    private static int stint = Stm.print("SON static field stint created");
    private int local = Stm.print("SON local field local created");
    {
        System.out.println("SON non-static block");
    }
    public Son() {
        System.out.println("Constructor Son");
    }
}

public class Main {
    public static void main(String[] args) {
        new Son();
    }
}