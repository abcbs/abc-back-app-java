<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
int current =  page.getNumber() + 1;
int begin = Math.max(1, current - paginationSize/2);
int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
request.setAttribute("totalPage", page.getTotalPages());
request.setAttribute("totalElements", page.getTotalElements());
%>
					
	<% if (page.hasPreviousPage()){%>
             <a style="cursor:pointer;"   href="###" onclick="dishCatagoryChange('${billId}','${categoryId}',${current-1},'${dsCategoryId}','${keywords}','${estimateStatus}')" class="but_qx_left"></a>
      <%}else{%>
             <a class="but_qx_left_no"></a>
      <%} %>
   
    <% if (page.hasNextPage()){%>
            <a style="cursor:pointer;"   href="###" onclick="dishCatagoryChange('${billId}','${categoryId}',${current+1},'${dsCategoryId}','${keywords}','${estimateStatus}')"  class="but_qx_right"></a>		
      <%}else{%>
            <a class="but_qx_right_no"></a>		
      <%} %>
