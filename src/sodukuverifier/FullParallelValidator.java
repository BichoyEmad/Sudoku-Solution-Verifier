package sodukuverifier;
public class FullParallelValidator extends Validator {

    public FullParallelValidator(SudokuBoard board) {
        super(board);
    }

    public void validate() {
        Thread[] t = new Thread[27];
        int index = 0;

        // Row threads
        for (int r = 0; r < 9; r++) {
            final int row = r;
            t[index++] = new Thread(() -> checkRow(row));
        }

        // Column threads
        for (int c = 0; c < 9; c++) {
            final int col = c;
            t[index++] = new Thread(() -> checkCol(col));
        }

        // Box threads
        for (int b = 0; b < 9; b++) {
            final int box = b;
            t[index++] = new Thread(() -> checkBox(box));
        }

        for (Thread th : t) th.start();
        for (Thread th : t) {
            try { th.join(); } catch (Exception e) {}
        }
    }

    private void checkRow(int r) {
        int[] count = new int[10];
        for (int c = 0; c < 9; c++) count[board.get(r, c)]++;
        for (int n = 1; n <= 9; n++)
            if (count[n] > 1)
                result.addError("ROW " + (r + 1) + ", #" + n + ", [1,2,3,4,5,6,7,8,9]");
    }

    private void checkCol(int c) {
        int[] count = new int[10];
        for (int r = 0; r < 9; r++) count[board.get(r, c)]++;
        for (int n = 1; n <= 9; n++)
            if (count[n] > 1)
                result.addError("COL " + (c + 1) + ", #" + n + ", [1,2,3,4,5,6,7,8,9]");
    }

    private void checkBox(int boxId) {
        int r0 = (boxId / 3) * 3;
        int c0 = (boxId % 3) * 3;

        int[] count = new int[10];

        for (int r = r0; r < r0 + 3; r++)
            for (int c = c0; c < c0 + 3; c++)
                count[board.get(r, c)]++;

        for (int n = 1; n <= 9; n++)
            if (count[n] > 1)
                result.addError("BOX " + (boxId+1) + ", #" + n + ", [1,2,3,4,5,6,7,8,9]");
    }
}
