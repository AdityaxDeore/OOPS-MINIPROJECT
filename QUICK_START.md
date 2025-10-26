# MineStake Project - Quick Start Guide

## ✅ Project Status: READY TO RUN

### 📂 Files Created:
1. **MineStake.java** - Main game source code (578 lines)
2. **README.md** - Project documentation
3. **OOP_CONCEPTS_GUIDE.md** - Detailed OOP concepts reference for VIVA

### 🎯 Compilation Status: ✅ SUCCESS
All class files compiled successfully:
- MineStake.class
- Player.class
- Tile.class
- Board.class
- MineStakeGame.class
- AbstractGame.class
- Playable.class
- LoadingThread.class
- StatusUpdateRunnable.class
- InvalidBetException.class
- InsufficientBalanceException.class

## 🚀 How to Run

### Option 1: Run Directly (Already Compiled)
```bash
java MineStake
```

### Option 2: Compile and Run
```bash
javac MineStake.java
java MineStake
```

## 📚 Complete OOP Concepts Coverage

✅ **Encapsulation** - Private fields + getters/setters  
✅ **Constructors** - Default, Parameterized, Copy  
✅ **this Keyword** - Used throughout  
✅ **Array of Objects** - Tile[][] grid  
✅ **Access Modifiers** - public, private, protected, default  
✅ **Inheritance** - AbstractGame → MineStakeGame  
✅ **Abstract Class** - AbstractGame  
✅ **Interface** - Playable  
✅ **Polymorphism** - Method overriding  
✅ **Exception Handling** - try-catch-finally-throw-throws  
✅ **Custom Exceptions** - InvalidBetException, InsufficientBalanceException  
✅ **Multithreading** - extends Thread + implements Runnable  
✅ **File Handling** - Read/Write to game_log.txt  

## 🎮 Game Features

- 💰 Starting balance: Rs.1000
- 🎲 5×5 grid with customizable mines (1-10)
- 💎 Diamond picking with increasing multiplier
- 💥 Mine detection
- 💵 Cash out system
- 📝 Game logging to file
- 🔄 Play multiple rounds
- 📊 View game history

## 📝 Sample Game Flow

1. Start with Rs.1000
2. Place a bet (e.g., Rs.200)
3. Choose number of mines (e.g., 3)
4. Pick tiles by entering row and column (1-5)
5. Each safe tile increases multiplier by 0.25x
6. Cash out anytime or continue picking
7. Hit a mine = lose the bet
8. Repeat until balance = 0 or you quit

## 🎓 For VIVA Preparation

All concepts are clearly marked in the code with comments:
- Line numbers documented in OOP_CONCEPTS_GUIDE.md
- Each concept has real implementation
- No dummy/placeholder code
- Professional, production-ready implementation

## 📄 Auto-Generated Files

When you run the game, it will create:
- **game_log.txt** - Stores all game rounds with timestamps

## 🛠️ Technical Requirements Met

✅ Runs entirely in terminal (no GUI)  
✅ Uses only core Java (java.util, java.io, java.lang)  
✅ Single file compilation  
✅ Clean, well-commented code  
✅ Proper exception handling  
✅ Input validation  
✅ Professional output formatting  

## 🏆 Project Highlights

1. **Complete OOP Implementation** - All major concepts covered
2. **Real-world Application** - Actual game logic, not just examples
3. **Clean Code** - Well-structured, readable, maintainable
4. **Comprehensive Documentation** - README + Concepts Guide
5. **Error Handling** - Robust exception management
6. **User-Friendly** - Clear prompts and feedback
7. **Persistent Data** - File-based game logging

## 📞 Common Issues & Solutions

### Issue: "javac not recognized"
**Solution**: Make sure JDK is installed and added to PATH

### Issue: Emoji characters not showing
**Solution**: Already fixed! Using ASCII characters (D for diamond, X for mine)

### Issue: File encoding errors
**Solution**: Compile with `javac -encoding UTF-8 MineStake.java`

## 🎉 Ready to Present!

Your project is complete and ready to:
- ✅ Run and demonstrate
- ✅ Present to faculty
- ✅ Explain in VIVA
- ✅ Submit as mini project
- ✅ Showcase all OOP concepts

---

**Next Step**: Run `java MineStake` and start playing! 🎮

**For VIVA**: Review OOP_CONCEPTS_GUIDE.md for concept locations and explanations
