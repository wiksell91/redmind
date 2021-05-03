
package redmind;

import java.util.Scanner;


public class Redmind {
    
    static boolean loop = true;
    
     protected static final int[] notesAmount = { 1000, 500, 100 };
  
     protected static int[] notesQuant = { 2, 3, 5 };
     
     protected int[] notesWithdraw = { 0, 0, 0 };
     
     protected static int totalCorpus;
    static {
        calcTotalCorpus();
    }

    public static void calcTotalCorpus() {
        for (int i = 0; i < notesAmount.length; i++) {
            totalCorpus = totalCorpus + notesAmount[i] * notesQuant[i];
        }
    }

    public Redmind(){
        
    }
     
    public synchronized void withdrawCash(int amount) {
        if (amount <= totalCorpus) {
            for (int i = 0; i < notesAmount.length; i++) {
                if (notesAmount[i] <= amount) {
                    int noteCount = amount / notesAmount[i];
                   
                    if (notesQuant[i] > 0) {
                        notesWithdraw[i] = noteCount >= notesQuant[i] ? notesQuant[i] : noteCount;
                        notesQuant[i] = noteCount >= notesQuant[i] ? 0 : notesQuant[i] - noteCount;
                        totalCorpus = totalCorpus - (notesWithdraw[i] * notesAmount[i]);
                        amount = amount - (notesWithdraw[i] * notesAmount[i]);
                    }
                }
            }
            displayNotes();
            displayLeftNotes();

        } else {
            System.out.println("Sorry, you don't have enough money on your bank account");
        }

    }
    
      private void displayNotes() {
        for (int i = 0; i < notesWithdraw.length; i++) {
            if (notesWithdraw[i] != 0) {
                System.out.println(notesAmount[i] + " * " + notesWithdraw[i] + " = " + (notesAmount[i] * notesWithdraw[i]));
            }
        }
    }
      
    private void displayLeftNotes() {
        for (int i = 0; i < notesAmount.length; i++) {
            System.out.println("Notes of " + notesAmount[i] + " left are " + notesQuant[i]);
        }

    }
    public static void main(String[] args) {
       // Auto-withdraw
     /*   new Redmind().withdrawCash(1500);
        new Redmind().withdrawCash(700);
        new Redmind().withdrawCash(400);
        new Redmind().withdrawCash(1100);
        new Redmind().withdrawCash(1000);
        new Redmind().withdrawCash(700);
        new Redmind().withdrawCash(300);*/
        
        
        // Menu 
        Scanner sc = new Scanner(System.in);
        System.out.println("-------- Welcome to Bank of Redmind --------");
        System.out.println("1: Withdraw");
        System.out.println("2: Exit");
        int operation = sc.nextInt();
        while(loop){
       
            if (operation == 1) {
                System.out.println("Enter amount for withdraw");
                int amountWithdraw = sc.nextInt();     
                new Redmind().withdrawCash(amountWithdraw);
                continue;
            }
            else if(operation == 2){
                loop = false;
                break;
        }
            
            }
        
        
    }
    
}
