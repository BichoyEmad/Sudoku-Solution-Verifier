package sodukuverifier;

public class Main {
    public static void main(String[] args)  {

        String path;
        int mode;

        if (args.length == 2) {
            path = args[0];
            try {
                mode = Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                System.err.println("Mode must be 0, 3, or 27");
                return;
            }
        } else {
            // fallback to interactive input: still can run from IDE
            java.util.Scanner sc = new java.util.Scanner(System.in);
            System.out.print("CSV Path: ");
            path = sc.nextLine();
            System.out.print("Mode (0, 3, 27): ");
            mode = sc.nextInt();
        }

        SudokuBoard board = new SudokuBoard(path);

        ValidationResult result = ValidationResult.getInstance();
        result.reset();

        Validator validator = ValidatorFactory.create(mode, board);
        validator.validate();

        if (result.valid) {
            System.out.println("VALID");
        } else {
            System.out.println("INVALID");
            for (String e : result.errors) {
                System.out.println(e);
            }
        }
    }
}
