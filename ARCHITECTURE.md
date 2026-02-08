# Student Management System - Architecture Documentation

## 📐 System Architecture

The Student Management System follows a layered architecture pattern with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────┐
│                    Presentation Layer                    │
│                      (ConsoleUI)                         │
└─────────────────────┬───────────────────────────────────┘
                      │
┌─────────────────────▼───────────────────────────────────┐
│                    Service Layer                         │
│                  (StudentService)                        │
│              Business Logic & Validation                 │
└─────────────────────┬───────────────────────────────────┘
                      │
┌─────────────────────▼───────────────────────────────────┐
│                  Data Access Layer                       │
│         (StudentDAO & StudentDAOImpl)                    │
│              Data Persistence Logic                      │
└─────────────────────┬───────────────────────────────────┘
                      │
┌─────────────────────▼───────────────────────────────────┐
│                   Data Storage                           │
│                  (students.dat)                          │
│              File-based Persistence                      │
└─────────────────────────────────────────────────────────┘
```

## 🔷 Class Diagram

```
┌──────────────────────┐
│       Student        │
├──────────────────────┤
│ - id: int            │
│ - name: String       │
│ - email: String      │
│ - phone: String      │
│ - course: String     │
│ - gpa: double        │
├──────────────────────┤
│ + getId()            │
│ + setId()            │
│ + getName()          │
│ + setName()          │
│ + toString()         │
│ + equals()           │
│ + hashCode()         │
└──────────────────────┘
         △
         │ uses
         │
┌────────┴─────────────┐
│    StudentDAO        │
│   <<interface>>      │
├──────────────────────┤
│ + addStudent()       │
│ + updateStudent()    │
│ + deleteStudent()    │
│ + getStudentById()   │
│ + getAllStudents()   │
│ + searchByName()     │
│ + searchByCourse()   │
└──────────────────────┘
         △
         │ implements
         │
┌────────┴─────────────┐
│  StudentDAOImpl      │
├──────────────────────┤
│ - students: List     │
│ - DATA_FILE: String  │
├──────────────────────┤
│ + addStudent()       │
│ + updateStudent()    │
│ + deleteStudent()    │
│ + getAllStudents()   │
│ - loadStudents()     │
│ - saveStudents()     │
└──────────────────────┘
         △
         │ uses
         │
┌────────┴─────────────┐
│  StudentService      │
├──────────────────────┤
│ - studentDAO         │
├──────────────────────┤
│ + addStudent()       │
│ + updateStudent()    │
│ + deleteStudent()    │
│ + getStudentById()   │
│ + getAllStudents()   │
│ + searchByName()     │
│ + searchByCourse()   │
│ + getTotalStudents() │
│ + getAverageGPA()    │
└──────────────────────┘
         △
         │ uses
         │
┌────────┴─────────────┐
│     ConsoleUI        │
├──────────────────────┤
│ - studentService     │
│ - scanner            │
├──────────────────────┤
│ + start()            │
│ + displayMenu()      │
│ + addStudent()       │
│ + viewAllStudents()  │
│ + searchStudent()    │
│ + updateStudent()    │
│ + deleteStudent()    │
│ + displayStats()     │
└──────────────────────┘
         △
         │ uses
         │
┌────────┴─────────────┐
│   InputValidator     │
│    <<utility>>       │
├──────────────────────┤
│ + isValidEmail()     │
│ + isValidPhone()     │
│ + isValidGPA()       │
│ + isValidId()        │
│ + isNotEmpty()       │
└──────────────────────┘
```

## 🔄 Sequence Diagram - Add Student Flow

```
User → ConsoleUI → StudentService → StudentDAO → File

1. User selects "Add Student"
   User → ConsoleUI: Option 1

2. ConsoleUI prompts for student details
   ConsoleUI → User: Request input

3. User enters student information
   User → ConsoleUI: Student data

4. ConsoleUI creates Student object
   ConsoleUI → Student: new Student()

5. ConsoleUI calls service layer
   ConsoleUI → StudentService: addStudent(student)

6. Service validates student data
   StudentService → InputValidator: validate()

7. Service calls DAO layer
   StudentService → StudentDAO: addStudent(student)

8. DAO checks for duplicate ID
   StudentDAO → StudentDAO: studentExists(id)

9. DAO adds to list and saves
   StudentDAO → File: writeObject()

10. Success confirmation returned
    StudentDAO → StudentService → ConsoleUI → User
```

## 🏛️ Design Patterns Used

### 1. **DAO Pattern (Data Access Object)**
- **Purpose**: Separate persistence logic from business logic
- **Implementation**: StudentDAO interface and StudentDAOImpl
- **Benefit**: Easy to switch between file, database, or other storage

### 2. **Service Layer Pattern**
- **Purpose**: Centralize business logic and validation
- **Implementation**: StudentService class
- **Benefit**: Reusable logic, easier testing

### 3. **Singleton Pattern (Implicit)**
- **Purpose**: Single instance of DAO per application
- **Implementation**: Service creates one DAO instance
- **Benefit**: Consistent data access

### 4. **MVC Pattern (Modified)**
- **Model**: Student class
- **View**: ConsoleUI class
- **Controller**: StudentService class

## 📦 Package Structure

```
com.studentmgmt
├── Main.java                 # Entry point
├── model/                    # Domain entities
│   └── Student.java
├── dao/                      # Data access layer
│   ├── StudentDAO.java       # Interface
│   └── StudentDAOImpl.java   # Implementation
├── service/                  # Business logic layer
│   └── StudentService.java
├── ui/                       # User interface layer
│   └── ConsoleUI.java
└── util/                     # Utility classes
    └── InputValidator.java
```

## 🔐 Data Flow

### Read Operation
```
User Request → ConsoleUI → StudentService → StudentDAO → students.dat
                ↓              ↓               ↓
            Display ←──── Business Logic ←─ Deserialize
```

### Write Operation
```
User Input → ConsoleUI → StudentService → StudentDAO → students.dat
               ↓            ↓ Validate       ↓
          Create Object → Process → Serialize & Save
```

## 🎯 Key Features by Layer

### Model Layer
- Encapsulates student data
- Implements Serializable for persistence
- Provides equals() and hashCode() for comparisons

### DAO Layer
- Handles all file I/O operations
- Manages student data collection
- Provides CRUD operations
- Implements search functionality

### Service Layer
- Validates all inputs
- Enforces business rules
- Coordinates between UI and DAO
- Provides statistical operations

### UI Layer
- Handles user interaction
- Formats output
- Manages user input
- Provides menu navigation

### Utility Layer
- Validates email format
- Validates phone numbers
- Validates GPA range
- Reusable validation logic

## 🔧 Error Handling Strategy

1. **Input Validation**: At UI and Service layers
2. **Exception Handling**: Try-catch blocks in DAO layer
3. **User Feedback**: Clear error messages at UI layer
4. **Data Integrity**: Validation before persistence

## 🚀 Extensibility Points

The system is designed for easy extension:

1. **Add Database Support**: Implement new DAO class
2. **Add GUI**: Create new UI class using Swing/JavaFX
3. **Add Features**: Extend Student model and service layer
4. **Add Security**: Implement authentication in service layer
5. **Add API**: Create REST controllers using same service layer

## 📊 Performance Considerations

- **In-memory Operations**: Fast CRUD operations
- **Lazy Loading**: Data loaded only at startup
- **Batch Save**: Write to file only on changes
- **Stream API**: Efficient search operations

## 🔒 Security Considerations

- **Input Validation**: All inputs validated
- **Data Sanitization**: Trim and clean user inputs
- **File Permissions**: Ensure proper file access rights
- **No SQL Injection**: File-based storage (not applicable)

---

This architecture provides a solid foundation for building enterprise-level applications while remaining simple enough for learning purposes.
