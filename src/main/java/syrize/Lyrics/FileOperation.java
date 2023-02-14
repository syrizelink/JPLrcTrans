package syrize.Lyrics;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

/**
 * 文件操作
 *
 * @author Syrize
 * &#064;date  2023/02/01
 */
public class FileOperation {
    static String
            jsonRomaji,
            mainValue,
            romajiValue,
            krKey,
            jsonHomophonic,
            HomoValue,
            rhKey,
            lyricString,
            singleValue,
            doubleChara,
            trueValue;
    static int
            i,
            index;
    static char
            doubleChara1,
            doubleChara2;

    /**
     * 获取罗马字值
     * 该方法接收一个歌词List将其转换为罗马音形式
     *
     * @param x 假名字符
     * @return 罗马字
     * @throws IOException ioexception
     */
    public static List<String> getRomajiValue(@NotNull List<String> x) throws IOException {

        i = 0;
        jsonRomaji = "Romaji";
        mainValue = "main";
        romajiValue = "Romaji";
        krKey = "KtoR";
        List<String> kato = DataReading.mainJsonReading(jsonRomaji, krKey, mainValue);//将假名数据生成为列表
        List<String> romaji = DataReading.mainJsonReading(jsonRomaji, krKey, romajiValue);//将罗马字数据生成为列表

        for (int a = 0; i < x.size() - 1; a++){
            StringBuilder sb = new StringBuilder(x.get(a));
            for (; i < (kato.size() - 1); i++){
                lyricString = x.get(a);
                for (int n = 0; n < lyricString.length() - 1; n++){
                    doubleChara1 = lyricString.charAt(n);
                    doubleChara2 = lyricString.charAt(n + 1);
                    doubleChara = String.valueOf(doubleChara1) + doubleChara2;
                    if (doubleChara.equals(kato.get(i))){
                        sb.replace(n, n + 2, romaji.get(i) + " ");
                    }
                }
            }
            x.set(a, String.valueOf(sb));
        }
        return x;
    }

    /**
     * 该方法通过两次遍历匹配单/双字符进行替换
     * @param x 要进行操作的歌词行
     * @return 替换完成后的歌词行
     */
    private static @NotNull String matchCharacter(String x) throws IOException {
        jsonRomaji = "Romaji";
        mainValue = "main";
        romajiValue = "Romaji";
        krKey = "KtoR";
        List<String> kato = DataReading.mainJsonReading(jsonRomaji, krKey, mainValue);//将假名数据生成为列表
        List<String> romaji = DataReading.mainJsonReading(jsonRomaji, krKey, romajiValue);//将罗马字数据生成为列表
        StringBuilder sb = new StringBuilder(x);
        i = 0;

        for (; i < x.length() - 3; i++){
            doubleChara = x.substring(i, i + 2);
            trueValue = charaLocReplace(doubleChara, kato, romaji);
            sb.replace(i, i + 2, trueValue);
        }

        i = 0;

        for (; i < x.length() - 1; i++){
            singleValue = x.substring(i, i + 1);
            trueValue = charaLocReplace(singleValue, kato, romaji);
            sb.replace(i, i + 1, trueValue);
        }

        return sb.toString();
    }

    /**
     * 该方法用于在匹配替换给定双字符
     * @param x 要进行操作的双字符
     * @param matchList 比对匹配列表
     * @return 替换完成的歌词行
     */
    private static String charaLocReplace(String x, @NotNull List<String> matchList, List<String> searchList){
        i = 0;
        for (; i < matchList.size(); i++){
            String listValue = matchList.get(i);
            if (listValue.equals(x)){
                x = searchList.get(i);
            }
        }
        return x;
    }

    /**
     * 获得谐音值
     * 该方法接收一个字符将其转换为谐音形式
     *
     * @param x 罗马字字符
     * @return 谐音
     * @throws IOException ioexception
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

    /**
     * 是否为日文
     * 该方法用于判断字符串是否为日文字符
     *
     * @param x 需要判断的字符串
     * @return 布尔值
     */
    public static boolean isJP(@NotNull String x) {
        Character.UnicodeScript script = Character.UnicodeScript.of(x.charAt(0));
        return script == Character.UnicodeScript.HIRAGANA || script == Character.UnicodeScript.KATAKANA || script == Character.UnicodeScript.HAN;
    }

}