package gym.management.Sessions;

import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.customers.ForumType;
import gym.customers.Instructor;

import java.util.ArrayList;

public class Session {
    public SessionType sessionType;
    public String date;
    public ForumType forumType;
    public Instructor instructor;
    public ArrayList<Client> clients;
    public Type newType;

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
    public void setClients (Client c){
        if (!clients.contains(c)){
            clients.add(c);
        }
    }
    public ArrayList<Client> getClients(){
        return this.clients;
    }
    public Type gettype() {
        return this.newType;
    }
}


