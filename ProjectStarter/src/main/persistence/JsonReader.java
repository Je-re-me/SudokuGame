package persistence;

import model.Sudoku;
import model.SudokuList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a writer that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    /* 
     * EFFECTS: reads sudokuList from file and returns it;
     *          throws IOException if an error occurs reading data from file
    */
    public SudokuList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSudokuList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses sudokuList from JSON object and returns it
    private SudokuList parseSudokuList(JSONObject jsonObject) {
        SudokuList sl = new SudokuList();
        addSudokus(sl, jsonObject);
        return sl;
    }

    // MODIFIES: sl
    // EFFECTS: parses sudoku from JSON object and adds them to sudokuList
    private void addSudokus(SudokuList sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("sudokus");
        for (Object json : jsonArray) {
            JSONObject nextSudoku = (JSONObject) json;
            addSudoku(sl, nextSudoku);
        }
    }

    // MODIFIES: sl
    // EFFECTS: parses sudoku from JSON object and adds it to sudokuList
    private void addSudoku(SudokuList sl, JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        int blanks = jsonObject.getInt("blanks");

        JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("numbers"));
        int[][] numbers = new int[jsonArray.length()][];

        // json to int[][]
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONArray row = jsonArray.getJSONArray(i);
            numbers[i] = new int[row.length()];
            for (int j = 0; j < row.length(); j++) {
                numbers[i][j] = row.getInt(j);
            }
        }

        Sudoku sudoku = new Sudoku(id, blanks);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sudoku.changeSquare(numbers[i][j], i, j);
            }
        }
        sl.addSudoku(sudoku);
        
    }


}