package christmasPastryShop.core;

import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.interfaces.OpenBooth;
import christmasPastryShop.entities.booths.interfaces.PrivateBooth;
import christmasPastryShop.entities.cocktails.interfaces.Hibernation;
import christmasPastryShop.entities.cocktails.interfaces.MulledWine;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.delicacies.interfaces.Gingerbread;
import christmasPastryShop.entities.delicacies.interfaces.Stolen;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

import static christmasPastryShop.common.ExceptionMessages.*;
import static christmasPastryShop.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private final DelicacyRepository<Delicacy> delicacyRepository;
    private final CocktailRepository<Cocktail> cocktailRepository;
    private final BoothRepository<Booth> boothRepository;
    private double totalIncome;

    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
        this.totalIncome = 0;
    }
    @Override
    public String addDelicacy(String type, String name, double price) {
        Delicacy delicacy = this.delicacyRepository.getByName(name);
        if (delicacy != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
        if (type.equals("Gingerbread")) {
            delicacy = new Gingerbread(name, price);
        } else {
            delicacy = new Stolen(name, price);
        }
        this.delicacyRepository.add(delicacy);
        return String.format(DELICACY_ADDED, name, type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        Cocktail cocktail = this.cocktailRepository.getByName(name);
        if (cocktail != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
        if (type.equals("Hibernation")) {
            cocktail = new Hibernation(name, size, brand);
        } else {
            cocktail = new MulledWine(name, size, brand);
        }
        this.cocktailRepository.add(cocktail);
        return String.format(COCKTAIL_ADDED, name, brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        Booth booth = this.boothRepository.getByNumber(boothNumber);
        if (booth != null) {
            throw new IllegalArgumentException(String.format(BOOTH_EXIST, boothNumber));
        }
        if (type.equals("OpenBooth")) {
            booth = new OpenBooth(boothNumber, capacity);
        } else {
            booth = new PrivateBooth(boothNumber, capacity);
        }
        this.boothRepository.add(booth);
        return String.format(BOOTH_ADDED, boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
        for (Booth booth : this.boothRepository.getAll()) {
            if (!booth.isReserved() && booth.getCapacity() >= numberOfPeople) {
                booth.reserve(numberOfPeople);
                return String.format(BOOTH_RESERVED, booth.getBoothNumber(), numberOfPeople);
            }
        }
        return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {
        Booth booth = this.boothRepository.getByNumber(boothNumber);
        double bill = booth.getBill();
        this.totalIncome += bill;
        booth.clear();
        return String.format(BILL, boothNumber, bill);
    }

    @Override
    public String getIncome() {
        return String.format(TOTAL_INCOME, this.totalIncome);
    }

    }

