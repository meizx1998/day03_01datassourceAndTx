package com.mzx.test;

import com.mzx.dao.IUserDao;
import com.mzx.domain.QueryVo;
import com.mzx.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 测试mybatis的crud操作
 */
public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws Exception{
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取sqlsessionFactory对象
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        userDao=sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() throws Exception {

        List<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println(user);
        }

    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave() throws IOException {
        User user=new User();
        user.setUserName("modify user property");
        user.setUserAddress("东台市");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        System.out.println(user);
        userDao.saveUser(user);
        System.out.println(user);

    }

    /**
     * 测试更新操作
     * @throws IOException
     */
    @Test
    public void testUpdate() throws IOException {
        User user=new User();
        user.setUserId(50);
        user.setUserName("mybatis updateuser");
        user.setUserAddress("东台市");
        user.setUserSex("女");
        user.setUserBirthday(new Date());
        userDao.updateUser(user);

    }

    @Test
    public void testDelete() throws IOException {

        userDao.deleteUser(48);

    }
    @Test
    public void testFindOne() throws IOException {

        User user = userDao.findById(50);
        System.out.println(user);

    }
    @Test
    public void testFindByname() throws IOException {

        List<User> users = userDao.findByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testFindTotal() throws IOException {

        int count = userDao.findTotal();
        System.out.println(count);
    }

    /**
     * 测试使用queryvo作为
     * @throws IOException
     */
    @Test
    public void testFindByVo() throws IOException {

        QueryVo vo=new QueryVo();
        User user=new User();
        user.setUserName("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }
}


