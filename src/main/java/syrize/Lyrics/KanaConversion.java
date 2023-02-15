package syrize.Lyrics;


import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 假名转换
 *
 * @author Syrize
 * &#064;date  2023/02/01
 */
public class KanaConversion {
    /**
     * 假名转换
     * 该方法用于将日文歌词统一转换为平假名
     *
     * @param Lrc 输入的歌词List
     */
    public static void literator(@NotNull List<String> Lrc){

        int size = Lrc.size();
        String x;
        Tokenizer tokenizer = new Tokenizer.Builder().build();
        while (size > 0) {
            x = Lrc.get(size - 1);
            List<Token> tokens = tokenizer.tokenize(x);
            StringBuilder sb = new StringBuilder();

            for (Token token : tokens) {
                char[] surface = token.getSurface().toCharArray();
                boolean isJP = true;
                for (char c : surface){
                    if (!Character.UnicodeScript.of(c).equals(Character.UnicodeScript.HIRAGANA)
                            && !Character.UnicodeScript.of(c).equals(Character.UnicodeScript.KATAKANA)
                            && !Character.UnicodeScript.of(c).equals(Character.UnicodeScript.HAN)) {
                        isJP = false;
                        break;
                    }
                }
                if (isJP) {
                    sb.append(token.getReading());
                } else {
                    sb.append(token.getSurface());
                }
            }

            Lrc.set(size - 1, String.valueOf(sb));
            size--;
        }
    }
}
