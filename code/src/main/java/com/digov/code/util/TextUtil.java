package com.digov.code.util;

import java.io.*;

/**
 * 文本工具类
 * 2021年4月29日17:45:21
 * @author yuyongxing
 */
public class TextUtil {


    /**
     * 获取文本中的字符串
     * 默认编码：UTF-8
     *
     * @param pathname
     * @param charsetName
     */
    public static String getOriginTextByFile(String pathname, String charsetName) {
        if (CommonUtil.isEmpty(pathname)) {
            return null;
        }
        if (CommonUtil.isEmpty(charsetName)) {
            charsetName = "utf-8";
        }
        String textStr = "";
        File file = new File(pathname);
        if (file.exists() && file.isFile()) {
            try {
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file), charsetName);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    textStr += lineTxt + "\n";
                }
                bufferedReader.close();
                reader.close();
                return textStr;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将字符串写到指定文件中
     * over(true:不覆盖原来的,false:覆盖原来的内容)
     *
     * @param file
     * @param txt
     * @param over
     */
    public static void writeStrToFile(String file, String txt, boolean over) {
        try {
            FileOutputStream os = new FileOutputStream(new File(file), over);
            os.write((txt + "\n").getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
