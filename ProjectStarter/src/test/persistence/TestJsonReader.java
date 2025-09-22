package persistence;

import model.Sudoku;
import model.SudokuList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader extends TestJson {
    private int[][] numbers = {
        {3,2,1,4},
        {4,1,3,2},
        {1,4,2,3},
        {2,3,4,1}};

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SudokuList sl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySudokuList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySudokuList.json");
        try {
            SudokuList sl = reader.read();
            assertEquals(0, sl.getSudokuListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSudokuList.json");
        try {
            SudokuList sl = reader.read();
            List<Sudoku> sudokus = sl.getSudokuList();
            assertEquals(2, sudokus.size());
            checkSudoku(1, numbers, 3, sudokus.get(0));
            checkSudoku(2, numbers, 2, sudokus.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    
}
