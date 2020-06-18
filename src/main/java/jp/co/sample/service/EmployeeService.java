package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報一覧を全件検索する処理を行うメソッドの作成.
 * @author tatsuro.miyazaki
 *
 */
@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 全件検索.
	 * 
	 * @return　従業員一覧
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	
	/**
	 * 主キー検索をする.
	 * 
	 * @param id ID
	 * @return 従業員
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}
	
	/**
	 * 従業員情報を更新する.
	 * @param employee 従業員所不応
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}
}
