package tasks.montyhallproblem;

import org.apache.log4j.Logger;
import util.Randomizer;
import util.Task;

/**
 * Created by RuLemur on 21.10.2017 in 20:37.
 * StaticTasks
 * <p>
 * Парадокс Монти Холла — одна из известных задач теории вероятностей, решение которой, на первый взгляд, противоречит здравому смыслу.
 * <p>
 * Представьте, что вы стали участником игры, в которой вам нужно выбрать одну из трёх дверей.
 * За одной из дверей находится автомобиль, за двумя другими дверями — козы.
 * Вы выбираете одну из дверей, например, номер 1, после этого ведущий, который знает, где находится автомобиль,
 * а где — козы, открывает одну из оставшихся дверей, например, номер 3, за которой находится коза.
 * После этого он спрашивает вас — не желаете ли вы изменить свой выбор и выбрать дверь номер 2?
 * Увеличатся ли ваши шансы выиграть автомобиль, если вы примете предложение ведущего и измените свой выбор?
 */
public class MontyHallProblem implements Task {

    private static final Logger LOG = Logger.getLogger(MontyHallProblem.class);
    private boolean isChooseChanged;

    public MontyHallProblem() {
        this(true);
    }

    public MontyHallProblem(boolean isChooseChanged) {
        this.isChooseChanged = isChooseChanged;
    }


    public void startSimulation(int countGames) {
        LOG.info(String.format("Начинам сималяцию Парадокса Монти Холла на %s игр! Меняется ли выбор игрока, после открытия первой двери: %B", countGames, isChooseChanged));
        int wins = 0, looses = 0;
        for (int i = 0; i < countGames; i++) {

            int prizeDoor = Randomizer.getRandomInt(0, 2);                  //Дверь, за которой лежит приз
            int playerChoose = Randomizer.getRandomInt(0, 2);               //ДВерь, которую изначально выбирает игрок
            int openOtherDoor = -1;                                         //Дверь, которую откроет ведущий

            for (int j = 0; j < 3; j++) {                                   //Ведущий выбирает дверь, за которой нет приза
                if (j != playerChoose && j != prizeDoor) {
                    openOtherDoor = j;
                }
            }

            LOG.debug(String.format("Игра №%s. Игрок выбрал дверь номер %s, ведущий открыл дверь номер %s.", i + 1, playerChoose + 1, openOtherDoor + 1));
            if (isChooseChanged) {                                          //Проверка того, будет ли игрок менять свой выбор
                for (int j = 0; j < 3; j++) {                               //Меняем выбор на другую дверь
                    if (j != playerChoose && j != openOtherDoor) {
                        playerChoose = j;
                        break;
                    }
                }
                LOG.debug(String.format("Игрок изменил свой выбор на дверь номер %s", playerChoose + 1));
            } else {
                LOG.debug("Игрок не менял свой выбор");
            }

            if (playerChoose == prizeDoor) {                                //Проверяем, выиграл игрок или нет
                wins++;
                LOG.debug("Вы выиграли автомобиль!");
            } else {
                LOG.debug("Вы получаете осла :(");
                looses++;
            }
            LOG.debug("ПРИЗ ЗА ДВЕРЬЮ: " + (prizeDoor + 1));
        }

        double winRate = (100.0 / (wins + looses)) * wins;
        LOG.info(("ИТОГИ: " +
                "\nПОБЕД: " + wins +
                ",\nПОРАЖЕНИЙ: " + looses +
                ".\nПРОЦЕНТ ПОБЕД: " + String.format("%.2f", winRate)));

    }

}
