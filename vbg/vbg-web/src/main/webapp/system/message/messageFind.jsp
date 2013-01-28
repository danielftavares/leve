<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="message.find">
	<l:header />
	<l:form dto="org.leve.beans.MessageFindDto" action="/message/list" id="find">
		<l:input attribute="key" uppercase="false" />
		<l:input attribute="message" uppercase="false" />
		<l:select attribute="locale" />
	</l:form>
	<l:table formId="find">
		<l:collumn label="locale" attribute="locale" width="30" />
		<l:collumn label="key" attribute="key" width="30" />
		<l:collumn label="message" attribute="message" width="40" />
	</l:table>
</l:page>