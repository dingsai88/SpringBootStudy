package designpatterns.abstratfactory;

/**
 * �����ӿ�
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-17 ����10:26:05
 */
public interface FactoryService {
	/*
	 * ����û��ӿ�
	 */
	public UserService getUserService();

	/*
	 * ��ò��Žӿ�
	 */
	public DepartmentService getDepartmentService();

}
