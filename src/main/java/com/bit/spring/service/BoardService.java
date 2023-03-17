package com.bit.spring.service;

import com.bit.spring.model.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> selectByKeyword(String keyword, int pageNo){
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("start", (pageNo-1) * PAGE_SIZE);
        params.put("size", PAGE_SIZE);
        params.put("keyword", keyword);

        result.put("list", session.selectList(NAMESPACE + ".selectByKeyword", params));
        result.put("totalPage", countSearchResult(keyword));
        return result;
    }

    public int countSearchResult(String keyword){
        int temp = session.selectOne(NAMESPACE + ".countSearchResult", keyword);
        int totalPage = temp / PAGE_SIZE;
        if (temp % PAGE_SIZE != 0){
            totalPage++;
        }
        return totalPage;
    }


    public void update(BoardDTO attempt){
        session.update(NAMESPACE + ".update", attempt);
    }

    public void delete(int id){
        session.delete(NAMESPACE+".delete", id);
    }

}
