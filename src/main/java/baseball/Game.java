package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private static final String PROMPT_GUESS = "숫자를 입력해주세요 : ";
    private static final String PROMPT_GAME_OVER = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private static final String PROMPT_CONTINUE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";

    public static void play() {
        boolean shouldContinue = true;
        while (shouldContinue) {
            Game game = new Game();
            game.playRound();
            shouldContinue = askContinue();
        }
    }

    private static boolean askContinue() throws IllegalArgumentException {
        System.out.println(PROMPT_CONTINUE);
        String continueInput = Console.readLine();

        if (!continueInput.matches("[12]")) {
            throw new IllegalArgumentException();
        }

        return continueInput.equals("1");
    }


    private final String answer;

    private Game() {
        answer = generateAnswer();
    }

    private void playRound() {
        boolean isWrong = true;

        while (isWrong) {
            String guess = askGuess();
            Hint hint = new Hint(answer, guess);
            System.out.println(hint);
            isWrong = !answer.equals(guess);
        }

        System.out.println(PROMPT_GAME_OVER);
    }

    private String generateAnswer() {
        List<Integer> answer = new ArrayList<>();

        while (answer.size() < 3) {
            int n = Randoms.pickNumberInRange(1, 9);
            if (!answer.contains(n)) {
                answer.add(n);
            }
        }

        return answer.stream()
                .map(n -> Integer.toString(n))
                .collect(Collectors.joining());
    }

    private String askGuess() throws IllegalArgumentException {
        System.out.print(PROMPT_GUESS);
        String guessInput = Console.readLine();

        if (!guessInput.matches("[1-9]{3}")) {
            throw new IllegalArgumentException();
        }

        return guessInput;
    }

}
