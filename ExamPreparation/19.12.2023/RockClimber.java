package climbers.models.climber;

public class RockClimber extends BaseClimber {
    private static final double STRENGTH = 120;
    public RockClimber(String name) {
        super(name, STRENGTH);
    }

    @Override
    public void climb() {
        try {
            this.setStrength(this.getStrength() - 60);
        } catch (IllegalArgumentException ex) {
            this.setStrength(0);
        }
    }
}
