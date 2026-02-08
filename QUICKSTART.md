# Quick Start Guide - Student Management System

## 🚀 Getting Started in 3 Minutes

### Step 1: Import to Eclipse (30 seconds)
1. Open Eclipse IDE
2. File → Import → General → Existing Projects into Workspace
3. Browse to StudentManagementSystem folder
4. Click Finish

### Step 2: Run the Application (10 seconds)
1. In Project Explorer, expand: src → com.studentmgmt
2. Right-click `Main.java`
3. Select "Run As" → "Java Application"

### Step 3: Try Basic Operations (2 minutes)

#### Add Your First Student
```
Choose option: 1
Enter Student ID: 101
Enter Student Name: John Doe
Enter Email: john.doe@email.com
Enter Phone: 1234567890
Enter Course: Computer Science
Enter GPA: 3.75
```

#### View All Students
```
Choose option: 2
```

#### Search by Name
```
Choose option: 3
Select: 2 (Search by Name)
Enter name: John
```

#### Update a Student
```
Choose option: 4
Enter Student ID: 101
(Press Enter to keep values, or type new ones)
```

#### Delete a Student
```
Choose option: 5
Enter Student ID: 101
Confirm: yes
```

## 📊 Sample Test Data

Try adding these sample students:

**Student 1:**
- ID: 101
- Name: Alice Johnson
- Email: alice.j@university.edu
- Phone: 5551234567
- Course: Computer Science
- GPA: 3.85

**Student 2:**
- ID: 102
- Name: Bob Smith
- Email: bob.smith@university.edu
- Phone: 5559876543
- Course: Electrical Engineering
- GPA: 3.62

**Student 3:**
- ID: 103
- Name: Carol Williams
- Email: carol.w@university.edu
- Phone: 5555551234
- Course: Computer Science
- GPA: 3.91

**Student 4:**
- ID: 104
- Name: David Brown
- Email: david.b@university.edu
- Phone: 5554443333
- Course: Mechanical Engineering
- GPA: 3.45

**Student 5:**
- ID: 105
- Name: Emma Davis
- Email: emma.davis@university.edu
- Phone: 5552221111
- Course: Computer Science
- GPA: 3.78

## 🎯 Common Tasks

### Search Computer Science Students
1. Choose option: 3
2. Select: 3 (Search by Course)
3. Enter course: Computer Science

### Update Student's GPA
1. Choose option: 4
2. Enter Student ID
3. Press Enter for all fields except GPA
4. Enter new GPA value

### View Statistics
1. Choose option: 6
2. See total students and average GPA

## ⚠️ Important Notes

- **Email Format**: Must include @ and domain (e.g., user@example.com)
- **Phone Format**: Exactly 10 digits, no spaces or dashes
- **GPA Range**: Between 0.0 and 4.0
- **Student ID**: Must be unique and positive

## 🐛 Quick Troubleshooting

**Problem**: "Student already exists"
- Solution: Use a different Student ID

**Problem**: "Invalid email format"
- Solution: Use format like: name@domain.com

**Problem**: "Invalid phone number"
- Solution: Enter exactly 10 digits: 1234567890

**Problem**: Data not saving
- Solution: Check if `students.dat` file has write permissions

## 💡 Tips

1. **Backup Data**: Copy `students.dat` file to backup your student data
2. **Reset Data**: Delete `students.dat` to start fresh
3. **Import Data**: Place existing `students.dat` in project folder
4. **Exit Properly**: Use option 7 to exit and save all data

## 📞 Testing Scenarios

### Scenario 1: Add Multiple Students
Add 5-10 students with different courses to test the system

### Scenario 2: Search Functionality
- Search by partial name (e.g., "Jo" to find John, Johnson)
- Search by complete course name
- Search by exact ID

### Scenario 3: Update Operations
Update each student field individually to ensure validation works

### Scenario 4: Statistics
After adding students, verify average GPA calculation

### Scenario 5: Delete with Confirmation
Test delete operation with "yes" and "no" responses

---

**You're all set! Happy testing! 🎓**
