<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="user.find">
	<l:header />
	<l:form dto="org.leve.beans.UserFindDto" action="/user/list" id="find" >
		<l:input attribute="key" uppercase="false" />
		<l:input attribute="name" uppercase="false" />
	</l:form>
	<l:table formId="find">
		<l:collumn label="key" attribute="key" width="40" />  
		<l:collumn label="name" attribute="name" width="60" />
	</l:table>
</l:page>