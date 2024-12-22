package gym.management;

import gym.customers.Person;
//this class is singelton
public class Gym {
    private static Gym gym;
    public String mane;
    public Secretary secretary;


    private Gym(){}

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
            secretary.employeesdata.add("ID: "+secretary.getPerson().getID()+" | Name: "+secretary.getPerson().getName()+" | Gender: "+secretary.getPerson().getGender()+" | Birthday: "+secretary.getPerson().getDateOfBirth()+" | Age: "+Secretary.getAge(secretary.getPerson())+" | Balance: "+secretary.getPerson().getMany()+" | Role: Secretary | Salery per Hour: "+secretary.many);
        }
        else {
            secretary.employeesdata.remove("ID: "+secretary.getPerson().getID()+" | Name: "+secretary.getPerson().getName()+" | Gender: "+secretary.getPerson().getGender()+" | Birthday: "+secretary.getPerson().getDateOfBirth()+" | Age: "+Secretary.getAge(secretary.getPerson())+" | Balance: "+secretary.getPerson().getMany()+" | Role: Secretary | Salery per Hour: "+secretary.many);
            this.secretary.setPerson(p1);
            this.secretary.setMany(i);
            secretary.employeesdata.add("ID: "+secretary.getPerson().getID()+" | Name: "+secretary.getPerson().getName()+" | Gender: "+secretary.getPerson().getGender()+" | Birthday: "+secretary.getPerson().getDateOfBirth()+" | Age: "+Secretary.getAge(secretary.getPerson())+" | Balance: "+secretary.getPerson().getMany()+" | Role: Secretary | Salery per Hour: "+secretary.many);
        }
    }


    public static Secretary getSecretary() {
        return gym.secretary;
    }
}