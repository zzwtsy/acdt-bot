package cn.edu.acdt.tools;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author <a href=https://github.com/Raptor-wxw/MiariConfigHelper>Raptor-wxw</a>
 */
public class JavaConfigHelper {
    /**
     * 获取配置文件内容
     *
     * @param pathName 文件夹名
     * @param fileName 配置文件名
     * @param object   配置文件类
     * @return 配置文件内容
     * @throws IOException 获取配置文件错误
     */
    public static <T> T getConfigFromFile(String pathName, String fileName, Class<T> object) throws IOException {
        String filePath = String.format("config/%s/%s.json", pathName, fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(filePath)), StandardCharsets.UTF_8));
        StringBuilder jsonString = new StringBuilder();
        String buffer;
        while ((buffer = reader.readLine()) != null) {
            jsonString.append(buffer);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString.toString(), object);
    }

    /**
     * 将配置文件类写入到本地配置文件
     *
     * @param pathName 文件夹名
     * @param fileName 配置文件名
     * @param object   配置文件类
     * @throws IOException 配置文件内容写入错误
     */
    public static void setConfigFile(String pathName, String fileName, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        String filePath = String.format("config/%s/%s.json", pathName, fileName);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, false), StandardCharsets.UTF_8));
        bw.write(jsonString);
        bw.close();
    }

    /**
     * 生成配置文件
     *
     * @param pathName 配置文件夹名
     * @param fileName 配置文件名
     * @return 如果配置文件夹已存在返回false，不存在则创建文件夹
     */
    @SuppressWarnings("all")
    public static boolean createConfigFile(String pathName, String fileName) {
        String filePath = String.format("config/%s/%s.json", pathName, fileName);
        File file = new File(filePath);
        File dir = new File("config/" + pathName);
        if (!file.exists()) {
            try {
                return dir.mkdirs() && file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
