package martingeyl;

import util.Randomizer;

public class Martingeyl {
    double startDeposit;
    int nextDepositRate;
    double balance;
    double curentDeposit;


    public Martingeyl() {
        //todo: loggin info
        this.startDeposit = 1;
        nextDepositRate = 2;
        balance = 100;
    }

    public Martingeyl(double startDeposit, int nextDepositRate, double balance) {
        this.balance = balance;
        this.nextDepositRate = nextDepositRate;
        this.startDeposit = startDeposit;
    }

    public void start(int rounds) {
        curentDeposit = startDeposit;
        int wins = 0, looses = 0;
        int i;
        for (i = 0; i < rounds; i++) {
            balance -= curentDeposit;
            if (balance <= 0) {
                System.out.println("ВЫ БАНКРОТ!");
                break;
            }
            System.out.println("RED");
//            System.out.println(String.format("Ставка %s с номиналом %s. Ваш баланс: %s", i + 1, curentDeposit, balance));
            if (Randomizer.getRandomBooelan(50)) {
                double win = curentDeposit * 2;
                balance += win;
                System.out.println(String.format("%s СТАВКА %s --- ВЫ ВЫИГРАЛИ! ВАШ БАЛАНС: %.2f", i, curentDeposit, balance));
                curentDeposit = startDeposit;
                wins++;
            } else {
                System.out.println(String.format("%s СТАВКА %s --- ВЫ ПРОИГРАЛИ! ВАШ БАЛАНС: %.2f", i, curentDeposit, balance));
                curentDeposit *= nextDepositRate;
                looses++;
            }
        }
        System.out.println("wins: " + wins + ", looses: " + looses + ". WINRATE: " + (100.0 / (Double.valueOf(i) + 1.0)) * Double.valueOf(wins));

    }
}
