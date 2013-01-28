<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="cad" title="invoice.cad">
	<l:header />
	<l:form dto="org.leve.vbg.beans.stock.Invoice" action="/invoice" >
		<l:input attribute="number"/>
		<l:input attribute="serial"/>
		<l:input attribute="dtIssuance"/>
		<l:input attribute="dtDeparture"/>
		<l:input attribute="dtArrive"/>
		
	</l:form>
</l:page>