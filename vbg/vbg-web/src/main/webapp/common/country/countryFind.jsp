<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="country.find">
	<l:header />
	<l:form dto="org.leve.vbg.beans.common.CountryFindDto" action="/country/list" id="find" >
		<l:input label="abbreviation" attribute="abbreviation"/>
		<l:input label="name" attribute="name"/>
	</l:form>
	<l:table formId="find">
		<l:collumn label="abbreviation" attribute="abbreviation" width="20" />
		<l:collumn label="name" attribute="name" width="80" />  
	</l:table>
</l:page>