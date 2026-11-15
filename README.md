# College Student Record Management System

A simple menu-driven Core Java application for managing student records. It is designed as a two-member course-end project and runs entirely in the terminal.

## Features

1. Add a student
2. Display all students
3. Search by roll number
4. Update marks
5. Display rankings
6. Save records
7. Load saved records
8. Exit

## Java concepts used

| Concept | Where it is used |
| --- | --- |
| `ArrayList` | Stores all student records in entry order. |
| `HashMap` | Finds a student quickly by roll number. |
| `TreeSet` | Produces a marks-based ranking without changing the original list. |
| `Comparable` | Defines the default ranking: marks descending, then roll number ascending. |
| `Comparator` | Provides alphabetical name sorting in `StudentManager`. |
| File I/O and serialization | Saves and restores the student list in `student_records.dat`. |
| Exception handling | Validates input and handles save/load errors. |

## Project structure

```text
StudentManagementSystem/
├── src/
│   ├── Main.java
│   ├── model/Student.java
│   ├── service/StudentManager.java
│   └── util/FileHandler.java
├── README.md
└── REPORT_SCREENSHOTS_CHECKLIST.md
```

## How to run

Open a terminal in this project folder.

```bash
mkdir -p out
javac -d out src/model/Student.java src/service/StudentManager.java src/util/FileHandler.java src/Main.java
java -cp out Main
```

The first time the program runs, no data file exists. After choosing **Save records**, it creates `student_records.dat` in this project folder. The program loads this file automatically whenever it starts.

## Sample data for testing

| Roll no. | Name | Marks |
| ---: | --- | ---: |
| 101 | Rahul | 85 |
| 102 | Priya | 92 |
| 103 | Arjun | 76 |
| 104 | Neha | 95 |

## Suggested two-member work split

| Member | Main responsibilities |
| --- | --- |
| Member 1 | `Student`, adding, displaying, searching, input validation |
| Member 2 | Rankings, file saving/loading, testing, README and report |

Both members should understand the full project for the viva.
