package gym.management.Sessions;

import gym.customers.ForumType;
import gym.customers.Instructor;

public class Ninja extends Session {
    private int pepole;
    private int cash;
    private   String name ="Ninja";

    public Ninja(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
        this.pepole=5;
        this.cash=150;
    }

    @Override
    public SessionType gettype() {
        return null;
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
