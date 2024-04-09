<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ page
trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>ex08</title>
  </head>
  <body>
    포워딩된 페이지는 ex08.jsp입니다. id 파라미터 값은 : <%= request.getParameter("id") %> <br />
    pw 파라미터 값은 : <%= request.getParameter("pw") %>
  </body>
</html>
