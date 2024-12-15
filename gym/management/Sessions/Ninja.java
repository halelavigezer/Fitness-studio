package gym.management.Sessions;

public class Ninja implements Type {
    public int pepole;
    public int cash;
    public  String name ="Ninja";

    public Ninja() {
        this.pepole=5;
        this.cash=150;
    }

    public int getCash() {
        return cash;
    }

    @Override
    public int GetNumber() {
        return 5;
    }

    @Override
    public int GetMany() {
        return 150;
    }
}
