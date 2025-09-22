package persistence;

import model.Sudoku;
import model.SudokuList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestJson {
    protected void checkSudoku(int id, int[][] numbers, int blanks, Sudoku sudoku) {
        assertEquals(id, sudoku.getId());
        //making sure numbers is generated in sudoku class
        int[][] number = { {3,2,1,4},
                           {4,1,3,2},
                           {1,4,2,3},
                           {2,3,4,1}};
        assertFalse(number == sudoku.getNumbers());
        assertEquals(blanks, sudoku.getBlanks());
    }
}
