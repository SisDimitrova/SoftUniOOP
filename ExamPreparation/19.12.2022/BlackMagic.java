package magicGame.models.magics;

public class BlackMagic extends MagicImpl{
    private static final int BULLETS = 10;
    public BlackMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (getBulletsCount() >= 10) {
            setBulletsCount(getBulletsCount() - BULLETS);
            return BULLETS;
        }
        setBulletsCount(0);
        return 0;
    }
}
