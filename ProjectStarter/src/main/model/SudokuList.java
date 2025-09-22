package model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of Sudoku puzzles that have been finished
public class SudokuList implements Writable {
    private List<Sudoku> sudokus;

    /*
     * EFFECTS: constructs a new sudoku list with no Sudoku puzzles inside it
     */
    public SudokuList() {
        sudokus = new ArrayList<Sudoku>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a Sudoku puzzle into sudokus
     */
    public void addSudoku(Sudoku sudoku) {
        sudokus.add(sudoku);
        EventLog.getInstance().logEvent(new Event("Puzzle Added!"));
    }

    public List<Sudoku> getSudokuList() {
        return sudokus;
    }

    // Modifies: EventLog
    // EFFECTS: creates Event that sudoku puzzles were returned and adds it to EventLog
    public void returnedSudokuList() {
        EventLog.getInstance().logEvent(new Event("Sudoku puzzle returned!"));
    }

    public int getSudokuListSize() {
        return sudokus.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("sudokus", sudokusToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    public JSONArray sudokusToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Sudoku s : sudokus) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}
