package model;

import java.util.Random;

import org.json.JSONObject;

import persistence.Writable;

import java.util.Iterator;

//  Represents an Sudoku game that is 6 x 6, with a unique id
public class Sudoku implements Writable {
    private int id;                          // sodoku id
    private int[][] numbers = new int[4][4]; // grid of the sudoku game
    private int blanks;                      // number of blanks in the sudoku puzzle
    static final int GRID = 4;
    static final int SUBGRID = 2;            // quarter of the grid, 2x2
    static Random rand = new Random();

    /*
     * REQURIES: 0 <= blanks <= 15
     * EFFECTS: creates a new Sudoku class with a 2d array of 4 x 4, 
     *          with the number of blanks
     */
    public Sudoku(int id, int blanks) {
        this.id = id;
        this.blanks = blanks;
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates a sudoku puzzle with numbers filled into the 2D array;
     * blank is the amount of squares with 0 as its number
     */
    public void createPuzzle() {
        int change = blanks;

        if (fillGrid(numbers, 0, 0)) {
            // we good
        } else {
            System.out.println("No solution found!");
        }

        while (change > 0) {
            int x = rand.nextInt(GRID);
            int y = rand.nextInt(GRID);
            if (numbers[x][y] != 0) {
                numbers[x][y] = 0;
                change--;
            }
        }
        EventLog.getInstance().logEvent(new Event("Puzzle created!"));
    }

    /*
     * REQUIRES: 1 <= num <= 4
     * MODIFIES: this
     * EFFECTS: inputs a number into the first grid square that is 0
     */
    public void changeSquare(int num) {
        boolean changed = false;

        for (int i = 0; i < 4; i++) {        // row
            if (changed == true) {
                break;
            }
            for (int j = 0; j < 4; j++) {    // column
                if (numbers[i][j] == 0) {
                    numbers[i][j] = num;
                    changed = true;
                    break;
                }
            }
        }
        EventLog.getInstance().logEvent(new Event("Blank changed!"));
        blanks--;
    }

    // MODIFIES: this
    // EFFECTS: changes the number at a specific row and column
    public void changeSquare(int num, int row, int column) {
        numbers[row][column] = num;
    }

    /*
     * EFFECTS: true if there are no blank squares in the sudoku puzzle, 
     *          otherwise false
     */
    public boolean checkFinish() {
        boolean noBlanks = true;
        for (int i = 0; i < GRID; i++) {
            for (int j = 0; j < GRID; j++) {
                if (numbers[i][j] == 0) {
                    noBlanks = false;
                }
            }
        }
        return noBlanks;
    }

    public int getId() {
        return id;
    }

    public int[][] getNumbers() {
        return numbers;
    }

    public int getBlanks() {
        return blanks;
    }

    // MODIFIES: this
    // EFFECTS: fills grid with numbers that are valid
    static boolean fillGrid(int[][] grid, int row, int col) {
        // If we reached beyond last row, grid is full
        if (row == GRID) {
            return true;
        }

        // Move to next cell
        int nextRow = (col == GRID - 1) ? row + 1 : row;
        int nextCol = (col + 1) % GRID;

        // Try numbers 1-4 in random order
        int[] nums = {1, 2, 3, 4};
        shuffle(nums);

        for (int num : nums) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;
                if (fillGrid(grid, nextRow, nextCol)) {
                    return true;
                }
                grid[row][col] = 0; // backtrack
            }
        }
        return false; // no valid number found, backtrack
    }

    // EFFECTS: checks if the number placed inside is valid according to sudoku rules
    static boolean isSafe(int[][] grid, int row, int col, int num) {
        for (int c = 0; c < GRID; c++) {
            if (grid[row][c] == num) {
                return false;
            }
        }

        for (int r = 0; r < GRID; r++) {
            if (grid[r][col] == num) {
                return false;
            }
        }

        int startRow = row - row % SUBGRID;
        int startCol = col - col % SUBGRID;
        for (int r = 0; r < SUBGRID; r++) {
            for (int c = 0; c < SUBGRID; c++) {
                if (grid[startRow + r][startCol + c] == num) {
                    return false;
                }
            }
        }
            
        return true;
    }

    // EFFECTS: helper function that switches the values of 2 indexs
    static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i]; 
            arr[i] = arr[j]; 
            arr[j] = temp;
        }
    }


    // EFFECTS: gets events and turns it into a string
    public String getEvents() {
        Iterator<Event> iterator = EventLog.getInstance().iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("blanks", blanks);
        json.put("numbers", numbers);
        return json;
    }
}
