package syrize.Lyrics;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LyricsReading {
    public static List<String> lrcLine(String path) throws IOException {
        File lrc = new File(path);
        List<String> lineTraversal = new ArrayList<>();

        BufferedReader reader = null;

        {
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(lrc), "GBK"));
                String line;

                Pattern pattern = Pattern.compile("\\[\\d{2}:\\d{2}\\.\\d{2}\\](?![\\u4e00-\\u9fa5|A-Za-z]+\\s*[.:：/／-]).*");

                while ((line = reader.readLine()) != null){
                    Matcher matcher = pattern.matcher(line);
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
     * @param Lrc
     * @throws IOException
     */
    public static void lrcTraversal(List<String> Lrc, String Type) throws IOException {
        int size = Lrc.size();
        String x;
        while (size > 0){
            x = Lrc.get(size - 1);
            StringBuffer sb = new StringBuffer();
            char[] chars = x.toCharArray();
            for (char i:chars){
                if (Objects.equals(Type, "Romaji")){
                    String replacementValue = FileOperation.getRomajiValue(String.valueOf(i));
                    sb.append(replacementValue);
                }else if (Objects.equals(Type, "Homo")){
                    String replacementValue = FileOperation.getHomophonicValue(String.valueOf(i));
                    sb.append(replacementValue);
                }
            }
            Lrc.set(size - 1, String.valueOf(sb));
            size--;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("输入文件绝对路径 或 拖放文件至此处");
        String lrcPath = scan.nextLine();
        List<String> lrcLineStream = lrcLine(lrcPath);
        KanaConversion.literator(lrcLineStream);
        lrcTraversal(lrcLineStream, "Romaji");
        System.out.println(lrcLineStream.get(15));
    }

}
