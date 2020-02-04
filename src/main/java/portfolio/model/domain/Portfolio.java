package portfolio.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Portfolio {
    private Long id;
    private List<Share> shares;
    private List<TotalValue> totalValues;

    public void print() {
        System.out.println("Portfolio " + ":");
        for (Share share : shares) {
            System.out.println(share.toString());
        }
    }
}
