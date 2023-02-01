package syrize.Lyrics;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Syrize
 */
public class LyricsReading {
    static String TYPE_R = "Romaji";
    static String TYPE_H = "Homo";

    static String regex = "\\[\\d{2}:\\d{2}\\.\\d{2}\\](?![\\u4e00-\\u9fa5|A-Za-z]+\\s*[.:：/／-]).*";

    private static final Pattern PATTERN_LYRIC = Pattern.compile(regex);


    public static List<String> lrcLine(String path) throws IOException {
        File lrc = new File(path);
        List<String> lineTraversal = new ArrayList<>();

        BufferedReader reader = null;

        {
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(lrc), "GBK"));
                String line;


                while ((line = reader.readLine()) != null){
                    Matcher matcher = PATTERN_LYRIC.matcher(line);
                    if (matcher.find()) {
                            lineTraversal.add(line);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                if (reader != null){
                    reader.close();
                }
            }
        }
        return lineTraversal;
    }

    /**
     * 该方法用于遍历歌词List将其转为其他形式
     * @param
     * @throws IOException
     */
    public static List<String> lrcTraversal(List<String> Lrc, String Type) throws IOException {
        List<String> replacementValue = null;
                if (Objects.equals(Type, TYPE_R)) {
                    replacementValue = FileOperation.getRomajiValue(Lrc);
                } else if (Objects.equals(Type, TYPE_H)) {
                }
            return replacementValue;
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("输入文件绝对路径 或 拖放文件至此处");
        String lrcPath = scan.nextLine();
        List<String> lrcLineStream = lrcLine(lrcPath);
        KanaConversion.literator(lrcLineStream);
        List<String> Lrc = lrcTraversal(lrcLineStream, TYPE_R);
        System.out.println(Lrc.get(15));
    }
}
