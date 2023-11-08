package model;

public class UserInfoDto {

	private String userId;         //ユーザーID
	private String userName;       //ユーザー名
	private String passWord;       //ユーザーパスワード

	//getter/setter（対象フィールド：userId）
	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }

	//getter/setter（対象フィールド：userName）
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }

	//getter/setter（対象フィールド：passWord）
	public String getPassWord() { return passWord; }
	public void setPassWord(String passWord) { this.passWord = passWord; }

}
