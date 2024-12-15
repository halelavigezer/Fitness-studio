package gym.management.Sessions;

public class ThaiBoxing implements  Type{
    public int pepole;
    public int cash;
    public  String name="ThaiBoxing";

    public ThaiBoxing() {
        this.pepole=20;
        this.cash=100;
    }

    @Override
    public int GetNumber() {
        return 20 ;
    }

    @Override
    public int GetMany() {
        return 100;
    }

}
