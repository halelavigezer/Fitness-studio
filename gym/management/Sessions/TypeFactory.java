package gym.management.Sessions;

import gym.customers.ForumType;
import gym.customers.Instructor;

public class TypeFactory {

    public static Session creatsession (SessionType type, String date, ForumType forumType, Instructor instructor){
        switch (type) {
            case ThaiBoxing:
                return new ThaiBoxing(date,forumType,instructor);
            case Pilates:
                return new Pilates(date,forumType,instructor);
            case Ninja:
                return new Ninja(date,forumType,instructor);
            case MachinePilates:
                return new MachinePilates(date,forumType,instructor);
            default:
                throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}
