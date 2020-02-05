package portfolio.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.model.domain.Portfolio;
import portfolio.model.domain.Share;
import portfolio.model.service.ParserService;
import portfolio.model.service.PortfolioService;
import portfolio.model.service.TotalValueService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DemoController {
    private static final String FILE_NAME_STOCK = "src/main/resources/price/stocks.csv";
    private static final String FILE_NAME_SHARE = "src/main/resources/quantity/shares.csv";
    private ParserService parserService;
    private PortfolioService portfolioService;
    private TotalValueService totalValueService;

    public void menu() {
        Portfolio portfolio = Portfolio.builder().id(1L).name("first_portfolio").build();
        portfolioService.addPortfolio(portfolio);
        portfolioService.addPortfolio(Portfolio.builder().id(1L).name("second_portfolio").build());
        parserService.parseStock(FILE_NAME_STOCK);
        parserService.parseShare(FILE_NAME_SHARE);

        List<String> years = portfolioService.getAvailableYears(portfolio);
        System.out.println("\nChose portfolio from available year " + years + " (write '0' for exit)");
        Scanner scanner = new Scanner(System.in);
        String firstYear = scanner.next();
        if (firstYear.equals("0")) {
            System.exit(0);
        }
        String secondYear = scanner.next();
        if (years.contains(firstYear) && years.contains(secondYear)) {
            findDifference(firstYear, secondYear, portfolio);
        }
        System.out.println("Sorry but this years are not available!");
        menu();
    }

    private void findDifference(String firstYear, String secondYear, Portfolio portfolio) {
        printPortfolio(portfolio, firstYear);
        printPortfolio(portfolio, secondYear);

        BigDecimal difference = portfolioService.differenceTotalValuesByTwoYear(portfolio, firstYear, secondYear);
        printDifference(difference);
        menu();
    }

    private void printPortfolio(Portfolio portfolio, String year) {
        System.out.println("Portfolio " + portfolio.getName() + " of " + year + " year:");
        for (Share share : portfolio.getShares()) {
            if (share.getYear().equals(year))
                System.out.println(share);
        }
        BigDecimal totalValue = totalValueService.getTotalValueByYear(portfolio, year);
        System.out.println("Total Value of Portfolio for " + year + " is " + totalValue);
    }

    private void printDifference(BigDecimal difference) {
        String differenceOutput = difference.signum() > 0 ?
                "Difference: +" + difference + "(congratulations!)"
                : "Difference: " + difference + "(don`t worry, will be lucky next year!)";
        System.out.println(differenceOutput);
        System.out.println("-----------------------------------");
    }
}
