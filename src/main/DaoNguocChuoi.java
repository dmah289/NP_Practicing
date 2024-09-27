package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DaoNguocChuoi {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("172.188.19.218", 1605);
        DataInputStream in = new DataInputStream(client.getInputStream());
        DataOutputStream out = new DataOutputStream(client.getOutputStream());

        out.writeUTF("B21DCCN642;NBKOOtA");
        out.flush();

        String s = in.readUTF();
        String res = new StringBuilder(s).reverse().toString();

        out.writeUTF(res);
        out.flush();

        in.close();
        out.close();
        client.close();
    }
}
