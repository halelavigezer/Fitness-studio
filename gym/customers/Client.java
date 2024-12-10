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
}
