MineStake is a terminal-based Java mini-project that simulates a simplified version of the Stake.com Mines Game.
The player starts with a fixed virtual balance (e.g., ₹1000) and places bets on a 5×5 grid filled with hidden tiles — some contain diamonds (safe tiles), others hide mines.

The goal is to find as many diamonds as possible without hitting a mine.
Each safe pick increases your multiplier and potential payout.
The player can cash out anytime to secure winnings or risk continuing to uncover more diamonds — if they hit a mine, the round ends and the bet is lost.

Everything happens in the terminal — no graphics, no libraries, just clean, logical console output and proper Java OOP design.

⚙️ HOW THE GAME WORKS (CLEAR MECHANICS)

The user starts with ₹1000 virtual balance.

On each round:

Enter bet amount (validated against current balance).

Choose the number of mines (risk level, 1–10).

The system generates a 5×5 hidden grid with random mine placement.

User enters row and column (1–5) to uncover a tile:

If diamond → safe tile → balance multiplier increases.

If mine → explosion → lose that round’s bet instantly.

After every safe pick:

Show current multiplier.

Ask: “Do you want to cash out or continue?”

If user cashes out → winnings = bet × multiplier → added to balance.

After each round → prompt to play again or exit.

Game logs (win/loss, time, balance) are stored in a text file (game_log.txt).

At exit → show summary and last 5 rounds from log.

Build a complete terminal-based Java project named MineStake, simulating a diamond-and-mine betting game similar to Stake.com Mines.

🎮 Game Rules

The user starts with ₹1000.

Each round:

User enters a bet amount.

User chooses the number of mines (1–10).

A 5×5 grid is generated with random mine placement.

The grid is hidden (shown as [ ? ]).

The player inputs row and column to uncover a tile.

If it’s a diamond → increase multiplier and ask if they want to cash out.

If it’s a mine → lose that round’s bet instantly.

If player cashes out, winnings = bet × multiplier.

Game repeats until balance = 0 or user exits.

🧩 Technical Requirements

Must run entirely in terminal (no GUI).

Must use only core Java (no libraries beyond java.util, java.io, java.lang, java.thread).

Must compile and run with:

javac MineStake.java
java MineStake

🧱 Java Concepts to Implement
OOP Concepts

Multiple classes: Player, Board, Game, Tile, and Main.

Encapsulation via private fields and public getters/setters.

Constructors: default, parameterized, copy.

Use of this keyword in constructors/methods.

Array of Objects for the 5×5 tile grid.

Access Modifiers: public, private, protected, default.

Inheritance: an abstract parent class AbstractGame and a subclass MineStakeGame.

Interface: Playable with methods startGame() and endGame().

Polymorphism: method overriding between base and derived classes.

Exception Handling

Use try, catch, finally, throw, and throws.

Custom exceptions:

InvalidBetException

InsufficientBalanceException

Multithreading

Thread 1: extends Thread → handles loading animation (“Placing mines…”).

Thread 2: implements Runnable → handles async status updates or countdowns.

Use Thread.sleep(), start(), and lifecycle methods.

File Handling

Write each round’s result to game_log.txt.

Read and display the last few game entries on exit.

Use FileWriter, BufferedWriter, FileReader, BufferedReader.

Core Design

Clean ASCII-based grid visualization.

Option to replay or exit after each round.

Proper input validation (no negative bets, invalid coords).

Clear inline comments marking where each Java concept is used.

Optional Extras

Add a “Leaderboard” that tracks the top balances.

Add color-coded output for win/loss (optional ANSI escape codes).

Output must be professional and well-formatted in the terminal with clear menus, separators, and summaries.

🧭 OUTPUT EXPECTATION

When the program runs, the terminal experience should feel like this:

====== MineStake ======
Balance: ₹1000
Enter bet: 200
Enter number of mines (1–10): 3
Placing mines... please wait.

Game Started!
  [ ? ][ ? ][ ? ][ ? ][ ? ]
  [ ? ][ ? ][ ? ][ ? ][ ? ]
  [ ? ][ ? ][ ? ][ ? ][ ? ]
  [ ? ][ ? ][ ? ][ ? ][ ? ]
  [ ? ][ ? ][ ? ][ ? ][ ? ]

Enter row and column (1-5): 3 2
💎 Safe! Multiplier: 1.25x
Cash out? (y/n): n

Enter next tile (1-5): 2 5
💥 Mine hit! You lost ₹200.

Do you want to play again? (y/n): y

🧰 TOPICS COVERED FOR VIVA / REPORT
Concept	Demonstration
Encapsulation	Private balance + getters/setters
Constructor Overloading	Player and Board classes
Inheritance & Interface	AbstractGame and Playable
Polymorphism	Overridden methods in subclasses
Multithreading	Two threads for animation and messages
Exception Handling	Custom + built-in
File Handling	Logs and reading history
OOP Cohesion	Modular, reusable class design
I/O Stream	Character-based file writing
Access Modifiers	All four types shown