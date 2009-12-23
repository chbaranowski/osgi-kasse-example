<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Verwaltung der Verkäufer des Basars</title>
</head>
<body>
<f:view>
	<h:form id="sellers">
		
		<p><h:commandLink value="Kasse" action="Kasse" /></p>
	    <p><h1>Verwaltung der Verkäufer</h1></p>
	    
	    <p><h:messages /></p>
	    
	    <h:dataTable id="sellerList" value="#{sellerController.sellerList}" var="seller" border="1">
			<h:column>
				<f:facet name="header">
                   <h:outputText value="Nummer"/>
                </f:facet> 
                <h:outputText value="#{seller.basarNumber}"/>
			</h:column>
			<h:column>
				<f:facet name="header" >
                   <h:outputText value="Name"/>
                </f:facet> 
                <h:outputText value="#{seller.name}"/>
			</h:column>
			<h:column>
				<h:commandLink id="edit" value="Bearbeiten" action="#{sellerController.editSellerAction}"/><br></br>
				<h:commandLink id="delete" value="Löschen" action="#{sellerController.deleteSellerAction}"/><br></br>
			</h:column>
		</h:dataTable>
		
		<p><h:commandButton id="newSeller"  value="Neuen Verkäufer anlegen" action="#{sellerController.newSellerAction}"/></p>
		<p><h:commandButton id="newSellers" value="Liste Neuer Verkäufer anlegen" action="newListCustomers"/></p>
		
	</h:form>
</f:view>
</body>
</html>