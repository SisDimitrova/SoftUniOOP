package RandomArrayList_03;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class RandomArrayList extends ArrayList {
    public Object getRandomElement() {
        int index = new Random().nextInt(super.size());
        return super.remove(index);
    }

}
