package gym.management.Sessions;

import gym.Exception.InstructorNotQualifiedException;
import gym.customers.Client;
import gym.customers.ForumType;
import gym.customers.Instructor;

import java.util.ArrayList;
// this is class factory
public abstract class Session {
    public String date;
    public ForumType forumType;
    public Instructor instructor;
    public ArrayList<Client> clients;

    public Session( String date, ForumType forumType, Instructor instructor) {
        this.instructor=instructor;
        this.date=date;
        this.clients = new ArrayList<>();
        this.forumType=forumType;
    }


    public void setClients (Client c){
        if (!clients.contains(c)){
            clients.add(c);
        }
    }
    public ArrayList<Client> getClients(){
        return this.clients;
    }
    public abstract SessionType gettype() ;
    public abstract int GetNumber();
    public abstract int GetMany();
}
