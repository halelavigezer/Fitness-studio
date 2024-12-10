package gym.customers;

public class Person
{
    protected String name;
    protected double many;
    protected Gender gender;
    protected String DateOfBirth;

    public Person(String name, double many , Gender gender, String DateOfBirth)
    {
        this.name=name;
        this.many=many;
        this.gender=gender;
        this.DateOfBirth=DateOfBirth;
    }
}
