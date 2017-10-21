package tasks.montyhallproblem;

import org.apache.log4j.Logger;

/**
 * Created by RuLemur on 21.10.2017 in 20:37.
 * StaticTasks
 */
public class MontyHallProblem {

    private static final Logger LOG = Logger.getLogger(MontyHallProblem.class);

    public MontyHallProblem() {

    }

    public void startSimulattion(int countGames) {
        LOG.info(String.format("НАЧИНАЕМ СИМУЛЯЦИЮ ПАРАДОКСА МОНТИ ХОЛЛА НА %s ИГР!", countGames));


    }

}
