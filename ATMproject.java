import java.util.*;
public class ATMproject {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        ATMsimulator atm=new ATMsimulator();
        System.out.println("****Welcome to mini ATM Simulator****");
        System.out.println("Please enter your ATM PIN number: ");
        //Check whether the entered pin is correct or not
        boolean pin_verified=false;
        //If the entered pin is correct then the menu is displayed
        for(int attempts = 0; attempts < 3; attempts++) {
    System.out.print("Enter your ATM PIN number: ");
    String entered_pin = sc.next();
     pin_verified=atm.PIN_verification(entered_pin);
    if(pin_verified) {
        break;
    } else {
        System.out.println("Incorrect PIN. Try again.");
    }
}
if(!pin_verified){
    System.out.println("Too many incorrect attempts. Exiting...");
    System.exit(0);
}
           System.out.println("Here are the actions to be taken on your account");
           int choice;
           do{
           System.out.println("1. Balance enquiry\n2. Deposit money\n3. Withdraw money\n4. Exit");
           System.out.println("Enter your choice:");
            choice=sc.nextInt();
           switch(choice){
            case 1:
                    atm.balance_enquiry();
                    break;
            case 2:  System.out.println("Enter amount to be credited:");
                     double amount=sc.nextDouble();
                     atm.deposit_money(amount);
                     break;
            case 3: System.out.println("Enter amount to be debited:");
                    double cash=sc.nextDouble();
                    atm.withdraw_money(cash);
                    break;
            case 4: System.out.println("Thanks for using!!");
                    break;
           }
        }while(choice!=4);
    
    //If it is incorrect the program is terminated
        sc.close();

    }
}
class ATMsimulator{
    double balance;
    //This function helps to verify the pin number
    public boolean PIN_verification(String pinnumber){
       String original_pin="1877";
      if(original_pin.equals(pinnumber))
        return true;
    else{
        System.out.println("PIN is incorrect");
        return false;
    }
 }
 //This function prints the available balance
    public void balance_enquiry(){
        if(balance>=0)
            System.out.printf("Available balance: %.2f\n ",balance);
    }
    //function deposits the money to the account
    public void deposit_money(double amount){
        if(amount>0){
            balance+=amount;
            System.out.println("**Successfully credited**");
        }
        else
            System.out.println("Please enter valid amount to deposit");
         
    }
    //This function withdraws the money 
    public void withdraw_money(double amount){
        if(amount>balance)
            System.out.println("Entered amount is greater than your balance!!");
        else if(amount<0)
            System.out.println("Inavlid amount");
        else{
            balance-=amount;
            System.out.println("**Successfully debited**");
        }

    }
}

