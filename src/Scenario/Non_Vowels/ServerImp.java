package Scenario.Non_Vowels;

import java.io.*;
import java.net.*;

public class ServerImp {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2831);   // Tạo ServerSocket trên port 2831
        System.out.println("Server is listening on port 2831...");

        while (true) {
            Socket client = server.accept();    // Chấp nhận kết nối từ client
            BufferedReader fileReader = new BufferedReader(new FileReader("server_data.txt"));  // Mở file cần đọc
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // Đọc toàn bộ nội dung file và gửi cho client
            String fileData;
            StringBuilder dataBuilder = new StringBuilder();
            while ((fileData = fileReader.readLine()) != null) {
                dataBuilder.append(fileData);
            }

            // Gửi dữ liệu file tới client
            out.println(dataBuilder.toString());
            System.out.println("Data sent to client for processing.");

            // Nhận kết quả xử lý từ client
            String result = in.readLine();
            System.out.println("Processed data from client: " + result);

            // Đóng luồng và socket
            fileReader.close();
            client.close();
        }
    }
}

