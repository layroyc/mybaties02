import com.hp.bean.Person;
import com.hp.bean.dto.PersonDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    //全查
    @Test
    public void test01(){
        //mybaties
        List<Person> personList = sqlSession.selectList("com.hp.dao.PersonDao.selectAll");
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //单查    带参数的
    @Test
    public void test02(){
        List<Person> personList = sqlSession.selectList("com.hp.dao.PersonDao.selectPersonBySex", 2);
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //查询总条数     这个主要学习的是 返回数据类型，和上面的数据类型不一样
    @Test
    public void test03(){
        long o = sqlSession.selectOne("com.hp.dao.PersonDao.selectCount");
        System.out.println("o = " + o);
        sqlSession.close();
    }

    //查询女生总条数有多少个
    @Test
    public void test04(){
        Person person = new Person();
        person.setScore(100);
        person.setGender(2);
        long o = sqlSession.selectOne("com.hp.dao.PersonDao.selectCountByParam01", person);
        System.out.println("o = " + o);
        sqlSession.close();
    }

    //带参数的查询   第二种方式  map传参--多见于 多表联查
    //查询性别是2，且生日 小于 2020-10-14 的人有哪些
    @Test
    public void test05() throws ParseException {
        String date = "2020-10-14";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sf.parse(date);
        Map map = new HashMap();
            map.put("gender",2);    //key 要和值保持一致
            map.put("birthday",birthday);
        List<Person> lists = sqlSession.selectList("com.hp.dao.PersonDao.selectCountByParam02", map);
        for (Person person : lists) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

   //子查询
    @Test
    public void test06(){
        List<Person> lists = sqlSession.selectList("com.hp.dao.PersonDao.selectPersonByZi");
        for (Person list : lists) {
            System.out.println("list = " + list);
        }
        sqlSession.close();
    }

    //分组查询
    @Test
    public void test07(){
        List<PersonDTO> personDTOList = sqlSession.selectList("com.hp.dao.PersonDao.selectAvgScore");
        for (PersonDTO personDTO : personDTOList) {
            System.out.println("personDTO = " + personDTO);
        }
        sqlSession.close();
    }

    //带参数的分组查询
    @Test
    public void test08(){
        List<PersonDTO> personDTOList = sqlSession.selectList("com.hp.dao.PersonDao.selectAvgScoreParam",200);
        for (PersonDTO personDTO : personDTOList) {
            System.out.println("personDTO = " + personDTO);
        }
        sqlSession.close();
    }



    //  第二种方式  map分组查询   上面的不灵活
    @Test
    public void test09(){
        List<Map> maps = sqlSession.selectList("com.hp.dao.PersonDao.selectAvgScore02");
        for (Map map : maps) {
            System.out.println("map = " + map);
        }
        sqlSession.close();
    }
    //带参数的分组查询
    @Test
    public void test10(){
        List<Map> maps = sqlSession.selectList("com.hp.dao.PersonDao.selectAvgScoreParam02", 200);
        for (Map map : maps) {
            System.out.println("map = " + map);
        }
        sqlSession.close();
    }


    //模糊查询
    //第一种方式 拼接型     不建议这样写
    @Test
    public void test11(){
        Map map = new HashMap();
            map.put("name","孙");

        List<Person> personList = sqlSession.selectList("com.hp.dao.PersonDao.selectPersonByLike",map);
        //There is no getter for property named 'name'   因为 $是拼接的，没有getter这个概念
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //查询名字中所有戴孙的
    @Test
    public void test12(){
        List<Person> personList = sqlSession.selectList("com.hp.dao.PersonDao.selectPersonByLike02","孙");
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //查询名字中所有戴孙的    第三种方式 常用
    @Test
    public void test13(){
        List<Person> personList = sqlSession.selectList("com.hp.dao.PersonDao.selectPersonByLike03","孙");
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //以上是单表的所有查询！！！

    //玩  增加  insert into.....
    @Test
    public void test14(){
        Person person = new Person();
            person.setName("王源");
            person.setGender(1);
            person.setBirthday(new Date());
            person.setAddress("重庆");
            person.setScore(888);
        int insert = sqlSession.insert("com.hp.dao.PersonDao.insertPerson",person);
        System.out.println("insert = " + insert);
        sqlSession.commit();
        sqlSession.close();
    }
}
