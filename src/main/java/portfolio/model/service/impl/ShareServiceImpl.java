package portfolio.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.model.domain.Portfolio;
import portfolio.model.domain.Share;
import portfolio.model.entity.PortfolioEntity;
import portfolio.model.entity.ShareEntity;
import portfolio.model.repository.ShareRepository;
import portfolio.model.service.ShareService;
import portfolio.model.service.exception.InvalidDataRuntimeException;
import portfolio.model.service.mapper.PortfolioMapper;
import portfolio.model.service.mapper.ShareMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {
    private ShareMapper mapper;
    private PortfolioMapper portfolioMapper;
    private ShareRepository repository;

    @Override
    public void addShare(Share share) {
        if (Objects.isNull(share)) {
            log.warn("Invalid input share data");
            throw new InvalidDataRuntimeException("Invalid input share data");
        }
        ShareEntity entity = mapper.shareToShareEntity(share);
        repository.save(entity);
    }

    @Override
    public List<Share> findByPortfolioAndYear(Portfolio portfolio, String year) {
        PortfolioEntity portfolioEntity = portfolioMapper.portfolioToPortfolioEntity(portfolio);
        List<ShareEntity> shares = repository.findByPortfolioAndYear(portfolioEntity, year);
        return shares.isEmpty() ? Collections.emptyList()
                : shares.stream()
                .map(mapper::shareEntityToShare)
                .collect(Collectors.toList());
    }
}
