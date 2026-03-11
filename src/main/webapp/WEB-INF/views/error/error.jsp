<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="ko">
<head>
    <title>오류 발생</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="${contextPath}/css/style.css" rel="stylesheet">
    <link href="${contextPath}/css/error.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="error-page">
    <div class="error-card">
        <div class="error-card-top">
            <h1>Lumiera Official Online Shop</h1>
        </div>

        <div class="error-card-body">
            <div class="error-status">${status}</div>
            <div class="error-title">문제가 발생했습니다</div>

            <div class="error-message">
                ${message}
            </div>

            <div class="error-code">
                에러 코드: <strong>${code}</strong>
            </div>

            <div class="error-actions">
                <a href="${contextPath}/products" class="btn btn-primary">상품 목록으로</a>
                <a href="javascript:history.back()" class="btn btn-outline-secondary">이전 페이지</a>
            </div>

            <div class="error-subtext">
                같은 문제가 계속 발생하면 관리자에게 문의해주세요.
            </div>
        </div>
    </div>
</div>

<div class="app-footer">
    © Lumiera Official Online Shop
</div>

</body>
</html>