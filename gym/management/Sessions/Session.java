package gym.management.Sessions;

import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.customers.ForumType;
import gym.customers.Instructor;

public class Session {
    public SessionType sessionType;
    public String date;
    public ForumType forumType;
    public Instructor instructor;

    public Session(SessionType sessionType, String date, ForumType forumType, Instructor instructor) {
        if (!instructor.getTutorials().contains(forumType)) {
            throw new InstructorNotQualifiedException("InstructorNotQualifiedException") ;
        }
        else
            this.instructor=instructor;
        this.date=date;

        switch (vehicleType) {
            case CAR:
                return new Car();
            case BIKE:
                return new Bike();
            case TRUCK:
                return new Truck();
            default:
                throw new IllegalArgumentException("Invalid vehicle type");
        }
    }


}


