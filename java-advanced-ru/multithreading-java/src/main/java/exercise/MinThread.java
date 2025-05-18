package exercise;

// BEGIN
import java.util.Arrays;

public class MinThread extends Thread {

    private int[] numbers;
    private int min = 0;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public void run() {
        min = Arrays.stream(numbers).min().getAsInt();
    }

    public Integer getMin() {
        return min;
    }
}
// END
