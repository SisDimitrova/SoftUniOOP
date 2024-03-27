package bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTests {
    private static final int CAPACITY = 10;
    private static final String NAME = "Central";

    private Bank bank;
    private Client client;

    @Before
    public void setUp() {
        this.bank = new Bank(NAME, CAPACITY);
        this.client = new Client("testClient");
    }

    @Test(expected = NullPointerException.class)
    public void testCreatingBankWithNullNameShouldThrow() {
       new Bank(null, CAPACITY);
    }
    @Test(expected = NullPointerException.class)
    public void testIsEmptyNameShouldThrow() {
        new Bank("   ", CAPACITY);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreatingBankWithNegativeCapacityShouldThrow() {
        new Bank(NAME, -1);
    }
    @Test
    public void testCreatingBankWithValidArguments() {
        Assert.assertEquals(NAME, bank.getName());
        Assert.assertEquals(CAPACITY, bank.getCapacity());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddClientShouldFailWhenCapacityReached() {
      Bank bank = new Bank(NAME, 0);
      bank.addClient(client);
    }
    @Test
    public void testAddClientCorrectlySize() {
      bank.addClient(client);
      Assert.assertEquals(1, bank.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveClientShouldThrowMissing() {
        bank.removeClient(client.getName());
    }
    @Test
    public void testRemoveClientShouldDecrease() {
        bank.addClient(client);
        bank.removeClient(client.getName());
        Assert.assertEquals(0, bank.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testLoanWithdrawalShouldThrowMissing() {
        bank.loanWithdrawal(client.getName());
    }
    @Test
    public void testLoanWithdrawalShouldSetClient() {
        bank.addClient(client);
        bank.loanWithdrawal(client.getName());
        Assert.assertFalse(client.isApprovedForLoan());
    }

}
