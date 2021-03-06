package socket.java_socket_demo;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            //读取
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String info = null;
            while ((info = br.readLine()) != null){
                System.out.println("我是服务器，客户端说："+info);
            }
            socket.shutdownInput();
            //写出
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write("服务器欢迎你");
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭资源
                if (pw != null){
                    pw.close();
                }
                if (os != null){
                    os.close();
                }
                if (br != null){
                    br.close();
                }
                if (isr != null){
                    isr.close();
                }
                if (is != null){
                    is.close();
                }
                if (socket != null){
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
