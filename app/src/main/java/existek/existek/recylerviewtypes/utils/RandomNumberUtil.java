package existek.existek.recylerviewtypes.utils;

import java.util.Random;

public class RandomNumberUtil {

    public static int getRandomNumber(int high) {
        Random r = new Random();
        int result = (r.nextInt(high)) + 1;
        return result == 1 ? 2 : result;
    }
}
