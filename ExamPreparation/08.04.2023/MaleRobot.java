package robotService.entities.robot;

public class MaleRobot extends BaseRobot{
    private final static int KILOGRAMS = 9;
    private final static int  INCREASES_KILOGRAMS = 3;
    public MaleRobot(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }

    @Override
    public void eating() {
        int newKilograms = super.getKilograms() + INCREASES_KILOGRAMS;
        super.setKilograms(newKilograms);
    }
}
