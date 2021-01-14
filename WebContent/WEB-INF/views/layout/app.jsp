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
                            <a href="<c:url value='/users/index' />"><i class="fas fa-users"></i> 欠席遅刻管理</a>&nbsp;&emsp;&emsp;
                            <a href="<c:url value='/users/index' />"><i class="fas fa-users"></i> クラス管理</a>&nbsp;&emsp;&emsp;
                        </c:if>
                        <a href="<c:url value='/users/index' />"><i class="fas fa-clipboard-list"></i> ユーザー管理</a>&nbsp;&emsp;&emsp;
                        <a href="<c:url value='/users/index' />"><i class="fas fa-hands-helping"></i> 生徒管理</a>&nbsp;&emsp;&emsp;
                        <a href="<c:url value='/users/index' />"><i class="fas fa-heart"></i> 保護者管理</a>&nbsp;&emsp;&emsp;
                    </c:if>



                </div>
                <c:if test="${sessionScope.login_user != null}">
                    <div id="user_name">
                        <i class="far fa-user-circle"></i> <c:out value="${sessionScope.login_user.name}" />&nbsp;さん&nbsp;&nbsp;&nbsp;&emsp;
                        <i class="fas fa-window-restore"></i><a href="<c:url value='/user/index' />"> 出退勤管理</a>&nbsp;&nbsp;&nbsp;&emsp;
                        <i class="fas fa-door-open"></i><a href="<c:url value='/logout' />"> ログアウト</a>
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