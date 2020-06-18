package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;
/**
 * 従業員情報を検索する処理を記述.
 * 
 * @author tatsuro.miyazaki
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	/**
	 * 従業員一覧を出力.
	 * 
	 * @param model 従業員一覧を格納
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		System.out.println(employeeList.toString());
		
		return "employee/list";
	}

	/**
	 * 従業員情報を取得する.
	 * 
	 * @param id 従業員のID
	 * @param model リクエストスコープ
	 * @return 従業員詳細画面にフォワード
	 */
	@RequestMapping("/showDetail")
	public String showDetai(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		
		return "employee/detail";
	}
	
	/**
	 * 従業員詳細（不要人数）を更新.
	 * 
	 * @param form 送られてきたリクエストパラメータ
	 * @return showListへフォワード
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		System.out.println(form.toString());
		Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));
		System.out.println(employee.toString());
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		System.out.println(employee.toString());
		employeeService.update(employee);
		
		return"redirect:/employee/showList";
	}
}
