package ru.netology.web;


        import com.github.javafaker.Faker;

        import java.time.LocalDate;
        import java.time.format.DateTimeFormatter;
        import java.util.Locale;
        import java.util.Random;

public class DataGenerator {

    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static User registrationUser() {
            Faker faker = new Faker(new Locale("ru"));
            Random random = new Random();
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.uuuu");
            int plusDaysToLocal = random.nextInt(10) + 3;
            User value = new User(faker.address().city(),
                    LocalDate.now().plusDays(plusDaysToLocal).format(formatDate),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());
            return value;
        }
    }

}
