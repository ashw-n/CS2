public class Student
{
    private String name;
    private String skill;
    private double gpa;
    private String standing;

    public Student(String name, String skill, double gpa, String standing)
    {
        this.name = name;
        this.skill = skill;
        this.gpa = gpa;
        this.standing = standing;
    }

    public String getName()
    {
        return name;
    }

    public String getSkill()
    {
        return skill;
    }

    public double getGpa()
    {
        return gpa;
    }

    public String getStanding()
    {
        return standing;
    }
}
