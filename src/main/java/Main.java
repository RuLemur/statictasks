import tasks.martingeyl.Martingeyl;
import org.apache.log4j.PropertyConfigurator;
import tasks.montyhallproblem.MontyHallProblem;

/**
 * Created by RuLemur on 17.10.2017 in 23:46.
 * StaticTasks
 *
 * Главный класс обработки парадоксов и тестовых задач
 */
public class Main {

    public Main() {

    }

    public static void main(String[] args) {
        PropertyConfigurator.configure("./src/main/resources/config/log4j.properties");
//        Martingeyl martingeyl = new Martingeyl();
//        martingeyl.start(10000);
        MontyHallProblem montyHallProblem = new MontyHallProblem();
        montyHallProblem.startSimulattion(1000,false);
    }
}
