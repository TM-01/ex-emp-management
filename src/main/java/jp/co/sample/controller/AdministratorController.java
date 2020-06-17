package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;
/**
 * コントローラー.
 * @author tatsuro.miyazaki
 * 
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	/**
	 * 
	 * @return 入力フォーム
	 * 入力フォームの表示
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}
	
	/**
	 * 
	 * @param form
	 * formの内容をdomainにコピー
	 * insertの実行
	 * @return /
	 * リダイレクトで最初に戻る
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator =new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/";
	}
<<<<<<< HEAD
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	/**
	 * 
	 * @return
	 * loginページにフォワード
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login.html";
	}
=======
>>>>>>> 8617dacd37c353931212f90a9af9c5a7aa78d70a

}
