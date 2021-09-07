import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatiesTest {
    private SqlSession sqlSession;
    @Before //@Test注解之前，执行的方法，提取重复的方法
    public void before() throws Exception {
        //加载并读取xml
        String path = "SqlMapConfig.xml";
        InputStream is = Resources.getResourceAsStream(path);
        //sql  连接的工厂类
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
        System.out.println("sqlSession = " + sqlSession);//sqlSession = org.apache.ibatis.session.defaults.DefaultSqlSession@5ce81285


    }
    @Test
    public void test01(){
        Map map = new HashMap();
            map.put("c_id1","01");
            map.put("c_id2","02");
        List<Map> lists = sqlSession.selectList("com.hp.dao.StudentDao.selectByScore",map);
        for (Map list : lists) {
            System.out.println("list = " + list);
        }
        sqlSession.close();
    }

    @Test
    public void test02(){
        Map map = new HashMap();
        map.put("c_id01","01");
        map.put("c_id02","02");
        List<Map> lists = sqlSession.selectList("com.hp.dao.StudentDao.selectByScore02",map);
        for (Map list : lists) {
            System.out.println("list = " + list);
        }
        sqlSession.close();
    }

    @Test
    public void test03(){
        List<Map> maps = sqlSession.selectList("com.hp.dao.StudentDao.selectStudent",60);
        for (Map map : maps) {
            System.out.println("map = " + map);
        }
        sqlSession.close();
    }

    @Test
    public void test04(){
        List<Map> maps = sqlSession.selectList("com.hp.dao.StudentDao.selectStudent02",60);
        for (Map map : maps) {
            System.out.println("map = " + map);
        }
        sqlSession.close();
    }

    @Test
    public void test05(){
        List<Map> lists= sqlSession.selectList("com.hp.dao.StudentDao.selectCount");
        for (Map list : lists) {
            System.out.println("list = " + list);
        }
        sqlSession.close();
    }

    @Test
    public void test06(){
        int teacherList = sqlSession.selectOne("com.hp.dao.StudentDao.selectByLikeName","李");
        System.out.println("teacherList = " + teacherList);

        sqlSession.close();
    }

    @Test
    public void test07(){
        List<Map> maps = sqlSession.selectList("com.hp.dao.StudentDao.selectTByStudent","张三");
        for (Map map : maps) {
            System.out.println("map = " + map);
        }
        sqlSession.close();
    }

    @Test
    public void test08(){
        List<Map> maps = sqlSession.selectList("com.hp.dao.StudentDao.selectTByStudent02","张三");
        for (Map map : maps) {
            System.out.println("map = " + map);
        }
        sqlSession.close();
    }

    @Test
    public void test09(){
        Map map = new HashMap();
            map.put("c_id1","01");
            map.put("c_id2","02");
        List<Map> maps = sqlSession.selectList("com.hp.dao.StudentDao.studentcid", map);
        for (Map map1 : maps) {
            System.out.println("map1 = " + map1);
        }
        sqlSession.close();
    }

    @Test
    public void test10(){
        Map map = new HashMap();
        map.put("c_id01","01");
        map.put("c_id02","02");
        List<Map> maps = sqlSession.selectList("com.hp.dao.StudentDao.studentcidNo", map);
        for (Map map2 : maps) {
            System.out.println("map2 = " + map2);
        }
        sqlSession.close();
    }
}
