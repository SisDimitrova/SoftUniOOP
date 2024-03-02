package SayHelloExtendInterfaceAndAbstraction;

public abstract class BasePerson implements Person{
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    protected BasePerson(String name) {
        setName(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

}
