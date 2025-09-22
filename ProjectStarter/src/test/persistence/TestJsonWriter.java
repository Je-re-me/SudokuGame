package persistence;

import model.Sudoku;
import model.SudokuList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

public class TestJsonWriter extends TestJson {
    @Test
    void testWriterInvalidFile() {
        try {
            SudokuList sl = new SudokuList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            SudokuList sl = new SudokuList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySudokuList.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySudokuList.json");
            sl = reader.read();
            assertEquals(0, sl.getSudokuListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            SudokuList sl = new SudokuList();
            Sudoku sudoku1 = new Sudoku(1, 1);
            sudoku1.createPuzzle();
            int[][] numbers1 = sudoku1.getNumbers();
            sl.addSudoku(sudoku1);

            Sudoku sudoku2 = new Sudoku(2,2);
            sudoku2.createPuzzle();
            int[][] numbers2 = sudoku2.getNumbers();
            sl.addSudoku(sudoku2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSudokuList.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSudokuList.json");
            sl = reader.read();
            List<Sudoku> sudokus = sl.getSudokuList();
            assertEquals(2, sl.getSudokuListSize());
            checkSudoku(1, numbers1, 1, sudokus.get(0));
            checkSudoku(2, numbers2, 2, sudokus.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
