package com.bit.spring.service;

import com.bit.spring.model.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final String NAMESPACE = "mapper.UserMapper";
   private SqlSession session;
    @Autowired
    public UserService(SqlSession session){
        this.session = session;

    }

    public UserDTO auth(UserDTO attempt){
        return session.selectOne(NAMESPACE + ".auth", attempt); // UserMapper 안에 아이디가 auth 사용하기 .auth
    }

    public boolean validate(String username){
        return session.selectOne(NAMESPACE + ".validate", username) == null;
    }

    public boolean register(UserDTO attempt){

        if (validate(attempt.getUsername())){
            session.selectOne(NAMESPACE + ".register", attempt);
            return true;
        } else {
            return false;
        }

    }

    public void update(UserDTO attempt){
        session.update(NAMESPACE + ".update", attempt);
    }

    public void delete(int id){
        session.delete(NAMESPACE + ".delete", id);
    }

    /*

    public UserDTO selectOne(int id){
        UserDTO u = null;
        String query = "SELECT * FROM `user` WHERE `id` = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()){
                u = new UserDTO();
                u.setId(resultSet.getInt("id"));
                u.setUsername(resultSet.getString("username"));
                u.setPassword(resultSet.getString("password"));
                u.setNickname(resultSet.getString("nickname"));
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

*/
}
