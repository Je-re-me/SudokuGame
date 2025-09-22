package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

public class TestSudokuList {
    private Sudoku testSudoku1;
    private Sudoku testSudoku2;
    private SudokuList testSudokus;

    @BeforeEach
    void runBefore() {
        testSudoku1 = new Sudoku(1, 5);
        testSudoku2 = new Sudoku(2, 0);
        testSudokus = new SudokuList();
    }

    @Test
    void testConstructor() {
        SudokuList newSudokuList = new SudokuList();
        assertEquals(newSudokuList.getSudokuList(), testSudokus.getSudokuList());
    }

    @Test
    void testAddOneSudoku() {
        testSudoku1.createPuzzle();
        testSudokus.addSudoku(testSudoku1);
        List<Sudoku> sudokus = new ArrayList<>();
        sudokus.add(testSudoku1);
        assertEquals(sudokus, testSudokus.getSudokuList());
    }

    @Test
    void testAddMultipleSudoku() {
        testSudoku1.createPuzzle();
        testSudoku2.createPuzzle();
        testSudokus.addSudoku(testSudoku1);
        testSudokus.addSudoku(testSudoku2);

        List<Sudoku> sudokus = new ArrayList<>();
        sudokus.add(testSudoku1);
        sudokus.add(testSudoku2);

        assertEquals(sudokus, testSudokus.getSudokuList());
    }
}
