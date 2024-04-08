package magicGame.models.magics;

public class RedMagic extends MagicImpl{
    private static final int BULLETS = 1;
    public RedMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (getBulletsCount() >= 1) {
            setBulletsCount(getBulletsCount() - BULLETS);
            return BULLETS;
        }
        setBulletsCount(0);
        return 0;
    }
}
