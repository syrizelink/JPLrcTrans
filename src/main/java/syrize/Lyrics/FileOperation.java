package syrize.Lyrics;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
            singleValue,
            doubleChara,
            trueValue;
    static int
            i;

    /**
     * 获取罗马字值
     * 该方法接收一个歌词List将其转换为罗马音形式
     *
     * @param x 假名字符
     * @return 罗马字
     * @throws IOException ioexception
     */
    public static List<String> getRomajiValue(@NotNull List<String> x) throws IOException {

        for (int a = 0; a < x.size(); a++){
            x.set(a, matchCharacter(x.get(a), "Romaji"));
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
    public static List<String> getHomophonicValue(@NotNull List<String> x) throws IOException {

        for (int a = 0; a < x.size(); a++){
            x.set(a, matchCharacter(x.get(a), "Homo"));
        }

        return x;
    }

    /**
     * 该方法通过两次遍历匹配单/双字符进行替换
     * @param x 要进行操作的歌词行
     * @return 替换完成后的歌词行
     */
    private static @NotNull String matchCharacter(String x, String type) throws IOException {
        int indexD = 0, indexS = 0;
        List<String> tagSearch = null, tagMatch = null;
        jsonRomaji = "Romaji";
        jsonHomophonic = "Homophonic";
        mainValue = "main";
        romajiValue = "Romaji";
        HomoValue = "Homo";
        krKey = "KtoR";
        rhKey = "RtoH";
        List<String> katoR = DataReading.mainJsonReading(jsonRomaji, krKey, mainValue);//将假名数据生成为列表
        List<String> katoH = DataReading.mainJsonReading(jsonHomophonic, rhKey, mainValue);
        List<String> romaji = DataReading.mainJsonReading(jsonRomaji, krKey, romajiValue);//将罗马字数据生成为列表
        List<String> homo = DataReading.mainJsonReading(jsonHomophonic, rhKey, HomoValue);

        StringBuilder sb = new StringBuilder(x);

        if (type.equals("Romaji")){
            tagMatch = katoR;
            tagSearch = romaji;
        }else if (type.equals("Homo")){
            tagMatch = katoH;
            tagSearch = homo;
        }

        for (int subD = 0; subD < x.length() - 3; subD++){
            if (subD == x.length() - 3){
                break;
            }
            doubleChara = x.substring(subD, subD + 2);
            trueValue = charaLocReplace(doubleChara, Objects.requireNonNull(tagMatch), tagSearch);
            sb.delete(indexD, indexD + 2);
            sb.insert(indexD, trueValue);
            if (trueValue.equals(doubleChara)){
                indexD += 1;
            }else {
                indexD += trueValue.length();
            }
        }


        for (int subS = 0; subS < x.length(); subS++){
            singleValue = x.substring(subS, subS + 1);
            trueValue = charaLocReplace(singleValue, Objects.requireNonNull(tagMatch), tagSearch);
            sb.delete(indexS, indexS + 1);
            sb.insert(indexS, trueValue);
            if (trueValue.equals(doubleChara)){
                indexS += 1;
            }else {
                indexS += trueValue.length();
            }
        }

        return sb.toString();
    }

    /**
     * 该方法用于在匹配替换给定字符
     * @param x 要进行操作的字符
     * @param matchList 比对匹配列表
     * @return 替换完成的字符
     */
    private static String charaLocReplace(String x, @NotNull List<String> matchList, List<String> searchList){
        i = 0;
        for (; i < matchList.size(); i++){
            if (matchList.get(i).equals(x)){
                x = searchList.get(i);
            }
        }
        return x;
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