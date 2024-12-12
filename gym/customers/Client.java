package gym.customers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Client {
    protected Person person;
    public ArrayList<ForumType> types =new ArrayList<>();

    public Client(Person person) {
        this.person=person;
        this.types.add(ForumType.All);
        if(person.getGender()==Gender.Female) {
            this.types.add(ForumType.Female);
        }
        else
            this.types.add(ForumType.Male);
        if(Up65(person.DateOfBirth)) {
            this.types.add(ForumType.Seniors);
        }
    }
    public Person getPerson() {
        return this.person;
    }
    public ArrayList<ForumType> getTypes(){
        return this.types;
    }

    public boolean Up65(String date) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate birthDate = LocalDate.parse(date, formatter);
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthDate, currentDate);
            return age.getYears() >= 65;
        }

    }


