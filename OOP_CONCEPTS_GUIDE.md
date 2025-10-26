# OOP Concepts Reference Guide - MineStake Project

## Complete List of Java OOP Concepts with Line References

### 1. **Interface**
- **Location**: Lines 6-9
- **Implementation**: 
  - Interface `Playable` with methods `startGame()` and `endGame()`
  - Implemented by `MineStakeGame` class

### 2. **Custom Exceptions**
- **InvalidBetException**: Lines 14-18
- **InsufficientBalanceException**: Lines 20-24
- **Usage**: Exception handling with try-catch blocks

### 3. **Encapsulation**
- **Tile Class** (Lines 29-68): Private fields `isMine`, `isRevealed`, `displayChar` with public getters/setters
- **Player Class** (Lines 74-115): Private fields `name`, `balance` with public access methods
- **Board Class** (Lines 121-199): Private field `grid`, `size`, `mineCount`

### 4. **Constructors**
- **Default Constructor**: 
  - Tile() - Line 34
  - Player() - Line 81
  - Board() - Line 128
- **Parameterized Constructor**:
  - Tile(boolean isMine) - Line 40
  - Player(String name, double balance) - Line 87
  - Board(int size, int mineCount) - Line 134
- **Copy Constructor**:
  - Player(Player other) - Line 93

### 5. **this Keyword**
- Used extensively in all classes for field access and method calls
- Examples: Lines 35-37, 48, 88-89, 104-113, 135-137

### 6. **Array of Objects**
- **Tile[][] grid** in Board class (Line 122)
- Initialization at Lines 141-146

### 7. **Access Modifiers**
- **private**: Used for all class fields (encapsulation)
- **public**: Used for methods and constructors
- **protected**: Used in AbstractGame class (Line 208)
- **default** (package-private): Demonstrated in helper methods

### 8. **Inheritance**
- **Abstract Base Class**: `AbstractGame` (Lines 205-219)
- **Derived Class**: `MineStakeGame extends AbstractGame` (Line 225)
- Demonstrates IS-A relationship

### 9. **Abstract Class and Methods**
- **AbstractGame** class marked as `abstract` (Line 205)
- **Abstract method**: `public abstract void playRound()` (Line 214)
- Must be implemented by subclass

### 10. **Polymorphism**
- **Method Overriding**: 
  - `playRound()` overridden in MineStakeGame (Line 252)
  - `startGame()` implemented from Playable interface (Line 234)
  - `endGame()` implemented from Playable interface (Line 498)
- **Runtime Polymorphism**: Interface reference to concrete implementation

### 11. **Multithreading**

#### Thread 1 - extends Thread (Lines 165-181)
- **Class**: `LoadingThread extends Thread`
- **Method**: Overrides `run()` method
- **Usage**: Loading animation for mine placement
- **Lifecycle**: `start()`, `Thread.sleep()`, `join()`
- **Location in use**: Lines 297-303

#### Thread 2 - implements Runnable (Lines 187-201)
- **Class**: `StatusUpdateRunnable implements Runnable`
- **Method**: Implements `run()` method
- **Usage**: Async status updates
- **Location in use**: Lines 307-312

### 12. **Exception Handling**

#### try-catch-finally blocks:
- **File Writing** (Lines 457-473): 
  - try: File operations
  - catch: IOException handling
  - finally: Resource cleanup
  
- **File Reading** (Lines 508-538):
  - try: Reading game log
  - catch: IOException handling  
  - finally: Closing streams

#### throw and throws:
- **throws**: Method signatures (Lines 214, 252, 413, 414)
- **throw**: Lines 416, 419 (throwing custom exceptions)

#### Custom Exception Usage:
- InvalidBetException - Line 416
- InsufficientBalanceException - Line 419

### 13. **File Handling**

#### Writing to File (Lines 448-473):
- **FileWriter**: Line 457
- **BufferedWriter**: Line 458
- **Append mode**: `new FileWriter("game_log.txt", true)`
- **Writing**: `bw.write(logEntry)`

#### Reading from File (Lines 505-538):
- **File**: Line 514
- **FileReader**: Line 519
- **BufferedReader**: Line 520
- **Reading**: `br.readLine()`
- **ArrayList**: For storing lines (Line 524)

### 14. **java.util Package Classes**
- **Scanner**: For user input (Line 210, used throughout)
- **Random**: For mine placement (Line 152)
- **ArrayList**: For storing game log lines (Line 524)
- **Date**: For timestamps (Line 451)

### 15. **java.io Package Classes**
- **FileWriter**, **BufferedWriter**: Writing
- **FileReader**, **BufferedReader**: Reading
- **IOException**: Exception handling
- **File**: File operations

### 16. **Method Overloading**
- Not explicitly shown but can be demonstrated by adding overloaded constructors

### 17. **Static vs Instance**
- **Instance**: All methods and fields are instance-based
- **static**: Only `main()` method is static (Line 549)

## Viva Questions and Answers

### Q1: Why did you use an interface?
**A**: The `Playable` interface defines a contract that any game class must implement `startGame()` and `endGame()` methods. This ensures consistency and allows for future game types.

### Q2: Difference between Abstract Class and Interface?
**A**: 
- Abstract class can have both abstract and concrete methods (like `displayBalance()`)
- Interface only has method signatures (until Java 8)
- A class can implement multiple interfaces but extend only one abstract class

### Q3: Why use custom exceptions?
**A**: Custom exceptions like `InvalidBetException` provide more specific error information than generic exceptions, making debugging easier and code more readable.

### Q4: Explain the multithreading implementation
**A**: 
- **Thread 1** (LoadingThread): Extends Thread, used for loading animation
- **Thread 2** (StatusUpdateRunnable): Implements Runnable, used for status messages
- Both demonstrate different ways to create threads in Java

### Q5: What is encapsulation and where is it used?
**A**: Encapsulation means hiding internal data and providing controlled access through methods. Used in `Player` class - balance is private, accessed only via getters/setters.

### Q6: Explain inheritance in your project
**A**: `MineStakeGame` extends `AbstractGame`, inheriting the `player` and `scanner` fields and the `displayBalance()` method, while implementing the abstract `playRound()` method.

### Q7: What is polymorphism in your code?
**A**: Method overriding demonstrates polymorphism - `playRound()` is declared in `AbstractGame` and implemented differently in `MineStakeGame`. Also, interface methods are overridden.

### Q8: Why use finally block?
**A**: The `finally` block ensures resources like file streams are closed even if an exception occurs, preventing resource leaks.

### Q9: Difference between FileWriter and BufferedWriter?
**A**: 
- FileWriter writes characters to file
- BufferedWriter buffers characters for efficient writing
- BufferedWriter wraps FileWriter for better performance

### Q10: What is the 'this' keyword?
**A**: `this` refers to the current object instance. Used to distinguish between instance variables and parameters with the same name.

## Testing Checklist

✅ Compile without errors  
✅ Run and start game  
✅ Test bet validation (negative, zero, exceeds balance)  
✅ Test mine count validation (1-10 range)  
✅ Test coordinate validation (1-5 range)  
✅ Test hitting a mine  
✅ Test finding diamonds  
✅ Test cash out functionality  
✅ Test game log creation  
✅ Test balance updates  
✅ Test threading (loading animation)  
✅ Test exception handling  
✅ Test file I/O (read game history)  

---
**All OOP concepts are clearly marked with comments in the source code**
