package gym.customers;

public class Client
{
    protected Person person;

    public Client(Person person)
    {
        this.person=person;
    }
    public Person getPerson()
    {
        return this.person;
    }

    public boolean Up65(String date) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate birthDate = LocalDate.parse(date, formatter);
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthDate, currentDate);
            return age.getYears() >= 65;
        }

    }

