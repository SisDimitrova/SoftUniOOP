package robotService.entities.robot;

public class FemaleRobot extends BaseRobot{

    private final static int KILOGRAMS = 7;
    private final static int  INCREASES_KILOGRAMS = 1;

    public FemaleRobot(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);

    }

    @Override
    public void eating() {
        int newKilograms = super.getKilograms() + INCREASES_KILOGRAMS;
        super.setKilograms(newKilograms);
    }
}
