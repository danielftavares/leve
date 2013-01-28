<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="domain.find">
	<l:header />
	<l:form dto="org.leve.beans.DomainFindDto" action="/domain/list" id="find" >
		<l:input attribute="name" />
	</l:form>
	<l:table formId="find">
		<l:collumn label="name" attribute="name" width="100" />
	</l:table>
</l:page>