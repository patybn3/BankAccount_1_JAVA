public class Checking_PatriciaAntlitz extends BankAccount_PatriciaAntlitz{

    private double negativeFee;

    public double getFee(){
        return negativeFee;
    }

    public void setFee(double balance){
        this.negativeFee = balance - 35.00;
    }
}
