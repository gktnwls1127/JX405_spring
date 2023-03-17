package com.bit.spring.service;

import com.bit.spring.model.UserCustomDetails;
import com.bit.spring.model.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final String NAMESPACE = "mapper.UserMapper";
   private SqlSession session;
   private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(SqlSession session, BCryptPasswordEncoder passwordEncoder){
        this.session = session;
        this.passwordEncoder = passwordEncoder;

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

    public void delete(int id){
        session.delete(NAMESPACE + ".delete", id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDTO user = session.selectOne(NAMESPACE + ".validate", s);
        if (user != null){ //해당 회원을 찾음
            return new UserCustomDetails(user);

        }
        return null;
    }

    public List<UserDTO> selectAll(){
        return session.selectList(NAMESPACE + ".selectAll");
    }

    public void update(UserDTO userDTO){
        session.update(NAMESPACE + ".update", userDTO);
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
