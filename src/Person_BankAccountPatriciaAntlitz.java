import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//future update will contain inheritance (not listed in part 1)
public class Person_BankAccountPatriciaAntlitz {

    BankTransactions_PatriciaAntlitz getBalance = new BankTransactions_PatriciaAntlitz();

    //calling scanner again, personal preference
    Scanner scnr = new Scanner(System.in);

    String ERROR_MESSAGE = "INVALID ENTRY. PLEASE TRY AGAIN:";
    /*all variables used in this program
    * some of these variable do not need to be global - this is a preference
    * separated by variable type
    * initialized Strings will print as null when not initialized
     */
    String ownersName = "";
    String secondaryHolder = "";
    String ownersAddress = "";
    String emailAddress = "";
    String ownersDOB = "";
    String tempOwnersDOB = "";
    String ownersPhoneNum = "";
    String lastChars = "";
    String turnLower;
    //will hold numbers
    String yesOrNo;
    String pinNum;
    //regex used
    String regex;
    String regexEmail;
    //will hold new number extracted from string
    int turnToNum;
    boolean isNumber;
    boolean jointAcct;

    // asks the user for their information and scan the entry

    public void createAccount(){
//        //bank title
//        System.out.println("--------------------");
//        System.out.println("ANTLITZ CREDIT UNION");
//        System.out.println("--------------------\n");
//        //create account
//        System.out.println("Create a New Account:");
//        //questions
//        System.out.println("Account Holder's Full Name:");
//        ownersName = scnr.nextLine();
//
//        System.out.println("Account Holder's Full Address:");
//        ownersAddress = scnr.nextLine();
//
//        //starting from this line all scans will be inside of other methods
//        System.out.println("Contact Email:");
//        //method that validates the email
//        validateEmail();
//
//        System.out.println("Date of Birth (MM/DD/YYYY): ");
//        //method that validates the date of birth
//        checkDOB();
//
//        System.out.println("Phone Number:");
//        //method that validates the phone number
//        checkPhoneNum();
//
//        System.out.println("Is This a Joint Account? (yes/no) ");
//        isJoint();

        System.out.println("Would You Like to Set a PIN Number? (yes/no)");
        //method that generates a pin number
        pinNum();
    }

    //this method will check if a string is composed by numbers
    public void isNumeric(String lastLetters){
        //lastLetters will get the variable that holds the string
        if(lastLetters == null){
            //if its null/empty/invalid the boolean will be set to false
            isNumber = false;
        }
        else{
            try {
                //if not, we will turn it to a number
                turnToNum = Integer.parseInt(lastLetters);
                isNumber = true;
            } catch (NumberFormatException e) {
                isNumber = false;
            }
        }
    }

    //generates a pin
    public void pinNum(){

//        String turnLower;

        while(scnr.hasNextLine()) {
            //we get a pin number as a string
            yesOrNo = scnr.nextLine();
            //turn all letters to lowercase
            turnLower = yesOrNo.toLowerCase();

            //check if the user entered yes or y
            if(turnLower.equals("yes") || turnLower.equals("y")){
                System.out.println("Create 4-Digits PIN Number:");

                //inner loop will get user's entry for the pin
                while (scnr.hasNextLine()){
                    pinNum = scnr.nextLine();

                    //gets the length of the entire string
                    lastChars = pinNum.substring(pinNum.length());
                    //turn it to number
                    isNumeric(lastChars);

                    //will only allow PINs of 4 numbers
                    if(pinNum.length() != 4){
                        System.out.println("PIN Number Must Be 4-Digits Only:");
                    }
                    else{
                        break;
                    }
                }
                System.out.println("PIN Number Created: " + pinNum);
                System.out.println("Save this Number in Your Records.\n");
                break;
            } //if user types no
            else if(turnLower.equals("no") || turnLower.equals("n")){
                System.out.println("An Account Number or PIN Number MIGHT be Required to Perform a Transaction.");
                System.out.println("Are you sure you do not want to create a PIN? (yes/no)");

                //loop will get answer to above question
                while(scnr.hasNextLine()){

                    yesOrNo = scnr.nextLine();
                    turnLower = yesOrNo.toLowerCase();

                    //this will allow the user another chance to create a pin
                    if(turnLower.equals("no") || turnLower.equals("n")){
                        System.out.println("Create 4-Digits PIN Number:");
                        pinNum = scnr.nextLine();
                        System.out.println("PIN Number Created: " + pinNum);
                        System.out.println("Save this Number in Your Records.\n");
                        break;
                    } //loop breaks if the user selects yes
                    if(turnLower.equals("yes") || turnLower.equals("y")){
                        break;
                    }
                }
                //if statement breaks after finalizing
                break;
            }
            else{
                System.out.println(ERROR_MESSAGE);
            }
        }
    }

    //this method validates the date of birth
    public boolean checkDOB(){

        //loop will allow the user to enter the date again in case of an error
        while(scnr.hasNextLine()){
            //will get the date from java.util in the following format
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date getDate;

            //scans user's entry as a string
            ownersDOB = scnr.nextLine();

            //date validation
            try{
                //parsing to get each character / match
                getDate = df.parse(ownersDOB);
            }
            catch(ParseException e){
                //will throw error, if the user enters letters or any thin that is not in the format desired
                System.out.println(ERROR_MESSAGE);
                //calls the method again so the previous calls run again
                checkDOB();
                return false;
            }

            //if the entry does not match the desired format
            if(!df.format(getDate).equals(ownersDOB)){
                //error, user is prompted to enter again
                System.out.println(ERROR_MESSAGE);
            }
            else{ //if it does
                //turn the last 4 characters into a number by getting the last characters
                lastChars = ownersDOB.substring(ownersDOB.length()-4);
                //and passing to method isNumeric()
                isNumeric(lastChars);

                //checks if the variable inside the method is equal to the following range of numbers as YYYY
                if(turnToNum > 1916 && turnToNum < 2005) {
                    break;
                }
                else{
                    //if not, throw error, user enters again
                    System.out.println("ERROR. ENTER A VALID DATE. ACCOUNT HOLDER MUST BE BORN AFTER 1916 OR BEFORE 2005: ");
                }
            }
        }
        //if everything runs, the program will end this method and continue
        return true;
    }

    //validates the number of digits in the phone number
    public void checkPhoneNum(){
        //checks type of entry
        regex = "([0-9]*$)";

        while(scnr.hasNextLine()){
            //will get the number as a string in case user enters letters, this will allow the program to
            //print an error instead of simply quitting
            ownersPhoneNum = scnr.nextLine();

            //will check how many character are in the string, and if the string is composed by numbers only
            //passes the regex 0-9
            if(ownersPhoneNum.length() == 10 && ownersPhoneNum.matches(regex)) {
                //if so end this method
                break;
            }
            else{
                //if not, print error, user is to enter again
                System.out.println("ERROR. ENTER A 10 DIGIT PHONE NUMBER FORMAT: ");
            }
        }
    }

    //this method will validate the email the user enters
    public void validateEmail(){

        regexEmail = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        //allows the user to enter it multiple times in case entry does not match the regex
        while(scnr.hasNextLine()){
            //gets entry as a string
            emailAddress = scnr.nextLine();
            //will get regex pattern
            Pattern pattern = Pattern.compile(regexEmail);
            //will match regex to the string
            Matcher matcher = pattern.matcher(emailAddress);

            //if it does not match user gets an error
            if(!matcher.matches()){
                System.out.println("ERROR. PLEASE ENTER THE FOLLOWING FORMAT: email@email.com");
            }
            else{
                break;
            }
        }
    }

    public void isJoint(){
        jointAcct = false;
        tempOwnersDOB = ownersDOB;

        while(scnr.hasNextLine()){
            yesOrNo = scnr.nextLine();
            turnLower = yesOrNo.toLowerCase();

            //check if the user entered yes or y
            if(turnLower.equals("yes") || turnLower.equals("y")) {
                jointAcct = true;
                System.out.println("Please Enter the Secondary Account Holder's Name:");
                secondaryHolder = scnr.nextLine();

                System.out.println("Date of Birth (MM/DD/YYYY):");
                checkDOB();

                break;
            }
            else if(turnLower.equals("no") || turnLower.equals("n")){
                jointAcct = false;
                break;
            }
            else{
                System.out.println(ERROR_MESSAGE);
            }

        }

    }

    public void printInfo(double deposit){

        //will print the information entered by the user
        System.out.println("\nAccount Information:");
        System.out.println("------------------------------------------");

        System.out.println("Main Account Holder\n");
        System.out.print("Full Name: ");
        System.out.println(ownersName);

        System.out.print("DOB: ");
        System.out.println(tempOwnersDOB);
        System.out.print("Address: ");
        System.out.println(ownersAddress);
        System.out.print("Phone Number: 1+");
        System.out.println(ownersPhoneNum);
        System.out.print("Email: ");
        System.out.println(emailAddress);

        if(jointAcct == true) {
            System.out.println("------------------------------------------");
            System.out.println("Secondary Account Holder\n");
            System.out.print("Full Name: ");
            System.out.println(secondaryHolder);
            System.out.print("DOB: ");
            System.out.println(ownersDOB);
            System.out.println("------------------------------------------");
        }
    }
}
// end.
