import java.util.Scanner;
public class Main1{
    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the number of subjects: ");
        int subjects=sc.nextInt();
        float[] marks=new float[subjects];
        float totalmarks=0;
        for(int i=0;i<subjects;i++){
            System.out.println("Enter marks obtained in subject "+(i+1)+"(out of 100): ");
            float score=sc.nextFloat();
            if(score>=0&&score<=100){
                marks[i]=score;
                totalmarks+=score;
            }
            else{
                System.out.println("Invalid marks! Please enter marks b/w 0 to 100");
                return;
            }
        }
        System.out.println("Total Marks: "+totalmarks);
        float avgper=totalmarks/subjects;
        System.out.println("Average Percentage: "+avgper);
        if(avgper>=90){
            System.out.println("Grade A");
        }
        else if(avgper<90 || avgper>=80){
            System.out.println("Grade B");
        }
        else if(avgper<80 || avgper>=60){
            System.out.println("Grade C");
        }
        else if(avgper<60 || avgper>=45){
            System.out.println("Grade D");
        }
        else{
            System.out.println("Grade E");
        }
    }
}