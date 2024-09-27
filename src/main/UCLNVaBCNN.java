package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class UCLNVaBCNN {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("172.188.19.218", 1605);

        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF("B21DCCN055;HSksuEJ");
        dos.flush();

        DataInputStream dis = new DataInputStream(client.getInputStream());
        int a = dis.readInt();
        int b = dis.readInt();
        System.out.println("Received: a = " + a + ", b = " + b);

        int res1 = gcd(a, b);
        int res2 = (a * b) / res1;

        dos.writeInt(res1);
        dos.writeInt(res2);
        dos.flush();


        dos.close();
        dis.close();
        client.close();
    }

    private static int gcd(int a, int b) {
        while(a * b != 0){
            if(a > b) a %= b;
            else b %= a;
        }
        return a + b;
    }
}
