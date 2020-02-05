package portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import portfolio.controller.DemoController;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private DemoController demoController;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        demoController.run();
    }
}
