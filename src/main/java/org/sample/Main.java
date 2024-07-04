package org.sample;

import com.spire.pdf.graphics.PdfMargins;
import com.spire.pdf.htmlconverter.LoadHtmlType;
import com.spire.pdf.htmlconverter.qt.HtmlConverter;
import com.spire.pdf.htmlconverter.qt.Size;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class Main {

//    public static void main(String[] args) {
//        try {
//
////            String htmlString = "/home/root/convertPDF/file/aaa.html";
////            String pluginPath = "D:\\Work\\Doc\\plugins-windows-x64\\plugins";
//
////            htmlString = HtmlToString(htmlString);
//
////            String a = "Hello";
//
//            // 假设你的jar文件名为example.jar
////            ProcessBuilder pb = new ProcessBuilder("java", "-jar" , "-DhtmlString=" + htmlString, "./SpirePDF-3.0.jar");
//            ProcessBuilder pb = new ProcessBuilder("java", "-jar" , "./SpirePDF-3.0.jar");
//            Process process = pb.start();
//
//            // 读取jar文件的输出
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            // 等待jar执行完成
//            process.waitFor();
//
//            // 获取jar执行的退出值
//            int exitValue = process.exitValue();
//            System.out.println("Exit value: " + exitValue);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public static void main(String[] args)  {

        try{
            String htmlString = System.getProperty("htmlString");
            String pluginPath = System.getProperty("pluginPath");;
            //设置插件路径
            HtmlConverter.setPluginPath(pluginPath);
            // 转字节流
            OutputStream bos = new ByteArrayOutputStream();
            //将HTML字符串转换为PDF
            HtmlConverter.convert(htmlString, bos, true, 100000, new Size(700, 900), new PdfMargins(0), LoadHtmlType.Source_Code);
            System.out.println(bos);
            bos.close();
            System.exit(0);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }



    public static String HtmlToString(String filePath) throws IOException {
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String temp = "";

        while((temp = bufferedReader.readLine()) != null) {
            stringBuilder.append(temp).append("\n");
        }

        bufferedReader.close();
        return stringBuilder.toString();
    }

}