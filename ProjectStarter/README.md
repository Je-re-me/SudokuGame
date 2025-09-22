# A 6x6 Sudoku Game

## Discription

My application will be a 4x4 Sudoku game, **similar** to the famous 9x9 sudoku game, but with **4** *2x2 grids* that forms a *4x4 table*, and numbers ranging from **1-4** inside the grids.

## Who Will Use It?

This game can be played by **ANYONE**, made for all demographics. Most people can intuitively understand how to play the game after just a brief introduction of how the game works.

## Why Does This Project Interest Me?

I had a great interest in Sudoku when I was younger, and at one point I actually wanted to code this game, but I never got the motivation to do so until now!

## User Stories

- As a user, I want to be able to *generate* a new sudoku puzzle 
- As a user, I want to be able to *choose* the amount of squares that are *blank* in the puzzle while generating the puzzle, and the blanks in the puzzle are all placed *randomly*
- As a user, I want to be able to *add* a finished sudoku puzzle into the list of solved puzzles
- As a user, I want to *list* the ids of all the sudoku puzzles I've comepleted

- As a user, I want to be able to *view* the number of sudoku puzzles I've solved
- As a user, I want to be able to *view* after I inputed numbers into the blank squares if I was correct in solving the puzzle

- As a user, I want to be able to *input* a number in a square (cannot be a square that was generated; not blank at the start of the game)

- As a user, I want to be able to save my finished Sudoku puzzles to file (if I so choose)
- As a user, I want to be able to load my sudoku list from file (if I so choose)

## Instructions for End User
- To add a Sudoku to SudokuList, click the button "Add" after giving a id and blanks, and after complete the sudoku puzzle to add the completeled sudoku to SudokuList
- To show the saved Sudokus, click the button "Saved Sudokus" to see all the previously saved sudokus
- To locate my visual component, look at the menu screen where I have included a picture of meat
- To save the state of the application to file, click the button "Save"
- To load the state of the application to file, click the button
"Load"

## Citations
- Modeled code of JsonReader, JsonWriter, and Writable from JSON-java by stleary on github
- Modeled code of JsonReaderTest, JsonTest, and JsonWriterTest from JSON-java by stleary on github


## Phase 4: Task 2
Sun Mar 30 16:07:24 PDT 2025
Puzzle created!, Sun Mar 30 16:07:25 PDT 2025
Blank changed!, Sun Mar 30 16:07:25 PDT 2025
Puzzle Added!, Sun Mar 30 16:07:29 PDT 2025
Puzzle created!, Sun Mar 30 16:07:30 PDT 2025
Blank changed!, Sun Mar 30 16:07:30 PDT 2025
Puzzle Added!, Sun Mar 30 16:07:33 PDT 2025
Sudoku puzzle returned!, Sun Mar 30 16:07:33 PDT 2025
Sudoku puzzles returned!

## Phase 4: Task 3
- If I had more time to work on this project, I would try to refactor my Sudoku and my SudokuUI class, as they are super messy. I can barely read my code and it has just been a mess when I needed to change my implementation in those methods. I think I would need to completely rewrite the code and fit in helper methods to make the reading of my code smoother, as I struggled quite a bit after the third phase because I couldn't understand the own code I wrote. Additionally, I would try to shorten and move some lines into new methods as some methods have multiple things going on at the same time, and I think seperating them into different methods would make my code a lot easier to read.