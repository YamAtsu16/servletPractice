<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="model.UserInfoDto"      %>

<%--
-------------------------------------------------------------------------------------------------
■■■ファイル名：input_survey.jsp■■■
概要：JSP
詳細：HTML文書（回答入力画面）を出力する。
-------------------------------------------------------------------------------------------------
--%>

<%
//セッションからユーザーデータを取得
UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");
String userName  = userInfoOnSession.getUserName();
%>

<html>
<head>
  <title>回答入力</title>
</head>
<body>
  <h2>わんちゃん暮らし改善アンケートフォーム</h2>
  <form action="SaveSurvey" method="post">
    <p>名前：<%= userName %>
      <input type="hidden" name="NAME"  value="<%= userName %>">
    </p>
    <p>年齢：
      <input type="number" name="AGE" maxlength = "3" id="ID_AGE">
    </p>
    <p>性別：
      <input type="radio" name="SEX" value="1" checked>オス
      <input type="radio" name="SEX" value="2">メス
    </p>
    <p> 満足度：
      <select name="SATISFACTION_LEVEL">
        <option value="5">とても満足</option>
        <option value="4">満足      </option>
        <option value="3">普通      </option>
        <option value="2">不満      </option>
        <option value="1">とても不満</option>
      </select>
    </p>
    <p>飼い主へのご意見・ご感想をご記入ください：<br>
      <textarea name="MESSAGE" rows="4" cols="50" maxlength = "250" id="ID_MESSAGE"></textarea>
    </p>
    <input type="submit" value="回答する(SaveSurveyを起動)" id="ID_SUBMIT">
  </form>
  <script type="text/javascript" src="js/input_survey.js"></script>
  <br>
  <a href="ShowAllSurvey">回答一覧画面へ</a>
  <br>
  <a href="ExecuteLogout">ログアウトする</a>
</body>
</html>
