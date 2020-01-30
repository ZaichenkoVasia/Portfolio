package portfolio.view;

import portfolio.service.domain.Portfolio;
import portfolio.service.PortfolioService;
import portfolio.data.FileParser;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

public class Demo {
    private static final String FILE_NAME_PRICE = "src/main/resources/price/price.csv";
    private static final String FILE_NAME_QUANTITY = "src/main/resources/quantity/quantity.csv";
    private static FileParser fileParser = new FileParser(FILE_NAME_PRICE, FILE_NAME_QUANTITY);
    private static PortfolioService portfolioService = new PortfolioService(fileParser);

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        Set<String> years = portfolioService.getAvailableYears();
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
        Portfolio firstPortfolio = portfolioService.getPortfolio(firstYear);
        BigDecimal firstTotalValue = portfolioService.calculateTotalValue(firstPortfolio);
        portfolioService.print(firstPortfolio);
        showTotalValue(firstTotalValue, firstYear);

        Portfolio secondPortfolio =portfolioService.getPortfolio(secondYear);

        BigDecimal secondTotalValue = portfolioService.calculateTotalValue(secondPortfolio);
        portfolioService.print(secondPortfolio);
        showTotalValue(secondTotalValue, secondYear);

        BigDecimal difference = portfolioService.differenceTotalValuesByTwoYear(firstPortfolio, secondPortfolio);
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
