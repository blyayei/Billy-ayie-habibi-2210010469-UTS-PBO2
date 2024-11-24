import java.util.ArrayList;

public class InventoryManager {
    private ArrayList<InventoryItem> items;

    public InventoryManager() {
        items = new ArrayList<>();
    }

    public void addItem(InventoryItem item) {
        items.add(item);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public void updateItem(int index, InventoryItem newItem) {
        if (index >= 0 && index < items.size()) {
            items.set(index, newItem);
        }
    }

    public ArrayList<InventoryItem> getItems() {
        return items;
    }
}
