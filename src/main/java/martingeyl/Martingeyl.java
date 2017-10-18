package martingeyl;

public class Martingeyl {
    double startDeposit;
    int nextDepositRate;
    int balance;


    public Martingeyl(){
        //todo: loggin info
        this.startDeposit = 0.1;
        nextDepositRate = 2;
        balance = 100000;
    }

    public Martingeyl(double startDeposit, int nextDepositRate, int balance){
        this.balance =balance;
        this.nextDepositRate = nextDepositRate;
        this.startDeposit = startDeposit;
    }

    public void start(){

    }
}
