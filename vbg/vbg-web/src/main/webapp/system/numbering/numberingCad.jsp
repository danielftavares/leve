<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="cad" title="numbering.cad">
	<l:header />
	<l:form dto="org.leve.beans.Numbering" action="/numbering" >
		<l:input attribute="clazz" label="class" uppercase="false" />
		<l:input attribute="format" uppercase="false" />
		<l:input attribute="counter" />
		<l:input attribute="year" />
	</l:form>
</l:page>