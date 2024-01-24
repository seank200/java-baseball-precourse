package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private static final String PROMPT_GUESS = "숫자를 입력해주세요 : ";
    private static final String PROMPT_GAME_OVER = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";

    private final String answer;

    public Game() {
        answer = generateAnswer();
    }

    public void play() {
        boolean isCorrect = false;

        while (!isCorrect) {
            String guess = askGuess();
            Hint hint = new Hint(answer, guess);
            System.out.println(hint);
            isCorrect = answer.equals(guess);
        }

        System.out.println(PROMPT_GAME_OVER);
    }

    private String generateAnswer() {
        List<Integer> answer = new ArrayList<>();

        while (answer.size() < 3) {
            int n = Randoms.pickNumberInRange(1, 9);
            while (answer.contains(n)) {
                n = Randoms.pickNumberInRange(1, 9);
            }
            answer.add(n);
        }

        return answer
                .stream()
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
