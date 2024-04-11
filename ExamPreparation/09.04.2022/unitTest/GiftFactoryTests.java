package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class GiftFactoryTests {
    private GiftFactory giftFactory;
    private Gift gift;
    @Before
    public void setUp() {
        this.giftFactory = new GiftFactory();
        this.gift = new Gift("Surprise", 10.0);
    }
    @Test (expected = UnsupportedOperationException.class)
    public void testGetGiftShouldReturnUnmodifiableList() {
        Collection<Gift> gifts = giftFactory.getPresents();
        gifts.remove(new Gift("Test", 5.5));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateGiftShouldThrowSameTypes() {
        giftFactory.createGift(gift);
        giftFactory.createGift(new Gift("Surprise", 5.5));
    }
    @Test
    public void testCreateGift() {
        giftFactory.createGift(gift);
        assertEquals(1, giftFactory.getCount());
    }
    @Test(expected = NullPointerException.class)
    public void testRemoveGiftShouldThrowIsNull() {
        giftFactory.removeGift(null);
    }
    @Test(expected = NullPointerException.class)
    public void testRemoveGiftShouldThrowIsEmpty() {
        giftFactory.removeGift("    ");
    }
    @Test
    public void testRemoveGift() {
        giftFactory.createGift(gift);
      Gift gift1 =  new Gift("Test", 5.5);
        giftFactory.createGift(gift1);
        giftFactory.removeGift("Test");
        assertEquals(1, giftFactory.getCount());
    }
    @Test
    public void testGetPresentWithLeastMagic() {
        giftFactory.createGift(gift);
        Gift gift1 =  new Gift("Test", 5.5);
        giftFactory.createGift(gift1);
        giftFactory.getPresentWithLeastMagic();
        assertEquals(gift1, giftFactory.getPresentWithLeastMagic());
    }
    @Test
    public void testGetPresent() {
        giftFactory.createGift(gift);
        Gift gift1 =  new Gift("Test", 5.5);
        giftFactory.createGift(gift1);
        giftFactory.getPresent("Test");
        assertEquals(gift1, giftFactory.getPresent("Test"));
    }

}
