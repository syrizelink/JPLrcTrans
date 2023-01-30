package syrize.Lyrics;


import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;

import java.util.List;
import java.util.regex.Pattern;

public class KanaConversion {
    /**
     *
     * @param Lrc
     */
    public static void literator(List<String> Lrc){

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
