package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;

// BEGIN
import org.springframework.context.annotation.Bean;

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    public Daytime getDaytime() {
        var dateTimeNow = LocalDateTime.now();
        var hourNow = dateTimeNow.getHour();
        if (hourNow >= 6 && hourNow < 22) {
            return new Day();
        } else {
            return new Night();
        }
    }
    // END
}
