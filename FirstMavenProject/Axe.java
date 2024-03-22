package rpg_lab;

public class Axe implements Weapon {

    private int attackPoints;
    private int durabilityPoints;

    public Axe(int attack, int durabilityPoints) {
        this.attackPoints = attack;
        this.durabilityPoints = durabilityPoints;
    }

    public int getAttackPoints() {
        return this.attackPoints;
    }


    public int getDurabilityPoint() {
        return this.durabilityPoints;
    }


    public void attack(Target target) {
        if (this.durabilityPoints <= 0) {
            throw new IllegalStateException("Axe is broken.");
        }

        target.takeAttack(this.attackPoints);
        this.durabilityPoints -= 1;
    }
}
