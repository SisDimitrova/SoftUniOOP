package christmasPastryShop.repositories.interfaces;

import christmasPastryShop.entities.booths.interfaces.Booth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BootRepositoryImpl implements BoothRepository<Booth> {
    private Collection<Booth> booths;

    public BootRepositoryImpl() {
        this.booths = new ArrayList<>();
    }

    @Override
    public Booth getByNumber(int number) {
        return booths.stream().filter(b-> b.getBoothNumber() == number)
                .findFirst().orElse(null);
    }

    @Override
    public Collection<Booth> getAll() {
        return Collections.unmodifiableCollection(booths);
    }

    @Override
    public void add(Booth booth) {
        booths.add(booth);
    }
}
