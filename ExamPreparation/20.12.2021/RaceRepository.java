package christmasRaces.repositorie.interfaces;

import christmasRaces.entities.races.Race;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceRepository implements Repository<Race> {
    private Collection<Race> races;

    public RaceRepository() {
        this.races = new ArrayList<>();
    }

    @Override
    public Race getByName(String name) {
        return races.stream().filter(race -> race.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(races);
    }

    @Override
    public void add(Race model) {
     this.races.add(model);
    }

    @Override
    public boolean remove(Race model) {
        return this.races.remove(model);
    }
}
