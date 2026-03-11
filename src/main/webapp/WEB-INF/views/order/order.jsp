<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/base.jsp" %>

<html lang="ko">
<head>
    <title>주문 상세</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="${contextPath}/css/style.css" rel="stylesheet">
    <link href="${contextPath}/css/order.css" rel="stylesheet">
</head>
<body>

<%@ include file="/WEB-INF/views/common/index.jsp" %>

<div class="page-container">
    <div class="page-header">
        <h1 class="page-title">주문 상세</h1>
    </div>

    <div class="content-card mb-4">
        <div class="card-section">
            <div class="summary-top">
                <div>
                    <div class="data-label">주문 번호</div>
                    <div class="data-value">#${order.id}</div>
                </div>
                <span class="badge-status">${order.status}</span>
            </div>

            <div class="summary-grid">
                <div>
                    <div class="data-label">주문 금액</div>
                    <div class="data-value">${order.totalPrice}원</div>
                </div>
                <div>
                    <div class="data-label">주문 상태</div>
                    <div class="data-value">${order.status}</div>
                </div>
                <div>
                    <div class="data-label">주문일</div>
                    <div class="data-value">${order.createdAt}</div>
                </div>
            </div>

            <sec:authorize access="hasRole('ADMIN')">
                <div class="admin-extra">
                    <h2 class="section-title">관리자 정보</h2>
                    <div class="summary-grid">
                        <div>
                            <div class="data-label">주문자</div>
                            <div class="data-value">${order.username}</div>
                        </div>
                        <div>
                            <div class="data-label">수정일</div>
                            <div class="data-value">${order.updatedAt}</div>
                        </div>
                        <div>
                            <div class="data-label">삭제일</div>
                            <div class="data-value">${order.deletedAt}</div>
                        </div>
                    </div>
                </div>
            </sec:authorize>
        </div>
    </div>

    <div class="content-card">
        <div class="card-section">
            <h2 class="section-title">주문 상품</h2>

            <div class="table-wrap">
                <table class="table table-clean">
                    <thead>
                    <tr>
                        <th style="width: 100px;">이미지</th>
                        <th>상품명</th>
                        <th style="width: 140px;">가격</th>
                        <th style="width: 120px;">수량</th>
                        <th style="width: 160px;">합계</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${order.orderItems}" var="orderItem">
                        <c:url var="productDetailUrl" value="${productUrl}/${orderItem.productId}"/>
                        <tr>
                            <td>
                                <a href="${productDetailUrl}">
                                    <img src="${orderItem.thumbnailUrl}" class="thumbnail-sm" alt="${orderItem.name}">
                                </a>
                            </td>
                            <td>
                                <a href="${productDetailUrl}" class="cart-product-name">${orderItem.name}</a>
                            </td>
                            <td class="price-text">${orderItem.price}원</td>
                            <td>${orderItem.quantity}개</td>
                            <td class="price-text">${orderItem.price * orderItem.quantity}원</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="d-flex justify-content-between align-items-center gap-3 mt-4 flex-wrap">
                <sec:authorize access="hasRole('USER')">
                    <c:if test="${order.status == 'PENDING'}">
                        <c:url var="orderDetailUrl" value="${orderUrl}/${order.id}"/>
                        <form action="${orderDetailUrl}/mock-pay" method="post" class="mb-0">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <button type="submit" class="btn btn-primary">모의 결제</button>
                        </form>
                    </c:if>
                </sec:authorize>

                <div class="total-box">
                    <div class="data-label">총 결제금액</div>
                    <div class="fs-4 fw-bold">${order.totalPrice}원</div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="app-footer">
    © Lumiera Official Online Shop
</div>
</body>
</html>