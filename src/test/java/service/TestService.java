package service;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public boolean isTrue(String answer){
        return answer.equalsIgnoreCase("yes");
    }

    public Object testNull(Object object){
        return object == null;
    }
}
