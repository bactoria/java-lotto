package domain;

import java.util.List;
import java.util.stream.Collectors;

import static domain.LottoConst.LOTTO_PRICE;


public class LottoRankAnalyzer {
    private final WinningLotto winningLotto;

    public LottoRankAnalyzer(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public LottoRankCount getLottoRankCount(List<Lotto> lottos) {
        List<Rank> ranks = calculateRank(lottos);

        LottoRankCount lottoRankCount = new LottoRankCount();
        ranks.forEach(lottoRankCount::put);
        return lottoRankCount;
    }

    private List<Rank> calculateRank(List<Lotto> lottos) {
        return lottos.stream()
                .map(winningLotto::match)
                .collect(Collectors.toList());
    }

    public EarningRate getEarningRate(List<Lotto> lottos) {
        List<Rank> ranks = calculateRank(lottos);
        long earningMoney = getEarningMoney(ranks);
        int purchaseMoney = LOTTO_PRICE * lottos.size();
        return new EarningRate(earningMoney / (double) purchaseMoney);
    }

    private long getEarningMoney(List<Rank> ranks) {
        return ranks.stream()
                .mapToInt(Rank::getWinningMoney)
                .sum();
    }
}
