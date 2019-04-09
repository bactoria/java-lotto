import domain.Lotto;
import domain.WinningLotto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserInterface {

    private static final Scanner scanner = new Scanner(System.in);

    int getPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.valueOf(scanner.nextLine());
    }

    void printLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(System.out::println);
    }

    WinningLotto getWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String LottoWithComma = scanner.nextLine();
        List<Integer> WinningLottoNumbers = Arrays.stream(LottoWithComma.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Lotto lotto = new Lotto(WinningLottoNumbers);

        System.out.println("보너스 볼을 입력해 주세요.");
        int BonusNo = scanner.nextInt();

        return new WinningLotto(lotto, BonusNo);
    }
}
