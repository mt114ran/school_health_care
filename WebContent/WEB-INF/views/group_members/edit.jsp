<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>登録ユーザー　一覧</h2>
        <h3>操作欄より、ユーザーの登録、または登録の解除を行って下さい。</h3>
        <table id="user_list">
            <tbody>
                <tr>
                    <th>ユーザー番号</th>
                    <th>氏名</th>
                    <th>アカウント種類</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="user" items="${users}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${user.code}" /></td>
                        <td><c:out value="${user.name}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${user.acc_inf == 0}">先生</c:when>
                                <c:when test="${user.acc_inf == 1}">生徒</c:when>
                                <c:otherwise>保護者</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${user.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>

                                    <form action="${pageContext.request.contextPath}/group_members/create?id=${user.id}" method="post">
                                        <input type="hidden" name="_token" value="${_token}" />
                                        <input type="submit" value="POSTリクエスト送信" style="WIDTH: 200px; HEIGHT: 20px">
                                    </form>

                                    <a href="#" onclick="confirmMakeMember();">登録する</a>
                                    <form method="POST" action="${pageContext.request.contextPath}/group_members/create?id=${user.id}">
                                        <input type="hidden" name="_token" value="${_token}" />
                                    </form>
                                    <script>
                                    function confirmMakeMember() {
                                        if(confirm("登録してよろしいですか？")) {
                                            document.forms[0].submit();
                                            }
                                        }
                                    </script>

                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${users_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((users_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/users/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/users/new' />">新規ユーザーの登録</a></p>

    </c:param>
</c:import>