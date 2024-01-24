package baseball;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    private static final String PROMPT_CONTINUE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";

    public static void main(String[] args) {
        boolean shouldContinue = true;
        while (shouldContinue) {
            Game game = new Game();
            game.play();
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

}
