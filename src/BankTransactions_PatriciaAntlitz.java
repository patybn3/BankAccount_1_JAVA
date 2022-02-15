import java.util.Scanner;
//perform both deposits and withdraws
public class BankTransactions_PatriciaAntlitz {

    Scanner scnr = new Scanner(System.in);

    String newAcct;
    String acctStatus;
    double newTransaction;
    double totalBalance;
    double getBalance;
    double holdValue;
    static double depositMoney;
    static int randomNum;
    int acctOrPinNum;
    int routNumber;
    int max;
    int min;

    //generate a random number within a range that will be assigned as the account number
    public void generateAcctNum(){
        max = 30000000;
        min = 10000000;
        randomNum = (int)(Math.random() * (max - min)) +1;
        //set bank's routing number
        routNumber = 10045872;
    }

    //Initial deposit
    public void askForDeposit(boolean checkAcct){
        System.out.println("Initial Deposit Amount:");
//        System.out.println(richGuy.acctType);

        while(scnr.hasNextLine()){
            //gets the enter as a double
            depositMoney = scnr.nextDouble();

            //if less than $50 it will print an error

            if (checkAcct && depositMoney < 1000.00) {
                System.out.println("Deposit Must be of $1000.00 or More.");
                System.out.println("Deposit Amount:");
            }
            else if (!checkAcct && depositMoney < 50.00) {
                System.out.println("Deposit Must be of $50.00 or More.");
                System.out.println("Deposit Amount:");
            }
            else {
                break;
            }
        }

        //print the deposit, "creates" the account (printout)
        System.out.println("ACCOUNT CREATED");
        System.out.format("INITIAL DEPOSIT: $%.2f", depositMoney);
        System.out.println();
    }

    //new deposits ans withdraws
    public void acctTransactions(boolean checkTrans, boolean accType, String checkPin){
        Savings_PatriciaAntlitz addInterest = new Savings_PatriciaAntlitz();
        Checking_PatriciaAntlitz addFee = new Checking_PatriciaAntlitz();

        System.out.println("-----------------------------\n");
        //user must enter a pin number or account number for every transaction
        System.out.println("Enter Your Account Number OR Your PIN Number:");

        //gets the pin or account number
        acctOrPinNum = scnr.nextInt();
        //turn into into string to compare with the PIN string
        newAcct = Integer.toString(acctOrPinNum);

        //if the number entered by the user is equal to the random number generated or
        //if the new string is equal to the pin string
        if (acctOrPinNum == randomNum || newAcct.equals(checkPin)) {
            //if true (deposit)
           if(checkTrans){
               System.out.println("Amount to Deposit:");
               //ask for the deposit
               newTransaction = scnr.nextDouble();
               //in case it is the first deposit (after the initial deposit
               if(totalBalance == 0.0){
                   //this if statement will check if the deposit isn't 0, after initial deposit it won't be, so this will run
                   if(depositMoney != 0) {
                       totalBalance = newTransaction + depositMoney;
                       //it will then set the deposit to 0 to allow a correct calculation once the account is a 0 balance
                       //this will make the else run in case the account is 0
                       depositMoney = 0;
                   }
                   else {
                       //if the account is at 0 after withdraw/deposit, get user deposit and set it as balance
                       totalBalance = newTransaction;
                   }
                   //the total will be assigned to totalBalance to be used later
                   //this will always run once
                   getBalance = 0.0;
               }
               else {
                   //if totalBalance has a stored value (previous if) add it to the entered number
                   totalBalance = totalBalance + newTransaction;
               }
           }
           //if checkTrans is false, a withdraw will be performed
           else if(!checkTrans){
               System.out.println("Amount to Withdraw:");
               //ask for the deposit
               newTransaction = scnr.nextDouble();

               //first to run, if balance is 0, in case a withdraw is the first transaction

               if(totalBalance == 0.0){
                   totalBalance = depositMoney - newTransaction;
                }
               //if second and on, deduct
                else {
                    totalBalance = totalBalance - newTransaction;
                }
           }

           if(accType) {
               if(totalBalance >= 50000.00){
                   addInterest.setInterestRate(totalBalance, depositMoney, 0.0002);
                   acctStatus = "Gold Savings Member";
               }
               else if(totalBalance >= 1000000.00){
                   addInterest.setInterestRate(totalBalance, depositMoney, 0.0003);
                   acctStatus = "Diamond Savings Member";
               }
               else {
                   addInterest.setInterestRate(totalBalance, depositMoney, 0.0001);
                   acctStatus = "Silver Savings Member";
               }
               getBalance = addInterest.getInterestRate();
           }
           else if(!accType) {
               if(totalBalance < 0.00){
                   addFee.setFee(totalBalance);
                   getBalance = addFee.getFee();
                   totalBalance = getBalance;
                   System.out.println(totalBalance);
               }
               getBalance = 0.00;
               acctStatus = "Standard Checking";
           }

           System.out.format("Available Balance: $%.2f", totalBalance + getBalance);
           System.out.println(" -----> " + acctStatus);
//           System.out.format("Interested Added: %.2f", interestRate.setInterestRate(totalBalance));
//           System.out.println();
        }
        else{
            System.out.println("An Account or Pin Number is Required to Perform This Transaction.");
        }
    }

    //prints out the account, routing and pin number
    public void printAcctNumber(String checkPin){

        if(checkPin == null){
            checkPin = "Not Created.";
        }
        System.out.println("\nACCOUNT NUMBER = " + randomNum + "   |   ROUTING NUMBER = " + routNumber + "    |    PIN = " + checkPin);
    }
}
// end.
