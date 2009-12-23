<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Verwaltung der Positionen des Basars</title>
</head>
<body>
<f:view>
	<h:form id="sellers">
	    
	    <p><h:commandLink value="Kasse" action="Kasse" immediate="true"/></p>
	    <p><h1>Verwaltung der Positionen</h1></p>
	    
	    <p><h:messages /></p>
	    
	    <h:dataTable id="sellerList" value="#{positionController.positionList}" var="position" border="1">
			<h:column>
				<f:facet name="header">
                   <h:outputText value="Kasse"/>
                </f:facet> 
                <h:outputText value="#{position.positionKey.kasse}"/>
			</h:column>
			<h:column>
				<f:facet name="header">
                   <h:outputText value="Nummer"/>
                </f:facet> 
                <h:outputText value="#{position.positionKey.number}"/>
			</h:column>
			<h:column>
				<f:facet name="header">
                   <h:outputText value="Uhrzeit"/>
                </f:facet> 
                <h:outputText value="#{position.createTime}"/>
			</h:column>
			<h:column>
				<f:facet name="header">
                   <h:outputText value="Preis"/>
                </f:facet> 
                <h:outputText value="#{position.price}"/>
			</h:column>
			<h:column>
				<f:facet name="header">
                   <h:outputText value="Beschreibung"/>
                </f:facet> 
                <h:outputText value="#{position.description}"/>
			</h:column>
			<h:column>
				<f:facet name="header">
                   <h:outputText value="Typ"/>
                </f:facet> 
                <h:outputText value="#{position.type}"/>
			</h:column>
			<h:column>
				<f:facet name="header">
                   <h:outputText value="Verkäufer"/>
                </f:facet> 
                <h:outputText value="#{position.seller.name}"/>
			</h:column>
			<h:column>
				<f:facet name="header">
                   <h:outputText value="Verkäufer Basarnummer"/>
                </f:facet> 
                <h:outputText value="#{position.seller.basarNumber}"/>
			</h:column>
		</h:dataTable>
		
		<h1>Storno</h1>
		<h:messages></h:messages>
		<p>
			Basar Nummer:<br/>
			<h:inputText required="true" validator="#{positionController.validateBasarNumber}" value="#{positionController.stornoBasarNumber}"></h:inputText>
		</p>
		<p>
			Preis:<br/>
			<h:inputText rendered="true" validator="#{positionController.validatePreis}" value="#{positionController.stornoPrice}"></h:inputText>
		</p>
		<p><h:commandButton value="Storno" action="#{positionController.stornoAction}"></h:commandButton></p>
		
	</h:form>
</f:view>
</body>
</html>