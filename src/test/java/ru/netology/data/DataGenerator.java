package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    static Faker faker = new Faker();
    private final LocalDate actualData = LocalDate.now();
    private final DateTimeFormatter formatterYears = DateTimeFormatter.ofPattern("yy");
    private final DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");

    public Year shiftYear(int numberOfYears) {
        LocalDate newDate = actualData.plusYears(numberOfYears);
        return new Year(formatterYears.format(newDate));
    }

    public String wrongYear() {
        return LocalDate.now().plusYears(10).format(DateTimeFormatter.ofPattern("yy"));
    }

    public Month shiftMonth(int numberOfMonths) {
        LocalDate newDate = actualData.plusMonths(numberOfMonths);
        return new Month(formatterMonth.format(newDate));
    }

    public Month wrongMonth() {
        return new Month(Integer.toString(faker.number().numberBetween(13, 99)));
    }

    @Value
    public static class Year {
        private String year;
    }

    @Value
    public static class Month {
        private String month;
    }
}
