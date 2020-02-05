package portfolio;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import portfolio.model.service.impl.PortfolioServiceImpl;
import portfolio.model.domain.Portfolio;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
public class Application {

    private static final String FILE_NAME_PRICE = "src/main/resources/price/stocks.csv";
    private static final String FILE_NAME_QUANTITY = "src/main/resources/quantity/shares.csv";
    private static FileParserOld fileParserOld = new FileParserOld(FILE_NAME_PRICE, FILE_NAME_QUANTITY);
    private static PortfolioServiceImpl portfolioServiceImpl = new PortfolioServiceImpl(fileParserOld);
    private static long id =1;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            menu();
        };
    }

    private static void menu() {
        Set<String> years = portfolioServiceImpl.getAvailableYears();
        System.out.println("\nChose portfolio from available year" + years + " (write '0' for exit)");
        Scanner scanner = new Scanner(System.in);
        String firstYear = scanner.next();
        if (firstYear.equals("0")) {
            System.exit(0);
        }
        String secondYear = scanner.next();
        if (years.contains(firstYear) && years.contains(secondYear)) {
            findDifference(firstYear, secondYear);
        }
        System.out.println("Sorry but this years are not available!");
        menu();
    }

    private static void findDifference(String firstYear, String secondYear) {
        Portfolio firstPortfolio = portfolioServiceImpl.getPortfolio(firstYear, id);
        BigDecimal firstTotalValue = portfolioServiceImpl.calculateTotalValue(firstPortfolio);
        portfolioServiceImpl.print(firstPortfolio);
        showTotalValue(firstTotalValue, firstYear);

        Portfolio secondPortfolio = portfolioServiceImpl.getPortfolio(secondYear, id);

        BigDecimal secondTotalValue = portfolioServiceImpl.calculateTotalValue(secondPortfolio);
        portfolioServiceImpl.print(secondPortfolio);
        showTotalValue(secondTotalValue, secondYear);

        BigDecimal difference = portfolioServiceImpl.differenceTotalValuesByTwoYear(firstPortfolio, secondPortfolio);
        printDifference(difference);
        menu();
    }

    private static void printDifference(BigDecimal difference) {
        String differenceOutput = difference.signum() > 0 ?
                "Difference: +" + difference + "(congratulations!)"
                : "Difference: " + difference + "(don`t worry, will be lucky next year!)";
        System.out.println(differenceOutput);
        System.out.println("-----------------------------------");
    }

    private static void showTotalValue(BigDecimal totalValue, String year) {
        System.out.println("Total Value of Portfolio for " + year + " is " + totalValue);
    }

}