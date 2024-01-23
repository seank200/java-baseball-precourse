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
        boolean shouldContinue = true;
        while (shouldContinue) {
            playRound();
            shouldContinue = askContinue();
        }
    }

    /**
     * 메인 게임 로직
     */
    private static void playRound() {
        List<Integer> answer = generateAnswer();

        boolean isCorrect = false;
        while (!isCorrect) {
            System.out.print(GUESS_PROMPT);
            String guessInput = Console.readLine();

            Guess guess = new Guess(answer, guessInput);
            guess.check();

            System.out.println(guess);
            isCorrect = guess.isCorrect();
        }

        System.out.println(GAME_OVER_PROMPT);
    }

    /**
     * 새로운 정답(컴퓨터의 수) 생성
     */
    private static List<Integer> generateAnswer() {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int n = Randoms.pickNumberInRange(1, 9);
            while (answer.contains(n)) {
                // 중복 체크: 정답은 서로 다른 수로 이루어져 있음
                n = Randoms.pickNumberInRange(1, 9);
            }
            answer.add(n);
        }
        return answer;
    }

    /**
     * 게임 종료 의사를 유저에게 확인
     * @return 유저가 1을 입력하면 true, 2를 입력하면 false
     * @throws IllegalArgumentException 1, 2 이외의 다른 수 입력시
     */
    private static boolean askContinue() throws IllegalArgumentException {
        System.out.println(CONTINUE_PROMPT);
        String continueInput = Console.readLine();

        if (continueInput.equals("1")) {
            return true;
        } else if (continueInput.equals("2")) {
            return false;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
