package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービス.
 * 
 * @author tatsuro.miyazaki
 * 
 */

@Service
@Transactional
public class AdministratorService {
	@Autowired
	private AdministratorRepository administratorRepository;
	
	/**
	 * 管理者情報の挿入.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	
	/**
	 * メールアドレスとパスワードから管理者を検索する.
	 * 
	 * @param mailAddres　入力されたメールアドレス
	 * @param password　入力されたパスワード
	 * @return リストのサイズが0の時にnull,そうでないときには管理者情報を返す
	 */
	public Administrator login(String mailAddress, String password) {
		return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
	}
}
