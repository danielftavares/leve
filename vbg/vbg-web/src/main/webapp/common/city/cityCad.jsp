<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="cad" title="city.cad">
	<l:header />
	<l:form dto="org.leve.vbg.beans.common.City" action="/city" >
		<l:input attribute="name"/>
		<l:lookup attribute="state"/>
	</l:form>
</l:page>