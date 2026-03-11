<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/base.jsp" %>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 수정</title>

    <link href="${contextPath}/css/style.css" rel="stylesheet">
    <link href="${contextPath}/css/product-form.css" rel="stylesheet">
</head>
<body>

<%@ include file="/WEB-INF/views/common/index.jsp" %>

<div class="page-container">
    <div class="content-card form-card-narrow">
        <div class="card-section">
            <div class="form-header">
                <h1 class="form-title">상품 수정</h1>
                <p class="form-subtitle">기존 상품 정보를 변경할 수 있습니다.</p>
            </div>

            <form:form modelAttribute="updateForm"
                       action="${productUrl}/${productId}/update"
                       method="post"
                       enctype="multipart/form-data">

                <div class="form-block">
                    <label for="thumbnail" class="form-label">대표 이미지 변경</label>
                    <input id="thumbnail" name="thumbnail" type="file" accept="image/*" class="form-control">
                    <div class="file-guide">새 이미지를 등록하지 않으면 기존 대표 이미지를 유지합니다.</div>
                    <form:errors path="thumbnail" element="div" cssClass="field-error"/>
                </div>

                <div class="form-block">
                    <label class="form-label" for="categoryId">카테고리</label>
                    <form:select path="categoryId" id="categoryId" cssClass="form-select">
                        <form:option value="" label="카테고리를 선택해주세요"/>
                        <form:options items="${categoryList}" itemValue="id" itemLabel="type"/>
                    </form:select>
                    <form:errors path="categoryId" element="div" cssClass="field-error"/>
                </div>

                <div class="form-block">
                    <label class="form-label" for="name">상품명</label>
                    <form:input path="name" id="name" cssClass="form-control" placeholder="상품명을 입력하세요"/>
                    <form:errors path="name" element="div" cssClass="field-error"/>
                </div>

                <div class="form-block">
                    <label class="form-label" for="price">가격</label>
                    <form:input path="price" id="price" type="number" cssClass="form-control" placeholder="가격을 입력하세요"/>
                    <form:errors path="price" element="div" cssClass="field-error"/>
                </div>

                <div class="form-block">
                    <label class="form-label" for="stockQuantity">재고 수량</label>
                    <form:input path="stockQuantity" id="stockQuantity" type="number" cssClass="form-control"
                                placeholder="재고 수량을 입력하세요"/>
                    <form:errors path="stockQuantity" element="div" cssClass="field-error"/>
                </div>

                <div class="form-block">
                    <label for="detailImages" class="form-label">상세 이미지 변경</label>
                    <input id="detailImages" name="detailImages" type="file" multiple accept="image/*"
                           class="form-control">
                    <div class="file-guide">새 상세 이미지를 등록하면 기존 상세 이미지를 교체합니다.</div>
                    <form:errors path="detailImages" element="div" cssClass="field-error"/>
                </div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="form-actions">
                    <a href="${productUrl}/${productId}" class="btn btn-outline-secondary">취소</a>
                    <button type="submit" class="btn btn-primary">수정 완료</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<div class="app-footer">
    © Lumiera Official Online Shop
</div>
</body>
</html>