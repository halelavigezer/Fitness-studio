package gym.management;

import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.Session;

//this class is singelton
public class Gym {
    private static Gym gym;
    public String mane;
    public Secretary secretary;
    private int totalMany =0;



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

            this.secretary= new Secretary(p1,i);


    }
    public static void upMany(int many)
    {
        gym.totalMany+=many;
    }
    public static void dounMany(int many)
    {
        gym.totalMany-=many;
    }

    public Secretary getSecretary() {
        return this.secretary;
    }
    @Override
    public String toString() {
        String ans = "Gym Name: " + this.mane + "\n" +
                "Gym Secretary: ID: " + secretary.getPerson().getID() + " | Name: " + secretary.getPerson().getName() +
                " | Gender: " + secretary.getPerson().getGender() + " | Birthday: " + secretary.getPerson().getDateOfBirth() +
                " | Age: " + secretary.getAge(secretary.getPerson()) + " | Balance: " + secretary.getPerson().getMany() +
                " | Role: Secretary | Salary per Month: " + secretary.many + "\n" +
                "Gym Balance: " + this.totalMany + "\n\n";
        ans += "Clients Data:\n";
        for (Client client : secretary.clients) {
            ans += ("ID: "+client.getPerson().getID()+" | Name: "+client.getName()+" | Gender: "+client.getPerson().getGender()+" | Birthday: "+client.getPerson().getDateOfBirth()+" | Age: "+secretary.getAge(client.getPerson())+" | Balance: "+client.getPerson().getMany()) + "\n"; // הוספת כל לקוח לשורה נפרדת
        }

        ans += "\nEmployees Data:\n";
        for (Instructor instructor : secretary.instructors) {
            ans += ("ID: "+instructor.getPerson().getID()+" | Name: "+instructor.getPerson().getName()+" | Gender: "+instructor.getPerson().getGender()+" | Birthday: "+instructor.getPerson().getDateOfBirth()+" | Age: "+secretary.getAge(instructor.getPerson())+" | Balance: "+instructor.getPerson().getMany()+" | Role: Instructor | Salary per Hour: "+instructor.getSalary()+" | Certified Classes: "+secretary.Mess(instructor.getTutorials())) + "\n"; // הוספת כל עובד לשורה נפרדת
        }
        ans += ("ID: "+secretary.getPerson().getID()+" | Name: "+secretary.getPerson().getName()+" | Gender: "+secretary.getPerson().getGender()+" | Birthday: "+secretary.getPerson().getDateOfBirth()+" | Age: "+secretary.getAge(secretary.getPerson())+" | Balance: "+secretary.getPerson().getMany()+" | Role: Secretary | Salary per Month: "+secretary.many)+"\n";
        ans += "\nSessions Data:\n";
        for (Session session : secretary.sessions) {
            ans +=("Session Type:" +session.gettype()+" | Date: "+session.date+" | Forum: "+session.forumType+" | Instructor: "+session.instructor.getPerson().getName()+" | Participants: "+session.clients.size()+"/"+session.GetNumber())+ "\n"; // הוספת כל סשן לשורה נפרדת
        }

        return ans;
    }

}