package gym.management;
import gym.Exception.*;
import gym.customers.Client;
import gym.customers.ForumType;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.Session;
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
    protected List<Session> sessions=new ArrayList<>();
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


    //פעולה שמחזירה אמת אם הבן אדם מעל גיל 18
    public boolean up18(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(dateString, formatter);
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthDate, currentDate);
        return age.getYears() >= 18;
    }

    public Instructor hireInstructor(Person p, int i, ArrayList<SessionType> sessionTypes) {
        if(!up18(p.getDateOfBirth())) {
            throw new ClientNotRegisteredException("ClientNotRegisteredException");
        }
        Instructor instructor=new Instructor(p,i,sessionTypes);
        instructors.add(instructor);
        return instructor;
    }
    public Session addSession(SessionType s, String data,ForumType m, Instructor i){
        if (!i.getTutorials().contains(m)) {
            throw new InstructorNotQualifiedException("InstructorNotQualifiedException") ;
        }
        //לבדוק אם צריך לעשות בדיקה אם המדריך פנוי באותם שעות
        Session session=new Session(s,data,m,i);
        if (sessions.contains(session)) {
            throw new DuplicateClientException("DuplicateClientException");//צריך לשנות במקום שיש כבר את הלוקח הזה לזה שיש כבר את השיעור הזה
        }
        i.setHours();
        sessions.add(session);
       return session;
    }

    public void registerClientToLesson(Client c, Session s)
    {
        if (!c.getTypes().contains(s.forumType)) {
            throw new ClientNotRegisteredException("ClientNotRegisteredException") ;
        }
        if (s.clients.contains(c))
        {
            throw new DuplicateClientException("DuplicateClientException");
        }
        if(s.getClients().size()>s.gettype().GetNumber())
        {
            throw new FullOccupanciException("FullOccupanciException");
        }
        if (c.getPerson().getMany()<s.gettype().GetMany())
        {
            throw new notEnoughMoneyException("notEnoughMoneyException");
        }
        c.getPerson().setMany(c.getPerson().getMany()-s.gettype().GetMany());
        s.setClients(c);
    }

    public void notify(Session p, String s) {
        for (Client client: p.getClients()) {
                client.update(s);
        }
    }
    public void notify (String data,String s){
        for (Session session:sessions){
            String n= session.date;
            String sudn = n.substring(0,11);
            if (sudn == data) {
                for (Client c :session.getClients()){
                    c.update(s);
                }
            }
        }
    }

    public void notify(String m) {
        for (Client client:clients) {
            client.update(m);
        }
    }

    public void paySalaries() {
        for (int i=0; i<this.instructors.size();i++) {
            this.instructors.get(i).getPerson().setMany(this.instructors.get(i).getPerson().getMany()+this.instructors.get(i).getHours()*this.instructors.get(i).getSalary());
        }
        this.person.setMany(this.person.getMany()+this.many);
    }

    public void printActions()
    {
    }
}



