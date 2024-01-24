package baseball;

public class Hint {

    private final String answer;
    private final String guess;

    private Integer strike;
    private Integer ball;

    public Hint(String answer, String guess) {
        this.answer = answer;
        this.guess = guess;
        check();
    }

    private void check() {
        strike = 0;
        ball = 0;

        for (int i = 0; i < 3; i++) {
            char answerDigit = answer.charAt(i);
            char guessDigit = guess.charAt(i);

            if (guessDigit == answerDigit) {
                strike++;
            } else if (answer.contains("" + guessDigit)) {
                ball++;
            }
        }
    }

    @Override
    public String toString() {
        if (strike == 0 && ball == 0) {
            return "낫싱";
        }

        String ballStr;
        if (ball > 0) {
            ballStr = ball + "볼";
        } else {
            ballStr = "";
        }

        String sep;
        if (ball > 0 && strike > 0) {
            sep = " ";
        } else {
            sep = "";
        }

        String strikeStr;
        if (strike > 0) {
            strikeStr = strike + "스트라이크";
        } else {
            strikeStr = "";
        }

        return ballStr + sep + strikeStr;
    }
}
