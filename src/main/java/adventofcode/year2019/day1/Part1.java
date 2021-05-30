package adventofcode.year2019.day1;

import adventofcode.Answer;
import adventofcode.Session;
import adventofcode.Solver;
import adventofcode.utils.SSLhelper;

public class Part1 extends Solver<Integer> {
    @Override
    public Integer solve(String input) {
        int totalFuel = 0;
        for (String line : input.split("\n")) {
            int mass = Integer.parseInt(line);
            totalFuel += mass / 3 - 2;
        }
        return totalFuel;
    }

    public static void main(String[] args) throws Exception {
        SSLhelper.trustAll();
        Session session = Session.fromProperties(ClassLoader.getSystemResource("session.properties"));
        String input = session.getInput(2019, 1);
        Answer<Integer> answer = new Part1().execute(input);
        answer.print("Answer:");
    }
}
