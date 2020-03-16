import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("all")
public class MyIO {
    public static void main(String[] args) throws Exception {
        //new一个File对象
        File file = new File("D:"+File.separator+"MyIO_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".txt");
        //判断文件是否存在
        boolean exists = file.exists();
        //如果文件不存在创建一个新的文件
        if(!exists){
            file.createNewFile();
        }
        //new一个文件输出流对象、stream对象转writer对象、writer缓存对象
        OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        //往流里写入数据
        bufferedWriter.write("hello, this is MyIO.java program.");
        //flush流里数据
        bufferedWriter.flush();
        outputStreamWriter.flush();
        outputStream.flush();
        //关闭流
        bufferedWriter.close();
        outputStreamWriter.close();
        outputStream.close();

        //new一个输入流、stream对象转reader对象、reader缓存对象
        InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //new一个StringBuilder对象
        StringBuilder stringBuilder = new StringBuilder();
        String s = "";
        //从流读出数据并append到builder对象中
        while ((s=bufferedReader.readLine())!=null){
            stringBuilder.append(s);
        }
        //打印builder
        System.out.println(stringBuilder.toString());
        //关闭输入流
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();

        /**
         * 1.bio
         * 2.nio
         * 3.aio
         * 4.net
         * 5.netty
         * */

        /**
         * 1.gzip
         * 2.zip
         * */
    }
}
