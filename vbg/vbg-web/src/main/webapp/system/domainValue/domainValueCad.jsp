<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="cad" title="domainValue.cad">
	<l:header />
	<l:form dto="org.leve.beans.DomainValue" action="/domainValue" >
		<l:lookup attribute="domain"/>
		<l:input attribute="value" uppercase="false" />
		<l:input attribute="description" uppercase="false" />
		<l:input attribute="index" />
	</l:form>
</l:page>