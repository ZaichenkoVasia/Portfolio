package portfolio.view;

import portfolio.domain.Portfolio;
import portfolio.service.PortfolioService;
import portfolio.service.util.FileParser;
import portfolio.service.util.PortfolioFactory;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

public class Demo {
    private static PortfolioService portfolioService = new PortfolioService();

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        Set<String> years = FileParser.findAvailableYears();
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
        Portfolio firstPortfolio =
                PortfolioFactory.getPortfolio(firstYear);

        BigDecimal firstTotalValue = portfolioService.calculateTotalValue(firstPortfolio);
        portfolioService.print(firstPortfolio);
        showTotalValue(firstTotalValue, firstYear);

        Portfolio secondPortfolio =
                PortfolioFactory.getPortfolio(secondYear);

        BigDecimal secondTotalValue = portfolioService.calculateTotalValue(secondPortfolio);
        portfolioService.print(secondPortfolio);
        showTotalValue(secondTotalValue, secondYear);

        BigDecimal difference =
                portfolioService.differenceTotalValuesByTwoYear(firstPortfolio, secondPortfolio);
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
