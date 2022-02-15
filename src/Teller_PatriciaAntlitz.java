public class Teller_PatriciaAntlitz {
    static BankTransactions_PatriciaAntlitz makeTransaction = new BankTransactions_PatriciaAntlitz();
    static Savings_PatriciaAntlitz addInterest = new Savings_PatriciaAntlitz();
    static Checking_PatriciaAntlitz addFee = new Checking_PatriciaAntlitz();

    double depositAmount;
    double getBalance;
    double balanceTran;
    double setInterest;

    public void generateAccountsSavings(){
        balanceTran = makeTransaction.totalBalance;
        depositAmount = makeTransaction.depositMoney;

        BankAccount_PatriciaAntlitz richGuy = new BankAccount_PatriciaAntlitz();

        addInterest.setInterestRate(balanceTran, depositAmount, setInterest);
        getBalance = addInterest.getInterestRate();

        richGuy.accStatusOwner(balanceTran, depositAmount, getBalance);

    }

    public void generateAccountChecking(){
        balanceTran = makeTransaction.totalBalance;
        depositAmount = makeTransaction.depositMoney;

        BankAccount_PatriciaAntlitz poorGuy = new BankAccount_PatriciaAntlitz();

        addFee.setFee(balanceTran);
        getBalance = addFee.getFee();
        poorGuy.accStatusOwner(balanceTran, depositAmount);
    }
}
