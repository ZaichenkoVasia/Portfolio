package portfolio.model.service.impl;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import portfolio.model.domain.Portfolio;
import portfolio.model.domain.TotalValue;
import portfolio.model.entity.PortfolioEntity;
import portfolio.model.repository.PortfolioRepository;
import portfolio.model.service.TotalValueService;
import portfolio.model.service.mapper.PortfolioMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PortfolioServiceImpl.class})
public class PortfolioServiceImplTest {
    private static final Portfolio PORTFOLIO = getPortfolio();

    private static final PortfolioEntity PORTFOLIO_ENTITY = getPortfolioEntity();

    private static final TotalValue TOTAL_VALUE = getTotalValue();

    @MockBean
    private TotalValueService totalValueService;

    @MockBean
    private PortfolioMapper mapper;

    @MockBean
    private PortfolioRepository repository;

    @Autowired
    private PortfolioServiceImpl service;

    @After
    public void resetMock() {
        reset(totalValueService, repository, mapper);
    }

    @Test
    public void shouldSavePortfolio() {
        when(mapper.portfolioEntityToPortfolio(any(PortfolioEntity.class))).thenReturn(PORTFOLIO);
        Portfolio portfolio = Portfolio.builder().id(1L).build();
        service.addPortfolio(portfolio);

        verify(repository).save(any());
    }

    @Test
    public void shouldReturnPortfolioById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(PORTFOLIO_ENTITY));
        when(mapper.portfolioEntityToPortfolio(any(PortfolioEntity.class))).thenReturn(PORTFOLIO);
        Portfolio actual = service.findById(1L);

        verify(repository).findById(anyLong());
        verify(mapper).portfolioEntityToPortfolio(any(PortfolioEntity.class));
        assertThat(actual, equalTo(PORTFOLIO));
    }

    @Test
    public void shouldFindDifferenceTotalValuesByTwoYear() {
        when(totalValueService.findByPortfolioAndYear(any(Portfolio.class), anyString()))
                .thenReturn(TOTAL_VALUE).thenReturn(TOTAL_VALUE);
        BigDecimal diff = service.differenceTotalValuesByTwoYear(PORTFOLIO, anyString(), anyString());

        assertThat(diff, equalTo(BigDecimal.ZERO));
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

    private static TotalValue getTotalValue() {
        return TotalValue.builder()
                .value(BigDecimal.ONE)
                .build();
    }
}
