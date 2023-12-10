package dev.westy;

public class Entity {
    private String name;
    private int old;
    public static int HP = 20;
    public static final String SEX = "erko";

    public Entity(String name, int old) {
        this.name = name;
        this.old = old;
    }

    public String setName(String name) {
        this.name = name;
        return this.name;
    }

    public String getName() {
        return name;
    }
    public int getOld() {
        return old;
    }

    public int setOld(int old) {
        this.old = old;

        return this.old;
    }

    public String setProtection(String protectionName, int item) {
        return "Protection Changed!";
    }
}
