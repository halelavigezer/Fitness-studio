package gym.customers;

public class Person
{
    public static int cunt=0;
    protected String name;
    protected int ID =1111;
    protected int many;
    protected Gender gender;
    protected String DateOfBirth;

    public Person(String name, int many , Gender gender, String DateOfBirth)
    {
        this.ID=this.ID+cunt;
        cunt++;
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

    public int getMany() {
        return many;
    }

    public void setMany(int many) {
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
    public int getID(){
        return this.ID;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }
}
