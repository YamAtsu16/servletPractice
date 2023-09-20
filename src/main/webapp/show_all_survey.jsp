<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List"      %>
<%@ page import="model.SurveyDto"      %>

<%--
-------------------------------------------------------------------------------------------------
■■■ファイル名：show_all_survey.jsp■■■
概要：JSP
詳細：HTML文書（回答一覧画面）を出力する。
-------------------------------------------------------------------------------------------------
--%>

<%
//「survey」テーブルからデータを全件抽出
List<SurveyDto> list = (List<SurveyDto>)request.getAttribute("ALL_SURVEY_LIST");
%>


<html>
  <head>
    <title>回答一覧</title>
  </head>
  <body>
    <h2>アンケート回答一覧</h2>
    <table class="surbey_list" border=1>
      <tr bgcolor="#c0c0c0">
        <th>名前</th>
        <th>年齢</th>
        <th>性別</th>
        <th>満足度</th>
        <th>ご意見・ご感想</th>
        <th>回答時間</th>
      </tr>
<%
for (int i = 0; i < list.size(); i++) {
	SurveyDto dto = list.get(i);
%>
      <tr>
        <td><%= replaceEscapeChar(dto.getName()) %></td>
        <td><%= dto.getAge() %></td>
<%
	if (dto.getSex() == 1) {
%>
        <td>オス</td>
<%
	} else {
%>
        <td>メス</td>
<%
	}
	switch (dto.getSatisfactionLevel()) {
	case 1:
%>
        <td>とても不満</td>
<%
		break;
	case 2:
%>
        <td>不満</td>
<%
		break;
	case 3:
%>
        <td>普通</td>
<%
		break;
	case 4:
%>
        <td>満足</td>
<%
		break;
	case 5:
%>
        <td>とても満足</td>
<%
		break;
	}
%>

        <td><%= replaceEscapeChar(dto.getMessage()) %></td>
        <td><%= dto.getTime() %></td>
      </tr>
<%
}
%>
    </table>
    <br>
    <a href="<%=request.getContextPath()%>/InputSurvey">回答画面に戻る</a>
  </body>
</html>

<%!
/**----------------------------------------------------------------------*
 *■■■replaceEscapeCharクラス■■■
 *概要：文字列データのエスケープを行う
 *----------------------------------------------------------------------**/
String replaceEscapeChar(String inputText) {

	String charAfterEscape = inputText ; //エスケープ後の文字列データ

	// 「&」を変換
	charAfterEscape = charAfterEscape.replace("&", "&amp;");
	// 「<」を変換
	charAfterEscape = charAfterEscape.replace("<", "&lt;");
	// 「>」を変換
	charAfterEscape = charAfterEscape.replace(">", "&gt;");
	// 「"」を変換
	charAfterEscape = charAfterEscape.replace("\"", "&quot;");
	// 「'」を変換
	charAfterEscape = charAfterEscape.replace("'", "&#039;");

	return charAfterEscape;
}
%>