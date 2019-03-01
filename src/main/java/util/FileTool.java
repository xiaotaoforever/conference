package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileTool {


    /**
     * 按行读取文件
     * @param fileName
     * @return
     */
    public static List<String> readFile(String fileName){
        /*
        另外还有一种比较常见的形式
        ClassName.class.getClassLoader().getResourceAsStream("config.properties")，
        在这个时候使用Class.getClassLoader().getResource()和Class.getClassLoader().getResourceAsStream()
        在路径选择上是一样的，只不过两个方法返回的对象不同，
        前者返回URL对象，后者返回InputStream对象。

        在使用ClassName.class.getClassLoader().getResourceAsStream("config.properties")
        的时候千万不要写成ClassName.class.getClass().getClassLoader().getResourceAsStream("config.properties")，
        后者会始终抛NullPointerException，
        因为class.getClass().getClassLoader().getResourceAsStream(...)
        使用的是系统类加载器，而你的Jar对系统类加载器是不可见的。
         */
        InputStream is=FileTool.class.getClassLoader().getResourceAsStream(fileName);
        List<String> content = new ArrayList<>();
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) !=null) {
                if (line.isEmpty()) {
                    continue;
                }
                content.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
