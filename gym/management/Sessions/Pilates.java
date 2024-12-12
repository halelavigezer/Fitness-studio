package gym.management.Sessions;

public class Pilates implements Type{
    public int pepole;
    public int cash;
    public  String name="Pilates";

    public Pilates()
    {
        this.pepole=30;
        this.cash=60;
    }

    @Override
    public int GetNumber() {
        return 30;
    }
}
