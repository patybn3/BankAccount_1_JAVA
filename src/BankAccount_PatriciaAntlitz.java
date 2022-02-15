/* LAB BankAccount - OBJECTIVE PART 1:  Students will gain experience using methods and setting parameter/argument lists
OBJECTIVE PART 2: To demonstrate the concepts of Method Overloading and Inheritance in code

Name: Patricia Antlitz
Date: 11/19/2021
Class: CIS-160 Computer Science I - NECC Fall 2021
Professor: Kristen Sparrow

 * This is the first version of this program - Part 1
 * This program generates a bank account
 * User is asked to enter their personal information
 * User is asked to enter a deposit amount to open the account
 * Initial deposit cannot be less than $50.00
 * User is given options to deposit more money, withdraw, check balance, see their account's information,
 * see their bank account's number and leave the program
 * User is given the option to create a 4 digit PIN number
 * User can access the account's actions by entering the PIN number or the Account number
 * Account number is randomly generated
 * Routing number is a set number for this bank
 * User must enter their birthdate as MM/DD/YYYY
 * User must enter a valid 10 digit phone number
 * User must enter an email as email@email.com
 */
 /**
 * * @author Patricia N. Antlitz
 *  * @version 1.0
 */
 
//import all necessary functions
import java.util.Scanner;

/* 1.	Create a class called BankAccount_yourName save it as BankAccount_yourName
a.	It will be a generic simple type of BankAccount (not Checking, not Savings just BankAccount)
*/
public class BankAccount_PatriciaAntlitz {

    //the static objects will allow the usage in other methods besides main
    static Person_BankAccountPatriciaAntlitz acctOwner = new Person_BankAccountPatriciaAntlitz();
    static BankTransactions_PatriciaAntlitz makeTransaction = new BankTransactions_PatriciaAntlitz();
    static Savings_PatriciaAntlitz addInterest = new Savings_PatriciaAntlitz();
    static Checking_PatriciaAntlitz addFee = new Checking_PatriciaAntlitz();

    //Importing the scanner to allow the user to type the answers
    Scanner scnr = new Scanner(System.in);

    String ERROR_MESSAGE = "INVALID ENTRY. PLEASE TRY AGAIN:";
    //this one string will be used for the user's entry in the account's menu
    String userSelect;
    String selectType;
    String accStatus;
    //variable will hold the balance to be printed coming from makeTransaction class
    double totalTran;
    double getBalance;
    //will allow the program to reuse the same method to calculate the withdraw and the deposit
    boolean checkTrans = false;
    boolean acctType = false;

    public void bankIntro(){
        //bank title
        System.out.println("--------------------");
        System.out.println("ANTLITZ CREDIT UNION");
        System.out.println("--------------------\n");
    }

    public void acctType(){
        System.out.println("\nWhat Type of Account Would You Like To Create?");
        System.out.println("\n- Savings ------> Enter 's'");
        System.out.println("- Checking ------> Enter 'c'");

        selectType = scnr.nextLine();
        accStatus = selectType.toLowerCase();

        if(selectType.equals("s") || selectType.equals("savings")){
            acctType = true;
            System.out.println("Types pf Savings Accounts:\n");
            System.out.println("Silver  = Initial Deposit Minimum: $1,000.00 -> Interest Rate of 0.01%");
            System.out.println("Gold    = Must Have a Savings of: $50,000.00 or More -> Interest Rate of 0.02%");
            System.out.println("Diamond = Must Have a Savings of: $1,000,000.00 or More -> Interest Rate of 0.03%\n");
//            savOrCheck = true;
        }
        else if(selectType.equals("c") || selectType.equals("checking")){
            acctType = false;
//            savOrCheck =
        }
        else{
            System.out.println(ERROR_MESSAGE);
        }

        acctOwner.createAccount();
        //will print the acct' setting questions from the outer class
        makeTransaction.askForDeposit(acctType);
        //generated the account number
        makeTransaction.generateAcctNum();
        //gets user's menu entries
    }
    //this is the menu, user will select from this after creating an account and placing and initial deposit
    //future update will move this and the following method to a separated class
    public void userActions(){
        System.out.println("Deposit. - Enter 1.");
        System.out.println("Withdraw. - Enter 2.");
        System.out.println("Check Your Balance. - Enter 3");
        System.out.println("Print Account Information. - Enter 4");
        System.out.println("See Account Numbers. - Enter 5");
        System.out.println("End Transaction. - Enter 6");
    }

    public void printAction(String userName){

        System.out.println("\n" + userName + "'s Account Actions:\n");

    }

    public void printAction(String userName, String secName){

        System.out.println("\n" + userName + " & " + secName + "'s Account Actions:\n");
    }

    public void getUsers(){
        String userName = acctOwner.ownersName;
        String secName = acctOwner.secondaryHolder;

        if(acctOwner.jointAcct){
            printAction(userName, secName);
        }
        else {
            printAction(userName);
        }
        userActions();
    }

    //user selections from the menu
    public void holderAction(double balanceTran, double depositAmount, double getBalance){
        //will get the previous method passing the user's name from accOwner class' object
        getUsers();
        //loop tp allow the user to enter multiple times
        while(scnr.hasNextLine()){
            userSelect = scnr.nextLine();
             /*this variable stores the balance that for the deposit or withdraw that is calculated in
            the outer class called by the makeTransaction object
             */
            if(acctType) {

                totalTran = balanceTran + depositAmount + getBalance;
            }
            /*this variable hold the value for the deposit only, to be used as a balance when there is no
            other deposits or withdraw
             */
            else if(!acctType){
                getBalance = 0;
                totalTran = balanceTran + depositAmount;
                System.out.println();
            }
            //gets user's menu entry

            //switch statement was used to simplify
            switch (userSelect){
                case "1":
                    //when user select deposit
                    System.out.println("\nACCOUNT DEPOSIT:");
                    //will be used in an if statement int he makeTransaction object's class
                    checkTrans = true;
                    //calls the object's method
                    makeTransaction.acctTransactions(true, acctType, acctOwner.pinNum);
                    //will print the menu again
                    getUsers();
                    break;
                case "2":
                    //when the user selects withdraw
                    System.out.println("\nACCOUNT WITHDRAW:");
                    checkTrans = false;
                    makeTransaction.acctTransactions(false, acctType, acctOwner.pinNum);
                    getUsers();
                    break;
                case "3":
                    //if the user wants to see the balance
                    //if the balance calc from makeTransaction is 0 (no deposit or withdraw was made)
                    if(balanceTran == 0){
                        //it will assign the balance with the deposit amount only
                        totalTran = depositAmount;
                    }
                    //it will then print one value or the other
                    System.out.format("\nBALANCE: $%.2f", totalTran);
                    System.out.println();
                    System.out.format("Interest: %.2f", getBalance);
                    System.out.println();
                    getUsers();
                    break;
                case "4":
                    //this if statement performs the same conditional as the previous one, but calling a method instead
                    //prints the account information with the correct balance
                    if(balanceTran == 0){
                        acctOwner.printInfo(depositAmount);
                    }
                    else {
                        acctOwner.printInfo(totalTran);
                    }
                    System.out.format("Interest: %.2f", getBalance);
                    System.out.println();
//                    getAcctStatus();
                    //gets the method that prints the account's numbers
                    makeTransaction.printAcctNumber(acctOwner.pinNum);
                    getUsers();
                    break;
                case "5":
                    //gets the method that prints the account's numbers
                    makeTransaction.printAcctNumber(acctOwner.pinNum);
                    getUsers();
                    break;
                case "6":
                    //will print a goodbye message
                    System.out.println("\nThank You For Choosing Antlitz Credit Union");
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println(ERROR_MESSAGE);
                    break;
            }

            //if if statement makes sure the program quits when the user select 6 by breaking again
            if(userSelect.equals("6")){
                break;
            }
        }
    }

    public void accStatusOwner(double balanceTran, double depositAmount){
        balanceTran = makeTransaction.totalBalance;
        depositAmount = makeTransaction.depositMoney;

        addFee.setFee(balanceTran);
        getBalance = addFee.getFee();

        bankIntro();
        acctType();
        holderAction(balanceTran, depositAmount, getBalance);
    }

    public void accStatusOwner(double balanceTran, double depositAmount, double getBalance){
        bankIntro();
        acctType();
        holderAction(balanceTran, depositAmount, getBalance);
    }

    //main method
    // an richGuy vs. poorGuy methods were not created - I do not see/understand the need for it
    public static void main(String[] args){
        //general object
//     BankAccount_PatriciaAntlitz generateBank = new BankAccount_PatriciaAntlitz();
        Teller_PatriciaAntlitz generateAccounts = new Teller_PatriciaAntlitz();

        if(addFee.acctType) {
            generateAccounts.generateAccountsSavings();
        }
        else{
            generateAccounts.generateAccountChecking();
        }
        //these calls are printed in order
        //will print the greeting on this class
//        generateBank.printGreeting();
    }
}
// end.