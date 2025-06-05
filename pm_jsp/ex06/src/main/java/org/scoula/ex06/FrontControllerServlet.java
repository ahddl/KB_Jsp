package org.scoula.ex06;

import org.scoula.ex06.command.Command;
import org.scoula.ex06.controller.HomeController;
import org.scoula.ex06.controller.TodoController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServlet", value = "/")
public class FrontControllerServlet extends HttpServlet {
    // URL과 Command 매핑을 저장하는 Map
    Map<String, Command> getMap;
    Map<String, Command> postMap;

    HomeController homeController = new HomeController();
    TodoController todoController = new TodoController();

    // View Resolver 설정
    // - forward 요청 시 JSP 경로를 나타낼 접두사/접미사
    String prefix = "/WEB-INF/views/";
    String suffix = ".jsp";

    // 서블릿 객체 생성 시 실행되는 init() 메서드
    @Override
    public void init() {
        getMap = new HashMap<>();
        postMap = new HashMap<>();

        // 🏠 메인 페이지 요청
        getMap.put("/", homeController::getIndex);

        // GET 매핑
        getMap.put("/", homeController::getIndex);
        getMap.put("/todo/list", todoController::getList);
        getMap.put("/todo/view", todoController::getView);
        getMap.put("/todo/create", todoController::getCreate);
        getMap.put("/todo/update", todoController::getUpdate);

        // POST 매핑
        postMap.put("/todo/create", todoController::postCreate);
        postMap.put("/todo/update", todoController::postUpdate);
        postMap.put("/todo/delete", todoController::postDelete);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Command command = getCommand(req);
        if (command != null) {
            execute(command, req, resp);
        } else { // 404 에러 처리
            String view = prefix + "404" + suffix;
            RequestDispatcher dis = req.getRequestDispatcher(view);
            dis.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);  // GET과 동일한 처리 로직 사용
    }

    private String getCommandName(HttpServletRequest req) {
        // http://localhost:8080 GET
        String requestURI = req.getRequestURI(); // 전체 URI 반환

        //localhost:8080/contextpath/member을 잘라준다.
        // /contextpath
        String contextPath = req.getContextPath(); // context 경로 반환
        // /member
        return requestURI.substring(contextPath.length());
    }

    private Command getCommand(HttpServletRequest req) {
        // / 이거 하나 들어있음
        String commandName = getCommandName(req);
        Command command;

        // get
        if (req.getMethod().equalsIgnoreCase("GET")) {
            // homeController::getIndex -> "index"
            command = getMap.get(commandName);
        } else {
            command = postMap.get(commandName);
        }
        return command;
    }


    //매개 변수로  받은 command를 사용자에게서 받은 req와  resp를 담아 호출
    public void execute(Command command, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        // Command를 실행하여 View 이름 얻어오기
        String viewName = command.execute(req, resp);

        // 얻어온 View 이름이 "redirect:"로 시작하는 경우 Redirect
        if (viewName.startsWith("redirect:")) { // redirect 처리
            resp.sendRedirect(viewName.substring("redirect:".length()));
        }

        // 나머지 경우는 접두사/접미사를 붙여 JSP로 Forward
        else { // forward 처리
            // "/WEB-INF/views/" + "index" + ".jsp"
            //"/WEB-INF/views/index.jsp"
            String view = prefix + viewName + suffix;
            RequestDispatcher dis = req.getRequestDispatcher(view);
            dis.forward(req, resp);
        }
    }
}