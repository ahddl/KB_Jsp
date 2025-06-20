package org.scoula.sample.service;

public interface SampleService {
    //두 문자열을 정수로 변환하여 덧셈 연산 수행
    /*
    @param str1 첫번째 숫자 문자열
    @param str2 두번째 숫자 문자열
    @retunr 덧셈결과
    @throws Exception 숫자 변환 실패 시 예외 발생
     */
    public Integer doAdd(String str1, String str2) throws Exception;
}