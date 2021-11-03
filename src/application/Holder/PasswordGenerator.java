package application.Holder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {

	private final List<String> getTotalList() {
        List<String> list = new ArrayList<String>();
        for (int i = ((int)('a')); (i <= ((int)('z'))); i++) {
            list.add(("" + ((char)(i))));
        }
        
        for (int i = 0; (i <= 9); i++) {
            list.add(("" + i));
        }
        
        return list;
    }
    
    public final String getPassword() {
        String password = "";
        Random random = new Random();
        password = this.getTotalList().get(random.nextInt(this.getTotalList().size())).toUpperCase();
        for (int i = 0; (i < 11); i++) {
            password = (password + this.getTotalList().get(random.nextInt(this.getTotalList().size())));
        }
        
        return password;
    }
}
