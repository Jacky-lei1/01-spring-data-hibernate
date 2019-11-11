package com.bjsxt.dao.impl;

import java.util.List;

import com.bjsxt.dao.UsersDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.bjsxt.pojo.Users;

@Repository
public class UsersDaoImpl  implements UsersDao {

	/**
	 * HibernateTemplate：这个类中封装了一些对应Hibernate操作的方法模板
	 * 注入在配置文件中配置的HibernateTemplate对象，该对象需要一个Sqlsession对象
	 * 这里也可以通过继承的方法获取到这个对象
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void insertUsers(Users users) {
		this.hibernateTemplate.save(users);
	}

	@Override
	public void updateUsers(Users users) {
		this.hibernateTemplate.update(users);

	}

	@Override
	public void deleteUsers(Users users) {
		this.hibernateTemplate.delete(users);

	}

	@Override
	public Users selectUsersById(Integer userid) {
		return this.hibernateTemplate.get(Users.class, userid);
	}

	@Override
	public List<Users> selectUserByName(String username) {
		//getCurrentSession:当前session必须要有事务边界，且只能处理唯一的一个事务。当事务提交或者回滚后session自动失效
		//openSession:每次都会打开一个新的session.加入每次使用多次。则获得的是不同session对象。使用完毕后我们需要手动的调用colse方法关闭session
	Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
	//sql:select * from t_users where username =
		//通过标识符进行参数的绑定:abc
	Query query = session.createQuery("from Users where username = :abc");
	//可以根据位置或者标识符来绑定参数，查出来的索引是从0开始的
	Query queryTemp = query.setString("abc",username);
	//以一个list类型返回结果
		return queryTemp.list();
	}

	@Override
	public List<Users> selectUserByNameUseSQL(String username) {
		//通过hibernateTemplate获取到上下文对象
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		//这里是通过SQL语句进行查询，所有需要通过addEntity方法关联实体类,通过setString绑定参数
		Query query = session.createSQLQuery("select * from t_users where username = ?").addEntity(Users.class).setString(0,username);
		return query.list();
	}

	@Override
	public List<Users> selectUserByNameUseCriteria(String username) {

		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		//sql:select * from t_users where username = 张三
		//表示需要查找的对象，此时就相当于写了上面那个SQL语句
		Criteria c = session.createCriteria(Users.class);
		//这种查询方式就是将所有的查询通过对象和方法来实现,"username":对象当中的属性名，username：给定的查询条件
		c.add(Restrictions.eq("username",username));
		return c.list();
	}

}
