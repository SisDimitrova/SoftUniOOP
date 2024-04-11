package fairyShop.models;

public class ShopImpl implements Shop{

    @Override
    public void craft(Present present, Helper helper) {
        for (Instrument instrument : helper.getInstruments()) {
            while (!instrument.isBroken() && helper.canWork() && !present.isDone()) {
                helper.work();
                present.getCrafted();
                instrument.use();
            }

        }
    }
}
