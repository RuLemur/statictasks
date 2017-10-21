package tasks.martingeyl;

import org.apache.log4j.Logger;
import util.Randomizer;

import java.util.InputMismatchException;

public class Martingeyl {
    private int winChance;
    private double startDeposit;
    private int nextDepositRate;
    private double balance;
    private double currentDeposit;

    private static final Logger LOG = Logger.getLogger(Martingeyl.class);

    public Martingeyl() {
        this(1.0, 2, 0, 50);
    }

    public Martingeyl(double startDeposit, int nextDepositRate, double balance, int winChance) {
        this.balance = balance;
        this.nextDepositRate = nextDepositRate;
        this.startDeposit = startDeposit;
        this.winChance = winChance;
        if (balance <= 0) {
            throw new InputMismatchException("НЕДОСТАТОЧНО ДЕНЕГ ДЛЯ ИГРЫ");
        }
        if (nextDepositRate <= 0){
            throw new InputMismatchException("НЕВЕРНЫЙ КОЭФИЦЕНТ");
        }
        if (startDeposit <= 0){
            throw new InputMismatchException("НЕВЕРНАЯ НАЧАЛЬНАЯ СТАВКА");
        }
        currentDeposit = startDeposit;
    }

    public void start(int rounds) {
        LOG.info("НАЧИНАЕМ СИМУЛЯЦИЮ ИГР ПО СТРАТЕГИИ МАРТИНГЕЙЛА!" +
                "\nВХОДНЫЕ ДАННЫЕ:" +
                "\nСТАРТОВЫЙ БАЛАНС: " + balance + "$" +
                "\nНАЧАЛЬНАЯ СТАВКА: " + startDeposit + "$" +
                "\nКОЕФИЦЕНТ СЛЕДУЮЩЕЙ СТАВКИ: " + nextDepositRate +
                "\nШАНС ВЫИГРЫША: " + winChance + "%");

        int round, wins = 0, looses = 0;
        double maxDeposit = startDeposit;

        for (round = 0; round < rounds; round++) {
            balance -= currentDeposit;
            if (balance <= 0) {
                LOG.info("ВЫ ПРОИГРАЛИ И У ВАС НЕ ОСТАЛОСТЬ ДЕНЕГ! ");
                break;
            }
            if (Randomizer.getRandomBooelan(winChance)) {
                double win = currentDeposit * 2;
                balance += win;

                LOG.debug(String.format("№%s СТАВКА %s$ --- ВЫ ВЫИГРАЛИ! ВАШ БАЛАНС: %.2f$", round, currentDeposit, balance));
                currentDeposit = startDeposit;
                wins++;
            } else {
                LOG.debug(String.format("№%s СТАВКА %s$ --- ВЫ ПРОИГРАЛИ! ВАШ БАЛАНС: %.2f$", round, currentDeposit, balance));
                currentDeposit *= nextDepositRate;
                looses++;
            }
            if (maxDeposit < currentDeposit) {
                maxDeposit = currentDeposit;
            }
        }
        if (round == rounds) {
            LOG.info("ВАМ ХВАТИЛО ДЕНЕГ НА ВСЕ РОЗЫГРЫШИ, ПОЗДРАВЛЯЮ! ");
        }
        double winRate = (100.0 / (wins + looses)) * wins;
        LOG.info(("ИТОГИ: " +
                "\nПОБЕД: " + wins +
                ",\nПОРАЖЕНИЙ: " + looses +
                ".\nПРОЦЕНТ ПОБЕД: " + String.format("%.2f", winRate) +
                "\nМАКСИМАЛЬНАЯ СТАВКА: " + maxDeposit + "$"));

    }
}
