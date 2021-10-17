package adventofcode.calendar.year2019.day8;

import adventofcode.framework.AbstractPart;

public class Part2 extends AbstractPart<String> {
    private static final int width = 25;
    private static final int height = 6;

    @Override
    public String solve(String input) {
        int depth = input.length() / (width * height);
        StringBuilder[] image = new StringBuilder[height];
        for (int y = 0; y < height; y++) {
            image[y] = new StringBuilder(width);
            for (int x = 0; x < width; x++) {
                image[y].append(' ');
            }
        }
        for (int z = depth - 1; z >= 0; z--) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int i = x + y * width + z * width * height;
                    int code = input.charAt(i) - '0';
                    if (code == 0) {
                        image[y].setCharAt(x, '.');
                    } else if (code == 1) {
                        image[y].setCharAt(x, '#');
                    }
                }
            }
        }
        return String.join("\n", image);
    }
}
