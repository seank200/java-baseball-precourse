package baseball;

import java.util.List;

public class Guess {

    private final List<Integer> answer;

    private final String guessInput;

    private Integer strike;
    private Integer ball;

    public Guess(List<Integer> answer, String guessInput) {
        this.answer = answer;
        this.guessInput = guessInput;
        strike = null;
        ball = null;
    }

    private boolean isInputValid() {
        return guessInput.matches("[1-9]{3}");
    }

    public void check() throws IllegalArgumentException {
        if (!isInputValid()) {
            throw new IllegalArgumentException();
        }

        strike = 0;
        ball = 0;

        for (int i = 0; i < 3; i++) {
            int guessDigit = Integer.parseInt(String.valueOf(guessInput.charAt(i)));
            int answerDigit = answer.get(i);

            if (guessDigit == answerDigit) {
                strike++;
            } else if (answer.indexOf(guessDigit) >= 0) {
                ball++;
            }

        }
    }

    public boolean isCorrect() {
        return strike == 3;
    }

    @Override
    public String toString() {
        if (ball == 0 && strike == 0) {
            return "낫싱";
        }

        String ballString;
        if (ball > 0) {
            ballString = ball + "볼";
        } else {
            ballString = "";
        }

        String sep;
        if (ball > 0 && strike > 0) {
            sep = " ";
        } else {
            sep = "";
        }

        String strikeString;
        if (strike > 0) {
            strikeString = strike + "스트라이크";
        } else {
            strikeString = "";
        }

        return ballString + sep + strikeString;
    }
}
