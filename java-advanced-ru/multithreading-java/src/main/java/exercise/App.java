package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {

        var maxThread = new MaxThread(numbers);
        var minThread = new MinThread(numbers);

        maxThread.start();
        LOGGER.log(Level.INFO, "Thread " + maxThread.getName() + " started");

        minThread.start();
        LOGGER.log(Level.INFO, "Thread " + minThread.getName() + " started");

        try {
            maxThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, "Thread " + maxThread.getName() + " finished");

        try {
            minThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, "Thread " + minThread.getName() + " finished");

        return Map.of("min", minThread.getMin(), "max", maxThread.getMax());
    }
    // END
}
