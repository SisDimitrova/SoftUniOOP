package jediGalaxy;

public class Evil {
    public   void moveEvil(int rowEvil, int colEvil, Field field) {
        while (rowEvil >= 0 && colEvil >= 0) {
            if (field.isInBounds(rowEvil, colEvil)) {
                field.setValue(rowEvil, colEvil, 0);
            }
            rowEvil--;
            colEvil--;
        }
    }
}


