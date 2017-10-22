import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import tasks.martingeyl.Martingeyl;
import tasks.montyhallproblem.MontyHallProblem;
import util.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RuLemur on 17.10.2017 in 23:46.
 * StaticTasks
 * <p>
 * Главный класс обработки парадоксов и тестовых задач
 */
public class Main {
    private static Map<String, Class> tasks = new HashMap<String, Class>();
    private static Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        init();
        PropertyConfigurator.configure("./src/main/resources/config/log4j.properties");
        LOG.info("Добро пожаловать в проверяку задач");
        LOG.info(("Список доступных задач:" +
                "\n \"montyhall\" - Парадокс Монти Холла" +
                "\n \"martingeyl\" - Стратегия Мартингейла"));
        LOG.info("Для помощий используйте -help");

        String help = System.getProperty("help");
        if (help != null) {
            LOG.info("HELP THERE");
        }



        try {
            Task task = (Task) tasks.get(taskName).newInstance();
            task.startSimulation(123);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private static void init() {
        tasks.put("montyhall", MontyHallProblem.class);
        tasks.put("martingeyl", Martingeyl.class);
    }

}
