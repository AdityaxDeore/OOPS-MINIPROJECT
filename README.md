# MineStake - Terminal-Based Java Game

## 📋 Overview
MineStake is a terminal-based Java mini-project that simulates a simplified version of the Stake.com Mines Game. The player starts with Rs.1000 and places bets on a 5×5 grid to find diamonds while avoiding mines.

## 🎮 How to Run

### Compile the program:
```bash
javac MineStake.java
```

### Run the program:
```bash
java MineStake
```

## 🎯 Game Rules

1. **Starting Balance**: Rs.1000
2. **Each Round**:
   - Enter a bet amount
   - Choose the number of mines (1-10)
   - A 5×5 grid is generated with random mine placement
   - Input row and column (1-5) to uncover tiles
   - Safe tiles show 'D' (Diamond)
   - Mines show 'X'
   - Each safe pick increases your multiplier by 0.25x
   - Cash out anytime to secure winnings
   - Hit a mine = lose your bet

3. **Winning**: Bet × Multiplier
4. **Game Ends**: When balance reaches 0 or you choose to quit

## 🧩 Java OOP Concepts Implemented

| Concept | Implementation |
|---------|----------------|
| **Encapsulation** | Private fields with getters/setters in `Player`, `Tile`, `Board` classes |
| **Constructors** | Default, parameterized, and copy constructors |
| **this keyword** | Used throughout for clarity |
| **Array of Objects** | `Tile[][]` grid in Board class |
| **Access Modifiers** | public, private, protected demonstrated |
| **Inheritance** | `AbstractGame` (parent) → `MineStakeGame` (child) |
| **Abstract Class** | `AbstractGame` with abstract method `playRound()` |
| **Interface** | `Playable` interface with `startGame()` and `endGame()` |
| **Polymorphism** | Method overriding in `MineStakeGame` |
| **Exception Handling** | try-catch-finally, custom exceptions |
| **Custom Exceptions** | `InvalidBetException`, `InsufficientBalanceException` |
| **Multithreading** | Thread 1 (extends Thread), Thread 2 (implements Runnable) |
| **File Handling** | Writing to/reading from `game_log.txt` |

## 📁 File Structure

```
MineStake.java          # Main game file with all classes
CODE_EXPLANATION.md    # Detailed line-by-line code explanation
game_log.txt           # Auto-generated game history log
README.md              # This file
```

## 📖 Documentation

- **[CODE_EXPLANATION.md](CODE_EXPLANATION.md)** - Complete line-by-line explanation of every concept, perfect for:
  - Understanding the logic behind each method
  - Preparing for VIVA questions
  - Teaching/presenting the project to professors
  - Deep-diving into OOP concepts implementation

## 🎲 Example Gameplay

```
==================================================
         WELCOME TO MINESTAKE GAME
==================================================

--------------------------------------------------
Current Balance: Rs.1000.0
Enter bet amount: Rs.200
Enter number of mines (1-10): 3
Placing mines... Done!
>> Game Started!

  1   2   3   4   5
1 [ ? ][ ? ][ ? ][ ? ][ ? ]
2 [ ? ][ ? ][ ? ][ ? ][ ? ]
3 [ ? ][ ? ][ ? ][ ? ][ ? ]
4 [ ? ][ ? ][ ? ][ ? ][ ? ]
5 [ ? ][ ? ][ ? ][ ? ][ ? ]

Enter row and column (1-5): 3 2

DIAMOND Safe! Multiplier: 1.25x
Potential Winnings: Rs.250.00

Cash out? (y/n): y

Congratulations! You won Rs.250.00
```

## 📊 Features

✅ Clean terminal-based interface  
✅ Input validation (no negative bets, invalid coordinates)  
✅ Persistent game logging to file  
✅ Loading animations using threads  
✅ Balance tracking  
✅ Game history display  
✅ Comprehensive OOP design  

## 🔧 Technical Details

- **Language**: Java (Core Java only)
- **Libraries Used**: java.util, java.io, java.lang
- **No External Dependencies**: Runs with standard JDK
- **Platform**: Cross-platform (Windows, Linux, Mac)

## 📝 Class Structure

1. **Tile**: Represents individual grid cells
2. **Player**: Manages player data and balance
3. **Board**: Handles the 5×5 game grid
4. **LoadingThread**: Animation thread (extends Thread)
5. **StatusUpdateRunnable**: Status updates (implements Runnable)
6. **AbstractGame**: Base game class (abstract)
7. **MineStakeGame**: Main game logic (implements Playable)
8. **MineStake**: Entry point with main method

## 🎓 Perfect for

- OOP Mini Projects
- Java concept demonstration
- Learning exception handling
- Understanding multithreading
- File I/O practice
- VIVA preparation

---

**Developed as an OOP Mini Project**  
*All core Java concepts implemented in a single, well-documented file*
