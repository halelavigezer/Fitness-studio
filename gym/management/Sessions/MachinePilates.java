package gym.management.Sessions;

public class MachinePilates implements Type {
        public int pepole;
        public int cash;
        public  String name ="MachinePilates";

        public MachinePilates() {
            this.pepole=10;
            this.cash=80;
        }


    @Override
    public int GetNumber() {
        return 10;
    }
}
