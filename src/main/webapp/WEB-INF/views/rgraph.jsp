<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>R 시각화 결과 표현하기</h1>
<hr>
<%
if(request.getAttribute("img_content") != null) {		
	byte src[] = (byte[])request.getAttribute("img_content");
%>
	<img src="
	   data:image/png;base64, <%= Base64.getEncoder().encodeToString(src) %>"
	   width="500" height="400">
<%
} 
%>	
</body>
</html>