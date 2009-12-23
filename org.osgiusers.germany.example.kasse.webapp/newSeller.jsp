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
		<p>Basar Nummer:</p>
		<p><h:inputText id="basarNummer" label="Basar Nummer" value="#{sellerController.basarNumber}" required="true"/></p>
		<p>Nachname: </p>
		<p><h:inputText id="name" label="Nachname" value="#{sellerController.name}" required="false" /></p>
	    <p><h:commandButton value="Speichern" action="#{sellerController.insertSellerAction}"/></p>
	</h:form>
</f:view>
</body>
</html>