package gym.management.Sessions;

import gym.customers.ForumType;
import gym.customers.Instructor;

public class ThaiBoxing extends Session{
    private int pepole;
    private int cash;
    private  String name="ThaiBoxing";

    public ThaiBoxing(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
        this.pepole=20;
        this.cash=100;
    }


    @Override
    public SessionType gettype() {
        return SessionType.ThaiBoxing;
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
