<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:set var="number" value="12345.6789"/>

<%-- 천 단위 구분 기호 --%>
<div>
  <fmt:formatNumber value="${number}" pattern="#,##0"></fmt:formatNumber>
</div>
<%-- 천 단위 구분 기호 + 소수점 지정(2번째 까지)--%>
<div>
  <fmt:formatNumber value="${number}" pattern="#,##0.00"></fmt:formatNumber>
</div>
<%-- percent : 백분율--%>
<div>
  <fmt:formatNumber value="${number}" type="percent"></fmt:formatNumber>
</div>
<%-- currency : 통화 --%>
<div>
  <fmt:formatNumber value="${number}" type="currency"></fmt:formatNumber>
</div>
<%-- currencySymbol : 통화 기호 지정 --%>
<div>
  <fmt:formatNumber value="${number}" type="currency" currencySymbol="$"></fmt:formatNumber>
</div>

</body>
</html>