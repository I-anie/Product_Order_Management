<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<header>
    <div class="site-banner">
        <div class="container">
            <h1 class="site-banner-title">Lumiera Official Online Shop</h1>
            <p class="site-banner-subtitle">Elegant beauty, curated for your everyday moment.</p>
        </div>
    </div>

    <nav class="site-nav">
        <div class="site-nav-inner">
            <ul class="site-nav-list">
                <sec:authorize access="isAnonymous()">
                    <li class="site-nav-item">
                        <a class="site-nav-link" href="${registerUrl}">회원가입</a>
                    </li>
                    <li class="site-nav-item">
                        <a class="site-nav-link" href="${loginUrl}">로그인</a>
                    </li>
                </sec:authorize>

                <sec:authorize access="hasRole('ADMIN')">
                    <li class="site-nav-item">
                        <a class="site-nav-link" href="${productUrl}">상품관리</a>
                    </li>
                    <li class="site-nav-item">
                        <a class="site-nav-link" href="${orderUrl}">주문관리</a>
                    </li>
                    <li class="site-nav-item">
                        <form action="${logoutUrl}" method="post" class="site-nav-form">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="site-nav-link site-nav-button">로그아웃</button>
                        </form>
                    </li>
                </sec:authorize>

                <sec:authorize access="hasRole('USER')">
                    <li class="site-nav-item">
                        <a class="site-nav-link" href="${productUrl}">상품목록</a>
                    </li>
                    <li class="site-nav-item">
                        <a class="site-nav-link" href="${cartUrl}">장바구니</a>
                    </li>
                    <li class="site-nav-item">
                        <a class="site-nav-link" href="${orderUrl}">주문내역</a>
                    </li>
                    <li class="site-nav-item">
                        <form action="${logoutUrl}" method="post" class="site-nav-form">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="site-nav-link site-nav-button">로그아웃</button>
                        </form>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </nav>
</header>