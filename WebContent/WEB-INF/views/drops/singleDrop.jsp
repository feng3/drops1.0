<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
	String path = request.getContextPath();
%>
<c:set value="<%=path%>" var="path" /> 

<!DOCTYPE html >
<link rel="shortcut icon" href="${path }/images/favicon.png" type="image/png">
${article.content }
