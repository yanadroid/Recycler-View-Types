package existek.existek.recylerviewtypes.utils;

import java.util.LinkedList;
import java.util.List;

import existek.existek.recylerviewtypes.data.Email;
import existek.existek.recylerviewtypes.data.Progress;
import existek.existek.recylerviewtypes.data.Random;
import existek.existek.recylerviewtypes.data.Settings;
import existek.existek.recylerviewtypes.data.Type;
import existek.existek.recylerviewtypes.data.User;

public class GenerateFakeRandomDataUtil {

    public static final String IMAGE_URL = "https://globalpacta.com/wp-content/uploads/2018/10/mcruz.jpg";


    public static List<Type> generateData() {
        List<Type> data = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            Type settings = new Settings("Some text\nSome text");
            data.add(settings);
            Type user = new User("Anna", "Orlova", i, IMAGE_URL);
            data.add(user);
            Type email = new Email();
            data.add(email);
            Type progress = new Progress(i);
            data.add(progress);
            Type random = new Random(RandomNumberUtil.getRandomNumber( 3));
            data.add(random);
        }
        return data;
    }
}
