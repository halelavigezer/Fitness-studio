package gym.customers;

import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class Instructor {
protected Person person;
protected  double salary;
protected ArrayList<SessionType> tutorials;
protected double hours;

public Instructor(Person p1,double salary,ArrayList<SessionType> t) {
    this.person = p1;
    this.salary = salary;
    this.tutorials = t;
    this.hours =0;
}

    public ArrayList<SessionType> getTutorials() {
        return this.tutorials;
    }

    public void setTutorials (ArrayList tutorials) {
        this.tutorials = tutorials;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    public void  setHours(){
    this.hours++;
    }
    public double getHours(){
    return this.hours;
    }
}
