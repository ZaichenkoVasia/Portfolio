package portfolio.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Builder
public class Portfolio {
    private Long id;
    private String name;
    private List<Share> shares;
    private List<TotalValue> totalValues;

    public void print() {
        System.out.println("Portfolio " + ":");
        for (Share share : shares) {
            System.out.println(share.toString());
        }
    }
}
