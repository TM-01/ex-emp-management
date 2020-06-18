package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;
/**
 * 従業員情報を操作するコントローラー.
 * 
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
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * 入力フォームの表示.
	 * @return 入力フォーム
	 * 
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}
	
	/**
	 * 管理者情報を登録する.
	 * 
	 * @param form フォーム
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator =new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/";
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
	
	@Autowired
	private HttpSession session;
	/**
	 * ログインをする.
	 * 
	 * @param form ログインフォームで入力された値
	 * @param model リクエストスコープ
	 * @return ログイン成功時に雇用リストへ、失敗時には再読み込み
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		if(administrator==null) {
			model.addAttribute("message", "メールアドレスまたはパスワードが不正です");
			return "administrator/login";
		}else {
			session.setAttribute("administratorName", administrator.getName());
		}
		return "employee/list";
	}
	
	/**
	 * ログアウトをする.
	 * 
	 * @return ログイン画面へフォワード
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "administrator/login";
	}
}
