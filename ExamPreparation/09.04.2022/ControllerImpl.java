package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;
import fairyShop.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private Repository<Helper> helperRepository;
    private Repository<Present> presentRepository;
    private int presentsDone;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
    }

    @Override
    public String addHelper(String type, String helperName) {
       Helper helper;
       if (type.equals("Sleepy")) {
           helper = new Sleepy(helperName);
       } else if (type.equals("Happy")) {
           helper = new Happy(helperName);
       } else {
           throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
       }
       helperRepository.add(helper);
        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = helperRepository.findByName(helperName);
        if (helper == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }
        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);
        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);

        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        Present present = this.presentRepository.findByName(presentName);
        List<Helper> helpers = helperRepository.getModels().stream().filter(helper -> helper.getEnergy() > 50)
                .collect(Collectors.toList());
        if (helpers.isEmpty()) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }
        Shop shop = new ShopImpl();
        String result = "not done";
        for (Helper helper : helpers) {
            shop.craft(present, helper);
            if (present.isDone()) {
                result = "done";
                break;
            }
        }
        long count = 0;
        for (Helper helper : helpers) {
            for (Instrument instrument : helper.getInstruments()) {
                if (instrument.isBroken()) {
                    count++;
            }
        }

        }
        if (result.equals("done")) {
            this.presentsDone++;
        }
        return String.format(PRESENT_DONE + COUNT_BROKEN_INSTRUMENTS, presentName, result, count);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d presents are done!", presentsDone)).append(System.lineSeparator());
        sb.append("Helpers info:").append(System.lineSeparator());
        for (Helper model : helperRepository.getModels()) {
            sb.append(String.format("Name: %s%nEnergy: %s%n", model.getName(), model.getEnergy()));
            int counter = 0;
            for (Instrument instrument : model.getInstruments()) {
                if (!instrument.isBroken()) {
                    counter++;
                }
            }
            sb.append(String.format("Instruments: %d not broken left%n", counter));

        }
        return sb.toString().trim();
    }
}
