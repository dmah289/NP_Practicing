package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TongCacSo {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("172.188.19.218", 1604);

        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();

        out.write("B21DCCN511;bxtVUJz".getBytes());
        out.flush();

        byte[] data = new byte[1024];
        int bytesRead = in.read(data);
        String s = new String(data, 0, bytesRead);

        String[] tmp = s.trim().split("\\|");
        int sum = 0;
        for(String x : tmp) sum += Integer.parseInt(x);
        String res = String.valueOf(sum);

        out.write(res.getBytes());
        out.flush();

        in.close();
        out.close();
        client.close();
    }
}
