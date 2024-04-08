package magicGame.models.magicians;

import magicGame.models.magics.Magic;

import static magicGame.common.ExceptionMessages.*;

public abstract class MagicianImpl implements Magician {
    private String 	username;
    private int health;
    private int protection;
    private boolean isAlive;
    private Magic magic;

    public MagicianImpl(String username, int health, int protection, Magic magic) {
        this.setUsername(username);
        this.setHealth(health);
        this.setProtection(protection);
        this.setMagic(magic);
        this.isAlive = true;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(INVALID_MAGICIAN_NAME);
        }
        this.username = username;
    }

    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_HEALTH);
        }
        this.health = health;
    }

    public void setProtection(int protection) {
        if (protection < 0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_PROTECTION);
        }
        this.protection = protection;
    }

    public void setMagic(Magic magic) {
        if (magic == null) {
            throw new NullPointerException(INVALID_MAGIC);
        }
        this.magic = magic;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getProtection() {
        return this.protection;
    }

    @Override
    public Magic getMagic() {
        return this.magic;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public void takeDamage(int points) {
        int currentPoints = points;
        if (this.getProtection() < currentPoints) {
            currentPoints -= this.getProtection();
            this.setProtection(0);
            if (this.getHealth() <= 0) {
                this.setHealth(0);
                this.isAlive = false;
            } else {
                this.setHealth(this.getHealth() - currentPoints);
                if (this.getHealth() <= 0) {
                    this.setHealth(0);
                    this.isAlive = false;
                }
            }
        } else {
            setProtection(this.getProtection() - currentPoints);
            if (this.getHealth() <= 0) {
                this.setHealth(0);
                this.isAlive = false;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s: %s", this.getClass().getSimpleName(), this.username))
                .append(System.lineSeparator());
        sb.append(String.format("Health: %d", this.health)).append(System.lineSeparator());
        sb.append(String.format("Protection: %d", this.protection)).append(System.lineSeparator());
        sb.append(String.format("Magic: %s", this.magic.getName())).append(System.lineSeparator());
        return sb.toString();
    }
}
