package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoDao {

	//JDBCドライバの相対パス
	String DRIVER_NAME = "org.postgresql.Driver";
	//接続先のデータベース
	String JDBC_URL = "jdbc:postgresql://localhost:5432/sample";
	//接続するユーザー名
	String USER_ID = "postgres";
	//接続するユーザーのパスワード
	String USER_PASS = "postgres";

	/**
	 * 引数のユーザー情報に紐づくユーザーデータを「user_info」テーブルから抽出する
	 * @param inputUserId ユーザID
	 * @param inputPassWord ユーザパスワード
	 * @return 「user_info」テーブルから抽出したユーザーデータ
	 */
	public UserInfoDto doSelect(String inputUserId, String inputPassWord) {

		//DB接続
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//JDBCの接続に使用するオブジェクトを宣言
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数
		ResultSet         rs  = null ;   // ResultSet（SQL抽出結果）格納用変数

		//抽出データ格納用変数
		UserInfoDto dto = new UserInfoDto();

		try {
			//接続の確立（Connectionオブジェクトの取得）
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//発行するSQL文の生成（SELECT）
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT             ");
			buf.append("   USER_ID  ,       ");
			buf.append("   USER_NAME,       ");
			buf.append("   PASSWORD         ");
			buf.append(" FROM               ");
			buf.append("   USER_INFO        ");
			buf.append(" WHERE              ");
			buf.append("   USER_ID  = ? AND ");  //第1パラメータ
			buf.append("   PASSWORD = ?     ");  //第2パラメータ

			//PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setString( 1, inputUserId   );  //第1パラメータ
			ps.setString( 2, inputPassWord );  //第2パラメータ

			//SQL文の送信＆戻り値としてResultSet（SQL抽出結果）を取得
			rs = ps.executeQuery();

			//ResultSetオブジェクトからユーザーデータを抽出
			if (rs.next()) {
				dto.setUserId(   rs.getString("USER_ID")   );    //ユーザーID
				dto.setUserName( rs.getString("USER_NAME") );    //ユーザー名
				dto.setPassWord( rs.getString("PASSWORD")  );    //ユーザーパスワード
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			//ResultSetオブジェクトの接続解除
			if (rs != null) {
				try {
					rs.close();  
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//PreparedStatementオブジェクトの接続解除
			if (ps != null) {
				try {
					ps.close();  
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//Connectionオブジェクトの接続解除
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//抽出したユーザーデータを戻す
		return dto;
	}
}
