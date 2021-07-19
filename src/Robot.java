public class Robot {
    //constants
    private static final char ORIENTATION_N = 'N';
    private static final char ORIENTATION_S = 'S';
    private static final char ORIENTATION_W = 'W';
    private static final char ORIENTATION_E = 'E';

    private static final char SPIN_90_LEFT = 'L';
    private static final char SPIN_90_RIGHT = 'R';
    private static final char MOVE_FORWARD = 'M';

    int upperRightX;
    int upperRightY;

    char orientation;
    int coordinateX;
    int coordinateY;

    String instructions;

    public Robot() {
        //empty constructor
    }

    public void setUpperRightCoordinates(int X, int Y) {
        this.upperRightX = X;
        this.upperRightY = Y;
    }

    public void setRobotPosition(int x, int y, char orientation) {
        this.coordinateX = x;
        this.coordinateY = y;
        this.orientation = orientation;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void robotProcess() {
        for (int i = 0; i < instructions.length(); i++) {
            char instruction = instructions.charAt(i);

            if (instruction == SPIN_90_LEFT) {
                rotateRobotLeft(orientation);
            } else if (instruction == SPIN_90_RIGHT) {
                rotateRobotRight(orientation);
            } else if (instruction == MOVE_FORWARD) {
                moveRobot();
            } else {
                throw new IllegalArgumentException("Please, Give valid instructions.");
            }

            if (isInvalidOutput()) {
                System.out.println("ERROR! Due to instructions, Robot goes to out of ward. Please try again.");
                return;
            }

//            System.out.println(coordinateX + "\t" + coordinateY + "\t" + orientation);
        }

        System.out.println(coordinateX + "\t" + coordinateY + "\t" + orientation);
    }

    public void rotateRobotLeft(char instruction) {
        if (instruction == ORIENTATION_N) {
            orientation = ORIENTATION_W;
        } else if (instruction == ORIENTATION_S) {
            orientation = ORIENTATION_E;
        } else if (instruction == ORIENTATION_W) {
            orientation = ORIENTATION_S;
        } else {
            orientation = ORIENTATION_N;
        }
    }

    public void rotateRobotRight(char instruction) {
        if (instruction == ORIENTATION_N) {
            orientation = ORIENTATION_E;
        } else if (instruction == ORIENTATION_S) {
            orientation = ORIENTATION_W;
        } else if (instruction == ORIENTATION_W) {
            orientation = ORIENTATION_N;
        } else {
            orientation = ORIENTATION_S;
        }
    }

    public void moveRobot() {
        if (orientation == ORIENTATION_W || orientation == ORIENTATION_E) {
            coordinateX = moveHorizontally(coordinateX, orientation);
        } else if (orientation == ORIENTATION_N || orientation == ORIENTATION_S) {
            coordinateY = moveVertically(coordinateY, orientation);
        }
    }

    public int moveHorizontally(int x, int direction) {
        if (direction == ORIENTATION_W) {
            return x - 1;
        } else {
            return x + 1;
        }
    }

    public int moveVertically(int y, int direction) {
        if (direction == ORIENTATION_S) {
            return y - 1;
        } else {
            return y + 1;
        }
    }

    public boolean isInvalidOutput() {
        if (0 > coordinateX || coordinateX > upperRightX) {
            return true;
        }

        if (0 > coordinateY || coordinateY > upperRightY) {
            return true;
        }

        return false;
    }


    //    main method
    public static void main(String[] args) {
        Robot robot = new Robot();

        robot.setUpperRightCoordinates(5, 5);
        robot.setRobotPosition(1, 2, ORIENTATION_N);
        robot.setInstructions("LMLMLMLMM");
        robot.robotProcess();

        robot.setUpperRightCoordinates(5, 5);
        robot.setRobotPosition(3, 3, ORIENTATION_E);
        robot.setInstructions("MMRMMRMRRM");
        robot.robotProcess();

        robot.setUpperRightCoordinates(5, 5);
        robot.setRobotPosition(3, 3, ORIENTATION_E);
        robot.setInstructions("LRRRRRRRLLRRRLMMRMMMMMMM");
        robot.robotProcess();

        robot.setUpperRightCoordinates(7, 2);
        robot.setRobotPosition(7, 2, ORIENTATION_E);
        robot.setInstructions("LLLMR");
        robot.robotProcess();

        robot.setUpperRightCoordinates(0, 0);
        robot.setRobotPosition(0, 0, ORIENTATION_N);
        robot.setInstructions("LLLMM");
        robot.robotProcess();
    }
}
