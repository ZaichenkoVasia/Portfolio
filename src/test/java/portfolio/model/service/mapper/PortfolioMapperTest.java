package portfolio.model.service.mapper;

import org.junit.Test;
import portfolio.model.domain.Portfolio;
import portfolio.model.entity.PortfolioEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class PortfolioMapperTest {
    private final PortfolioMapper mapper = new PortfolioMapper();
    private static final Portfolio PORTFOLIO = getPortfolio();
    private static final PortfolioEntity PORTFOLIO_ENTITY = getPortfolioEntity();

    @Test
    public void shouldMapPortfolioEntityToPortfolio() {
        Portfolio actual = mapper.portfolioEntityToPortfolio(PORTFOLIO_ENTITY);

        assertThat(actual.getId(), is(PORTFOLIO.getId()));
        assertThat(actual.getName(), is(PORTFOLIO.getName()));
    }

    @Test
    public void shouldMapPortfolioToPortfolioEntity() {
        PortfolioEntity actual = mapper.portfolioToPortfolioEntity(PORTFOLIO);

        assertThat(actual.getId(), is(PORTFOLIO_ENTITY.getId()));
        assertThat(actual.getName(), is(PORTFOLIO_ENTITY.getName()));
    }

    @Test
    public void mapPortfolioToPortfolioEntityShouldReturnNull() {
        PortfolioEntity actual = mapper.portfolioToPortfolioEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapPortfolioEntityToPortfolioShouldReturnNull() {
        Portfolio actual = mapper.portfolioEntityToPortfolio(null);

        assertThat(actual, is(nullValue()));
    }

    private static PortfolioEntity getPortfolioEntity() {
        return PortfolioEntity.builder()
                .id(1L)
                .name("name_portfolio")
                .build();
    }

    private static Portfolio getPortfolio() {
        return Portfolio.builder()
                .id(1L)
                .name("name_portfolio")
                .build();
    }
}
