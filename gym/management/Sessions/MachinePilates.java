package gym.management.Sessions;

import gym.customers.ForumType;
import gym.customers.Instructor;

public class MachinePilates extends Session {
        private int people;
        private int cash;
        private   String name ="MachinePilates";

    public MachinePilates(String date, ForumType forumType, Instructor instructor) {
        super(date, forumType, instructor);
        this.people=10;
        this.cash=80;
    }


    @Override
    public SessionType gettype() {
        return  SessionType.MachinePilates;
    }

    @Override
    public int GetNumber() {
        return this.people;
    }

    @Override
    public int GetMany() {
        return this.cash;
    }
}
