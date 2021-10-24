package adventofcode.calendar.year2019;

public class Bonus {
    public static void main(String[] args) {
        //https://www.reddit.com/r/adventofcode/comments/ef0imt/intcode_merry_christmas_everyone/
        String code = "109,48,1201,0,0,44,1001,3,2,3,1201,1,0,19,1001,11,2,11,104,0,1001,43,1,43,8,43,44,45,1006,45,18,1101,0,0,43,8,3,47,40,1106,0,2,99,0,0,0,0,784,7,32,1,46,5,32,1,46,23,32,3,42,1,32,2,42,1,10,16,32,1,33,6,32,1,46,11,32,1,46,1,95,1,42,1,46,23,32,1,46,1,10,13,32,1,45,1,32,1,45,1,42,1,45,1,32,1,45,7,32,1,46,1,45,1,39,1,45,1,46,3,32,1,33,2,32,1,33,5,32,1,46,1,10,4,32,1,46,4,32,1,46,6,32,1,42,7,32,1,46,1,45,1,39,1,32,1,46,1,45,1,46,1,32,1,39,1,45,1,46,1,33,2,32,1,33,13,32,1,46,14,32,1,46,1,10,15,32,3,42,3,32,1,46,1,45,1,39,1,32,1,46,1,45,1,39,3,32,1,39,1,45,1,46,1,32,1,39,1,45,1,46,1,33,4,32,1,46,1,10,7,32,1,42,7,32,3,42,1,46,1,45,1,39,1,32,1,46,1,45,1,39,9,32,1,39,1,45,1,46,1,32,1,39,1,45,1,46,19,32,1,46,1,10,7,32,1,42,6,32,3,42,1,36,1,42,1,46,1,45,1,39,15,32,1,39,1,45,1,46,1,32,1,39,1,45,1,46,5,32,1,42,1,10,2,32,1,42,3,32,3,42,5,32,1,42,1,32,3,42,5,32,11,95,5,32,1,33,1,45,2,46,1,33,1,45,1,46,2,32,1,42,5,32,1,42,9,32,1,42,4,32,1,42,1,10,2,32,1,42,3,32,3,42,4,32,2,42,1,36,2,42,1,32,1,42,3,32,1,33,2,95,1,33,2,95,1,33,2,95,1,33,2,95,1,33,4,32,1,33,4,32,1,33,2,32,3,42,3,32,3,42,4,32,1,46,3,32,1,42,3,32,3,42,1,10,1,32,3,42,1,32,4,42,4,32,1,42,1,32,5,42,3,32,1,33,2,95,1,33,2,95,1,33,2,95,1,33,2,95,1,33,4,32,1,33,6,32,1,46,3,42,1,45,1,46,1,45,3,42,1,32,1,42,5,32,3,42,1,32,1,42,1,32,1,35,1,95,1,10,10,42,2,32,1,42,1,32,4,42,1,36,1,32,1,42,2,32,1,33,2,95,1,33,2,95,1,33,2,95,1,33,2,95,1,33,4,32,1,33,1,45,2,46,2,45,1,39,5,42,3,32,1,35,1,32,1,39,1,42,1,45,2,46,3,45,1,35,1,32,3,42,1,10,4,42,1,32,5,42,2,32,1,42,1,32,1,36,2,42,1,32,3,42,6,32,1,46,12,32,1,33,6,32,5,42,5,32,3,42,7,32,3,42,1,10,12,42,1,32,5,42,1,32,3,42,1,45,2,46,1,45,1,39,1,32,1,45,2,46,9,95,1,33,5,32,7,42,4,32,3,42,6,32,5,42,1,10,11,42,3,32,1,46,1,45,1,35,1,46,1,45,1,39,11,32,1,39,1,45,1,46,1,45,2,39,1,45,2,46,1,33,5,32,7,42,3,32,4,42,3,46,5,32,1,35,1,10,2,32,1,35,1,32,2,39,1,45,1,46,3,45,2,39,27,32,1,39,1,45,4,46,3,45,1,35,2,46,2,45,1,39,6,42,1,32,2,39,1,45,1,46,3,45,2,39,1,45,1,10,18,32,1,77,1,101,2,114,1,121,1,32,1,67,1,104,1,114,1,105,1,115,1,116,1,109,1,97,1,115,1,10,18,32,1,126,1,82,1,117,1,100,1,111,1,108,1,112,1,104,1,80,1,108,1,97,1,121,1,115,1,126,1,10";
        StreamedIntcode.run(code, System.in, System.out);
    }
}