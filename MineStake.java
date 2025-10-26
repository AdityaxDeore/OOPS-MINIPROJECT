import java.util.*;
import java.io.*;

// ============================================================
// INTERFACE - Playable
// ============================================================
interface Playable {
    void startGame();
    void endGame();
}

// ============================================================
// CUSTOM EXCEPTIONS
// ============================================================
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

// ============================================================
// TILE CLASS - Represents individual grid cells
// ============================================================
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

// ============================================================
// PLAYER CLASS
// ============================================================
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

// ============================================================
// BOARD CLASS
// ============================================================
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

    // Place mines randomly
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

    // Display the board
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

// ============================================================
// THREAD 1 - Loading Animation (extends Thread)
// ============================================================
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

// ============================================================
// THREAD 2 - Status Updates (implements Runnable)
// ============================================================
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

// ============================================================
// ABSTRACT BASE CLASS - AbstractGame (INHERITANCE)
// ============================================================
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

// ============================================================
// MINESTAKE GAME CLASS - POLYMORPHISM (method overriding)
// ============================================================
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

    // Helper method to get tile coordinates
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

// ============================================================
// MAIN CLASS
// ============================================================
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
