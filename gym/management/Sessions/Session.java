package gym.management.Sessions;

import gym.Exception.InstructorNotQualifiedException;
import gym.customers.Client;
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
        this.instructor=instructor;
        this.date=date;
        this.clients = new ArrayList<>();
        this.forumType=forumType;

        switch (sessionType) {
            case ThaiBoxing:
                 newType= new ThaiBoxing();
            case Pilates:
                  newType= new Pilates();
            case Ninja:
                 newType=new Ninja();
            case MachinePilates:
                newType=new MachinePilates();
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


