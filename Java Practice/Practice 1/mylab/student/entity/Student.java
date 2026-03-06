package mylab.student.entity;

import mylab.student.exception.InvalidGradeException;

public class Student {
    private String studentId;
    private String name;
    private String major;
    private int grade;

    public Student(String studentId, String name, String major, int grade) throws InvalidGradeException {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        setGrade(grade);
    }

    // getters
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGrade() { return grade; }

    // setters
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setName(String name) { this.name = name; }
    public void setMajor(String major) { this.major = major; }

    public void setGrade(int grade) throws InvalidGradeException {
        if (grade < 1 || grade > 4) {
            throw new InvalidGradeException("학년은 1~4 사이여야 합니다.");
        }
        this.grade = grade;
    }

    @Override
    public String toString() {
        return name + " / " + major + " / " + grade + "학년";
    }
}