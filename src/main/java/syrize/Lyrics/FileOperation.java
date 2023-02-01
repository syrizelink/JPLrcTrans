package syrize.Lyrics;

import java.io.IOException;
import java.util.List;

/**
 * @author Syrize
 */
public class FileOperation {
    static String jsonRomaji, mainValue, romajiValue, krKey, jsonHomophonic, HomoValue, rhKey, trueValue;
    static int i, index;
    /**
     * 该方法接收一个字符将其转换为罗马音形式
     * @param x 假名字符
     * @return 罗马字
     */
    public static List<String> getRomajiValue(List<String> x) throws IOException {
        i = 0;
        jsonRomaji = "Romaji";
        mainValue = "main";
        romajiValue = "Romaji";
        krKey = "KtoR";
        List<String> kato = DataReading.mainJsonReading(jsonRomaji, krKey, mainValue);
        List<String> romaji = DataReading.mainJsonReading(jsonRomaji, krKey, romajiValue);

            for (int a = 0; i < x.size() - 1; a++){
                StringBuilder sb = new StringBuilder(x.get(a));
                    for (; i < (kato.size() - 1); i++) {

                        index = (kato.get(i)).indexOf(x.get(a));
                        if (index != -1){
                            sb.replace(index, index + 1, romaji.get(i) + " ");
                    }
                }
                    x.set(a, String.valueOf(sb));
        }
        return x;
    }

    /**
     * 该方法接收一个字符将其转换为谐音形式
     * @param x 罗马字字符
     * @return 谐音
     */
    public static String getHomophonicValue(String x) throws IOException {
        i = 0;
        jsonHomophonic = "Homophonic";
        HomoValue = "Homo";
        mainValue = "main";
        rhKey = "RtoH";
        List<String> homophonic = DataReading.mainJsonReading(jsonHomophonic, rhKey, HomoValue);
        List<String> main = DataReading.mainJsonReading(jsonHomophonic, rhKey, mainValue);

        if (isJP(x)){
            for (; i < (main.size() - 1); i++) {
                if (x.equals(main.get(i))) {
                    break;
                } else {
                    i++;
                }
            }
            trueValue = homophonic.get(i) + " ";
        }else {
            trueValue = x;
        }

        return trueValue;
    }

    public static boolean isJP(String x) {
        Character.UnicodeScript script = Character.UnicodeScript.of(x.charAt(0));
        return script == Character.UnicodeScript.HIRAGANA || script == Character.UnicodeScript.KATAKANA || script == Character.UnicodeScript.HAN;
    }

}