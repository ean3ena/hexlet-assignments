package exercise;

// BEGIN
import java.util.Arrays;

public class MaxThread extends Thread {

    private int[] numbers;
    private int max = 0;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    public void run() {
        max = Arrays.stream(numbers).max().getAsInt();
    }

    public Integer getMax() {
        return max;
    }
}
// END
