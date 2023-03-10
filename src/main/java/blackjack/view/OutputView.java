package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import blackjack.domain.result.ResultMatcher;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String CARD_STRING = " 카드: ";
    private static final String RESULT_STRING = " - 결과: ";

    private OutputView() {
    }

    // 유저들에게 두장의 카드를 나누어 주었다는 메세지를 출력한다.
    public static void printReadyMessage(List<Name> names) {
        String allName = names.stream().map(Name::getName).collect(Collectors.joining(", "));
        System.out.println("\n딜러와 " + allName + "에게 2장을 나누었습니다.");
    }

    public static void printPlayerCurrentCards(Player player) {
        System.out.println(player.getPlayerName() + CARD_STRING + OutViewHelper.getUserCards(player));
    }

    // 딜러의 처음 받은 두장의 카드를 히든카드와 함께 출력한다.
    public static void printDealerFirstCard(Dealer dealer) {
        Card firstCard = dealer.getPlayerCards().get(0);
        System.out.println(dealer.getDealerName() + CARD_STRING + firstCard.getCardNumber().getNumber() + firstCard.getSymbol());
    }

    public static void printResults(Dealer dealer, List<Player> players) {
        System.out.println();
        System.out.println(dealer.getDealerName() + CARD_STRING + OutViewHelper.getUserCards(dealer) + RESULT_STRING + dealer.getTotalScore());
        for (Player player : players) {
            System.out.println(player.getPlayerName() + CARD_STRING + OutViewHelper.getUserCards(player) + RESULT_STRING + player.getTotalScore());
        }
    }

    public static void printScore(Map<String, ResultMatcher> playersScore, EnumMap<ResultMatcher, Integer> dealerScore) {
        System.out.println("\n## 최종 승패");
        System.out.print("딜러: ");
        for (Map.Entry<ResultMatcher, Integer> score : dealerScore.entrySet()) {
            if (score.getValue() > 0) {
                System.out.print(score.getValue() + score.getKey().getResult() + " ");
            }
        }
        System.out.println();
        for (Map.Entry<String, ResultMatcher> score : playersScore.entrySet()) {
            System.out.println(score.getKey() + ": " + score.getValue().getResult());
        }
    }

    public static void printMessageDealerOneMore() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
