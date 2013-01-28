<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="cad" title="group.cad">
	<l:header />
	<l:form dto="org.leve.beans.Group" action="/group" >
		<l:input attribute="name" uppercase="false"/>
	</l:form>
</l:page>