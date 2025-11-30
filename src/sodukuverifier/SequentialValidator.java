package sodukuverifier;
public class SequentialValidator extends Validator {

    public SequentialValidator(SudokuBoard board) {
        super(board);
    }

    public void validate() {
        checkRows();
        checkCols();
        checkBoxes();
    }

    public void checkRows() {
        for (int r = 0; r < 9; r++) {
            int[] count = new int[10];

            for (int c = 0; c < 9; c++) {
                count[board.get(r, c)]++;
            }

            for (int n = 1; n <= 9; n++) {
                if (count[n] > 1) {
                    result.addError("ROW " + (r + 1) + ", #" + n + ", [1,2,3,4,5,6,7,8,9]");
                }
            }
        }
    }

    public void checkCols() {
        for (int c = 0; c < 9; c++) {
            int[] count = new int[10];

            for (int r = 0; r < 9; r++) {
                count[board.get(r, c)]++;
            }

            for (int n = 1; n <= 9; n++) {
                if (count[n] > 1) {
                    result.addError("COL " + (c + 1) + ", #" + n + ", [1,2,3,4,5,6,7,8,9]");
                }
            }
        }
    }

    public void checkBoxes() {
        int boxId = 1;

        for (int br = 0; br < 3; br++) {
            for (int bc = 0; bc < 3; bc++) {
                int[] count = new int[10];

                for (int r = br * 3; r < br * 3 + 3; r++) {
                    for (int c = bc * 3; c < bc * 3 + 3; c++) {
                        count[board.get(r, c)]++;
                    }
                }

                for (int n = 1; n <= 9; n++) {
                    if (count[n] > 1) {
                        result.addError("BOX " + (boxId) + ", #" + n + ", [1,2,3,4,5,6,7,8,9]");
                    }
                }
                boxId++;
            }
        }
    }
}
