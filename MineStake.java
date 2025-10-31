import java.io.*;
import java.util.*;

// INTERFACE - Playable
interface Playable {
    void startGame();
    void endGame();
}

// CUSTOM EXCEPTIONS
class InvalidBetException extends Exception {
    public InvalidBetException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

// TILE CLASS - Represents individual grid cells
class Tile {
    private boolean isMine;
    private boolean isRevealed;
    private char displayChar;

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

    // Getters and Setters - ENCAPSULATION
    public boolean isMine() {
        return this.isMine;
    }

    public void setMine(boolean mine) {
        this.isMine = mine;
    }

    public boolean isRevealed() {
        return this.isRevealed;
    }

    public void reveal() {
        this.isRevealed = true;
        this.displayChar = this.isMine ? 'X' : 'D';
    }

    public char getDisplayChar() {
        return this.isRevealed ? this.displayChar : '?';
    }
}

// PLAYER CLASS
class Player {
    private String name;
    private double balance;

    // Default constructor
    public Player() {
        this.name = "Player";
        this.balance = 1000.0;
    }

    // Parameterized constructor
    public Player(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    // Copy constructor
    public Player(Player other) {
        this.name = other.name;
        this.balance = other.balance;
    }

    // Getters and Setters - ENCAPSULATION
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Methods using 'this' keyword
    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void deductBalance(double amount) {
        this.balance -= amount;
    }
}

// BOARD CLASS
class Board {
    private Tile[][] grid;
    private int size;
    private int mineCount;

    // Default constructor
    public Board() {
        this.size = 5;
        this.mineCount = 3;
        this.grid = new Tile[size][size];
        initializeGrid();
    }

    // Parameterized constructor
    public Board(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
        this.grid = new Tile[size][size];
        initializeGrid();
    }

    // Initialize grid - Array of Objects
    private void initializeGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.grid[i][j] = new Tile();
            }
        }
    }

    // Place mines using N-Queens approach
    public void placeMines() {
        Random random = new Random();
        int choice = random.nextInt(3) + 1; // Randomly select 1, 2, or 3
        
        System.out.println("\nRandomly selecting mine placement algorithm...");
        
        switch (choice) {
            case 1:
                System.out.println("Selected: N-Queens Algorithm");
                placeMinesNQueens();
                break;
            case 2:
                System.out.println("Selected: Minimum Distance Algorithm");
                placeMinesMinDistance();
                break;
            case 3:
                System.out.println("Selected: Random Placement");
                placeMinesRandom();
                break;
        }
    }
    
    // METHOD 1: N-Queens Algorithm
    private void placeMinesNQueens() {
        Random random = new Random();
        
        // Step 1: Place first mine randomly
        int firstRow = random.nextInt(this.size);
        int firstCol = random.nextInt(this.size);
        this.grid[firstRow][firstCol].setMine(true);
        
        // Step 2: Use N-Queens algorithm for remaining mines
        int remainingMines = this.mineCount - 1;
        ArrayList<int[]> placedMines = new ArrayList<>();
        placedMines.add(new int[]{firstRow, firstCol});
        placeRemainingMinesNQueens(remainingMines, 0, placedMines);
    }
    
    // N-Queens recursive backtracking algorithm
    private boolean placeRemainingMinesNQueens(int minesToPlace, int row, ArrayList<int[]> placedMines) {
        if (minesToPlace == 0) {
            return true; // All mines placed successfully
        }
        
        if (row >= this.size) {
            return false; // Reached end of board, backtrack
        }
        
        // Try placing mine in each column of current row
        for (int col = 0; col < this.size; col++) {
            if (isSafeNQueens(row, col, placedMines) && !this.grid[row][col].isMine()) {
                // Place mine
                this.grid[row][col].setMine(true);
                placedMines.add(new int[]{row, col});
                
                // Recursively place remaining mines
                if (placeRemainingMinesNQueens(minesToPlace - 1, row + 1, placedMines)) {
                    return true;
                }
                
                // Backtrack if placement didn't work
                this.grid[row][col].setMine(false);
                placedMines.remove(placedMines.size() - 1);
            }
        }
        
        // Try next row without placing in current row
        return placeRemainingMinesNQueens(minesToPlace, row + 1, placedMines);
    }
    
    // Check if position is safe according to N-Queens rules
    private boolean isSafeNQueens(int row, int col, ArrayList<int[]> placedMines) {
        for (int[] mine : placedMines) {
            int placedRow = mine[0];
            int placedCol = mine[1];
            
            // Check if in same row
            if (placedRow == row) {
                return false;
            }
            
            // Check if in same column
            if (placedCol == col) {
                return false;
            }
            
            // Check if in same diagonal
            if (Math.abs(placedRow - row) == Math.abs(placedCol - col)) {
                return false;
            }
        }
        return true;
    }
    
    // METHOD 2: Minimum Distance Algorithm
    private void placeMinesMinDistance() {
        ArrayList<int[]> placedMines = new ArrayList<>();
        int minDistance = Math.max(1, size / 3); // Minimum distance between mines
        
        if (placeMinesWithMinDistance(mineCount, minDistance, placedMines)) {
            System.out.println("(Minimum distance between mines: " + minDistance + " tiles)");
        } else {
            // Fallback to random if backtracking fails
            System.out.println("Min distance placement failed, using random...");
            placeMinesRandom();
        }
    }
    
    private boolean placeMinesWithMinDistance(int minesToPlace, int minDist, ArrayList<int[]> placedMines) {
        if (minesToPlace == 0) {
            return true; // All mines placed successfully
        }
        
        Random random = new Random();
        int maxAttempts = size * size * 10;
        
        // Try random positions
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            
            if (!grid[row][col].isMine() && hasMinimumDistance(row, col, placedMines, minDist)) {
                // Place mine
                grid[row][col].setMine(true);
                placedMines.add(new int[]{row, col});
                
                // Recursively place remaining mines
                if (placeMinesWithMinDistance(minesToPlace - 1, minDist, placedMines)) {
                    return true;
                }
                
                // Backtrack if placement didn't work
                grid[row][col].setMine(false);
                placedMines.remove(placedMines.size() - 1);
            }
        }
        
        return false;
    }
    
    private boolean hasMinimumDistance(int row, int col, ArrayList<int[]> placedMines, int minDist) {
        for (int[] mine : placedMines) {
            // Manhattan distance: |x1-x2| + |y1-y2|
            int distance = Math.abs(row - mine[0]) + Math.abs(col - mine[1]);
            if (distance < minDist) {
                return false;
            }
        }
        return true;
    }
    
    // METHOD 3: Random Placement
    private void placeMinesRandom() {
        Random random = new Random();
        int placed = 0;
        
        while (placed < mineCount) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            
            if (!grid[row][col].isMine()) {
                grid[row][col].setMine(true);
                placed++;
            }
        }
    }

    // Display the board
    public void displayBoard() {
        // Print column headers
        System.out.print("\n   ");
        for (int i = 1; i <= size; i++) {
            System.out.print(String.format("%4d  ", i));
        }
        System.out.println();
        
        // Print rows
        for (int i = 0; i < size; i++) {
            System.out.print(String.format("%2d ", (i + 1)));
            for (int j = 0; j < size; j++) {
                char display = this.grid[i][j].getDisplayChar();
                System.out.print("[ " + display + " ]");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Reveal a tile
    public boolean revealTile(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        this.grid[row][col].reveal();
        return !this.grid[row][col].isMine();
    }

    public boolean isTileRevealed(int row, int col) {
        return this.grid[row][col].isRevealed();
    }

    public int getSize() {
        return this.size;
    }
}

// THREAD 1 - Loading Animation (extends Thread)
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

// THREAD 2 - Status Updates (implements Runnable)
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

// ABSTRACT BASE CLASS - AbstractGame (INHERITANCE)
abstract class AbstractGame {
    protected Player player;
    protected Scanner scanner;

    public AbstractGame(Player player) {
        this.player = player;
        this.scanner = new Scanner(System.in);
    }

    // Abstract method to be overridden
    public abstract void playRound() throws InvalidBetException, InsufficientBalanceException;

    // Concrete method
    public void displayBalance() {
        System.out.println("Current Balance: Rs." + this.player.getBalance());
    }
}

// MINESTAKE GAME CLASS - POLYMORPHISM (method overriding)
class MineStakeGame extends AbstractGame implements Playable {
    private Board board;
    private double currentBet;
    private int numberOfMines;
    private double multiplier;
    private int safeTilesRevealed;

    // Constructor
    public MineStakeGame(Player player) {
        super(player);
        this.multiplier = 1.0;
        this.safeTilesRevealed = 0;
    }

    // Implementing interface method
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

    // Overriding abstract method - POLYMORPHISM
    @Override
    public void playRound() throws InvalidBetException, InsufficientBalanceException {
        System.out.println("\n" + "-".repeat(50));
        displayBalance();

        // Get and validate bet
        currentBet = getBetAmount();
        validateBet(currentBet);

        // Get number of mines
        numberOfMines = getMineCount();
        
        // Board size = number of mines (3 mines = 3×3, 4 mines = 4×4, etc.)
        int boardSize = numberOfMines;

        // Deduct bet from balance
        player.deductBalance(currentBet);

        // Create board and place mines with loading animation
        LoadingThread loadingThread = new LoadingThread("Placing mines");
        loadingThread.start();

        try {
            loadingThread.join(); // Wait for loading to complete
        } catch (InterruptedException e) {
            System.out.println("Loading was interrupted");
        }

        board = new Board(boardSize, numberOfMines);
        board.placeMines();

        // Start status update thread
        Thread statusThread = new Thread(new StatusUpdateRunnable("Game Started!"));
        statusThread.start();

        try {
            statusThread.join();
        } catch (InterruptedException e) {
            System.out.println("Status update interrupted");
        }

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

        if (!hitMine && gameActive) {
            System.out.println("\nRound ended.");
        }
    }

    // Helper method to get bet amount
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

    // Helper method to validate bet - EXCEPTION HANDLING
    private void validateBet(double bet) throws InvalidBetException, InsufficientBalanceException {
        if (bet <= 0) {
            throw new InvalidBetException("Bet amount must be greater than zero!");
        }
        if (bet > player.getBalance()) {
            throw new InsufficientBalanceException("Insufficient balance! You only have Rs." + player.getBalance());
        }
    }

    // Helper method to get mine count
    private int getMineCount() {
        while (true) {
            try {
                System.out.print("Enter number of mines (2-10): ");
                String input = scanner.nextLine().trim();
                int mines = Integer.parseInt(input);
                if (mines >= 2 && mines <= 10) {
                    return mines;
                } else {
                    System.out.println("Please enter a number between 2 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    // Helper method to get tile coordinates
    private int[] getTileCoordinates() {
        int maxSize = board.getSize();
        while (true) {
            try {
                System.out.print("Enter row and column (1-" + maxSize + "): ");
                String input = scanner.nextLine().trim();
                String[] parts = input.split(" ");
                
                if (parts.length != 2) {
                    System.out.println("Please enter two numbers separated by space.");
                    continue;
                }

                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                if (row >= 1 && row <= maxSize && col >= 1 && col <= maxSize) {
                    return new int[]{row, col};
                } else {
                    System.out.println("Please enter numbers between 1 and " + maxSize + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers.");
            }
        }
    }

    // FILE HANDLING - Write to log
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

    // Implementing interface method
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

    // FILE HANDLING - Read from log
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
}

// MAIN CLASS
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
