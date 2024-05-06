<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<table class="styled-table">
<th><tr>
${naslovi}
</th></tr>
<c:forEach var="var" items="${tablica}">
    <tr><td>${var.brojTecajnice}</td>
    <td>${var.datum}</td>
    <td>${var.drzava}</td>
    <td>${var.sifraValute}</td>
    <td>${var.valuta}</td>
    <td>${var.jedinica}</td>
    <td>${var.kupovniTecaj}</td>
    <td>${var.srednjiTecaj}</td>
    <td>${var.prodajniTecaj}</td>
    <td>${var.drzavaIso}</td></tr>
</c:forEach>
</table>

</body>
</html>