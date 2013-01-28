<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="cad" title="user.cad">
	<l:header />
	<l:form dto="org.leve.beans.User" action="/user" >
		<l:input attribute="key" disabled="true"/>
		<l:input attribute="name" uppercase="false"/>
		<l:input attribute="password" uppercase="false"/>
	</l:form>
</l:page>