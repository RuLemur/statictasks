package tasks.montyhallproblem;

import javafx.scene.paint.RadialGradient;
import org.apache.log4j.Logger;
import util.Randomizer;

/**
 * Created by RuLemur on 21.10.2017 in 20:37.
 * StaticTasks
 */
public class MontyHallProblem {

    private static final Logger LOG = Logger.getLogger(MontyHallProblem.class);

    public MontyHallProblem() {

    }

    public void startSimulattion(int countGames, boolean isChooseChanged) {
        LOG.info(String.format("Начинам сималяцию Парадокса Монти Холла на %s игр! Меняется ли выбор игрока, после открытия первой двери: %B", countGames, isChooseChanged));
        int wins = 0, looses = 0;
//        int prizeDoor = -1;
//        int playerChoose = -1;
        for (int i = 0; i < countGames; i++) {


            int prizeDoor = Randomizer.getRandomInt(0, 2);
            int playerChoose = Randomizer.getRandomInt(0, 2);
            int openOtherDoor = -1;

            for (int j = 0; j < 3; j++) {                                   //выбор двери, которую откроет ведущий
                if (j != playerChoose && j != prizeDoor) {
                    openOtherDoor = j;
                }
            }
            LOG.debug(String.format("Игра №%s. Игрок выбрал дверь номер %s, ведущий открыл дверь номер %s.", i + 1, playerChoose + 1, openOtherDoor + 1));
            if (isChooseChanged) {
                for (int j = 0; j < 3; j++) {                               //меняем выбор на другую дверь
                    if (j != playerChoose && j != openOtherDoor) {
                        playerChoose = j;
                        break;
                    }
                }
                LOG.debug(String.format("Игрок изменил свой выбор на дверь номер %s", playerChoose + 1));
            } else {
                LOG.debug("Игрок не менял свой выбор");
            }

            if (playerChoose == prizeDoor) {
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
