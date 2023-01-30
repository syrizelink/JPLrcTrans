package syrize.Lyrics;

import java.io.IOException;
import java.util.List;

public class FileOperation {
    /**
     * 该方法接收一个字符将其转换为罗马音形式
     * @param x 假名字符
     * @return 罗马字
     */
    public static String getRomajiValue(String x) throws IOException {
        String jsonRomaji, mainValue, romajiValue, krKey, trueValue = null;
        int i;
        i = 0;
        jsonRomaji = "Romaji";
        mainValue = "main";
        romajiValue = "Romaji";
        krKey = "KtoR";
        List<String> kato = DataReading.mainJsonReading(jsonRomaji, krKey, mainValue);
        List<String> romaji = DataReading.mainJsonReading(jsonRomaji, krKey, romajiValue);

        if (isJP(x) || x.equals("ー")){
            for (; i < (kato.size() - 1); i++) {
                if (x.equals(kato.get(i))) {
                    break;
                } else {
                    i++;
                }
            }
            trueValue = romaji.get(i) + " ";
        }else {
            trueValue = x;
        }


        return trueValue;
    }

    /**
     * 该方法接收一个字符将其转换为谐音形式
     * @param x 罗马字字符
     * @return 谐音
     */
    public static String getHomophonicValue(String x) throws IOException {
        String jsonHomophonic, HomoValue, mainValue, rhKey, trueValue;
        int i;
        i = 0;
        jsonHomophonic = "Homophonic";
        HomoValue = "Homo";
        mainValue = "main";
        rhKey = "RtoH";
        List<String> homophonic = DataReading.mainJsonReading(jsonHomophonic, rhKey, HomoValue);
        List<String> main = DataReading.mainJsonReading(jsonHomophonic, rhKey, mainValue);

        if (isJP(x) || x.equals("ー")){
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