package designpatterns.composite;
/**
 * 公司类抽象啊
 * @author daniel
 *
 */
public abstract class Company {

	/**
	 * 公司或部门名字
	 */
	 protected String name;
	 public Company(String name){
	  this.name=name;
	 }

	/**
	 * 添加公司或者部门
	 * @param c
     */
	 public abstract void add(Company c);

	/**
	 * 删除公司或者部门
	 * @param c
     */
	 public abstract void remove(Company c);

	/**
	 * 展示公司 或部门名称  层级
	 * @param i
     */
	 public abstract void display(int i);

	/**
	 * 展示部门职能
	 */
	 public abstract void lineOfDuty();
	}
