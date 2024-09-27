package main;

import java.io.*;
import java.net.Socket;

public class LoaiBoNguyenAm {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("172.188.19.218", 1606);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        out.write("B21DCCN511;OY45Tdc"); out.newLine();
        out.flush();

        String s = in.readLine();
        String res = s.replaceAll("[ueoaiUEOAI]", "");

        out.write(res); out.newLine();
        out.flush();

        in.close();
        out.close();
        client.close();
    }
}
