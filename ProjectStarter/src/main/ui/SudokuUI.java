package ui;

import model.Sudoku;
import model.SudokuList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.EventLog;
import javax.swing.*;

import java.util.Arrays;
import java.util.Iterator;

public class SudokuUI extends JPanel implements ActionListener {
    private static final String JSON_STORE = "./data/sudokuList.json";
    private JsonWriter jsonWriter;
    private static JsonReader jsonReader;

    protected JButton b1;
    protected JButton b2;
    protected JButton b3;
    protected JButton b4;
    protected JButton b5;
    protected JButton meatButton;

    private JFrame baseFrame;
    private JFrame sudokuFrame;
    private JFrame viewNewFrame;
    private JFrame viewSavedFrame;

    private JPanel basePanel;
    private JPanel sudokuPanel;
    private JPanel viewNewPanel;
    private JPanel viewSavedPanel;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;

    // part of the sudoku panel
    private JLabel label6;
    private JLabel numLabel;
    private JLabel addLabel;
    private JTextField enteredNumber;
    protected JButton numberButton;

    // part of the view panel
    private JLabel idLabel;
    private JLabel numLabel1;

    //picture
    private ImageIcon picture;
    private JLabel meat;

    private JTextField id;
    private JTextField blanks;

    private SudokuList sl; 
    private Sudoku sudoku;

    // EFFECTS: constucts the main menu, and when closing the application, it prints out all the events
    public SudokuUI() {
        initializeBaseFields();
        initializeBasePanel();

        baseFrame = new JFrame();

        baseFrame.add(basePanel, BorderLayout.CENTER);
        baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        baseFrame.setTitle("Menu");
        baseFrame.pack();
        baseFrame.setVisible(true);
        

        baseFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String events = sudoku.getEvents();
                System.out.println(events);
            }
        });

    }

    static class ForceClosedMessage extends Thread {
        public void run() {
        
        }
    }


    // MODIFIES: this
    // EFFECTS: initalizes base fields
    public void initializeBaseFields() {
        loadButton();
        saveButton();
        viewNewButton();
        viewOldButton();
        newButton();

        meatButton = new JButton("Save");
        meatButton.addActionListener(this);
        meatButton.setActionCommand("save");

        label1 = new JLabel("What do you want to do?");
        label2 = new JLabel("Make a new Sudoku:");
        label3 = new JLabel("");
        label3 = new JLabel("id:");
        label4 = new JLabel("blanks:");
        label6 = new JLabel("");

        id = new JTextField(20);
        id.setBounds(100, 20, 165, 25);

        blanks = new JTextField(20);
        blanks.setBounds(100, 20, 165, 25);

        sl = new SudokuList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: creates a image icon
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = SudokuUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    // MODIFIES: this
    // EFFECTS: initalizes base fields
    public void initializeBasePanel() {
        basePanel = new JPanel();
        basePanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        basePanel.setLayout(new GridLayout(2,6));
        basePanel.add(label1);
        basePanel.add(b1);
        basePanel.add(b2);
        basePanel.add(b4);
        basePanel.add(b5);

        picture = new ImageIcon("src\\images\\meat.png");
        meat = new JLabel(picture);
        JScrollPane scrollPane = new JScrollPane(meat);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        basePanel.add(scrollPane);

        basePanel.add(label2);
        basePanel.add(label3);
        basePanel.add(id);
        basePanel.add(label4);
        basePanel.add(blanks);
        basePanel.add(b3);
    }

    // MODIFIES: this
    // EFFECTS: initalizes load button
    public void loadButton() {
        b1 = new JButton("Load");
        b1.addActionListener(this);
        b1.setActionCommand("load");
    }

    // MODIFIES: this
    // EFFECTS: initalizes save button
    public void saveButton() {
        b2 = new JButton("Save");
        b2.addActionListener(this);
        b2.setActionCommand("save");
    }

    // MODIFIES: this
    // EFFECTS: initalizes new button
    public void newButton() {
        b3 = new JButton("New");
        b3.addActionListener(this);
        b3.setActionCommand("new");
    }

    // MODIFIES: this
    // EFFECTS: initalizes viewNew button
    public void viewNewButton() {
        b4 = new JButton("View New");
        b4.addActionListener(this);
        b4.setActionCommand("viewNew");
    }

    // MODIFIES: this
    // EFFECTS: initalizes viewSaved button
    public void viewOldButton() {
        b5 = new JButton("View Saved");
        b5.addActionListener(this);
        b5.setActionCommand("viewSave");
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadSudokuList(SudokuList sl) {
        JsonReader jsonReader = new JsonReader(JSON_STORE);
        try {
            sl = jsonReader.read();
            System.out.println("Loaded!");
        } catch (IOException e) {
            // shite it's finished
        }
    }

    // EFFECTS: saves the sudokuList to file
    private void saveSudokuList(SudokuList sl) {
        try {
            jsonWriter = new JsonWriter(JSON_STORE);
            jsonWriter.open();
            jsonWriter.write(sl);
            jsonWriter.close();
            System.out.println("Saved!");
        } catch (FileNotFoundException e) {
            // shite it's finished
        }
    }

    // EFFECTS: views all the sudokus in sudokuList
    private void viewNewSudokuList() {
        for (Sudoku s : sl.getSudokuList()) {
            initializeNewViewFields();
            initializeNewViewPanel();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    numLabel = new JLabel(String.valueOf(s.getNumbers()[i][j]));
                    viewNewPanel.add(numLabel);
                }
            }
            int id = s.getId();
            JLabel numId = new JLabel(String.valueOf(id));
            viewNewPanel.add(idLabel);
            viewNewPanel.add(numId);

            viewNewFrame = new JFrame("New Sudokus Finished");
            viewNewFrame.add(viewNewPanel, BorderLayout.CENTER);
            viewNewFrame.pack();
            viewNewFrame.setVisible(true);
        }
        sl.returnedSudokuList();
    }

    // MODIFIES: this
    // EFFECTS: initalizes the new view fields
    private void initializeNewViewFields() {    
        idLabel = new JLabel("id:");
        viewNewPanel = new JPanel();
    }

    // MODIFIES: this
    // EFFECTS: initalizes new view panels
    private void initializeNewViewPanel() {
        viewNewPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        viewNewPanel.setLayout(new GridLayout(5,4));
    }

    // EFFECTS: views saved SudokuList
    private void viewSavedSudokuList() {
        SudokuList savedSL = new SudokuList();
        try {
            savedSL = jsonReader.read();            
            for (int n = 0; n < savedSL.getSudokuList().size(); n++) {
                initializeSavedViewFields();
                initializeSavedViewPanel();
                
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        numLabel1 = new JLabel(String.valueOf(savedSL.getSudokuList().get(n).getNumbers()[i][j]));
                        viewSavedPanel.add(numLabel1);
                    }
                }
                JLabel numId = new JLabel(String.valueOf(savedSL.getSudokuList().get(n).getId()));
                viewSavedPanel.add(idLabel);
                viewSavedPanel.add(numId);
    
                viewSavedFrame = new JFrame("Saved Sudokus Finished");
                viewSavedFrame.add(viewSavedPanel, BorderLayout.CENTER);
                viewSavedFrame.pack();
                viewSavedFrame.setVisible(true);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: initalizes the new view fields
    private void initializeSavedViewFields() {    
        idLabel = new JLabel("id:");
        viewSavedPanel = new JPanel();
    }

    // MODIFIES: this
    // EFFECTS: initalizes new view panels
    private void initializeSavedViewPanel() {
        viewSavedPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        viewSavedPanel.setLayout(new GridLayout(5,4));
    }

    // EFFECTS: creates a new Sudoku puzzle from user input
    public void createPuzzle(Sudoku sudoku) {
        initializeSudokuFields();
        initializeSudokuPanel();
        // makes part of the panel for the sudoku
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                numLabel = new JLabel(String.valueOf(sudoku.getNumbers()[i][j]));
                sudokuPanel.add(numLabel);
            }
        }
        sudokuPanel.add(addLabel);
        sudokuPanel.add(enteredNumber);
        sudokuPanel.add(numberButton);

        sudokuFrame = new JFrame("Sudoku");
        sudokuFrame.add(sudokuPanel, BorderLayout.CENTER);
        sudokuFrame.pack();
        sudokuFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initalizes the sudoku fields
    private void initializeSudokuFields() {    
        addLabel = new JLabel("Enter a number, then press submit");

        enteredNumber = new JTextField(20);
        enteredNumber.setBounds(100, 20, 165, 25);

        numberButton = new JButton("Add");
        numberButton.addActionListener(this);
        numberButton.setActionCommand("add");

        sudokuPanel = new JPanel();
    }

    // MODIFIES: this
    // EFFECTS: initalizes the sudoku panels
    private void initializeSudokuPanel() {
        sudokuPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        sudokuPanel.setLayout(new GridLayout(5,4));
    }

    // MODIFIES: this
    // EFFECTS: changes the blank number "0" to the number inputted
    private void addNumber() {
        int insertNum = Integer.parseInt(enteredNumber.getText());
        sudoku.changeSquare(insertNum);
        if (sudoku.getBlanks() > 0) {
            sudokuFrame.dispose();
            createPuzzle(sudoku);
        } else {
            sl.addSudoku(sudoku);
            sudokuFrame.dispose();
        }
    }
    
    // EFFECTS: gets the actions performed
    public void actionPerformed(ActionEvent e) {
        if ("load".equals(e.getActionCommand())) {
            loadSudokuList(sl);
        } else if ("save".equals(e.getActionCommand())) {
            saveSudokuList(sl);
        } else if ("viewNew".equals(e.getActionCommand())) {
            viewNewSudokuList();
        } else if ("viewSave".equals(e.getActionCommand())) {
            viewSavedSudokuList();
        } else if ("new".equals(e.getActionCommand())) {
            int currentId = Integer.parseInt(id.getText());
            int currentBlanks = Integer.parseInt(blanks.getText());
            sudoku = new Sudoku(currentId, currentBlanks);
            sudoku.createPuzzle();
            createPuzzle(sudoku);
        } else {
            addNumber();
        }
    }



    public static void main(String[] args) {
        new SudokuUI();
    }
}
