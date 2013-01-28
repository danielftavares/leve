<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="cad" title="country.cad">
	<l:header />
	<l:form dto="org.leve.vbg.beans.common.Country" action="/country" >
		<l:input attribute="abbreviation"/>
		<l:input attribute="name"/>
	</l:form>
</l:page>