package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;

public class MissionImpl implements Mission {

    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        Collection<String> stateExhibits = state.getExhibits();
        for (Explorer currentExplorer : explorers) {
            while (currentExplorer.canSearch() && stateExhibits.iterator().hasNext()) {
                currentExplorer.search();
                String currentExhibit = stateExhibits.iterator().next();
                currentExplorer.getSuitcase().getExhibits().add(currentExhibit);
                stateExhibits.remove(currentExhibit);
            }
        }
    }
}
