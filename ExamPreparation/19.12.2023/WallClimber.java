package climbers.models.climber;

public class WallClimber extends BaseClimber {
    private static final double STRENGTH = 90;
    public WallClimber(String name) {
        super(name, STRENGTH);
    }

    @Override
    public void climb() {
        try {
       this.setStrength(this.getStrength() - 30);
        } catch (IllegalArgumentException ex) {
            this.setStrength(0);
        }

    }
}
