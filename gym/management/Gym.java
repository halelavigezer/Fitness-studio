package gym.management;

import gym.customers.Person;

public class Gym {
    private static Gym gym;
    public String mane;
    public Secretary secretary;
    private Gym(){

    }
    public static Gym getInstance(){
        if(gym==null){
            gym = new Gym();
        }  return gym;
    }
    public void setName(String crossFit) {
        this.mane=crossFit;
    }

    public void setSecretary(Person p1, int i) {
        if (secretary== null){
            this.secretary= new Secretary(p1,i);
        }
        this.secretary.setPerson(p1);
        this.secretary.setMany(i);
    }

    public Secretary getSecretary() {
        return this.secretary;
    }
}
