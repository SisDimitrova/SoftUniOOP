package BorderControlInterfaceAndAbstraction;

public class Robot implements Identifiable{
    private String model;
    private String id;

    public String getModel() {
        return model;
    }

    public Robot(String model, String id) {
        this.model = model;
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
