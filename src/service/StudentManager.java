package service;

import model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/** Handles all in-memory student record operations using Java collections. */
public class StudentManager {
    private final ArrayList<Student> students = new ArrayList<>();
    private final HashMap<Integer, Student> studentMap = new HashMap<>();

    public boolean addStudent(int rollNo, String name, double marks) {
        if (studentMap.containsKey(rollNo)) {
            return false;
        }
        Student student = new Student(rollNo, name, marks);
        students.add(student);
        studentMap.put(rollNo, student);
        return true;
    }

    public Student searchStudent(int rollNo) {
        return studentMap.get(rollNo);
    }

    public boolean updateMarks(int rollNo, double newMarks) {
        Student student = searchStudent(rollNo);
        if (student == null) {
            return false;
        }
        student.setMarks(newMarks);
        return true;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    /** TreeSet uses Student.compareTo to place the highest marks first. */
    public TreeSet<Student> getRankedStudents() {
        return new TreeSet<>(students);
    }

    /** Demonstrates Comparator with a separate alphabetical ordering. */
    public List<Student> getStudentsSortedByName() {
        ArrayList<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort(Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER)
                .thenComparingInt(Student::getRollNo));
        return sortedStudents;
    }

    public void replaceStudents(List<Student> loadedStudents) {
        students.clear();
        studentMap.clear();
        for (Student student : loadedStudents) {
            if (!studentMap.containsKey(student.getRollNo())) {
                students.add(student);
                studentMap.put(student.getRollNo(), student);
            }
        }
    }

    public int getStudentCount() {
        return students.size();
    }
}
