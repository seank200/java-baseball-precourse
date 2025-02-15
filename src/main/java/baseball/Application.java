package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final String GUESS_PROMPT = "숫자를 입력해주세요 : ";
    private static final String CONTINUE_PROMPT = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private static final String GAME_OVER_PROMPT = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";

    public static void main(String[] args) {
        while (true) {
            playRound();

            System.out.println(CONTINUE_PROMPT);

            String continueInput = Console.readLine();
            if (continueInput.equals("2")) {
                break;
            } else if (!continueInput.equals("1")) {
                throw new IllegalArgumentException();
            }
        }
    }

    private static void playRound() {
        List<Integer> answer = generateAnswer();

        while (true) {
            System.out.print(GUESS_PROMPT);
            String guessInput = Console.readLine();

            Guess guess = new Guess(answer, guessInput);
            guess.check();

            System.out.println(guess);

            if (guess.isCorrect()) {
                break;
            }
        }

        System.out.println(GAME_OVER_PROMPT);
    }

    private static List<Integer> generateAnswer() {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Integer n = Randoms.pickNumberInRange(1, 9);
            while (answer.contains(n)) {
                n = Randoms.pickNumberInRange(1, 9);
            }
            answer.add(n);
        }
        return answer;
    }

}
