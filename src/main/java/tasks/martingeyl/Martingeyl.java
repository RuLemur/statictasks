package tasks.martingeyl;

import exceptions.IncorrectDataException;
import org.apache.log4j.Logger;
import util.Randomizer;
import util.Task;

/**
 * Created by RuLemur on 17.10.2017 in 23:56.
 * StaticTasks
 * <p>
 * Мартинге́йл — система управления ставками в азартных играх.
 * Суть системы заключается в следующем:
 * <p>
 * Начинается игра с некоторой заранее выбранной минимальной ставки.
 * После каждого проигрыша игрок должен увеличивать ставку так, чтобы в случае выигрыша окупить все прошлые проигрыши в этой серии, с небольшим доходом.  При соблюдении последовательности прибыль игрока при выигрыше будет равна начальной ставке.
 * В случае выигрыша игрок должен вернуться обратно к минимальной ставке.
 */
public class Martingeyl implements Task{

    private int winChance;
    private double startDeposit;
    private int nextDepositRate;
    private double balance;
    private double currentDeposit;

    private static final Logger LOG = Logger.getLogger(Martingeyl.class);

    public Martingeyl() {
        this(1.0, 2, 10000, 47);
    }

    public Martingeyl(double startDeposit, int nextDepositRate, double balance, int winChance) {
        this.balance = balance;
        this.nextDepositRate = nextDepositRate;
        this.startDeposit = startDeposit;
        this.winChance = winChance;
        if (balance <= 0) {
            throw new IncorrectDataException("Недостаточно денег для игры");
        }
        if (nextDepositRate <= 0) {
            throw new IncorrectDataException("Неверный коэфицент");
        }
        if (startDeposit <= 0) {
            throw new IncorrectDataException("Неверная начальная ставка");
        }
        currentDeposit = startDeposit;
    }

    public void startSimulation(int countGames) {
        LOG.info("Начинаем симуляцию игры по стратегии Мартингейла!" +
                "\nВходные данные:" +
                "\nСтартовый баланс: " + balance + "$" +
                "\nПервоначальная ставка: " + startDeposit + "$" +
                "\nКоэфицент следующей ставки: " + nextDepositRate +
                "\nШанс выигрыша: " + winChance + "%");

        int round, wins = 0, looses = 0;
        double maxDeposit = startDeposit;
        for (round = 0; round < countGames; round++) {
            balance -= currentDeposit;
            if (balance <= 0) {
                LOG.info("Вы проиграли все свои деньги! ");
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
        if (round == countGames) {
            LOG.info(String.format("Вы сыграли все %s розыгрышей и теперь у вас %s$! Поздравляю!", countGames, balance));
        }
        double winRate = (100.0 / (wins + looses)) * wins;
        LOG.info(("ИТОГИ: " +
                "\nПОБЕД: " + wins +
                ",\nПОРАЖЕНИЙ: " + looses +
                ".\nПРОЦЕНТ ПОБЕД: " + String.format("%.2f", winRate) +
                "\nМАКСИМАЛЬНАЯ СТАВКА: " + maxDeposit + "$"));

    }
}
