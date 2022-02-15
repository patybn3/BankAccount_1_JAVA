public class Savings_PatriciaAntlitz extends BankAccount_PatriciaAntlitz{

    private double interestRate;

    public Savings_PatriciaAntlitz() {
        super();
    }

    public double getInterestRate(){
        return interestRate;
    }

    public void setInterestRate( double balance, double deposit, double interest){
        this.interestRate = (deposit + balance) * interest;
    }


}
