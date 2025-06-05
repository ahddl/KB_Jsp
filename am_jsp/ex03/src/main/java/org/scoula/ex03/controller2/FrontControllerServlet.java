package org.scoula.ex03.controller2;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.scoula.ex03.command.Command;
import org.scoula.ex03.controller.HomeController;
import org.scoula.ex03.controller.TodoController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class FrontControllerServlet extends HttpServlet {

    //해쉬맵 테이블을 만들어 주소와 메서드가 구현된 익명 클래스를 넣는다.
    //get 요청, post 요청 테이블 따로 2개 필요함

    //핸들러
    Map<String, Command> getMap;
    Map<String, Command> postMap;

    //뷰리졸버에서 사용할 접두어/접미사 설정
    String prefix = "/WEB_INF/views/";
    String suffix = ".jsp";

    HomeController homeController = new HomeController();
    TodoController todoController = new TodoController();

    FrontControllerServlet(){
        System.out.println("프론트 컨트롤 생성됨");
    }

    @Override
    public void init() {
        getMap = new HashMap<>();
        postMap = new HashMap<>();

        // GET 요청에 대한 경로와 처리 메서드 등록 (메서드 참조 사용)
        getMap.put("/", homeController::getIndex);

        getMap.put("/", new Command(){
            public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
                return homeController.getIndex(request, response);
            }
        });

        getMap.put("/todo/list", todoController::getList);
        getMap.put("/todo/view", todoController::getView);
        getMap.put("/todo/create", todoController::getCreate);
        getMap.put("/todo/update", todoController::getUpdate);

        // POST 요청에 대한 경로와 처리 메서드 등록
        postMap.put("/todo/create", todoController::postCreate);
        postMap.put("/todo/update", todoController::postUpdate);
        postMap.put("/todo/delete", todoController::postDelete);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //post 요청도 get과 동일하게 처리할 예정임
        doGet(req, resp);
    }

    //주소 추출함수
    // 전체 URI에서 ContextPath 이후의 경로만 추출
    private String getCommandName(HttpServletRequest req) {
        String requestURI = req.getRequestURI();     // 예: /myapp/todo/list
        String contextPath = req.getContextPath();   // 예: /myapp
        return requestURI.substring(contextPath.length()); // 예: /todo/list
    }

    //주소 추출한 것을 받아서 hashmap에서 어떤 컨트롤러의 메서드인지 알아오는 함수

    //
}
