# Build Instructions for Student Management System

## Prerequisites
- Java Development Kit (JDK) 11 or higher
- Eclipse IDE (2020-03 or later)

## Building from Command Line

### Step 1: Create bin directory
```bash
mkdir -p bin
```

### Step 2: Compile all Java files
```bash
javac -d bin -sourcepath src src/com/studentmgmt/Main.java
```

### Step 3: Run the application
```bash
java -cp bin com.studentmgmt.Main
```

## Building in Eclipse IDE

### Step 1: Import Project
1. File → Import → General → Existing Projects into Workspace
2. Browse to StudentManagementSystem folder
3. Click Finish

### Step 2: Build Project
- Eclipse automatically builds the project
- Check for compilation errors in Problems view

### Step 3: Run Application
1. Right-click on Main.java
2. Run As → Java Application

## Creating Executable JAR

### From Eclipse:
1. Right-click on project → Export
2. Java → Runnable JAR file
3. Select Main - StudentManagementSystem
4. Choose export destination
5. Click Finish

### From Command Line:
```bash
# After compilation
jar cfe StudentManagementSystem.jar com.studentmgmt.Main -C bin .
```

### Run the JAR:
```bash
java -jar StudentManagementSystem.jar
```

## Troubleshooting Build Issues

### Issue: "Cannot find symbol" errors
**Solution**: 
- Ensure all source files are in correct package structure
- Clean and rebuild the project
- In Eclipse: Project → Clean

### Issue: "Class not found" when running
**Solution**:
- Check that bin directory is in classpath
- Verify Main class package: com.studentmgmt.Main

### Issue: Eclipse doesn't recognize Java 11
**Solution**:
- Install JDK 11 or higher
- Window → Preferences → Java → Installed JREs
- Add JRE if not present

## Compilation Order

The project files will be compiled in this dependency order:
1. Student.java (model)
2. InputValidator.java (util)
3. StudentDAO.java (dao interface)
4. StudentDAOImpl.java (dao implementation)
5. StudentService.java (service)
6. ConsoleUI.java (ui)
7. Main.java (entry point)

## Build Output

After successful compilation:
```
bin/
└── com/
    └── studentmgmt/
        ├── Main.class
        ├── model/
        │   └── Student.class
        ├── dao/
        │   ├── StudentDAO.class
        │   └── StudentDAOImpl.class
        ├── service/
        │   └── StudentService.class
        ├── ui/
        │   └── ConsoleUI.class
        └── util/
            └── InputValidator.class
```

## Runtime Requirements

- Java Runtime Environment (JRE) 11 or higher
- Read/write permissions in application directory (for students.dat)
- Console/terminal for input/output

## Notes

- The application creates a `students.dat` file for data persistence
- This file will be created in the current working directory
- Ensure the application has write permissions

---

**Build successful! Ready to run! 🚀**
