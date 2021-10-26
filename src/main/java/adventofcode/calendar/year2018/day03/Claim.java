package adventofcode.calendar.year2018.day03;

public class Claim {
    public final int id;
    public final int x;
    public final int y;
    public final int width;
    public final int height;

    public Claim(String line) {
        int posIndex = line.indexOf('@');
        int sizeIndex = line.indexOf(':', posIndex);
        String[] posPair = line.substring(posIndex + 1, sizeIndex).split(",", 2);
        String[] sizePair = line.substring(sizeIndex + 1).split("x", 2);
        id = Integer.parseInt(line.substring(1, posIndex).trim());
        x = Integer.parseInt(posPair[0].trim());
        y = Integer.parseInt(posPair[1].trim());
        width = Integer.parseInt(sizePair[0].trim());
        height = Integer.parseInt(sizePair[1].trim());
    }
}
