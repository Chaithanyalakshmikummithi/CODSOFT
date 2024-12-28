import java.util.Random;
import java.util.Scanner;
public class Main{
    public static void main(String args[]){
        int min=1;
        int max=100;
        int maxattempts=8;
        int score=100;
        Random number = new Random();
        int num=number.nextInt((max-min)+1)+min;
        Scanner input =new Scanner(System.in);
        System.out.println("Enter the guess number:"+min+" and "+max+".You have "+maxattempts+" attempts.");
        System.out.println("Your intial score is: "+ score +".Each attempt it reduces 10 points");
        int guess=0;
        int limit=0;
        while(limit<maxattempts){
            limit++;
            System.out.println("Attempt "+limit+": Enter a number: ");
            guess=input.nextInt();
            if(guess==num){
                System.out.println("Congratulations your guess is correct: "+num);
            }
            else if(guess<num){
                System.out.println("It's a less number.");
            }
            else if(limit==maxattempts){
                System.out.println("Sorry,you have done your limits.");
                maxattempts++;
            }
            else{
                System.out.println("It's a big number.");
            } 
            score-=10;
            System.out.println("Your final score is: "+score);
        }
        
    }
}