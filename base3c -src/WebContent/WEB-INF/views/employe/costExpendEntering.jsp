<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>会员</title>
<script type="text/javascript">
var toEdit='${toEdit}';
var eid='${costExpend.id}';
</script>
<script type="text/javascript" src="${ctx}/static/js/CanYinValidate.js"></script>
<script type="text/javascript" src="${ctx}/static/js/employe/costExpend.js"></script>
</head>
<body>
 <div class="jiaojieban_popup">

 <form action="${ctx}/employe/costExpendsubmit/<c:if test="${costExpend.id eq null}">create</c:if>
	<c:if test="${costExpend.id ne null}">update</c:if>" id="costExpendsubmitForm" name="costExpendsubmitForm">
    <div class="jjb_div">原料成本支出（单位：元 ）</div>
    <input type="hidden" name="id" value="${costExpend.id}"/>
    <table cellpadding="0" cellspacing="0">
      <tr>
        <td class="td">原料消耗：</td>
        <td><input class="jjb_input input-shorter isInteger"  type="text" id="materialConsumption" name="materialConsumption" value="${costExpend.materialConsumption}" ></td>
        <td class="td">运输费：</td>
        <td><input class="jjb_input input-shorter isInteger" type="text" id="transportCost" name="transportCost" value="${costExpend.transportCost}"></td>
      </tr>
      <tr>
        <td class="td">其他：</td>
        <td><input class="jjb_input" type="text"  name="materialOther" id="materialOther" value="${costExpend.materialOther}"></td>
      </tr>
    </table>
    <div class="jjb_div">人工成本支出（单位：元）</div>
    <table cellpadding="0" cellspacing="0">
      <tr>
        <td class="td">工资：</td>
        <td><input class="jjb_input" type="text" id="salary"  name="salary" value="${costExpend.salary}"></td>
        <td class="td">伙食费：</td>
        <td><input class="jjb_input" type="text" id="foodCost"  name="foodCost" value="${costExpend.foodCost}"></td>
      </tr>
      <tr>
        <td class="td">员工福利：</td>
        <td><input class="jjb_input" type="text" id="employeeBenefit"  name="employeeBenefit" value="${costExpend.employeeBenefit}"></td>
        <td class="td">其他：</td>
        <td><input class="jjb_input" type="text" id="laborOther"  name="laborOther" value="${costExpend.laborOther}"></td>
      </tr>
    </table>
    <div class="jjb_div">其他成本支出（单位：元）</div>
    <table cellpadding="0" cellspacing="0">
      <tr>
        <td class="td">房租：</td>
        <td><input class="jjb_input" type="text" id="chummage"  name="chummage" value="${costExpend.chummage}"></td>
        <td class="td">税费：</td>
        <td><input class="jjb_input" type="text" id="taxesCost"  name="taxesCost" value="${costExpend.taxesCost}"></td>
      </tr>
      <tr>
        <td class="td">水费：</td>
        <td><input class="jjb_input" type="text" id="waterCost"  name="waterCost" value="${costExpend.waterCost}"></td>
        <td class="td">电费：</td>
        <td><input class="jjb_input" type="text" id="electricCost"  name="electricCost" value="${costExpend.electricCost}"></td>
      </tr>
      <tr>
        <td class="td">天然气：</td>
        <td><input class="jjb_input" type="text" id="natgas"  name="natgas" value="${costExpend.natgas}"></td>
        <td class="td">维修：</td>
        <td><input class="jjb_input" type="text" id="maintain"  name="maintain" value="${costExpend.maintain}"></td>
      </tr>
      <tr>
        <td class="td">消耗品：</td>
        <td><input class="jjb_input" type="text" id="consumables"  name="consumables" value="${costExpend.consumables}"></td>
        <td class="td">其他：</td>
        <td><input class="jjb_input" type="text" id="costOther"  name="costOther" value="${costExpend.costOther}"></td>
      </tr>
    </table>
    <div style="position:absolute;bottom:50px;left:200px;">
    <a href="javascript:void(0);" class="tj_but" onclick="" id="submit">提交</a>
    <a id="cancel" href="javascript:void(0);" class="qx_but" onclick="">取消</a></div>
    
    </form>
    
  </div>

</body>
</html>