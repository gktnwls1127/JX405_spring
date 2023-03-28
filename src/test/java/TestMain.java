
public class TestMain {

    // 메소드 단위로 테스트 진행
    public static Object testNotNull(String address){
        if (address.equals("ok")){
            return new Object();
        }
        return null;
    }
}
