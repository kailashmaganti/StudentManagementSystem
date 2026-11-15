package model;

import java.io.Serializable;

public class Student implements Serializable, Comparable<Student> {
    private static final long serialVersionUID = 1L;

    private final int rollNo;
    private String name;
    private double marks;

    public Student(int rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getGrade() {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B";
        if (marks >= 60) return "C";
        if (marks >= 50) return "D";
        return "F";
    }

    @Override
    public int compareTo(Student other) {
        int marksComparison = Double.compare(other.marks, this.marks);
        if (marksComparison != 0) {
            return marksComparison;
        }
        return Integer.compare(this.rollNo, other.rollNo);
    }

    @Override
    public String toString() {
        return String.format("Roll No: %-5d Name: %-20s Marks: %6.2f Grade: %s",
                rollNo, name, marks, getGrade());
    }
}
