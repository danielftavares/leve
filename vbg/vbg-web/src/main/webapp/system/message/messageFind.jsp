<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="message.find">
	<l:header />
	<l:table  dto="org.leve.beans.MessageFindDto" listAction="/message/list">
		<l:collumn label="locale" attribute="locale" width="30" >
			<l:filter>
				<l:select attribute="locale" />
			</l:filter>
		</l:collumn>
		<l:collumn label="key" attribute="key" width="30" >
			<l:filter>
				<l:input attribute="key" uppercase="false" />
			</l:filter>
		</l:collumn>
		<l:collumn label="message" attribute="message" width="40">
			<l:filter>
				<l:input attribute="message" uppercase="false" />
			</l:filter>
		</l:collumn>
	</l:table>
</l:page>