package syrize.Lyrics;

import java.io.*;
import java.util.*;

public class dataWriting {
    public static String line;

    /**
     * 该方法读取歌词文件中的所有行并生成为List
     * @param path 文件路径
     * @return List
     */
    public static List<String> lyricsFileStream(String path){
        File file = new File(path);
        List<String> completeLyrics = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            while ((line = reader.readLine()) != null){
                completeLyrics.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return completeLyrics;
    }


}
