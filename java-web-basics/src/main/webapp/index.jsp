<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/mytags.tld" prefix="m" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
for (int i=0;i<10;i++){
%>
Holaaaa<br>
<% 
}
%>


Current Date and Time is: <m:today/>

</body>
</html>