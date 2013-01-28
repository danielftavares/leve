<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="city.find">
	<l:header />
	<l:form dto="org.leve.vbg.beans.common.CityFindDto" action="/city/list" id="find" >
		<l:input attribute="name"/>
		<l:lookup attribute="state" />
	</l:form>
	<l:table formId="find">
		<l:collumn label="name" attribute="name" width="40" />
		<l:collumn label="state" attribute="state.name" width="30" />
		<l:collumn label="country" attribute="state.country.name" width="30" />  
	</l:table>
</l:page>