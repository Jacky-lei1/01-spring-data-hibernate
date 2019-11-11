package com.bjsxt.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Entity：表示当前类是一个实体类
 * @Table(name="t_users"):告诉Hibernate，当前这个Users对象是和t_users这个表作映射的
 * 还有一层含义就是：如果在配置文件中开启了正向工程，这个t_users将作为创建的表名
 */
@Entity
@Table(name="t_users")
public class Users implements Serializable{
	/**
	 * @Id:表示主键时userid
	 * @GeneratedValue(strategy=GenerationType.IDENTITY:表示自动增长的主键生成策略
	 * @Column(name="userid")：告诉hibernate，当前这个属性和表中的userid是对应的
	 * 同时也表示在正向工程中生成的表中对应的字段名
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//strategy=GenerationType.IDENTITY 自增长
	@Column(name="userid")
	private Integer userid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="userage")
	private Integer userage;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserage() {
		return userage;
	}

	public void setUserage(Integer userage) {
		this.userage = userage;
	}

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", username=" + username + ", userage=" + userage + "]";
	}
	
}
