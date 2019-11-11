package com.bjsxt.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bjsxt.dao.UsersDao;
import com.bjsxt.pojo.Users;

/**
 * @RunWith(SpringJUnit4ClassRunner.class):运行一个类完成Junit和Spring的整合
 * @ContextConfiguration("classpath:applicationContext.xml")：指定当前配置文件的位置
 * 以及Spring配置文件名称
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UsersDaoImplTest {

	@Autowired
	private UsersDao usersDao;
	
	
	/**
	 * 添加用户
	 */
	@Test
	@Transactional// 在测试类对于事务提交方式默认的是回滚。（添加进去后又被回滚了）
	@Rollback(false)//取消自动回滚
	public void testInsertUsers(){
		Users users = new Users();
		users.setUserage(20);
		users.setUsername("张三");
		this.usersDao.insertUsers(users);
	}
	
	/**
	 * 更新用户
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testUpdateUsers(){
		Users users = new Users();
		users.setUserid(1); //根据ID更新
		users.setUserage(22);
		users.setUsername("李四");
		this.usersDao.updateUsers(users);
	}
	
	/**
	 * 根据userid查询用户
	 */
	@Test
	public void testSelectUsersById(){
		Users users = this.usersDao.selectUsersById(1);
		System.out.println(users);
	}
	
	/**
	 * 删除用户
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testDeleteUsers(){
		Users users = new Users();
		users.setUserid(1); //根据ID删除
		this.usersDao.deleteUsers(users);
	}
	
	/**
	 * HQL测试
	 */
	@Test
	@Transactional
	public void testSelectUserByName(){
		List<Users> list = this.usersDao.selectUserByName("张三");
		for (Users users : list) {
			System.out.println(users);
		}
	}

	/**
	 * SQL测试，通过自定义SQL语句查询（不是自动生成的SQL语句）
	 */
	@Test
	@Transactional
	public void testSelectUserByNameUseSQL(){
		List<Users> list = this.usersDao.selectUserByNameUseSQL("张三");
		for (Users users : list) {
			System.out.println(users);
		}
	}

	/**
	 * QBC测试，将所有查询方法通过对象和方法来实现
	 */
	@Test
	@Transactional
	public void testSelectUserByUseCriteri(){
		List<Users> list = this.usersDao.selectUserByNameUseCriteria("张三");
		for (Users users : list) {
			System.out.println(users);
		}
	}

}
