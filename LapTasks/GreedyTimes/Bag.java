package greedyTimes;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private long capacity;
    private List<Item> items;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public void add(Item item) {
        if (canBagStore(item)) {
            items.add(item);
        }
    }

    private boolean canBagStore(Item item) {
        if (capacity < getBagTotalAmount() + item.getAmount()) {
            return false;
        }
        switch (item.getItemType()) {
            case GEM:
                if (getTypeAmount(ItemType.GEM) + item.getAmount() > getTypeAmount(ItemType.GOLD)) {
                    return false;
                }
                break;
            case CASH:
                if (getTypeAmount(ItemType.CASH) + item.getAmount() > getTypeAmount(ItemType.GEM)) {
                    return false;
                }
                break;
        }
        return true;
    }

    private long getBagTotalAmount() {
        return getTypeAmount(ItemType.GOLD) + getTypeAmount(ItemType.GEM) + getTypeAmount(ItemType.CASH);
    }

    private long getTypeAmount(ItemType itemType) {
        return items.stream()
                .filter(e -> e.getItemType().equals(itemType))
                .mapToLong(Item::getAmount)
                .sum();
    }

}
