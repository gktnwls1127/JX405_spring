package com.bit.spring.service;

import com.bit.spring.model.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BoardService {
    private final int PAGE_SIZE = 15;
    private final String NAMESPACE = "mapper.boardMapper";
    private SqlSession session;
    @Autowired
    public BoardService(SqlSession session){
        this.session = session;
    }

    //특정 페이지 출력하기
    public List<BoardDTO> selectAll(int pageNo){
        HashMap<String, Integer> params = new HashMap<>();
        params.put("start", (pageNo-1) * PAGE_SIZE);
        params.put("size", PAGE_SIZE);

        return session.selectList(NAMESPACE + ".selectAll", params);
    }

    public BoardDTO selectOne(int id){
        return session.selectOne(NAMESPACE + ".selectOne", id);
    }

    public void insert(BoardDTO boardDTO){
        session.insert(NAMESPACE + ".insert", boardDTO);
    }

    public int selectLastPage(){
        int count = session.selectOne(NAMESPACE + ".count");
        int total = count / PAGE_SIZE;
        if (count % PAGE_SIZE != 0){
            total++;
        }
        return total;
    }

    public List<BoardDTO> selectByKeyword(String keyword){
        return session.selectList(NAMESPACE + ".selectByKeyword", keyword);
    }

    /*
    public void update(BoardDTO boardDTO){
        String query = "UPDATE `board` SET `title` = ?, `content` = ? , `modify_date` = NOW() WHERE `id` = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, boardDTO.getTitle());
            preparedStatement.setString(2, boardDTO.getContent());
            preparedStatement.setInt(3, boardDTO.getId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        String query = "DELETE FROM `board` WHERE `id` = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(BoardDTO boardDTO){
        String query = "INSERT INTO `board`(`title`, `content`, `writerId`, `entry_date`, `modify_date`) VALUES (?, ?, ?, NOW(), NOW())";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, boardDTO.getTitle());
            pstmt.setString(2, boardDTO.getContent());
            pstmt.setInt(3, boardDTO.getWriterId());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/

}
