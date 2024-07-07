package org.sample;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.graphics.PdfMargins;
import com.spire.pdf.htmlconverter.LoadHtmlType;
import com.spire.pdf.htmlconverter.qt.HtmlConverter;
import com.spire.pdf.htmlconverter.qt.Size;
import com.spire.pdf.license.LicenseProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class Main {


    public static void main(String[] args) {
        try {

            String pluginPath = "/Users/majiaju/Work/SPD/Tools/plugins_mac";
            String htmlString = "/Users/majiaju/Work/SPD/PDF_File/aaa.html";
            htmlString = HtmlToString(htmlString);

            String jarPath = "/Users/majiaju/Project/Learn/SpirePDF/target/SpirePDF-1.0.jar";


            // 假设你的jar文件名为example.jar
            ProcessBuilder pb = new ProcessBuilder(
                    "java", "-jar" ,
                    "-DhtmlString=" + htmlString,
                    "-DpluginPath=" + pluginPath,
                    jarPath);
//            ProcessBuilder pb = new ProcessBuilder("java", "-jar" , jarPath);
            Process process = pb.start();

            // 读取jar文件的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();

            PdfDocument pdf = new PdfDocument();
            pdf.loadFromStream(new ByteArrayInputStream(stringBuilder.toString().getBytes(StandardCharsets.UTF_8)));
            System.out.println(pdf);

            // 等待jar执行完成
            process.waitFor();
            // 获取jar执行的退出值
            int exitValue = process.exitValue();
            System.out.println("Exit value: " + exitValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




//    public static void main(String[] args)  {
//
//        try{
//            LicenseProvider.setLicenseKey("key");
//
//            String htmlString = System.getProperty("htmlString");
//            String pluginPath = System.getProperty("pluginPath");
//
//            //设置插件路径
//            HtmlConverter.setPluginPath(pluginPath);
//            // 转字节流
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            //将HTML字符串转换为PDF
//            HtmlConverter.convert(htmlString, baos, true, 100000, new Size(700, 900), new PdfMargins(0), LoadHtmlType.Source_Code);
//            String outputString = baos.toString(StandardCharsets.UTF_8.name());
//            baos.close();
//            System.out.println(outputString);
//            System.exit(0);
//        }catch (IOException e){
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//            System.exit(1);
//        }
//
//    }




    public static String HtmlToString(String filePath) throws IOException {
        String encoding = "UTF-8";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(filePath)), encoding));

        StringBuilder stringBuilder = new StringBuilder();
        String temp = "";

        while((temp = bufferedReader.readLine()) != null) {
            stringBuilder.append(temp).append("\n");
        }

        bufferedReader.close();
        return stringBuilder.toString();
    }

}