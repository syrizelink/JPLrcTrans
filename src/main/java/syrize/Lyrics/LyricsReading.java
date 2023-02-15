package syrize.Lyrics;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 歌词读取
 *
 * @author Syrize
 * &#064;date  2023/02/01
 */
public class LyricsReading {
    static String TYPE;

    static String regex = "\\[\\d{2}:\\d{2}\\.\\d{2}\\](?![\\u4e00-\\u9fa5|A-Za-z]+\\s*[.:：/／-]).*";

    private static final Pattern PATTERN_LYRIC = Pattern.compile(regex);

    /**
     * lrc行
     * 该方法用于建立仅含有歌词行的List
     *
     * @param path JSON文件路径
     * @return 歌词List
     * @throws IOException ioexception
     */
    private static List<String> lrcLine(String path) throws IOException {
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
     * lrc遍历
     * 该方法用于遍历歌词List将其转为其他形式
     *
     * @param Lrc  歌词List
     * @param Type 需要转换成的形式名
     * @return {@link List}<{@link String}>
     * @throws IOException ioexception
     */
    private static List<String> lrcTraversal(List<String> Lrc, String Type) throws IOException {
        List<String> replacementValue = null;
                if (Objects.equals(Type, "Romaji")) {
                    replacementValue = FileOperation.getRomajiValue(Lrc);
                } else if (Objects.equals(Type, "Homo")) {
                    replacementValue = FileOperation.getHomophonicValue(Lrc);
                }
            return replacementValue;
    }

    /**
     * 主执行区
     *
     * @param args args
     * @throws IOException ioexception
     */
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("输入文件绝对路径 或 拖放文件至此处");
        String lrcPath = scan.nextLine();

        List<String> lrcLineStream = lrcLine(lrcPath);
        KanaConversion.literator(lrcLineStream);

        char c;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请选择你要转换的类, 输入对应数字键以应用选择:");
        System.out.println("1.  转为⌈罗马字⌋格式");
        System.out.println("2.  转为⌈谐音⌋格式");
        c = (char) bufferedReader.read();

        while (c != '1' && c != '2'){
            System.out.println("错误的选项, 请重新输入");
            c = (char) bufferedReader.read();
        }

        if (c == '1') {
            TYPE = "Romaji";
        } else {
            TYPE = "Homo";
        }

        List<String> Lrc = lrcTraversal(lrcLineStream, TYPE);
        DataWriting.writeFile(lrcPath, Lrc);
    }
}
