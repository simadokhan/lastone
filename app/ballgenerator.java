import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ballgenerator {
    private  int r_value;
    private  int g_value;
    private  int b_value;

    public ballgenerator() {
        this.r_value =  new Random().nextInt((255 - 0) + 1) + 0;
        this.g_value = new Random().nextInt((255 - 0) + 1) + 0;
        this.b_value = new Random().nextInt((255 - 0) + 1) + 0;
    }

    public Map<String, Integer> diffrentBallGenerator(String Level){
        Map<String, Integer> colors_map = new HashMap<String, Integer>();

        switch (Level) {
            case "Hard":
                int new_r_valueh = new Random().nextInt((this.r_value - (this.r_value - 10) ) + 1) +  (this.r_value - 10);
                int new_g_valueh = new Random().nextInt((this.g_value - (this.g_value + 10) ) + 1) +  (this.g_value + 10);

                while ( new_g_valueh > 255 || new_g_valueh < 0 || new_g_valueh != this.g_value
                        || new_r_valueh > 255 || new_r_valueh < 0 || new_r_valueh != this.r_value
                ) {
                    new_r_valueh = new Random().nextInt((this.r_value - (this.r_value - 10) ) + 1) +  (this.r_value - 10);
                    new_g_valueh = new Random().nextInt((this.g_value - (this.g_value + 10) ) + 1) +  (this.g_value + 10);
                }

                colors_map.put("red" , new Integer(new_r_valueh));
                colors_map.put("green", new Integer(new_g_valueh));
                colors_map.put("blue", new Integer(this.b_value));
            case "Medium": // update
                int new_r_valuem = new Random().nextInt((this.r_value - (this.r_value - 10) ) + 1) +  (this.r_value - 10);
                int new_g_valuem = new Random().nextInt((this.g_value - (this.g_value + 10) ) + 1) +  (this.g_value + 10);

                while ( new_g_valuem > 255 || new_g_valuem < 0 || new_g_valuem != this.g_value
                        || new_r_valuem > 255 || new_r_valuem < 0 || new_r_valuem != this.r_value
                ) {
                    new_r_valuem = new Random().nextInt((this.r_value - (this.r_value - 10) ) + 1) +  (this.r_value - 10);
                    new_g_valuem = new Random().nextInt((this.g_value - (this.g_value + 10) ) + 1) +  (this.g_value + 10);
                }

                colors_map.put("red" , new Integer(new_r_valuem));
                colors_map.put("green", new Integer(new_g_valuem));
                colors_map.put("blue", new Integer(this.b_value));
            case "Easy": // update
                int new_r_valueme = new Random().nextInt((this.r_value - (this.r_value - 10) ) + 1) +  (this.r_value - 10);
                int new_g_valueme = new Random().nextInt((this.g_value - (this.g_value + 10) ) + 1) +  (this.g_value + 10);

                while ( new_g_valueme > 255 || new_g_valueme < 0 || new_g_valueme != this.g_value
                        || new_r_valueme > 255 || new_r_valueme < 0 || new_r_valueme != this.r_value
                ) {
                    new_r_valuem = new Random().nextInt((this.r_value - (this.r_value - 10) ) + 1) +  (this.r_value - 10);
                    new_g_valuem = new Random().nextInt((this.g_value - (this.g_value + 10) ) + 1) +  (this.g_value + 10);
                }

                colors_map.put("red" , new Integer(new_r_valueme));
                colors_map.put("green", new Integer(new_g_valueme));
                colors_map.put("blue", new Integer(this.b_value));
        }


        if (Level == "Hard"){



        } else {

        }
        return colors_map;
    }


}
