<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="group.find">
	<l:header />
	<l:form dto="org.leve.beans.GroupFindDto" action="/group/list" id="find" >
		<l:input attribute="name" uppercase="false"/>
	</l:form>
	<l:table formId="find">
		<l:collumn label="name" attribute="name" width="100" />
	</l:table>
</l:page>