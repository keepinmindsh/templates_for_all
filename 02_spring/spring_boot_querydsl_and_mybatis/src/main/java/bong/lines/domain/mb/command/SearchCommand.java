package bong.lines.domain.mb.command;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchCommand {


    private final SqlSessionTemplate sqlSessionTemplate;

    public Object getValue(){
        return sqlSessionTemplate.selectOne("sample.selectBlog");
    }
}
