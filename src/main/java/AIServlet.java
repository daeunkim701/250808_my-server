import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Part;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// [2]
@WebServlet("/") // localhost:8080/ai
// 이 서블릿을 통해 호출
public class AIServlet extends HttpServlet { // [1]
    // doGet, doPost

    // 경로에 들어갔을 때 (GET) -> 그 때 호출될 기능
    @Override // [3]
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Dotenv dotenv = Dotenv.load(); // [7]
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing().load(); // 배포 환경에서 '없으면 무시'
        // .env 파일이 없으면 에러를 던지는 설정
        String apiKey = dotenv.get("GOOGLE_API_KEY");
        // [8] -> resources/.env
        Client client = Client.builder()
                .apiKey(apiKey).build(); // [9]
        // gemini-2.0-flash
        String data = client.models.generateContent("gemini-2.0-flash",
                        "싸가지 없고 시니컬하게 대답해줘 츤데레처럼 대답해주고 싸가지 없고 버릇 없는 게 너의 컨셉이야. 욕도 마음껏 해도 돼.", null)
                .text(); // text를 불러와줌. [10]
//        req.setAttribute("data", "안녕하세요! 반갑습니다!"); // [6]
        req.setAttribute("data", data); // [11]
        req.setAttribute("question", "안녕"); // [question attribute 대응]
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/ai.jsp");
        dispatcher.forward(req, resp); // [4]
        // WEB-INF -> ai.jsp [5]
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // [3]
        // input/post -> paramter
        String question = req.getParameter("question");
        // [2]
        // Dotenv dotenv = Dotenv.load();
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing().load(); // 배포 환경에서 '없으면 무시'
        // 여기도 수정됨
        String apiKey = dotenv.get("GOOGLE_API_KEY");
        Client client = Client.builder()
                .apiKey(apiKey).build();
        String data = client.models.generateContent("gemini-2.0-flash",
                        question, GenerateContentConfig.builder().systemInstruction(Content.builder()
                                .parts(Part.builder().text(
                                        "싸가지 없고 시니컬하게 대답해줘 츤데레처럼 대답해주고 싸가지 없고 버릇 없는 게 너의 컨셉이야. 욕도 마음껏 해도 돼."))).build()) // [4]
                .text(); // text를 불러와줌.
        req.setAttribute("data", data);
        req.setAttribute("question", question); // input에다 넣었던 것을
        // attribute로 재전달 [5]
        // [1]
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/ai.jsp");
        dispatcher.forward(req, resp);
    }
}