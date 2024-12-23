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
    protected List<Client> clients=new ArrayList<>();
    protected List<Instructor> instructors=new ArrayList<>();
    protected List<Session> sessions =new ArrayList<>();
    private List<String>message=new ArrayList<>();
    protected List<String> sessionsData= new ArrayList<>();
    protected List<String> employeesdata =new ArrayList<>();
    protected List<String> Clients=new ArrayList<>();
    private double totalMany =0;

   protected Secretary(Person person, int many) {
       this.person=person;
       this.many=many;
       message.add("A new secretary has started working at the gym: "+person.getName());
   }



    public boolean correctSecretary() {
        return Gym.getSecretary().equals(this);
    }


    public Client registerClient(Person p) throws NullPointerException,DuplicateClientException,InvalidAgeException{
       if (!correctSecretary()){
           throw new NullPointerException(" Former secretaries are not permitted to perform actions");
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
     Clients.add("ID: "+p.getID()+" | Name: "+p.getName()+" | Gender: "+p.getGender()+" | Birthday: "+p.getDateOfBirth()+" | Age: "+getAge(p)+" | Balance: "+p.getMany());
     return client;
    }

    public int getMany() throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        return many;
    }

    public void setMany(int many)throws NullPointerException {
        if (!correctSecretary()){
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        this.many = many;
    }

    public Person getPerson() throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        return person;
    }

    protected void setPerson(Person person) throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        this.person = person;
    }

    public void unregisterClient(Client c) throws NullPointerException,ClientNotRegisteredException{
        if (!correctSecretary()){
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        if(!clients.contains(c)) {
            throw new ClientNotRegisteredException("Error: The client is already registered for this lesson");
        }
        message.add("Unregistered client: "+c.getPerson().getName());
        clients.remove(c);
    }

    public boolean up18(String dateString)throws NullPointerException {
        if (!correctSecretary()){
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(dateString, formatter);
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthDate, currentDate);
        return age.getYears() >= 18;
    }

    public Instructor hireInstructor(Person p, int i, ArrayList<SessionType> sessionTypes)throws NullPointerException,InvalidAgeException,InstructorNotQualifiedException {
        if (!correctSecretary()){
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        if(!up18(p.getDateOfBirth())) {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        }
        Instructor instructor=new Instructor(p,i,sessionTypes);
        if(instructors.contains(instructor)) {
            throw new InstructorNotQualifiedException("Error: The client is already registered");
        }
        instructors.add(instructor);//                                                                            ID: 1116 | Name: Noam | Gender: Male | Birthday: 20-12-1984 | Age: 40 | Balance: 170 | Role: Instructor | Salary per Hour: 50 | Certified Classes: Pilates, Ninja
        int age =getAge(p);
        employeesdata.add("ID: "+instructor.getPerson().getID()+" | Name: "+instructor.getPerson().getName()+" | Gender: "+instructor.getPerson().getGender()+" | Birthday: "+instructor.getPerson().getDateOfBirth()+" | Age: "+age+" | Balance: "+instructor.getPerson().getMany()+" | Role: Instructor | Salery per Hour: "+instructor.getSalary()+" | Certified Classes:"+Mess(instructor.getTutorials()));
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
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        if (!i.getTutorials().contains(s)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.") ;
        }
        Session session= TypeFactory.creatsession(s,data,m,i);
//        if (sessions.contains(session)) {
//            throw new DuplicateClientException("Error: Registration is required before attempting to unregister");
//        }
        i.setHours();
        sessions.add(session);
        sessionsData.add("Session Type:" +session.gettype()+" | Date: "+session.date+" | Forum: "+session.forumType+" | Instructor: "+session.instructor.getPerson().getName()+" | Participants: "+session.clients.size()+"/"+session.GetNumber());
        message.add("Created new session: "+s+" on "+data+" with instructor: "+i.getPerson().getName());
        return session;
    }

    public void registerClientToLesson(Client c, Session s)throws NullPointerException, ClientNotRegisteredException, DuplicateClientException {
        if (!correctSecretary()){
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        if (sessionBefore(s.getDate()))
        {
            message.add("Failed registration: Session is not in the future");
        }
        if (!clients.contains(c)){
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }
        if (!c.getTypes().contains(s.forumType)) {
            if (s.forumType.equals("Seniors"))
            {
                message.add("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
            }
            else {
                message.add("Failed registration: Client's gender doesn't match the session's gender requirements");
            }
        }
        if (s.clients.contains(c)) {
            throw new DuplicateClientException("Error: The client is already registered");///////////
        }
        if(s.getClients().size()>s.GetNumber())
        {
            message.add("Failed registration: No available spots for session");
        }
        if (c.getPerson().getMany()<s.GetMany())
        {
            message.add("Failed registration: Client doesn't have enough balance");
        }
        else {
            c.getPerson().setMany(c.getPerson().getMany() - s.GetMany());
            this.totalMany += s.GetMany();
            message.add("Registered client: " + c.getPerson().getName() + " to session: " + s.forumType + " on " + s.date + " for price: " + s.GetMany());
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
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        for (Client client: p.getClients()) {
                client.update(s);
        }
        message.add("A message was sent to everyone registered for session "+p.forumType+" on "+p.date+" : "+s);
    }
    public void notify (String data,String s)throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        for (Session session:sessions){
            String n= session.date;
            String sudn = n.substring(0,11);
            if (sudn == data) {
                for (Client c :session.getClients()){
                    c.update(s);
                }
            }
        }
        message.add("A message was sent to everyone registered for a session on "+data+" : "+s);
    }

    public void notify(String m) throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        for (Client client:clients) {
            client.update(m);
        }
        message.add("A message was sent to all gym clients: "+m);
    }

    public void paySalaries() throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        for (int i=0; i<this.instructors.size();i++) {
             double balance = this.instructors.get(i).getPerson().getMany();
             double hoursWorkeOfInstructor = this.instructors.get(i).getHours();
             double getSalaryOfInstructor= this.instructors.get(i).getSalary();
             double all =balance + hoursWorkeOfInstructor * getSalaryOfInstructor;
            this.instructors.get(i).getPerson().setMany(all);
            this.totalMany -= all;
        }
        this.person.setMany(this.person.getMany()+this.many);
        message.add("Salaries have been paid to all employees");
    }

    public void printActions() throws NullPointerException{
        if (!correctSecretary()){
            throw new NullPointerException(" Former secretaries are not permitted to perform actions");
        }
        for(String string:message)
        {
            System.out.println(string);
        }
        System.out.println();
        for (Client client:clients){
            System.out.println(client.getPerson().getName()+"Notifications: ["+mess(client.getNotifications())+"]");
        }
    }
    public static String mess(ArrayList<String> s) {
        String result = "";
        for (int i = 0; i < s.size(); i++) {
            result += s.get(i);  // הוספת המחרוזת
            if (i != s.size() - 1) {
                result += ",";  // הוספת פסיק אחרי כל אלמנט חוץ מהאחרון
            }
        }
        return result;
    }
    public static String Mess(ArrayList<SessionType> s) {
        String result = "";
        for (int i = 0; i < s.size(); i++) {
            result += s.get(i).toString();  // המרה למחרוזת בעזרת toString()
            if (i != s.size() - 1) {
                result += ",";  // הוספת פסיק אחרי כל אלמנט חוץ מהאחרון
            }
        }
        return result;
    }


}



