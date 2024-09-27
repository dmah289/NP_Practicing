package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TongHieuTich {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("172.188.19.218",1605);
        DataInputStream in = new DataInputStream(client.getInputStream());
        DataOutputStream out = new DataOutputStream(client.getOutputStream());

        out.writeUTF("B21DCCN707;V7d3chd");
        out.flush();

        int a = in.readInt();
        int b = in.readInt();

        int sum = a + b;
        int sub = a - b;
        int mul = a * b;

        out.writeInt(sum);
        out.writeInt(sub);
        out.writeInt(mul);
        out.flush();

        in.close();
        out.close();
        client.close();
    }
}
