package gym.customers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class Client implements Observer {
    protected Person person;
    public ArrayList<ForumType> forumTypes =new ArrayList<>();
    public ArrayList<String> notify = new ArrayList<>();

    public Client(Person person) {
        this.person=person;
        this.forumTypes.add(ForumType.All);
        if(person.getGender()==Gender.Female) {
            this.forumTypes.add(ForumType.Female);
        }
        else{
            this.forumTypes.add(ForumType.Male);}
        if(Up65(person.DateOfBirth)) {
            this.forumTypes.add(ForumType.Seniors);
        }
    }
    public Person getPerson() {
        return this.person;
    }
    public ArrayList<ForumType> getTypes(){
        return this.forumTypes;
    }

   //The person is over 65 years old
    public boolean Up65(String date) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate birthDate = LocalDate.parse(date, formatter);
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthDate, currentDate);
            return age.getYears() >= 65;
        }

    public String getName()
    {
        return this.person.getName();
    }

    public ArrayList<String> getNotifications() {
        return this.notify;
    }

   //Design template Observer
    @Override
    public void update(String s) {
        this.notify.add(s);
    }

    //Create a new comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // אותו אובייקט בזיכרון
        if (o == null || getClass() != o.getClass()) return false; // סוג שונה
        Client client = (Client) o;
        return person.getID()==(client.getPerson().getID()); // השוואה לפי id
    }
    @Override
    public int hashCode() {
        return Objects.hash(person.getID()); // ייחודיות לפי id
    }
}


