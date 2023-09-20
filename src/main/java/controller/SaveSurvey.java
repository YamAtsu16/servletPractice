package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SaveSurveyBL;
import model.SurveyDto;
import model.UserInfoDto;

/**----------------------------------------------------------------------*
 *■■■SaveSurveyクラス■■■
 *概要：サーブレット
 *詳細：リクエスト（アンケートデータ）を「survey」テーブルに登録し、画面遷移する。
 *　　　＜遷移先＞登録成功：回答完了画面（finish.html）／登録失敗：エラー画面（error.html）
 *----------------------------------------------------------------------**/
public class SaveSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveSurvey() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");                  //文字コードをUTF-8で設定

		//セッションからユーザーデータを取得
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		//ログイン状態によって表示画面を振り分ける
		if (userInfoOnSession != null) {
			//ログイン済：登録処理＆結果画面への遷移を実施

			boolean succesFlg = true;  //成功フラグ（true:成功/false:失敗）

			//パラメータのバリデーションチェック
			if( !(validatePrmName(request.getParameter("NAME"))                &&
					validatePrmAge(request.getParameter("AGE"))                  &&
					validatePrmSex(request.getParameter("SEX"))                  &&
					validatePrmSatLv(request.getParameter("SATISFACTION_LEVEL")) &&
					validatePrmMessage(request.getParameter("MESSAGE"))               ) ) {

				//バリデーションNGの場合
				succesFlg = false ;

			}else {

				//バリデーションOKの場合

				//リクエストパラメータを取得
				String name              = request.getParameter("NAME");                                   //リクエストパラメータ（NAME）
				int    age               = Integer.parseInt( request.getParameter("AGE") );                //リクエストパラメータ（AGE）
				int    sex               = Integer.parseInt( request.getParameter("SEX") );                //リクエストパラメータ（SEX）
				int    satisfactionLevel = Integer.parseInt( request.getParameter("SATISFACTION_LEVEL") ); //リクエストパラメータ（SATISFACTION_LEVEL）
				String message           = request.getParameter("MESSAGE");                                //リクエストパラメータ（MESSAGE）

				//アンケートデータ（SurveyDto型）の作成
				SurveyDto dto = new SurveyDto();
				dto.setName( name );
				dto.setAge( age );
				dto.setSex( sex );
				dto.setSatisfactionLevel( satisfactionLevel );
				dto.setMessage( message );
				dto.setTime( new Timestamp(System.currentTimeMillis()) );   //現在時刻を更新時刻として設定

				//アンケートデータをDBに登録
				SaveSurveyBL logic = new SaveSurveyBL();
				succesFlg          = logic.executeInsertSurvey(dto);  //成功フラグ（true:成功/false:失敗）

			}

			//成功/失敗に応じて表示させる画面を振り分ける
			if (succesFlg) {

				//成功した場合、回答完了画面（finish.html）を表示する
				response.sendRedirect("htmls/finish.html");

			} else {

				//失敗した場合、エラー画面（error.html）を表示する
				response.sendRedirect("htmls/error.html");

			}

		} else {
			//未ログイン：ログイン画面へ転送
			response.sendRedirect("Login");
		}
	}

	/**----------------------------------------------------------------------*
	 *■■■validatePrmNameクラス■■■
	 *概要：バリデーションチェック
	 *詳細：入力値（名前）の検証を行う
	 *----------------------------------------------------------------------**/
	private boolean validatePrmName( String pr) {

		boolean validateResult = true ;

		//入力値がnullまたは空白の場合はエラーとする
		if( pr == null || pr.equals("") ) {
			validateResult = false ;
		}

		return validateResult ;
	}

	/**----------------------------------------------------------------------*
	 *■■■validatePrmAgeクラス■■■
	 *概要：バリデーションチェック
	 *詳細：入力値（年齢）の検証を行う
	 *----------------------------------------------------------------------**/
	private boolean validatePrmAge( String pr) {

		boolean validateResult = true ;

		//入力値がnullまたは正の数以外の場合はエラーとする
		if( pr == null || !( pr.matches("^[0-9]+$") )) {
			validateResult = false ;
		}

		return validateResult ;
	}

	/**----------------------------------------------------------------------*
	 *■■■validatePrmSexクラス■■■
	 *概要：バリデーションチェック
	 *詳細：入力値（性別）の検証を行う
	 *----------------------------------------------------------------------**/
	private boolean validatePrmSex( String pr) {

		boolean validateResult = true ;

		//入力値がnullまたは「1」「2」でない場合はエラーとする
		if( pr == null || !( pr.equals("1") || pr.equals("2") ) ) {
			validateResult = false ;
		}

		return validateResult ;
	}

	/**----------------------------------------------------------------------*
	 *■■■validatePrmSatLvクラス■■■
	 *概要：バリデーションチェック
	 *詳細：入力値（満足度）の検証を行う
	 *----------------------------------------------------------------------**/
	private boolean validatePrmSatLv( String pr) {

		boolean validateResult = true ;

		//入力値がnullまたは「1」「2」「3」「4」「5」でない場合はエラーとする
		if( pr == null || !( pr.equals("1") || pr.equals("2") || pr.equals("3") || pr.equals("4") || pr.equals("5") ) ) {
			validateResult = false ;
		}

		return validateResult ;
	}

	/**----------------------------------------------------------------------*
	 *■■■validatePrmMessageクラス■■■
	 *概要：バリデーションチェック
	 *詳細：入力値（意見）の検証を行う
	 *----------------------------------------------------------------------**/
	private boolean validatePrmMessage( String pr) {

		boolean validateResult = true ;

		//入力値が空白またはnullの場合はエラーとする
		if( pr == null || pr == "") {
			validateResult = false ;
		}

		return validateResult ;
	}

}
