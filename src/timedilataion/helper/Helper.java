package timedilataion.helper;

import java.util.Arrays;

/**
 * @author Gawa [Paweł Gawędzki]
 * 2016-11-06 18:48:19
 */
public class Helper {
    
    public static String text(double[] doubles, String[] strings) {
        StringBuilder result = new StringBuilder();
        Arrays.asList(doubles).stream().forEach(value -> result.append(value).append(" "));
        result.append("[");
        Arrays.asList(strings).stream().forEach(value -> result.append(value).append("/"));
        String msg = result.toString();
        return msg.substring(0, msg.length() - 1);
    }

}
