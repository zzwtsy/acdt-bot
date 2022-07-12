package com.acdt.tools;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JavaConfigHelper {
    public static <T> T getConfigFromFile(String fileName, Class<T> object) throws IOException {
        String filePath = String.format("config/%s/%s.json", fileName, fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(filePath)), StandardCharsets.UTF_8));
        StringBuilder jsonString = new StringBuilder();
        String buffer;
        while ((buffer = reader.readLine()) != null) {
            jsonString.append(buffer);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString.toString(), object);
    }

    public static void setConfigFile(String fileName, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        String filePath = String.format("config/%s/%s.json", fileName, fileName);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, false), StandardCharsets.UTF_8));
        bw.write(jsonString);
        bw.close();
    }

    @SuppressWarnings("all")
    public static boolean createConfigFile(String fileName) {
        String filePath = String.format("config/%s/%s.json", fileName, fileName);
        File file = new File(filePath);
        File dir = new File("config/" + fileName);
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
