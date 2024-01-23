package baseball;

import java.util.List;

public class Guess {

    /** 정답(컴퓨터가 생각한 수) */
    private final List<Integer> answer;

    /** 사용자가 입력한 수 */
    private final String guessInput;

    private Integer strike;
    private Integer ball;

    public Guess(List<Integer> answer, String guessInput) {
        this.answer = answer;
        this.guessInput = guessInput;
        strike = null;
        ball = null;
    }

    /**
     * 사용자가 입력한 수가 유효한지 검증
     * (1-9 사이의 수들로 이루어진 3자리 수)
     */
    private boolean isInputValid() {
        return guessInput.matches("[1-9]{3}");
    }

    /**
     * 유저가 입력한 수와 정답을 비교하여 힌트(볼, 스트라이크)를 생성한다
     * @throws IllegalArgumentException 유저가 유효하지 않은 값을 입력한 경우 발생
     */
    public void check() throws IllegalArgumentException {
        if (!isInputValid()) {
            throw new IllegalArgumentException();
        }

        strike = 0;
        ball = 0;

        for (int i = 0; i < 3; i++) {
            // 각 자리를 비교
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

    /**
     * 유저에게 출력할 힌트 String을 반환
     */
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
