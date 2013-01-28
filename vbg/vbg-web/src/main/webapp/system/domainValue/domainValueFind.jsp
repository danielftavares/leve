<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="domainValue.find">
	<l:header />
	<l:form dto="org.leve.beans.DomainValueFindDto" action="/domainValue/list" id="find">
		<l:lookup attribute="domain" />
		<l:input attribute="value" uppercase="false" />
		<l:input attribute="description" uppercase="false" />
	</l:form>
	<l:table formId="find">
		<l:collumn label="domain" attribute="domain.name" width="30" />
		<l:collumn label="index" attribute="index" width="20" />
		<l:collumn label="value" attribute="value" width="20" />
		<l:collumn label="description" attribute="description" width="30" />
	</l:table>
</l:page>