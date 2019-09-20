package designpatterns.abstratfactory;
/**
 * I.����:
�ṩһ������һϵ����ػ��໥�����Ľӿڣ�������ָ�����Ǿ�����ࡣ
Ϊ����һ����ػ��໥�����Ķ����ṩһ���ӿڣ���������ָ�����ǵľ����ࡣ

������
Oracle insert User
Oracle get User
Oracle insert Department
Oracle get Department
Mysql insert User
Mysql get User
Mysql insert Department
Mysql get Department


 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-17 ����10:35:58
 */
public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-6-17 ����10:29:44
	 * @param args
	 */
	public static void main(String[] args) {
		// ׼��ʵ�����
		User user = new User();
		Department department = new Department();

		// Oracle�汾�Ĺ���
		FactoryService factory = new FactoryServiceOracleImpl();
		// Oracle�汾�Ľӿ�
		UserService userService = factory.getUserService();
		DepartmentService departmentService = factory.getDepartmentService();

		// Oracle�汾��ִ��
		userService.insertUser(user);
		userService.getUser(1);
		departmentService.insertDepartment(department);
		departmentService.getDepartment(1);

		// Mysql�汾�Ĺ���
		factory = new FactoryServiceMysqlImpl();
		// Mysql�汾�Ľӿ�
		userService = factory.getUserService();
		departmentService = factory.getDepartmentService();

		// Mysql�汾��ִ��
		userService.insertUser(user);
		userService.getUser(1);
		departmentService.insertDepartment(department);
		departmentService.getDepartment(1);

	}

}
