package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LuyThua {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("172.188.19.218", 1604);
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();

        out.write("B21DCCN768;Yi0enkF".getBytes());
        out.flush();

        byte[] data = new byte[1024];
        int bytesRead = in.read(data);
        String s = new String(data, 0, bytesRead);

        String[] nums = s.trim().split("\\|");
        int num1 = Integer.parseInt(nums[0]), num2 = Integer.parseInt(nums[1]);
        int res = (int)Math.pow(num1, num2);

        String res1 = String.valueOf(res);
        out.write(res1.getBytes());

        in.close();
        out.close();
        client.close();
    }
}
