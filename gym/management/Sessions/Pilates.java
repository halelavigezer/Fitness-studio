package gym.management.Sessions;

import gym.customers.ForumType;
import gym.customers.Instructor;

public class Pilates extends Session{
    private int pepole;
    private int cash;


    public Pilates(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
        this.pepole=30;
        this.cash=60;
    }


    @Override
    public SessionType gettype() {
        return SessionType.Pilates;
    }

    @Override
    public int GetNumber() {
        return this.pepole;
    }

    @Override
    public int GetMany() {
        return this.cash;
    }
}
