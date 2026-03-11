<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:url var="registerUrl" value="/auth/register"/>
<c:url var="loginUrl" value="/auth/login"/>
<c:url var="logoutUrl" value="/auth/logout"/>

<c:url var="cartUrl" value="/cart"/>

<sec:authorize access="hasRole('ADMIN')">
    <c:url var="productUrl" value="/admin/products"/>
    <c:url var="orderUrl" value="/admin/orders"/>
</sec:authorize>

<sec:authorize access="!hasRole('ADMIN')">
    <c:url var="productUrl" value="/products"/>
    <c:url var="orderUrl" value="/orders"/>
</sec:authorize>