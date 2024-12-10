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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMany() {
        return many;
    }

    public void setMany(double many) {
        this.many = many;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }
}
