package harpoonDiver.core;

import harpoonDiver.models.diver.DeepWaterDiver;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.diver.OpenWaterDiver;
import harpoonDiver.models.diver.WreckDiver;
import harpoonDiver.models.diving.Diving;
import harpoonDiver.models.diving.DivingImpl;
import harpoonDiver.models.divingSite.DivingSite;
import harpoonDiver.models.divingSite.DivingSiteImpl;
import harpoonDiver.repositories.DiverRepository;
import harpoonDiver.repositories.DivingSiteRepository;
import harpoonDiver.repositories.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static harpoonDiver.common.ConstantMessages.*;
import static harpoonDiver.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private final Repository<Diver> diverRepository;
    private final Repository<DivingSite> divingSiteRepository;
    private int divingCount;

    public ControllerImpl() {
        this.diverRepository = new DiverRepository();
        this.divingSiteRepository = new DivingSiteRepository();
        this.divingCount = 0;
    }

    @Override
    public String addDiver(String kind, String diverName) {
        Diver diver;
        if (kind.equals("DeepWaterDiver")) {
            diver = new DeepWaterDiver(diverName);
        } else if (kind.equals("OpenWaterDiver")) {
            diver = new OpenWaterDiver(diverName);
        } else if (kind.equals("WreckDiver")) {
            diver = new WreckDiver(diverName);
        } else {
            throw new IllegalArgumentException(DIVER_INVALID_KIND);
        }
        diverRepository.add(diver);
        return String.format(DIVER_ADDED, kind, diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {
        DivingSite divingSite = new DivingSiteImpl(siteName);
        divingSite.getSeaCreatures().addAll(Arrays.asList(seaCreatures));
        this.divingSiteRepository.add(divingSite);
        return String.format(DIVING_SITE_ADDED, siteName);
    }

    @Override
    public String removeDiver(String diverName) {
        Diver diver = this.diverRepository.byName(diverName);
        if (diver == null) {
            throw new IllegalArgumentException(String.format(DIVER_DOES_NOT_EXIST, diverName));
        }
        this.diverRepository.remove(diver);
        return String.format(DIVER_REMOVE, diverName);
    }

    @Override
    public String startDiving(String siteName) {
        List<Diver> divers = this.diverRepository.getCollection().stream()
                .filter(diver -> diver.getOxygen() > 30)
                .collect(Collectors.toList());
        if (divers.isEmpty()) {
            throw new IllegalArgumentException(SITE_DIVERS_DOES_NOT_EXISTS);
        }
        DivingSite divingSite = this.divingSiteRepository.byName(siteName);
        Diving diving = new DivingImpl();
        diving.searching(divingSite, divers);
        long removeDivers = divers.stream().filter(diver -> diver.getOxygen() == 0).count();
        this.divingCount++;
        return String.format(SITE_DIVING, siteName, removeDivers);
    }

    @Override
    public String getStatistics() {
     StringBuilder sb = new StringBuilder();
     sb.append(String.format(FINAL_DIVING_SITES, this.divingCount))
             .append(System.lineSeparator());
     sb.append(FINAL_DIVERS_STATISTICS).append(System.lineSeparator());
     this.diverRepository.getCollection().forEach(diver -> sb.append(diver)
             .append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
