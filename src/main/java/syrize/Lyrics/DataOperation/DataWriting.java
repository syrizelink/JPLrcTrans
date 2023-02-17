package syrize.Lyrics.DataOperation;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DataWriting {
    static String line;

    /**
     * 该方法读取歌词文件中的所有行并生成为List
     * @param path 文件路径
     * @return List
     */
    private static List<String> lyricsFileStream(String path) {
        File file = new File(path);
        List<String> completeLyrics = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"))) {
            while ((line = reader.readLine()) != null) {
                completeLyrics.add(line);
            }
        } catch (IOException e) {
            try {
                System.out.println("读取歌词出错, 按任意键退出...");
                System.in.read();
            } catch (IOException ignored) {}
            System.exit(1);
            throw new RuntimeException(e);
        }

        return completeLyrics;
    }

    /**
     * 该方法用于合并两个List以生成完整的歌词List
     * @param fullList 原歌词List
     * @param correctList 变更后的歌词List
     * @return 完整的修改后List
     */
    private static List<String> listMerge(List<String> fullList, List<String> correctList){
        for (String strCorrect : correctList){
            for (int x = 0; x < fullList.size(); x++){
                if (fullList.get(x).startsWith(strCorrect.substring(0, 9))){
                    fullList.set(x, strCorrect);
                }
            }
        }
        return fullList;
    }

    /**
     * 该方法用于在源文件目录下创建一个相同的新文件
     * @param path 源文件路径
     */
    private static File createFile(String path) {
        File copyFile;
        try {
            File file = new File(path);
            copyFile = new File(file.getParentFile().getCanonicalPath() + File.separator + "New_" + file.getCanonicalFile().getName());
            if (!copyFile.createNewFile()) {
                System.out.println("已存在同名文件, 无法创建, 请删除同名文件后重试");
                try {
                    System.out.println("按任意键退出...");
                    System.in.read();
                } catch (IOException ignored) {}
                System.exit(1);
            }
        } catch (IOException e) {
            try {
                System.out.println("创建文件出错, 按任意键退出...");
                System.in.read();
            } catch (IOException ignored) {}
            System.exit(1);
            throw new RuntimeException(e);
        }
        return copyFile;
    }

    public static void writeFile(String path, List<String> correctList) {
        List<String> fullList = lyricsFileStream(path);
        File file = createFile(path);
        List<String> Lyrics = listMerge(fullList, correctList);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getCanonicalFile()), StandardCharsets.UTF_8))) {
            for (String line : Lyrics) {
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            try {
                System.out.println("写入文件出错, 按任意键退出...");
                System.in.read();
            } catch (IOException ignored) {}
            System.exit(1);
            throw new RuntimeException(e);
        }
    }

}
