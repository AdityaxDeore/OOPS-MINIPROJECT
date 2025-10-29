# MineStake Game - Complete Code Explanation

## 📚 Table of Contents
1. [Project Overview](#project-overview)
2. [Import Statements](#import-statements)
3. [Interface - Playable](#interface---playable)
4. [Custom Exceptions](#custom-exceptions)
5. [Tile Class](#tile-class)
6. [Player Class](#player-class)
7. [Board Class](#board-class)
8. [Multithreading Classes](#multithreading-classes)
9. [AbstractGame Class](#abstractgame-class)
10. [MineStakeGame Class](#minestakegame-class)
11. [Main Class](#main-class)
12. [OOP Concepts Summary](#oop-concepts-summary)

---

## 📖 Project Overview

**MineStake** is a terminal-based betting game inspired by Stake.com's Mines game. Players bet money on a 5×5 grid to find diamonds while avoiding mines. The project demonstrates **all major OOP concepts** in a single comprehensive Java program.

### Key Features:
- 💰 Balance management system
- 🎲 Random mine placement
- 📈 Dynamic multiplier system
- 🧵 Multithreading for animations
- 📁 File handling for game logs
- ⚠️ Custom exception handling

---

## 🔧 Import Statements

```java
import java.util.*;
import java.io.*;
```

### Explanation:
- **`java.util.*`** - Imports utility classes:
  - `Scanner` - For user input
  - `Random` - For random mine placement
  - `Date` - For timestamps in logs
  - `ArrayList` - For storing game history

- **`java.io.*`** - Imports I/O classes:
  - `FileWriter` - For writing to files
  - `BufferedWriter` - For efficient file writing
  - `FileReader` - For reading from files
  - `BufferedReader` - For efficient file reading
  - `File` - For file operations
  - `IOException` - Exception handling for file operations

---

## 🎯 Interface - Playable

```java
interface Playable {
    void startGame();
    void endGame();
}
```

### OOP Concept: **INTERFACE**

### Line-by-Line Explanation:
- **Line 1**: Declares an interface named `Playable`
- **Line 2**: Abstract method `startGame()` - must be implemented by any class that implements this interface
- **Line 3**: Abstract method `endGame()` - must be implemented

### Purpose:
- Defines a **contract** that any game class must follow
- Ensures that implementing classes have `startGame()` and `endGame()` methods
- Demonstrates **abstraction** - defining WHAT to do, not HOW

### Why Use Interface?
- Provides **multiple inheritance** capability
- Enforces a standard structure for all game types
- Makes code more maintainable and extensible

---

## ⚠️ Custom Exceptions

### 1. InvalidBetException

```java
class InvalidBetException extends Exception {
    public InvalidBetException(String message) {
        super(message);
    }
}
```

### Line-by-Line Explanation:
- **Line 1**: Class extends `Exception` - creating a custom checked exception
- **Line 2**: Constructor with String parameter for error message
- **Line 3**: `super(message)` - calls parent Exception class constructor with the message

### Purpose:
- Thrown when bet amount is invalid (≤ 0)
- Custom exception for domain-specific error handling

---

### 2. InsufficientBalanceException

```java
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
```

### Line-by-Line Explanation:
- Same structure as `InvalidBetException`
- Thrown when player tries to bet more than their balance

### OOP Concept: **EXCEPTION HANDLING**
- Custom exceptions provide **meaningful error messages**
- Better than generic exceptions
- Makes debugging easier

---

## 🎲 Tile Class

```java
class Tile {
    private boolean isMine;
    private boolean isRevealed;
    private char displayChar;
```

### OOP Concepts Demonstrated:
1. **ENCAPSULATION** - Private fields
2. **DATA HIDING** - Fields accessible only through methods

### Field Explanation:
- `isMine` - `true` if tile contains a mine, `false` if it's a diamond
- `isRevealed` - `true` if player has clicked this tile
- `displayChar` - Character to display ('?', 'X', or 'D')

---

### Constructors in Tile Class

```java
// Default constructor
public Tile() {
    this.isMine = false;
    this.isRevealed = false;
    this.displayChar = '?';
}

// Parameterized constructor
public Tile(boolean isMine) {
    this.isMine = isMine;
    this.isRevealed = false;
    this.displayChar = '?';
}
```

### OOP Concept: **CONSTRUCTOR OVERLOADING**

### Explanation:
1. **Default Constructor**:
   - Called when `new Tile()` is used
   - Initializes tile as safe (not a mine)
   - Sets display to '?' (unrevealed)

2. **Parameterized Constructor**:
   - Called when `new Tile(true)` is used
   - Allows specifying if tile is a mine
   - Useful for testing

### `this` Keyword:
- Refers to **current object**
- Distinguishes between instance variables and parameters
- Makes code clearer

---

### Getters and Setters

```java
public boolean isMine() {
    return this.isMine;
}

public void setMine(boolean mine) {
    this.isMine = mine;
}

public boolean isRevealed() {
    return this.isRevealed;
}
```

### OOP Concept: **ENCAPSULATION**

### Why Use Getters/Setters?
1. **Controlled Access** - Can add validation logic
2. **Data Protection** - Direct field access prevented
3. **Flexibility** - Can change internal representation without affecting code using the class
4. **Read-Only/Write-Only** - Can provide only getter or only setter

---

### Reveal Method

```java
public void reveal() {
    this.isRevealed = true;
    this.displayChar = this.isMine ? 'X' : 'D';
}
```

### Line-by-Line Explanation:
- **Line 1**: Marks tile as revealed
- **Line 2**: Ternary operator
  - If `isMine` is true → set displayChar to 'X' (mine)
  - If `isMine` is false → set displayChar to 'D' (diamond)

### Logic:
- When player clicks a tile, this method is called
- Changes state from hidden ('?') to revealed ('X' or 'D')

---

## 👤 Player Class

```java
class Player {
    private String name;
    private double balance;
```

### OOP Concept: **ENCAPSULATION**
- Private fields protect data from direct modification

---

### Three Types of Constructors

```java
// 1. Default constructor
public Player() {
    this.name = "Player";
    this.balance = 1000.0;
}

// 2. Parameterized constructor
public Player(String name, double balance) {
    this.name = name;
    this.balance = balance;
}

// 3. Copy constructor
public Player(Player other) {
    this.name = other.name;
    this.balance = other.balance;
}
```

### OOP Concept: **CONSTRUCTOR OVERLOADING**

### Detailed Explanation:

**1. Default Constructor:**
- No parameters
- Sets default values (name = "Player", balance = 1000)
- Used in: `Player player = new Player();`

**2. Parameterized Constructor:**
- Takes name and balance as parameters
- Allows custom initialization
- Used in: `Player player = new Player("John", 5000);`

**3. Copy Constructor:**
- Creates a new object as a copy of existing object
- Takes another `Player` object as parameter
- Used in: `Player player2 = new Player(player1);`
- **Deep copy** - creates independent object

### Why Three Constructors?
- **Flexibility** - Different ways to create objects
- **Convenience** - Use appropriate constructor for situation
- **Best Practice** - Standard in professional Java code

---

### Player Methods

```java
public void addBalance(double amount) {
    this.balance += amount;
}

public void deductBalance(double amount) {
    this.balance -= amount;
}
```

### Purpose:
- `addBalance()` - Called when player wins
- `deductBalance()` - Called when player places a bet

### Why Not Directly Modify?
- Encapsulation principle
- Future additions (validation, logging) can be added here
- Maintains data integrity

---

## 🎮 Board Class

```java
class Board {
    private Tile[][] grid;
    private int size;
    private int mineCount;
```

### OOP Concept: **ARRAY OF OBJECTS**
- `Tile[][] grid` is a 2D array of Tile objects
- Represents the game board

---

### Board Initialization

```java
private void initializeGrid() {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            this.grid[i][j] = new Tile();
        }
    }
}
```

### Line-by-Line Logic:
1. **Outer loop** (i) - Iterates through rows (0 to 4)
2. **Inner loop** (j) - Iterates through columns (0 to 4)
3. **Inside loops** - Creates a new Tile object at each position

### Result:
- 5×5 grid = 25 Tile objects created
- Each tile starts unrevealed ('?')

---

### Mine Placement Algorithm

```java
public void placeMines() {
    Random random = new Random();
    int placedMines = 0;

    while (placedMines < this.mineCount) {
        int row = random.nextInt(this.size);
        int col = random.nextInt(this.size);

        if (!this.grid[row][col].isMine()) {
            this.grid[row][col].setMine(true);
            placedMines++;
        }
    }
}
```

### Algorithm Explanation:

**Step 1:** Create Random object
**Step 2:** Initialize counter (`placedMines = 0`)
**Step 3:** While loop continues until required mines are placed

**Inside Loop:**
1. Generate random row (0-4)
2. Generate random column (0-4)
3. Check if tile at [row][col] is already a mine
4. If NOT a mine:
   - Set it as mine
   - Increment counter
5. If already a mine:
   - Loop continues, generates new coordinates

### Why This Algorithm?
- **Ensures unique mine positions** - No duplicate mines
- **Random distribution** - Fair gameplay
- **Guaranteed mine count** - Exactly the number player chose

---

### Display Board Method

```java
public void displayBoard() {
    System.out.println("\n  1   2   3   4   5");
    for (int i = 0; i < size; i++) {
        System.out.print((i + 1) + " ");
        for (int j = 0; j < size; j++) {
            char display = this.grid[i][j].getDisplayChar();
            System.out.print("[ " + display + " ]");
        }
        System.out.println();
    }
    System.out.println();
}
```

### Visual Output Example:
```
  1   2   3   4   5
1 [ ? ][ ? ][ D ][ ? ][ ? ]
2 [ ? ][ X ][ ? ][ ? ][ ? ]
3 [ D ][ ? ][ ? ][ ? ][ ? ]
4 [ ? ][ ? ][ ? ][ ? ][ ? ]
5 [ ? ][ ? ][ ? ][ ? ][ ? ]
```

### Logic Breakdown:
1. Print column headers (1 2 3 4 5)
2. For each row:
   - Print row number
   - For each column:
     - Get display character from tile
     - Print in bracket format
   - New line after row

---

### Reveal Tile Method

```java
public boolean revealTile(int row, int col) {
    if (row < 0 || row >= size || col < 0 || col >= size) {
        return false;
    }
    this.grid[row][col].reveal();
    return !this.grid[row][col].isMine();
}
```

### Line-by-Line Logic:

**Line 1:** Input validation
- Checks if row/col are within bounds (0-4)
- Returns `false` if invalid

**Line 2:** Calls `reveal()` on the tile
- Changes tile state to revealed

**Line 3:** Returns result
- `true` if safe (not a mine) → Player continues
- `false` if mine → Player loses

### Return Value Usage:
```java
boolean safe = board.revealTile(row, col);
if (safe) {
    // Continue game, increase multiplier
} else {
    // End round, player lost
}
```

---

## 🧵 Multithreading Classes

### Thread 1: LoadingThread (extends Thread)

```java
class LoadingThread extends Thread {
    private String message;

    public LoadingThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            System.out.print(this.message);
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            System.out.println(" Done!");
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.out.println("Loading interrupted!");
        }
    }
}
```

### OOP Concept: **MULTITHREADING - Method 1**

### How It Works:

**Creating Thread:**
```java
LoadingThread loadingThread = new LoadingThread("Placing mines");
loadingThread.start(); // Starts the thread
```

**Execution Flow:**
1. Prints message ("Placing mines")
2. Waits 500ms
3. Prints first dot (.)
4. Waits 500ms
5. Prints second dot (.)
6. Waits 500ms
7. Prints third dot (.)
8. Prints " Done!"
9. Waits 300ms

**Output:**
```
Placing mines... Done!
```

### Why Use Thread?
- Provides **loading animation** while game initializes
- Makes game feel more interactive
- Demonstrates asynchronous execution

---

### Thread 2: StatusUpdateRunnable (implements Runnable)

```java
class StatusUpdateRunnable implements Runnable {
    private String status;

    public StatusUpdateRunnable(String status) {
        this.status = status;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            System.out.println(">> " + this.status);
        } catch (InterruptedException e) {
            System.out.println("Status update interrupted!");
        }
    }
}
```

### OOP Concept: **MULTITHREADING - Method 2**

### How It Works:

**Creating Thread:**
```java
Thread statusThread = new Thread(new StatusUpdateRunnable("Game Started!"));
statusThread.start();
```

**Execution:**
1. Waits 100ms
2. Prints status message with ">>" prefix

**Output:**
```
>> Game Started!
```

### Difference Between Two Threading Methods:

| Feature | extends Thread | implements Runnable |
|---------|---------------|---------------------|
| **Inheritance** | Cannot extend other class | Can extend another class |
| **Flexibility** | Less flexible | More flexible |
| **Best Practice** | Not recommended | Recommended |
| **Object Reuse** | Thread object can't be reused | Runnable can be reused |

---

## 🎯 AbstractGame Class

```java
abstract class AbstractGame {
    protected Player player;
    protected Scanner scanner;

    public AbstractGame(Player player) {
        this.player = player;
        this.scanner = new Scanner(System.in);
    }

    public abstract void playRound() throws InvalidBetException, InsufficientBalanceException;

    public void displayBalance() {
        System.out.println("Current Balance: Rs." + this.player.getBalance());
    }
}
```

### OOP Concepts:
1. **ABSTRACT CLASS**
2. **INHERITANCE**
3. **ACCESS MODIFIERS**

### Line-by-Line Explanation:

**Line 1:** `abstract class` - Cannot be instantiated directly
**Line 2-3:** `protected` fields - accessible to child classes
**Line 5-8:** Constructor - initializes player and scanner
**Line 10:** `abstract` method - no implementation, must be overridden
**Line 12-14:** Concrete method - has implementation, can be inherited

### Protected Access Modifier:
- Accessible in:
  - Same class ✓
  - Subclasses ✓
  - Same package ✓
- Not accessible in:
  - Other packages ✗

### Why Abstract Class?
- Provides **common functionality** for all game types
- Forces child classes to implement `playRound()`
- Demonstrates **partial abstraction** (mix of abstract and concrete methods)

---

## 🎮 MineStakeGame Class

```java
class MineStakeGame extends AbstractGame implements Playable {
    private Board board;
    private double currentBet;
    private int numberOfMines;
    private double multiplier;
    private int safeTilesRevealed;
```

### OOP Concepts:
1. **INHERITANCE** - `extends AbstractGame`
2. **INTERFACE IMPLEMENTATION** - `implements Playable`
3. **POLYMORPHISM** - Method overriding

### Multiple Inheritance in Java:
- Java doesn't support multiple class inheritance
- BUT supports: **1 class + multiple interfaces**
- Here: extends 1 class (`AbstractGame`) + implements 1 interface (`Playable`)

---

### Constructor

```java
public MineStakeGame(Player player) {
    super(player);
    this.multiplier = 1.0;
    this.safeTilesRevealed = 0;
}
```

### Explanation:
- **`super(player)`** - Calls parent class (`AbstractGame`) constructor
- Initializes multiplier to 1.0
- Initializes safe tile counter to 0

### `super` Keyword:
- Refers to parent class
- Must be first statement in constructor
- Calls parent constructor

---

### startGame() Method

```java
@Override
public void startGame() {
    System.out.println("\n" + "=".repeat(50));
    System.out.println("         WELCOME TO MINESTAKE GAME");
    System.out.println("=".repeat(50));

    boolean playAgain = true;

    while (playAgain && player.getBalance() > 0) {
        try {
            playRound();
        } catch (InvalidBetException | InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
            continue;
        }

        if (player.getBalance() <= 0) {
            System.out.println("\nGame Over! You're out of balance.");
            break;
        }

        System.out.print("\nDo you want to play again? (y/n): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        playAgain = choice.equals("y");
    }

    endGame();
}
```

### OOP Concept: **POLYMORPHISM - Method Overriding**

### `@Override` Annotation:
- Tells compiler this method overrides interface method
- Compile-time check
- Not mandatory but good practice

### Logic Flow:

**1. Display Welcome Message:**
- Prints header with repeated '=' characters

**2. Main Game Loop:**
```
while (playAgain AND balance > 0)
```
- Continues as long as player wants to play AND has money

**3. Exception Handling:**
```java
try {
    playRound(); // May throw exceptions
} catch (InvalidBetException | InsufficientBalanceException e) {
    // Handle errors
}
```
- **Multi-catch** - Catches multiple exception types in one block

**4. Balance Check:**
- If balance reaches 0, game ends

**5. Play Again Prompt:**
- Asks if player wants another round
- `.trim()` removes whitespace
- `.toLowerCase()` converts to lowercase for easy comparison

**6. End Game:**
- Calls `endGame()` when loop exits

---

### playRound() Method - Main Game Logic

```java
@Override
public void playRound() throws InvalidBetException, InsufficientBalanceException {
    System.out.println("\n" + "-".repeat(50));
    displayBalance();

    // Get and validate bet
    currentBet = getBetAmount();
    validateBet(currentBet);

    // Get number of mines
    numberOfMines = getMineCount();

    // Deduct bet from balance
    player.deductBalance(currentBet);
```

### OOP Concept: **POLYMORPHISM - Method Overriding**

### Step-by-Step Flow:

**Step 1:** Display separator and balance

**Step 2:** Get bet amount from user
- Calls `getBetAmount()` helper method
- Returns double value

**Step 3:** Validate bet
- Calls `validateBet()` method
- **May throw exceptions** - note `throws` in method signature

**Step 4:** Get mine count from user
- Calls `getMineCount()` helper method
- Returns int (1-10)

**Step 5:** Deduct bet from player balance
- Uses Player class method
- Demonstrates **encapsulation** - using proper methods, not direct access

---

### Threading in playRound()

```java
// Create board and place mines with loading animation
LoadingThread loadingThread = new LoadingThread("Placing mines");
loadingThread.start();

try {
    loadingThread.join(); // Wait for loading to complete
} catch (InterruptedException e) {
    System.out.println("Loading was interrupted");
}

board = new Board(5, numberOfMines);
board.placeMines();

// Start status update thread
Thread statusThread = new Thread(new StatusUpdateRunnable("Game Started!"));
statusThread.start();

try {
    statusThread.join();
} catch (InterruptedException e) {
    System.out.println("Status update interrupted");
}
```

### Thread Methods Explained:

**`.start()`** - Starts thread execution
- Calls the `run()` method in separate thread
- Returns immediately (doesn't wait)

**`.join()`** - Wait for thread to complete
- Current thread pauses until the thread finishes
- Ensures loading animation completes before continuing

### Why Use `join()`?
- Ensures proper sequencing
- Loading animation must finish before board is created
- Synchronizes threads

---

### Game Loop Logic

```java
// Reset multiplier and safe tiles
multiplier = 1.0;
safeTilesRevealed = 0;

// Game loop
boolean gameActive = true;
boolean hitMine = false;

while (gameActive) {
    board.displayBoard();

    int[] coords = getTileCoordinates();
    int row = coords[0] - 1;
    int col = coords[1] - 1;

    if (board.isTileRevealed(row, col)) {
        System.out.println("WARNING: This tile is already revealed. Try another one.");
        continue;
    }

    boolean safe = board.revealTile(row, col);

    if (safe) {
        safeTilesRevealed++;
        multiplier += 0.25;
        System.out.println("\nDIAMOND Safe! Multiplier: " + String.format("%.2f", multiplier) + "x");
        System.out.println("Potential Winnings: Rs." + String.format("%.2f", currentBet * multiplier));

        System.out.print("\nCash out? (y/n): ");
        String cashOutChoice = scanner.nextLine().trim().toLowerCase();

        if (cashOutChoice.equals("y")) {
            double winnings = currentBet * multiplier;
            player.addBalance(winnings);
            System.out.println("\nCongratulations! You won Rs." + String.format("%.2f", winnings));
            logGame(true, winnings);
            gameActive = false;
        }
    } else {
        System.out.println("\nBOOM! Mine hit! You lost Rs." + String.format("%.2f", currentBet));
        board.displayBoard();
        hitMine = true;
        logGame(false, 0);
        gameActive = false;
    }
}
```

### Detailed Logic Breakdown:

**1. Initialization:**
- Reset multiplier to 1.0
- Reset safe tiles to 0
- Set game flags

**2. Main Loop:**
```
while (gameActive)
```
- Continues until player cashes out or hits mine

**3. Display Board:**
- Shows current state of grid

**4. Get User Input:**
- `getTileCoordinates()` returns `int[]` array
- `coords[0]` is row, `coords[1]` is column
- Subtract 1 to convert from 1-based (user) to 0-based (array)

**5. Duplicate Check:**
- Checks if tile already revealed
- `continue` - skip rest of loop, start next iteration

**6. Reveal Tile:**
- Returns `true` if safe, `false` if mine

**7. If Safe:**
- Increment counter
- Increase multiplier by 0.25x
- Calculate potential winnings: `bet × multiplier`
- Ask if player wants to cash out
- If yes:
  - Calculate winnings
  - Add to balance
  - Log game result
  - End round

**8. If Mine:**
- Show "BOOM" message
- Display board (shows all revealed tiles)
- Log loss
- End round

---

### Helper Method: getBetAmount()

```java
private double getBetAmount() {
    while (true) {
        try {
            System.out.print("Enter bet amount: Rs.");
            String input = scanner.nextLine().trim();
            double bet = Double.parseDouble(input);
            return bet;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
}
```

### Logic Explanation:

**Infinite Loop:**
```java
while (true)
```
- Continues until valid input received

**Try-Catch Block:**
- **Try:** Parse user input to double
- **Catch:** If input is not a number (e.g., "abc"), catch exception
- **Result:** Loop continues, asks again

### Why This Design?
- **Input Validation** - Ensures only numbers accepted
- **User-Friendly** - Doesn't crash, gives helpful message
- **Robust** - Handles all invalid inputs

---

### Helper Method: validateBet()

```java
private void validateBet(double bet) throws InvalidBetException, InsufficientBalanceException {
    if (bet <= 0) {
        throw new InvalidBetException("Bet amount must be greater than zero!");
    }
    if (bet > player.getBalance()) {
        throw new InsufficientBalanceException("Insufficient balance! You only have Rs." + player.getBalance());
    }
}
```

### OOP Concept: **EXCEPTION HANDLING - Throwing Exceptions**

### Logic Flow:

**Validation 1:** Bet must be positive
- If `bet <= 0` → **throw** `InvalidBetException`
- Method stops here, exception propagates up

**Validation 2:** Bet must not exceed balance
- If `bet > balance` → **throw** `InsufficientBalanceException`

### Exception Flow:
```
validateBet(200)
   ↓
   Exception thrown
   ↓
playRound() - has "throws" declaration, doesn't handle
   ↓
startGame() - has try-catch, HANDLES exception
   ↓
Error message displayed to user
```

### `throws` vs `throw`:
- **`throw`** - Actually throws an exception
- **`throws`** - Declares that method might throw exception (in method signature)

---

### Helper Method: getMineCount()

```java
private int getMineCount() {
    while (true) {
        try {
            System.out.print("Enter number of mines (1-10): ");
            String input = scanner.nextLine().trim();
            int mines = Integer.parseInt(input);
            if (mines >= 1 && mines <= 10) {
                return mines;
            } else {
                System.out.println("Please enter a number between 1 and 10.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
}
```

### Logic:
1. Infinite loop until valid input
2. Try to parse input as integer
3. Check if in range (1-10)
4. If valid → return
5. If invalid → loop continues

### Range Validation:
```java
if (mines >= 1 && mines <= 10)
```
- Ensures game is playable (not too easy or impossible)

---

### Helper Method: getTileCoordinates()

```java
private int[] getTileCoordinates() {
    while (true) {
        try {
            System.out.print("Enter row and column (1-5): ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");
            
            if (parts.length != 2) {
                System.out.println("Please enter two numbers separated by space.");
                continue;
            }

            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            if (row >= 1 && row <= 5 && col >= 1 && col <= 5) {
                return new int[]{row, col};
            } else {
                System.out.println("Please enter numbers between 1 and 5.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
        }
    }
}
```

### Advanced String Processing:

**`.split(" ")`** - Splits string by space
- Input: `"3 2"`
- Result: `["3", "2"]`
- `parts[0]` = `"3"`, `parts[1]` = `"2"`

**Length Check:**
```java
if (parts.length != 2)
```
- Ensures exactly 2 numbers entered

**Parsing:**
```java
int row = Integer.parseInt(parts[0]);
int col = Integer.parseInt(parts[1]);
```
- Converts strings to integers

**Range Validation:**
```java
if (row >= 1 && row <= 5 && col >= 1 && col <= 5)
```
- Ensures coordinates are on board

**Return Array:**
```java
return new int[]{row, col};
```
- Creates and returns new array with both values

---

### File Handling: logGame()

```java
private void logGame(boolean won, double winnings) {
    FileWriter fw = null;
    BufferedWriter bw = null;

    try {
        fw = new FileWriter("game_log.txt", true);
        bw = new BufferedWriter(fw);

        String timestamp = new Date().toString();
        String result = won ? "WIN" : "LOSS";
        String logEntry = String.format("%s | %s | Bet: Rs.%.2f | Winnings: Rs.%.2f | Balance: Rs.%.2f%n",
                timestamp, result, currentBet, winnings, player.getBalance());

        bw.write(logEntry);

    } catch (IOException e) {
        System.out.println("Error writing to log file: " + e.getMessage());
    } finally {
        try {
            if (bw != null) bw.close();
            if (fw != null) fw.close();
        } catch (IOException e) {
            System.out.println("Error closing file: " + e.getMessage());
        }
    }
}
```

### OOP Concept: **FILE HANDLING - Writing**

### Line-by-Line Explanation:

**Line 1-2:** Declare file writer variables
- `FileWriter` - writes to file
- `BufferedWriter` - efficient writing (uses buffer)

**Line 5:** Create FileWriter
- `"game_log.txt"` - filename
- `true` - **append mode** (add to end, don't overwrite)

**Line 6:** Wrap in BufferedWriter
- Improves performance
- Writes data in chunks

**Line 8:** Get current timestamp
```java
new Date().toString()
```
- Example: `"Tue Oct 29 14:35:22 IST 2025"`

**Line 9:** Ternary operator
```java
won ? "WIN" : "LOSS"
```
- If `won` is true → `"WIN"`
- If `won` is false → `"LOSS"`

**Line 10:** String formatting
```java
String.format("%s | %s | Bet: Rs.%.2f | ...", ...)
```
- `%s` - String placeholder
- `%.2f` - Double with 2 decimal places
- `%n` - Platform-independent newline

**Example Output:**
```
Tue Oct 29 14:35:22 IST 2025 | WIN | Bet: Rs.200.00 | Winnings: Rs.250.00 | Balance: Rs.1050.00
```

**Line 13:** Write to file
```java
bw.write(logEntry);
```

**Finally Block:**
- **ALWAYS executes** (even if exception occurs)
- Closes file resources
- Prevents memory leaks
- **Nested try-catch** - closing can also throw exception

### Why BufferedWriter?
- **Performance** - Writes in batches, not character-by-character
- **Efficiency** - Reduces disk I/O operations

---

### endGame() Method

```java
@Override
public void endGame() {
    System.out.println("\n" + "=".repeat(50));
    System.out.println("              GAME SUMMARY");
    System.out.println("=".repeat(50));
    System.out.println("Final Balance: Rs." + String.format("%.2f", player.getBalance()));
    System.out.println("\nLast 5 Game Records:");
    displayLastGames(5);
    System.out.println("\n" + "=".repeat(50));
    System.out.println("      Thanks for playing MineStake!");
    System.out.println("=".repeat(50));
}
```

### OOP Concept: **POLYMORPHISM - Method Overriding**

### Logic:
1. Display header
2. Show final balance
3. Call `displayLastGames(5)` to show history
4. Display footer

---

### File Handling: displayLastGames()

```java
private void displayLastGames(int count) {
    FileReader fr = null;
    BufferedReader br = null;

    try {
        File file = new File("game_log.txt");
        if (!file.exists()) {
            System.out.println("No game history found.");
            return;
        }

        fr = new FileReader(file);
        br = new BufferedReader(fr);

        // Read all lines
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }

        // Display last 'count' lines
        int start = Math.max(0, lines.size() - count);
        for (int i = start; i < lines.size(); i++) {
            System.out.println((i - start + 1) + ". " + lines.get(i));
        }

    } catch (IOException e) {
        System.out.println("Error reading log file: " + e.getMessage());
    } finally {
        try {
            if (br != null) br.close();
            if (fr != null) fr.close();
        } catch (IOException e) {
            System.out.println("Error closing file: " + e.getMessage());
        }
    }
}
```

### OOP Concept: **FILE HANDLING - Reading**

### Detailed Logic:

**Step 1:** Check if file exists
```java
if (!file.exists())
```
- If no file, no history to display

**Step 2:** Create readers
- `FileReader` - reads file
- `BufferedReader` - efficient reading (line by line)

**Step 3:** Read all lines into ArrayList
```java
while ((line = br.readLine()) != null) {
    lines.add(line);
}
```
- **Condition:** `br.readLine()` returns null when end of file
- **Action:** Add each line to ArrayList

**Step 4:** Calculate starting position
```java
int start = Math.max(0, lines.size() - count);
```

**Example:**
- If file has 10 lines, want last 5
- `start = Math.max(0, 10 - 5) = 5`
- Prints lines 5-9 (indices)

- If file has 3 lines, want last 5
- `start = Math.max(0, 3 - 5) = 0`
- Prints all 3 lines

**Step 5:** Display lines
```java
for (int i = start; i < lines.size(); i++) {
    System.out.println((i - start + 1) + ". " + lines.get(i));
}
```
- `(i - start + 1)` - Creates numbering 1, 2, 3, ...

**Example Output:**
```
1. Tue Oct 29 14:30:15 IST 2025 | LOSS | Bet: Rs.100.00 | Winnings: Rs.0.00 | Balance: Rs.900.00
2. Tue Oct 29 14:32:22 IST 2025 | WIN | Bet: Rs.150.00 | Winnings: Rs.187.50 | Balance: Rs.1037.50
```

---

## 🏁 Main Class

```java
public class MineStake {
    public static void main(String[] args) {
        // Create player with default constructor
        Player player = new Player();

        // Create game instance
        MineStakeGame game = new MineStakeGame(player);

        // Start the game
        game.startGame();
    }
}
```

### Line-by-Line Explanation:

**Line 1:** Public class with same name as file
- Required for Java programs

**Line 2:** `main` method - entry point
- `public` - accessible from anywhere
- `static` - can be called without creating object
- `void` - doesn't return anything
- `String[] args` - command-line arguments

**Line 4:** Create Player object
- Uses default constructor
- Initial balance: Rs.1000

**Line 7:** Create game object
- Pass player to constructor
- Demonstrates **object composition** (game HAS-A player)

**Line 10:** Start game
- Calls interface method
- Game begins

---

## 📊 OOP Concepts Summary

### 1. **ENCAPSULATION**
- **Where:** All classes (Tile, Player, Board)
- **How:** Private fields + public getters/setters
- **Why:** Data protection, controlled access

### 2. **CONSTRUCTORS**
- **Default:** `Player()`
- **Parameterized:** `Player(String name, double balance)`
- **Copy:** `Player(Player other)`
- **Overloading:** Multiple constructors in same class

### 3. **this KEYWORD**
- **Where:** Throughout all classes
- **Purpose:** Refers to current object
- **Usage:** Distinguish instance variables from parameters

### 4. **ARRAY OF OBJECTS**
- **Where:** `Tile[][] grid` in Board class
- **Why:** Represent 2D game board

### 5. **ACCESS MODIFIERS**
- **private:** Fields (data hiding)
- **public:** Methods (interface)
- **protected:** AbstractGame fields (inheritance)

### 6. **INHERITANCE**
- **Parent:** AbstractGame
- **Child:** MineStakeGame
- **Keyword:** `extends`
- **Benefit:** Code reuse

### 7. **ABSTRACT CLASS**
- **Class:** AbstractGame
- **Features:**
  - Abstract method: `playRound()`
  - Concrete method: `displayBalance()`
- **Purpose:** Partial abstraction

### 8. **INTERFACE**
- **Interface:** Playable
- **Methods:** `startGame()`, `endGame()`
- **Implementation:** MineStakeGame
- **Keyword:** `implements`

### 9. **POLYMORPHISM**
- **Method Overriding:**
  - `startGame()` - overrides interface method
  - `endGame()` - overrides interface method
  - `playRound()` - overrides abstract method
- **Annotation:** `@Override`

### 10. **EXCEPTION HANDLING**
- **Custom Exceptions:**
  - InvalidBetException
  - InsufficientBalanceException
- **Try-Catch-Finally:** In file handling
- **Multi-Catch:** `catch (Exception1 | Exception2 e)`
- **Throws:** Method signature declarations

### 11. **MULTITHREADING**
- **Method 1:** LoadingThread `extends Thread`
- **Method 2:** StatusUpdateRunnable `implements Runnable`
- **Thread Methods:** `start()`, `join()`
- **Sleep:** `Thread.sleep(milliseconds)`

### 12. **FILE HANDLING**
- **Writing:**
  - FileWriter, BufferedWriter
  - Append mode
- **Reading:**
  - FileReader, BufferedReader
  - Line-by-line reading
- **Resource Management:** Finally block for closing

---

## 🎓 Key Takeaways for VIVA

### Question 1: "What is encapsulation?"
**Answer:** 
Encapsulation is wrapping data (fields) and methods into a single unit (class) and restricting direct access to data using access modifiers. In our project, all classes (Tile, Player, Board) have private fields and public getters/setters.

**Example:** 
```java
private double balance; // Private field
public double getBalance() { return balance; } // Public getter
```

---

### Question 2: "Difference between abstract class and interface?"

| Feature | Abstract Class | Interface |
|---------|---------------|-----------|
| Methods | Both abstract and concrete | Only abstract (Java 7), can have default (Java 8+) |
| Fields | Can have instance variables | Only constants (public static final) |
| Inheritance | Can extend only ONE class | Can implement MULTIPLE interfaces |
| Constructor | Can have constructors | Cannot have constructors |
| Example | AbstractGame | Playable |

---

### Question 3: "Why use multithreading?"
**Answer:**
- Creates **concurrent execution** - multiple tasks run simultaneously
- Improves **user experience** - loading animations don't block game
- Demonstrates **asynchronous programming**
- Two ways: `extends Thread` or `implements Runnable` (latter is better)

---

### Question 4: "Explain polymorphism in your project"
**Answer:**
Polymorphism means "many forms". In our project:
- **Method Overriding:** MineStakeGame overrides methods from AbstractGame and Playable interface
- Same method name (`startGame`) behaves differently based on implementation
- Enables **runtime polymorphism**

---

### Question 5: "Why use custom exceptions?"
**Answer:**
- **Domain-specific errors** - InvalidBetException is more meaningful than generic Exception
- **Better debugging** - Exactly know what went wrong
- **Cleaner code** - Separate handling for different error types
- **Professional practice** - Used in real-world applications

---

## 📝 Testing Scenarios

### Test Case 1: Invalid Bet
**Input:** -50
**Expected:** InvalidBetException thrown
**Actual:** "Bet amount must be greater than zero!"

### Test Case 2: Insufficient Balance
**Input:** Bet 1500 when balance is 1000
**Expected:** InsufficientBalanceException thrown
**Actual:** "Insufficient balance! You only have Rs.1000.0"

### Test Case 3: Already Revealed Tile
**Input:** Click same tile twice
**Expected:** Warning message
**Actual:** "WARNING: This tile is already revealed. Try another one."

### Test Case 4: Out of Bounds
**Input:** Row 6, Col 3
**Expected:** Invalid input message
**Actual:** "Please enter numbers between 1 and 5."

---

## 🚀 Conclusion

This MineStake project demonstrates **ALL major OOP concepts** in a single, cohesive program:

✅ **Encapsulation** - Data hiding with getters/setters  
✅ **Constructors** - Default, parameterized, copy  
✅ **this & super** - Object and parent references  
✅ **Inheritance** - AbstractGame → MineStakeGame  
✅ **Abstract Class** - Partial abstraction  
✅ **Interface** - Contract for game classes  
✅ **Polymorphism** - Method overriding  
✅ **Exception Handling** - Custom exceptions, try-catch-finally  
✅ **Multithreading** - Two different approaches  
✅ **File Handling** - Reading and writing  
✅ **Array of Objects** - 2D Tile array  
✅ **Access Modifiers** - public, private, protected  

The project is **production-quality** with:
- Input validation
- Error handling
- Clean code structure
- Proper documentation
- Real-world applicability

Perfect for understanding Java OOP concepts and explaining to teachers! 🎓

---

**Author:** OOPS Mini Project  
**Date:** October 2025  
**Language:** Java  
**Concepts:** All Major OOP Concepts
