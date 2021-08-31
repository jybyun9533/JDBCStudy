package com.encore.test;

import com.encore.dao.impl.EmployeeDAOImpl;
import com.encore.vo.Employee;

import config.ServerInfo;

public class EmployeeDAOTest implements ServerInfo {

	public static void main(String[] args) {
		EmployeeDAOImpl service = EmployeeDAOImpl.getInstance();

//		service.insertEmp(new Employee(111, "변준영1", 200, "오산동1"));
//		service.insertEmp(new Employee(222, "변준영2", 300, "오산동2"));
//		service.removeEmp(222);
//		service.updateEmp(new Employee(111, "변준영", 1000, "오산시청"));

		System.out.println(service.selectAll());
	}

}
