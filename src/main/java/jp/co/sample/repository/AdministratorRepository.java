package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * administratorsテーブルを操作するリポジトリ.
 * 
 * @author tatsuro.miyazaki
 * 
 */

@Repository
public class AdministratorRepository {
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 管理者情報の挿入.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		String sql = "INSERT INTO administrators(name, mail_address, password) VALUES(:name, :mail, :pass)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", administrator.getName())
				.addValue("mail", administrator.getMailAddress()).addValue("pass", administrator.getPassword());
		template.update(sql, param);

	}
	/**
	 * メールアドレスとパスワードから管理者を検索する.
	 * 
	 * @param mailAddres　入力されたメールアドレス
	 * @param password　入力されたパスワード
	 * @return リストのサイズが0の時にnull,そうでないときには管理者情報を返す
	 */
	public Administrator findByMailAddressAndPassword(String mailAddres, String password) {
		String sql="SELECT id, name, mail_address, password FROM administrators WHERE mail_address = :mail AND password = :pass;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mail", mailAddres).addValue("pass", password);
		
		List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		
		if(administratorList.size() == 0) {
			return null;
			
		}

		return administratorList.get(0);
	}

}
