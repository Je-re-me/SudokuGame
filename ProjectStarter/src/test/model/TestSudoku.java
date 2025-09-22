package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSudoku {
    private Sudoku testSudoku1;
    private Sudoku testSudoku2;
    private Sudoku testSudoku3;
    
    @BeforeEach
    void runBefore() {
        testSudoku1 = new Sudoku(1,5);
        testSudoku2 = new Sudoku(2,1);
        testSudoku3 = new Sudoku(3,0);
    }

    @Test
    void testConstructor() {
        Sudoku newSudoku = new Sudoku(123,5);
        assertEquals(123, newSudoku.getId());
    }

    @Test
    void testCreatePuzzle() {
        testSudoku1.createPuzzle();
        assertFalse(testSudoku1.checkFinish());

    }

    @Test
    void testChangeSquareOneTime() {
        testSudoku2.createPuzzle();
        testSudoku2.changeSquare(1);
        assertTrue(testSudoku1.checkFinish());
    }

    @Test
    void testChangeSquareMultipleTimes() {
        testSudoku1.createPuzzle();
        testSudoku1.changeSquare(1);
        testSudoku1.changeSquare(2);
        testSudoku1.changeSquare(3);
        testSudoku1.changeSquare(4);
        testSudoku1.changeSquare(5);
        assertTrue(testSudoku1.checkFinish());
    }

    @Test
    void testCheckFinish() {
        testSudoku3.createPuzzle();
        assertTrue(testSudoku3.checkFinish());

        testSudoku1.createPuzzle();
        testSudoku1.changeSquare(1);
        assertFalse(testSudoku1.checkFinish());
    }

    @Test
    void testGetId() {
        assertEquals(1, testSudoku1.getId());
    }

    @Test
    void testGetNumbers() {
        testSudoku1.createPuzzle();
        // these numbers should not be the same since it's all randomly blanked when calling createPuzzle()
        // just making sure that getNumbers() is of the same type int[][]
        int[][] number = { {3,2,1,4},
                           {4,1,3,2},
                           {1,4,2,3},
                           {2,3,4,1}};
        assertFalse(number == testSudoku1.getNumbers());
    }

    @Test
    void testGetBlanks() {
        assertEquals(5, testSudoku1.getBlanks());
    }
}
