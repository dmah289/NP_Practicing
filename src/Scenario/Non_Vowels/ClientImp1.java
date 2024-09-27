package Scenario.Non_Vowels;

import java.io.*;
import java.net.*;

public class ClientImp1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 2831);   // Kết nối tới server

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String fileLine;
        while ((fileLine = in.readLine()) != null) {
            if (fileLine.equals("EOF")) {
                break;  // Kết thúc nếu nhận được tín hiệu EOF từ server
            }

            System.out.println("Received line from server: " + fileLine);

            // Xử lý: Tìm các chuỗi không chứa nguyên âm, phân tách bởi dấu phẩy
            String[] strings = fileLine.split(",");  // Phân tách chuỗi theo dấu phẩy
            StringBuilder resultBuilder = new StringBuilder();

            for (String str : strings) {
                if (!containsVowels(str.trim())) {  // Loại bỏ các chuỗi có nguyên âm
                    resultBuilder.append(str.trim()).append(",");
                }
            }

            // Loại bỏ dấu phẩy cuối cùng nếu có
            if (resultBuilder.length() > 0) {
                resultBuilder.setLength(resultBuilder.length() - 1);
            }

            // Gửi kết quả đã xử lý trở lại cho server
            out.println(resultBuilder.toString());
            System.out.println("Processed line sent to server: " + resultBuilder.toString());
        }

        // Đóng luồng và socket
        in.close();
        out.close();
        socket.close();
    }

    // Hàm kiểm tra chuỗi có chứa nguyên âm hay không
    public static boolean containsVowels(String str) {
        return str.matches(".*[aeiouAEIOU].*");  // Kiểm tra xem chuỗi có chứa nguyên âm không
    }
}

