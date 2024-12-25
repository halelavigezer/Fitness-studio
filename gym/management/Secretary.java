package gym.management;
import gym.Exception.*;
import gym.customers.Client;
import gym.customers.ForumType;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;
import gym.management.Sessions.TypeFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Secretary
{
    protected Person person;
    protected int many;
    protected static List<Client> clients=new ArrayList<>();
    protected static List<Instructor> instructors=new ArrayList<>();
    protected static List<Session> sessions =new ArrayList<>();
    private static List<String>message=new ArrayList<>();


   protected Secretary(Person person, int many) {
       this.person=person;
       this.many=many;
       message.add("A new secretary has started working at the gym: "+person.getName());
   }



    public boolean correctSecretary() {
        return Gym.getInstance().getSecretary().equals(this);
    }


    public Client registerClient(Person p) throws NullPointerException,DuplicateClientException,InvalidAgeException{
       if (!correctSecretary()){
           throw new NullPointerException(" Error: Former secretaries are not permitted to perform actions");
       }
     Client client=new Client(p);
     if(clients.contains(client)) {
        throw new DuplicateClientException("Error: The client is already registered");
     }
     if (!up18(p.getDateOfBirth())) {
         throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
     }
     clients.add(client);
     message.add("Registered new client: "+p.getName());
     return client;
    }

    public int getMany() throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException(" Error: Former secretaries are not permitted to perform actions");
        }
        return many;
    }

    public void setMany(int many)throws NullPointerException {
        if (!correctSecretary()){
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        this.many = many;
    }

    public Person getPerson() throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException(" Error: Former secretaries are not permitted to perform actions");
        }
        return person;
    }

    protected void setPerson(Person person) throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        this.person = person;
    }

    public void unregisterClient(Client c) throws NullPointerException,ClientNotRegisteredException{
        if (!correctSecretary()){
            throw new NullPointerException(" Error: Former secretaries are not permitted to perform actions");
        }
        if(!clients.contains(c)) {
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }
        message.add("Unregistered client: " + c.getPerson().getName());
        clients.remove(c);
    }

    public boolean up18(String dateString)throws NullPointerException {
        if (!correctSecretary()){
            throw new NullPointerException(" Error: Former secretaries are not permitted to perform actions ");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(dateString, formatter);
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthDate, currentDate);
        return age.getYears() >= 18;
    }

    public Instructor hireInstructor(Person p, int i, ArrayList<SessionType> sessionTypes)throws NullPointerException,InvalidAgeException,InstructorNotQualifiedException {
        if (!correctSecretary()){
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        if(!up18(p.getDateOfBirth())) {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        }
        Instructor instructor=new Instructor(p,i,sessionTypes);
        instructors.add(instructor);
        message.add("Hired new instructor: "+p.getName()+" with salary per hour: "+i);
        return instructor;
    }
    public static int getAge(Person person) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Adjust format if needed
        LocalDate birthDate = LocalDate.parse(person.getDateOfBirth(), formatter);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public Session addSession(SessionType s, String data,ForumType m, Instructor i)throws InstructorNotQualifiedException ,NullPointerException,DuplicateClientException{
        if (!correctSecretary()){
            throw new NullPointerException(" Error: Former secretaries are not permitted to perform actions");
        }
        if (!i.getTutorials().contains(s)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.") ;
        }
        Session session= TypeFactory.creatsession(s,data,m,i);
        i.setHours();
        sessions.add(session);
        LocalDateTime dateTime = convertToDateTime(data);
        message.add("Created new session: "+s+" on "+dateTime+" with instructor: "+i.getPerson().getName());
        return session;
    }
    public static LocalDateTime convertToDateTime(String dateTimeString) {
        // מגדירים פורמט צפוי
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        // ממירים את המחרוזת לפורמט זמן
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    public void registerClientToLesson(Client c, Session s)throws NullPointerException, ClientNotRegisteredException, DuplicateClientException {
        boolean allConditionsPassed = true;
       if (!correctSecretary()){
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        if (sessionBefore(s.getDate())) {
            message.add("Failed registration: Session is not in the future");
            allConditionsPassed=false;
        }
        if (!clients.contains(c)) {
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }
        if (!c.getTypes().contains(s.forumType)) {
            if (s.forumType.equals(ForumType.Seniors)) {
                message.add("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
                allConditionsPassed=false;
            }
            else {
                message.add("Failed registration: Client's gender doesn't match the session's gender requirements");
                allConditionsPassed=false;
            }
        }
         if (s.clients.contains(c)) {
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }
         if(s.getClients().size()>=s.GetNumber()) {
            message.add("Failed registration: No available spots for session");
             allConditionsPassed=false;
        }
         if (c.getPerson().getMany()<s.GetMany())
        {
            message.add("Failed registration: Client doesn't have enough balance");
            allConditionsPassed=false;
        }
         if(allConditionsPassed) {
             c.getPerson().setMany(c.getPerson().getMany() - s.GetMany());
             Gym.upMany(s.GetMany());
             LocalDateTime dateTime = convertToDateTime(s.date);
             message.add("Registered client: " + c.getPerson().getName() + " to session: " + s.gettype() + " on " + dateTime + " for price: " + s.GetMany());
             s.setClients(c);
         }

    }

    public boolean sessionBefore(String dateString) {
        // יצירת פורמט תאריך-שעה המתאים למבנה של המחרוזת
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        // המרת המחרוזת לאובייקט LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        LocalDateTime now = LocalDateTime.now();
        // החזרת אמת אם התאריך עבר
        return !dateTime.isAfter(now);
    }

    public void notify(Session p, String s)throws NullPointerException {
        if (!correctSecretary()){
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        for (Client client: p.getClients()) {
                client.update(s);
        }
        LocalDateTime dateTime= convertToDateTime(p.date);
        message.add("A message was sent to everyone registered for session "+p.gettype()+" on "+dateTime+" : "+s);
    }
    public void notify (String data,String s)throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        for (Session session:sessions){
            String n= session.date;
            String sudn = n.substring(0,10);
            if (sudn.equals(data) ) {
                for (Client c :session.getClients()){
                    c.update(s);
                }
            }
        }
        LocalDate dateTime= convertToDate(data);
        message.add("A message was sent to everyone registered for a session on "+dateTime+" : "+s);
    }
    public static LocalDate convertToDate(String dateString) {
        // מגדירים פורמט צפוי
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // ממירים את המחרוזת לפורמט תאריך
        return LocalDate.parse(dateString, formatter);
    }


    public void notify(String m) throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        for (Client client:clients) {
            client.update(m);
        }
        message.add("A message was sent to all gym clients: "+m);
    }

    public void paySalaries() throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException(" Error: Former secretaries are not permitted to perform actions");
        }
        for (int i=0; i<this.instructors.size();i++) {
             int balance = this.instructors.get(i).getPerson().getMany();
             int hoursWorkeOfInstructor = this.instructors.get(i).getHours();
             int getSalaryOfInstructor= this.instructors.get(i).getSalary();
             int all =balance + hoursWorkeOfInstructor*getSalaryOfInstructor;
            this.instructors.get(i).getPerson().setMany(all);
            Gym.dounMany( hoursWorkeOfInstructor*getSalaryOfInstructor);
        }
        this.person.setMany(this.person.getMany()+this.many);
        Gym.dounMany(this.many);
        message.add("Salaries have been paid to all employees");
    }

    public void printActions() throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        for(String string:message)
        {
            System.out.println(string);
        }
        System.out.println();
    }


    public static String Mess(ArrayList<SessionType> s) {
        String result = "";
        for (int i = 0; i < s.size(); i++) {
            result += s.get(i).toString();  // המרה למחרוזת בעזרת toString()
            if (i != s.size() - 1) {
                result += ", ";  // הוספת פסיק אחרי כל אלמנט חוץ מהאחרון
            }
        }
        return result;
    }




}



