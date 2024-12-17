package gym.management.Sessions;

public class TypeFactory {
    public static Type creatsession (SessionType type){
        switch (type) {
            case ThaiBoxing:
                return new ThaiBoxing();
            case Pilates:
                return new Pilates();
            case Ninja:
                return new Ninja();
            case MachinePilates:
                return new MachinePilates();
            default:
                throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}
