import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Student{
    private String name;
    private int rollnumber;
    private String email;
    private int age;
    public Student(String name,int rollnumber,String email,int age){
        this.name=name;
        this.rollnumber=rollnumber;
        this.email=email;
        this.age=age;
    }
    //getters and setters
    public int getRollNumber(){
        return rollnumber;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public String toFileFormat(){
        return rollnumber+","+name+","+email+","+age;
    }
    public void display(){
        System.out.println("!...Student Details...!");
        System.out.println("Student Name: "+name);
        System.out.println("Student Roll Number: "+rollnumber);
        System.out.println("Student Email: "+email);
        System.out.println("Student Age: "+age); 
    } 
    public static Student fromFileFormat(String line){
        String[] parts=line.split(",");
        int rollnumber=Integer.parseInt(parts[0]);
        String name=parts[1];
        String email=parts[2];
        int age=Integer.parseInt(parts[3]);
        return new Student(name,rollnumber,email,age);
    }
    public static class StudentManagementSystem{
        private ArrayList<Student>students=new ArrayList<>();
        private String getValidStringInput(Scanner sc,String prompt){
            String input;
            while(true){
                System.out.println(prompt);
                input=sc.nextLine().trim();
                if(!input.isEmpty()){
                    return input;
                }
                System.out.println("This field can't be empty,so try again.");
            }
        }
        private int getValidIntInput(Scanner sc,String prompt){
            while(true){
                System.out.println(prompt);
                String input=sc.nextLine().trim();
                try{
                    return Integer.parseInt(input);
                }
                catch(NumberFormatException e){
                    System.out.println("Invalid input.Please enter correctly.");
                }
            }
        }
        private final String FILE_PATH="students.txt";
        public void loadFromFile(){
            try(BufferedReader br =new BufferedReader(new FileReader(FILE_PATH))){
                String line;
                while((line=br.readLine())!=null){
                    students.add(Student.fromFileFormat(line));
                }
                System.out.println("Student Data loaded Successfully.");
            }
            catch(IOException e){
                System.out.println("No data Found.");
            }
        }
        public void saveToFile(){
            try(BufferedWriter bw =new BufferedWriter(new FileWriter(FILE_PATH))){
                String line;
                for(Student student:students){
                    bw.write(student.toFileFormat());
                    bw.newLine();
                }
                System.out.println("Student Data saved Successfully.");
            }
            catch(IOException e){
                System.out.println("Error in saving student data.");
            }
        }
        public void addStudent(Scanner sc){
            String name=getValidStringInput(sc,"Enter name: ");
            int rollnumber=getValidIntInput(sc,"Enter rollnumber: ");
            String email=getValidStringInput(sc,"Enter email: ");
            int age=getValidIntInput(sc,"Enter age: ");
            students.add(new Student(name,rollnumber,email,age));
            System.out.println("Student Added Successfully..!");
        }
        public void editStudent(int rollnumber,Scanner sc){
            for(Student student:students){
                if(student.getRollNumber()==rollnumber){
                    System.out.println("Edit the student details: ");
                    student.display();
                    System.out.println("Enter new Name: ");
                    String newName=sc.nextLine();
                    sc.nextLine();
                    if(!newName.isEmpty()){
                        student.setName(newName);
                    }
                    System.out.println("Enter new Email: ");
                    String newEmail=sc.nextLine();
                    if(!newEmail.isEmpty()){
                        student.setEmail(newEmail);
                    }
                    System.out.println("Enter new Age: ");
                    String newAge=sc.nextLine();
                    if(!newName.isEmpty()){
                        try{ 
                        student.setAge(Integer.parseInt(newAge));
                        }
                        catch(NumberFormatException e){
                            System.out.println("Invalid Age.Please enter valid input.");
                        }
                    }
                    System.out.println("Student Details are Updated Successfully...");
                    return;
                }
            }
            System.out.println("Student with this RollNumber: "+rollnumber+ "not found.");
        }
        public void searchStudent(int rollnumber){
            for(Student student:students){
                if(student.getRollNumber()==rollnumber){
                    System.out.println("Student Found: ");
                    student.display();
                    return;
                }
            }
            System.out.println("Student with this RollNumber: "+rollnumber+ "not found.");
        }
        public void displayAllStudents(){
            if(students.isEmpty()){
                System.out.println("No students in the List.");
                return;
            }
            System.out.println("All Students: ");
            for(Student student:students){
                student.display();
                System.out.println();
            }
        }
        public void menu(){
            Scanner sc =new Scanner(System.in);
            loadFromFile();
            while(true){
                System.out.println("\n!--Student Management System--!");
                System.out.println("1. Add Student");
                System.out.println("2. Edit Student Details");
                System.out.println("3. Search Student");
                System.out.println("4. Display all Students");
                System.out.println("5. Save & Close.");
                System.out.println("Enter your choice: ");
                int choice=getValidIntInput(sc,"");
                sc.nextLine();//new line
                switch(choice){
                    case 1:
                        addStudent(sc);
                        break;
                    case 2:
                        int rollToEdit=getValidIntInput(sc,"Enter Student Rollnumber to Edit: ");
                        editStudent(rollToEdit,sc);
                        break;
                    case 3:
                        int rollToSearch=getValidIntInput(sc,"Enter Student Roll number to Search: ");
                        searchStudent(rollToSearch);
                        break;
                    case 4:
                        displayAllStudents();
                        break;
                    case 5:
                        saveToFile();
                        System.out.println("Closing Guys...!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice.Try Again.");
                }
            }
        }
    }
    public static void main(String args[]){
        StudentManagementSystem sms=new StudentManagementSystem();
        sms.menu();
    }
}