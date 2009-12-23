<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Verkäufer anlegen</title>
</head>
<body>
<f:view>
	<h:form id="seller">
		<p><h:messages /></p>
		<p>Basar Nummer Von:</p>
		<p><h:inputText id="basarNummerVon" label="Basar Nummer Von" value="#{sellerController.basarNumberAt}" required="true"/></p>
		<p>Basar Nummer Bis:</p>
		<p><h:inputText id="basarNummerBis" label="Basar Nummer Bis" value="#{sellerController.basarNumberTo}" required="true" /></p>
	    <p><h:commandButton value="Speichern" action="#{sellerController.insertSellerListAction}"/></p>
	</h:form>
</f:view>
</body>
</html>