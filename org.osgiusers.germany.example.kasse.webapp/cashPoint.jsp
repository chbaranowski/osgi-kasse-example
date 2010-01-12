<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Basar Kasse</title>
</head>
<body>

<img src="logo.jpg" width="50"/> 

<f:view>
	<h:form id="cashPoint">
	
		<h1>Summe: <h:outputText id="summe" value="#{cashPointController.summe}"/> Euro</h1>
		
		<h:messages></h:messages>
		
		<p>
			Basar Number:<br></br>
			<h:inputText id="number" 
				label="Basar Nummer" 
				value="#{cashPointController.basarNumber}" 
				required="true"
				validator="#{cashPointController.validateBasarNumber}"
				size="50">
			</h:inputText>
		</p>
		
		<p>
			Preis:<br></br>
			<h:inputText id="price" 
				label="Preis" 
				value="#{cashPointController.price}" 
				required="true"
				validator="#{cashPointController.validatePreis}"
				size="50" >
			</h:inputText>
		</p>
		
		<p>
			Beschreibung:<br></br>
			<h:inputText id="description" 
				label="Beschreibung" 
				value="#{cashPointController.description}" 
				required="false"
				size="50" />
		</p>
		
		<p>
			<h:commandButton id="add" type="submit" value="Weiter" action="#{cashPointController.addPositionAction}"/>
			<h:commandButton id="purchase" value="Bezahlen" immediate="true" action="#{cashPointController.purchaseAction}"/>
			<h:commandButton id="storno" value="Stornieren" immediate="true" action="#{cashPointController.stornoAction}"/>
		</p>
		
		<p>
			<h:dataTable id="positionList" 
				value="#{cashPointController.salePositions}" 
				var="position" 
				border="1"
				rendered="#{cashPointController.salePositions.rowCount > 0}">
				<h:column>
					<f:facet name="header">
	                   <h:outputText value="Verkäufer - Nummer"/>
	                </f:facet> 
	                <h:outputText value="#{position.seller.basarNumber}"/>
				</h:column>
				<h:column>
					<f:facet name="header">
	                   <h:outputText value="Verkäufer"/>
	                </f:facet> 
	                <h:outputText value="#{position.seller.name}"/>
				</h:column>
				<h:column>
					<f:facet name="header">
	                   <h:outputText value="Beschreibung"/>
	                </f:facet> 
	                <h:outputText value="#{position.description}"/>
				</h:column>
				<h:column>
					<f:facet name="header">
	                   <h:outputText value="Preis (Euro)"/>
	                </f:facet> 
	                +<h:outputText value="#{position.printPrice}"/> Euro
				</h:column>
				<h:column>
					<h:commandLink value="Storno" immediate="true" action="#{cashPointController.removePositionAction}" />
				</h:column>
			</h:dataTable>
		</p>
	
		<hr/><br/>
		<h1>Umsatz: <h:outputText id="umsatz" value="#{cashPointController.umsatz}"/> Euro</h1>
		<h1>Gewinn: <h:outputText id="gewinn" value="#{cashPointController.gewinn}"/> Euro</h1>
		<hr/><br/>
	
		<p><b>Menü (weitere Funktionen)</b></p>
		<p><h:commandLink immediate="true" value="Verkäufer Verwalten" action="customers"/></p>
		<p><h:commandLink immediate="true" value="Übersicht der Positionen" action="position"/></p>		
		
	</h:form>
</f:view>
</body>
</html>