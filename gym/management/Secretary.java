package gym.management;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InvalidAgeException;
import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.SessionType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Secretary
{
    protected Person person;
    protected int many;
    protected List<Client> clients;
    protected List<Instructor> instructors;
   protected Secretary(Person person, int many) {
       this.person=person;
       this.many=many;
   }

    public Client registerClient(Person p) {
     Client client=new Client(p);
     if(clients.contains(client))
     {
        throw new InvalidAgeException("InvalidAgeException");
     }

     if (!up18(p.getDateOfBirth()))
     {
         throw new InvalidAgeException("InvalidAgeException");
     }
     clients.add(client);
     return client;
    }

    public int getMany() {
        return many;
    }

    public void setMany(int many) {
        this.many = many;
    }

    public Person getPerson() {
        return person;
    }

    protected void setPerson(Person person) {
        this.person = person;
    }

    public void unregisterClient(Client c)
    {
        if(!clients.contains(c))
        {
            throw new ClientNotRegisteredException("ClientNotRegisteredException");
        }
        clients.remove(c);
    }
    public boolean up18(String dateString)
    {
        // הגדרת פורמט התאריך (לדוגמה: "yyyy-MM-dd")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // המרת המחרוזת לתאריך
        LocalDate birthDate = LocalDate.parse(dateString, formatter);

        // התאריך הנוכחי
        LocalDate currentDate = LocalDate.now();

        // חישוב ההפרש בין תאריכי הלידה להווה
        Period age = Period.between(birthDate, currentDate);

        // בדיקה אם הגיל הוא לפחות 18
        return age.getYears() >= 18;
    }

    public Instructor hireInstructor(Person p, int i, ArrayList<SessionType> sessionTypes)
    {
        if(!up18(p.getDateOfBirth()))
        {
            throw new ClientNotRegisteredException("ClientNotRegisteredException");
        }
        Instructor instructor=new Instructor(p,i,sessionTypes);
        instructors.add(instructor);
        return instructor;
    }
}



