package jediGalaxy;

public class Field {
    private long[][] starsField;

    public Field(int rows, int cols) {
        this.starsField = new long[rows][cols];
        fillInGalaxy(rows, cols, starsField);
    }
    private void fillInGalaxy(int rows, int cols, long[][] galaxy) {
        int value = 0;
        for (int currentRow = 0; currentRow < rows; currentRow++)
        {
            for (int currentCol = 0; currentCol < cols; currentCol++)
            {
                galaxy[currentRow][currentCol] = value++;
            }
        }
    }
    public boolean isInBounds (int row, int col) {
        return row >= 0 && col >= 0 && row < starsField.length && col < starsField[row].length;
    }
    public long getValue (int row, int col) {
        return this.starsField[row][col];
    }
    public void setValue (int row, int col, int newValue) {
        starsField[row][col] = newValue;
    }
    public int getColLength () {
        return this.starsField[1].length;
    }
}
