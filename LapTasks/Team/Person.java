package Team;

public class Person {
    private int age;
    private String firstName;
    private String lastName;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.setAge(age);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setSalary(salary);
    }

    public void setAge(int age) {
        if (age < 1) {
            throw new IllegalArgumentException("Age cannot be zero or negative integer");
        }
        this.age = age;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().length() < 3) {
        throw new IllegalArgumentException("First name cannot be less than 3 symbols");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().length() < 3) {
            throw new IllegalArgumentException("Last name cannot be less than 3 symbols");
        }
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("%s %s gets %f leva", this.getFirstName(), this.getLastName(), this.getSalary());
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 460) {
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }
        this.salary = salary;
    }
    public void increaseSalary(double bonus) {
        bonus = this.getAge() < 30 ? bonus / 2 : bonus;
        this.setSalary(this.getSalary() + (this.getSalary() * bonus) / 100);
    }
}