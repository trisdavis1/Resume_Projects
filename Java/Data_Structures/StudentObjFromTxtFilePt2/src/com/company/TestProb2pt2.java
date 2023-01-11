package com.company;

import java.io.File;
import java.util.Scanner;

//This program allows for you to read student information from input txt files
//As the scanner reads each student record, an object is created for that student
//Objects calculate the percentage test score and generates a letter grade for each student and stores it in an integer
//Sorts the student objects based on their subsequent percent test scores (high - low)
/*
Creates an array Student[] for this Academic Class. As each student record is read in their object is created
and stored in said array Student[] for the Academic Class.

This program is an adaptation of "StudentObjectsFromTxtFilePt1", now built to add new students objects
and delete existing student objects

AddStudent.txt file contains additional students and their test records.
This program also adds and demonstrates a method to delete students from the academicClass array
*/

public class TestProb2pt2 {
    public static void main(String[] args) throws Exception{
        //import and create file reader for txt file

        //read and print contents of file
        File file = new File("/Users/tristandavis/Desktop/StudentsInput1.txt");
        Scanner input = new Scanner(file);
        Student[] academicClass = new Student[11];
        int i = 0;

        //while loop to read and load records from the txt file
        while ((input.hasNext())) {
            String stuID = input.next();
            String stuName = input.next();
            int sTest1 = input.nextInt();
            int sTest2 = input.nextInt();
            int sTest3 = input.nextInt();

            Student students = new Student(stuID, stuName, sTest1, sTest2, sTest3);
            academicClass[i] = students;
            i++;

        }
        //Listing objects from the Student[] including % score and letter grades
        System.out.println("Academic Class including student ID, test scores, and letter grade:\r\n");
        for(int j = 0; j <= 7; j++) {
            System.out.println(academicClass[j]);
        }

        //add new students from add student txt file
        System.out.println("---------------------------------------------");
        System.out.println("New Academic Class including newly added students: \r\n");
        AddStudent(academicClass);

        SortLarge(academicClass);

        //print new list after sort; highest to lowest
        System.out.println("---------------------------------------------");
        System.out.println("The Academic Class is now sorted by grade, highest to lowest:\r\n");
        for (i = 0; i < academicClass.length; i++) {
            System.out.println(academicClass[i]);
        }

        //listing students that have dropped the class
        System.out.println("---------------------------------------------");
        System.out.println("The following students have dropped the class:\r\n");
        DeleteStudent(academicClass,"45A3");
        DeleteStudent(academicClass,"42P4");
        System.out.println("---------------------------------------------");

        //print out a new list after dropped student records
        System.out.println("Academic Class after removing dropped students:\r\n ");
        for (int j = 0; j < academicClass.length; j++) {
            System.out.println(academicClass[j]);
         }

    }//end of driver class

    //Delete student method that deletes student based off matching Student ID
     public static Student[] DeleteStudent(Student[] academicClass, String StudentID){
        for(int i = 0; i < academicClass.length; i++){
            if(StudentID.equals(academicClass[i].getStudentID())) {
                System.out.println(academicClass[i]);
                academicClass[i] = null;
                break;
            }
        }
        for(int i = 0; i < academicClass.length - 1; i++){
            if(academicClass[i] == null){
                academicClass[i] = academicClass[i + 1];
                academicClass[i + 1] = null;
            }
        }
        return academicClass;
     }//end DeleteStudent

    //Add Student method to read in new student information, create its object, and store it to Student[]
    public static void AddStudent(Student[] students) throws Exception{
        File file = new File("/Users/tristandavis/Desktop/AddStudents.txt");
        Scanner input = new Scanner(file);
        int i = students.length - 3;

        while ((input.hasNext())) {
            String stuID = input.next();
            String stuName = input.next();
            int sTest1 = input.nextInt();
            int sTest2 = input.nextInt();
            int sTest3 = input.nextInt();

            Student newStudents = new Student(stuID, stuName, sTest1, sTest2, sTest3);
            students[i] = newStudents;
            i++;

        }
        //prints out added student records
        for(int j = 0; j < students.length; j++) {
            System.out.println(students[j]);
        }
    }//end of AddStudent

    //SortLarge method to sort records based off highest percent score to lowest
    public static void SortLarge(Student[] x){
        Student xsave;
        int isw = 1;
        while(isw == 1){
            isw = 0;
            for (int i = 0; i < x.length - 1; i++){
                switch (x[i].compareTo(x[i + 1])) {
                    case 1: // the objects are in the right order
                        break;
                    case -1: // the objects are in the wrong order
                        xsave = x[i];
                        x[i] = x[i + 1];
                        x[i + 1] = xsave;
                        isw = 1;
                        break;
                    default: // objects are equal so no change
                }
            }
        }
    } // end of SortLarge

}//end of TestProb2pt2
//create class Student and implements comparable based off Student
class Student implements Comparable<Student> {

    protected String StudentID;
    protected int ScoreTest1;
    protected int ScoreTest2;
    protected int ScoreTest3;
    protected String StudentName;
    protected int testPercent;
    protected String letterGrade;

    //Constructor for class Student
    public Student(String StudentID, String StudentName, int ScoreTest1, int ScoreTest2, int ScoreTest3){
        this.StudentID = StudentID;
        this.StudentName = StudentName;
        this.ScoreTest1 = ScoreTest1;
        this.ScoreTest2 = ScoreTest2;
        this.ScoreTest3 = ScoreTest3;
        percentTestScore();
        calcLetterGrade();
    }
    //calculate average test score
    public void percentTestScore(){
        testPercent = (int) ((ScoreTest1 + ScoreTest2 + ScoreTest3) / 3.0);
    }
    //calculate letter grade in class
    public void calcLetterGrade(){
        if (testPercent >= 90){

             letterGrade = "A";

        }
        else if(testPercent >= 80){

            letterGrade = "B";

        }
        else if(testPercent >= 70){

            letterGrade = "C";

        }
        else if(testPercent >= 60 ){

            letterGrade = "D";

        }
        else{
            letterGrade = "F";
        }
    }

    public String getStudentID(){
        return StudentID;
    }
    public void setStudentID(String StudentID){
        this.StudentID = StudentID;
    }

    public String getStudentName(){
        return StudentName;
    }

    public int getScoreTest1(){
        return ScoreTest1;
    }

    public int getScoreTest2(){
        return ScoreTest2;
    }

    public int getScoreTest3(){
        return ScoreTest3;
    }

    public void setTestPercent(int testPercent){
        this.testPercent = testPercent;
    }
    public int getTestPercent(){
        return testPercent;
    }

    public void setLetterGrade(String letterGrade){
        this.letterGrade = letterGrade;
    }

    public String getLetterGrade(){
        return letterGrade;
    }
    //override method to print out Students with specific format
    @Override
    public String toString() {
        String result;
        result = String.format("Student ID: %s\r\n Student Name: %s\r\n Test 1: %d\r\n Test 2: %d\r\n Test 3: %d\r\n" +
                        " Percent Grade: %d\r\n Letter Grade: %s\r\n" + "---------------------------" + "\r\n",
                StudentID, StudentName, ScoreTest1, ScoreTest2, ScoreTest3, testPercent, letterGrade);
        return result;
    } // end of toString
    //compareTo function to compare students based on their average test scores
     public int compareTo(Student o){
        if (this.testPercent > o.testPercent) {
            return 1;
        } else if (this.testPercent < o.testPercent) {
            return -1;
        } else {
            return 0;
        }
    } // end of compareTo

}//end of Student