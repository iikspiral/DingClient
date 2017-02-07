package cn.dc.ding.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dongchen on 2017/1/24.
 */
public class StringUtils {

    /**
     * 使用delimiter字符拼接list成为一个字符串
     *
     * @param list
     * @param delimiter
     * @return
     */
    public static String join(List<String> list, String delimiter) {
        for (int i = 0; i < list.size(); i++) {
            String tmp = list.get(i);
            if (tmp == null || "".equals(tmp)) {
                list.remove(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < list.size(); i++) {
            if (!"".equals(list.get(i))) {
                sb.append(list.get(i));
            }
            if (i != (size - 1)) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    /**
     * 使用delimiter字符拼接arr成为一个字符串
     *
     * @param arr
     * @param delimiter
     * @return
     */
    public static String join(String[] arr, String delimiter) {
        List<String> list = Arrays.asList(arr);
        return join(list, delimiter);
    }

    public static String encode(String old) throws UnsupportedEncodingException {
        return new String(old.getBytes("ISO8859-1"), "UTF-8");
    }


}
