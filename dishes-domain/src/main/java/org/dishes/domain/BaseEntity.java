package org.dishes.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * entity基类
 */
@MappedSuperclass
public class BaseEntity implements Serializable{
	private static final long serialVersionUID = -5945107095198552359L;
	@Transient
	private static SessionFactory sessionFactory;
	
	@Id
	@Column(name = "id")
	private String id = UUID.randomUUID().toString(); // 唯一标示
	@Column(name = "version")
	private int version; // 版本号，实现乐观锁
	
	@Column(name = "create_time")
	private long createTime = new Date().getTime(); // 创建时间
	
	@Column(name = "last_modify_time")
	private long lastModifyTime = new Date().getTime(); // 最后一次修改时间
	
	@Column(name = "sort_index")
	private long sortIndex; // 排序号
	

	/*******************************static mehod start***********************************/
	/**
	 * 获取session对象
	 * @return 
	 */
	public static Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	/*写操作*/
	public static void batchByHQL(String hql,Object ... params){
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		query.executeUpdate();
	}
	/*读操作*/
	@SuppressWarnings("unchecked")
	public static <T extends BaseEntity> T get(Class<T> entityClass,String id){
		return (T) getSession().get(entityClass, id);
	}
	@SuppressWarnings("unchecked")
	public static <T extends BaseEntity> T load(Class<T> entityClass,String id){
		return (T) getSession().load(entityClass, id);
	}
	@SuppressWarnings("unchecked")
	protected static <T extends BaseEntity> List<T> findByHQL(String hql,Object ... params){
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.list();
	}
	/*******************************static mehod end***********************************/
	
	
	/*******************************public mehod start***********************************/
	
	/*写操作*/
	/**
	 * 删除
	 */
	public void delete(){
		getSession().delete(this);
	}
	/**
	 * 保存或者更新
	 */
	public void saveOrUpdate(){
		getSession().saveOrUpdate(this);
	}
	/**
	 * 更新
	 */
	public void update(){
		getSession().update(this);
	}
	/**
	 * 保存
	 */
	public void save(){
		getSession().save(this);
	}
	/*******************************public mehod end***********************************/
	
	/*******************************private mehod start***********************************/
	
	/*******************************private mehod end***********************************/
	
	
	/***************************************get/set method*******************************/
	public static void setSessionFactory(SessionFactory sessionFactory) {
		BaseEntity.sessionFactory = sessionFactory;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public long getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(long sortIndex) {
		this.sortIndex = sortIndex;
	}
	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", version=" + version
				+ ", createTime=" + createTime + ", lastModifyTime="
				+ lastModifyTime + ", sortIndex=" + sortIndex + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (createTime ^ (createTime >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ (int) (lastModifyTime ^ (lastModifyTime >>> 32));
		result = prime * result + (int) (sortIndex ^ (sortIndex >>> 32));
		result = prime * result + version;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (createTime != other.createTime)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastModifyTime != other.lastModifyTime)
			return false;
		if (sortIndex != other.sortIndex)
			return false;
		if (version != other.version)
			return false;
		return true;
	}


	
}
