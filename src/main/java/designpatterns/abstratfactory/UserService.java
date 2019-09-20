package designpatterns.abstratfactory;
/**
 * �û��ӿ���
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-17 ����10:12:45
 */
public interface UserService {
	/**
	 * �����û�
	 * @author daniel
	 * @time 2016-6-17 ����10:13:01
	 * @param user
	 */
	public void insertUser(User user);
	/**
	 * ����û�����
	 * @author daniel
	 * @time 2016-6-17 ����10:13:51
	 * @param id
	 * @return
	 */
	 public   User getUser(int id);
}
