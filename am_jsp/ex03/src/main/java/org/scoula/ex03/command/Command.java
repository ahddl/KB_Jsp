package org.scoula.ex03.command;

import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import sun.security.mscapi.CPublicKey;

import javax.servlet.http.HttpServletRequest;

public class Command {
    public abstract String execute(HttpServletRequest request, HttpServletRequest request);

    //인터페이스를 부품(객체로 생성)으로 사용 불가능
    //인터페이스를 클래스로 구현한 다음 객체로 생성할 수 있음
    //create 요청 들어오면 execute()안에 해당 해쉬 테이블에서 찾은
    // 컨트롤러의 메서드를 호출하도록 추상 메서드를 구현한 클래스를 만들어사용함
//    new Command({
//        CPublicKey String execute(req, resp){
//            return memberController.create();
//        }
//    })
}
