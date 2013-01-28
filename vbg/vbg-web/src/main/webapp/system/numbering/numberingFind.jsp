<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="numbering.find">
	<l:header />
	<l:form dto="org.leve.beans.NumberingFindDto" action="/numbering/list" id="find" >
		<l:input attribute="clazz" label="class" uppercase="false"/>
	</l:form>
	<l:table formId="find">
		<l:collumn label="class" attribute="clazz" width="30" />
		<l:collumn label="format" attribute="format" width="30" />
		<l:collumn label="counter" attribute="counter" width="20" />
		<l:collumn label="year" attribute="year" width="20" />  
	</l:table>
</l:page>