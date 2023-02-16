package syrize.Lyrics.DataOperation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import syrize.Lyrics.Util.colorFont;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 数据读取
 *
 * @author Syrize
 * &#064;date  2023/02/01
 */
public class DataReading {
    /**
     * json读取
     *
     * @param JsonName  Json文件名
     * @param KeyName   Json文件主Key值
     * @param ValueName Json文件获取的Value值
     * @return 返回一个选定Value集合的List
     * @throws IOException ioexception
     */
    protected static List<String> mainJsonReading(String JsonName, String KeyName, String ValueName) throws IOException {
        JsonName += ".json";
        try (InputStream inputStream = DataReading.class.getResourceAsStream("/" + JsonName)) {
            Gson gson = new Gson();
            List<String> list = new ArrayList<>();
            JsonObject jsonObject = gson.fromJson(new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8), JsonObject.class);
            JsonArray array = jsonObject.get(KeyName).getAsJsonArray();

            for (int i = 1; i < array.size(); i++) {
                JsonObject item = array.get(i).getAsJsonObject();
                String mainValue = item.get(ValueName).getAsString();
                list.add(mainValue);
            }
            return list;
        } catch (JsonSyntaxException e) {
            try {
                System.out.println(colorFont.RED + "读取Json数据出错" + colorFont.RESET + ", 按任意键退出...");
                System.in.read();
            } catch (IOException ignored) {}
            System.exit(1);
            throw new RuntimeException(e);
        }
    }
}
