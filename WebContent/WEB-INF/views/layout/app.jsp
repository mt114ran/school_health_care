<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>SCHOOL HEALTH CARE</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
        <link rel="icon" type="image/x-icon" href="<c:url value='/image/school_icon.png'/>">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                    <h1><a href="<c:url value='/' />">SCHOOL HEALTH CARE</a></h1>&nbsp;&emsp;&emsp;
                    <c:if test="${sessionScope.login_user != null}">
                        <c:if test="${sessionScope.login_user.acc_inf == 0}">
                            <a href="<c:url value='/users/index' />"><i class="fas fa-users"></i> ユーザー</a>&nbsp;&emsp;&emsp;
                            <a href="<c:url value='/cards/index' />"><i class="fas fa-notes-medical"></i> カード</a>&nbsp;&emsp;&emsp;
                            <a href="<c:url value='/messages/index' />"><i class="fas fa-comment-dots"></i> メッセージ</a>&nbsp;&emsp;&emsp;
                        </c:if>
                        <a href="<c:url value='/families/index' />"><i class="fas fa-home"></i> 家族</a>&nbsp;&emsp;&emsp;
                        <a href="<c:url value='/groups/index' />"><i class="fas fa-clone"></i> グループ</a>&nbsp;&emsp;&emsp;
                    </c:if>
                </div>
                <c:if test="${sessionScope.login_user != null}">
                    <div id="user_name">
                        <a href="<c:url value='/users/show?id=${login_user.id}' />"><i class="far fa-user-circle"></i> <c:out value="${sessionScope.login_user.name}" /></a>&nbsp;&nbsp;&nbsp;&emsp;
                        <a href="<c:url value='/users/index' />"><i class="fas fa-window-restore"></i> 出退勤管理</a>&nbsp;&nbsp;&nbsp;&emsp;
                        <a href="<c:url value='/logout' />"><i class="fas fa-door-open"></i> ログアウト</a>
                    </div>
                </c:if>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Maita Tomoya.
            </div>
        </div>
    </body>
</html>