<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="state.find">
	<l:header />
	<l:form dto="org.leve.vbg.beans.common.StateFindDto" action="/state/list" id="find" >
		<l:input attribute="abbreviation"/>
		<l:input attribute="name"/>
		<l:lookup attribute="country" />
	</l:form>
	<l:table formId="find">
		<l:collumn label="abbreviation" attribute="abbreviation" width="20" />
		<l:collumn label="name" attribute="name" width="80" />  
	</l:table>
</l:page>