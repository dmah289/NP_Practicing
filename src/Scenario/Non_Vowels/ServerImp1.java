package Scenario.Non_Vowels;

import java.io.*;
import java.net.*;

public class ServerImp1 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2831);   // Tạo ServerSocket trên port 2831
        System.out.println("Server is listening on port 2831...");

        while (true) {
            Socket client = server.accept();    // Chấp nhận kết nối từ client

            BufferedReader fileReader = new BufferedReader(new FileReader("server_data.txt"));  // Mở file cần đọc
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String fileLine;
            while ((fileLine = fileReader.readLine()) != null) {
                // Gửi từng dòng cho client
                out.println(fileLine);
                System.out.println("Sent line to client: " + fileLine);

                // Nhận kết quả từ client sau khi client xử lý dòng
                String processedLine = in.readLine();
                System.out.println("Processed line received from client: " + processedLine);
            }

            // Gửi tín hiệu kết thúc (EOF) cho client
            out.println("EOF");

            // Đóng luồng và socket
            fileReader.close();
            client.close();
        }
    }
}

