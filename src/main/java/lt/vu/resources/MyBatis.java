package lt.vu.resources;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.cdi.SessionFactoryProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;

@ApplicationScoped
public class MyBatis {

    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    private SqlSessionFactory sqlSessionFactory() {
        try {
            return new SqlSessionFactoryBuilder().build(
                    Resources.getResourceAsStream("BatisConfig.xml")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
