<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/base.jsp" %>

<html lang="ko">
<head>
    <title>로그인</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="${contextPath}/css/style.css" rel="stylesheet">
    <link href="${contextPath}/css/auth.css" rel="stylesheet">
</head>
<body>

<%@ include file="/WEB-INF/views/common/index.jsp" %>

<div class="page-container page-narrow">
    <div class="content-card">
        <div class="card-section auth-card">
            <h1 class="auth-title">로그인</h1>
            <p class="auth-subtitle">
                아직 회원이 아니신가요?
                <a href="${registerUrl}" class="inline-link">회원가입</a>
            </p>

            <c:if test="${param.error != null}">
                <div class="auth-error-box">
                    아이디 또는 비밀번호가 올바르지 않습니다.
                </div>
            </c:if>

            <form action="${loginUrl}" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">아이디</label>
                    <input id="username" name="username" type="text" class="form-control" placeholder="아이디를 입력하세요">
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">비밀번호</label>
                    <input id="password" name="password" type="password" class="form-control" placeholder="비밀번호를 입력하세요">
                </div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary auth-submit">로그인</button>
            </form>
        </div>
    </div>
</div>

<div class="app-footer">
    © Lumiera Official Online Shop
</div>
</body>
</html>