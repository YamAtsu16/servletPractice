package model;


/**----------------------------------------------------------------------*
 *■■■ExecuteLoginBLクラス■■■
 *概要：ビジネスロジック（ユーザーデータの照合）
 *----------------------------------------------------------------------**/
public class ExecuteLoginBL {

	/**----------------------------------------------------------------------*
	 *■executeSelectUserInfoメソッド
	 *概要　：対象のアンケートデータを登録する
	 *引数１：入力されたユーザーID
	 *引数２：入力されたユーザーのパスワード
	 *戻り値：抽出したユーザーデータ（UserInfoDto型）
	 *----------------------------------------------------------------------**/
	public UserInfoDto executeSelectUserInfo(String userId, String passWord) {

		// データベースにアクセスしてユーザー情報を取得
		UserInfoDao dao = new UserInfoDao();
		UserInfoDto dto = dao.doSelect(userId, passWord);

		return dto;
	}

}
