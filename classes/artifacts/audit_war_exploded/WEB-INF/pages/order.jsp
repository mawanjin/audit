<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html>

<head>
    <title>order list</title>
<script>

    function pre(){
        document.getElementById('pageNO').value = parseInt(document.getElementById('pageNO').value)-1;
        document.forms['frm'].submit();
    }

    function nxt(){
        document.getElementById('pageNO').value = parseInt(document.getElementById('pageNO').value)+1;
        document.forms['frm'].submit();
    }

    function resumePageNO(){
        document.getElementById('pageNO').value=1;
    }

</script>
</head>

<body>

<form name="frm" action="${ctx}/audit/order" method="get">

<input id="pageNO" name="pageNO" type="hidden" value="${page.pageNO}">

    <div style="margin: 20px;">
        <select name="walletType" onchange="resumePageNO()">
            <option value="-" <c:if test="${walletType eq '-'}">selected</c:if>>全部</option>
            <option value="null" <c:if test="${walletType eq 'null'}">selected</c:if>>null</option>
            <option value="coupon" <c:if test="${walletType eq 'coupon'}">selected</c:if>>代金券</option>
            <option value="coupon_red_envelope" <c:if test="${walletType eq 'coupon_red_envelope'}">selected</c:if>>代金券+红包</option>
            <option value="coupon_wallet" <c:if test="${walletType eq 'coupon_wallet'}">selected</c:if>>代金券+啪币</option>
            <option value="red_envelope" <c:if test="${walletType eq 'red_envelope'}">selected</c:if>>红包</option>
            <option value="wallet" <c:if test="${walletType eq 'wallet'}">selected</c:if>>啪币</option>
        </select>

        开始日期: <input type="date" name="startTime" value="${startTime}" onchange="resumePageNO()" />  结束日期: <input type="date" name="endTime" value="${endTime}" onchange="resumePageNO()" />

        &nbsp;&nbsp;&nbsp;&nbsp;订单号:<input type="text" placeholder="请输入要查询的订单号" name="orderId" value="${orderId}" >
    </div>

<table border="1" cellpadding="0" cellspacing="0">
    <tr>
        <th>Id</th>
        <%--<th>商品编号</th>--%>
        <th>商品名称</th>
        <th>appOrderId</th>
        <%--<th>payType</th>--%>
        <th>openuid</th>
        <th>order_id</th>
        <th>time</th>
        <th>支付方式</th>
        <th>金额</th>
        <th>action</th>
        <th>walletType</th>
    </tr>

    <c:forEach items="${page.list}" var="order">

            <td>${order.id}</td>
            <%--<td>${order.productId}</td>--%>
            <td>${order.productName}</td>
            <td>${order.appOrderId}</td>
            <%--<td>${order.payType}</td>--%>
            <td>${order.openuid}</td>
            <td>${order.orderId}</td>
            <td>
                <fmt:formatDate pattern="yyyy-MM-dd" value="${order.time}"  />
            </td>
            <td>
                <c:choose>
                    <c:when test="${order.qudao eq '8'}">
                        骏网
                    </c:when>
                    <c:when test="${order.qudao eq '13'}">
                        支付宝
                    </c:when>

                    <c:when test="${order.qudao eq '88'}">
                        捷迅
                    </c:when>

                    <c:when test="${order.qudao eq '119'}">
                        mo9
                    </c:when>

                    <c:when test="${order.qudao eq '327'}">
                        银联
                    </c:when>

                    <c:when test="${order.qudao eq '328'}">
                        微信
                    </c:when>

                    <c:when test="${order.qudao eq '334'}">
                        啪币
                    </c:when>

                    <c:when test="${order.qudao eq '343'}">
                        代金卷
                    </c:when>

                    <c:otherwise>
                        ${order.qudao}
                    </c:otherwise>

                </c:choose>

            </td>
        <td>${order.payAmount}</td>
        <td>${order.action}</td>
        <td>${order.walletType}</td>
        </tr>
    </c:forEach>
</table>
    <!--分页-->
    <div style="margin-top: 30px;">
    共${page.totalCount}条 第${page.pageNO}页
        <c:choose>
            <c:when test="${page.hasPre}">
                <a href="#" onclick="pre()">上一页</a>
            </c:when>
            <c:otherwise>上一页</c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${page.hasNxt}"><a href="#" onclick="nxt()">下一页</a></c:when>
            <c:otherwise>下一页</c:otherwise>
        </c:choose>



        每页<input name="pageSize" type="text" value="${page.pageSize}" onchange="resumePageNO()" /> 条 共${page.totalPage}页
    </div>

    <input type="submit" value="提交" />
</form>
</body>
</html>