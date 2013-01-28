<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="cad" title="message.cad">
	<l:header />
	<l:form dto="org.leve.beans.Message" action="/message" >
		<l:input attribute="key" uppercase="false"/>
		<l:input attribute="message" uppercase="false"/>
		<l:select attribute="locale" />
	</l:form>
</l:page>