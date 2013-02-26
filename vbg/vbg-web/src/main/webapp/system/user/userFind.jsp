<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="user.find">
	<l:header />
	<l:table dto="org.leve.beans.UserFindDto" listAction="/user/list">
		<l:collumnFunction />
		<l:collumn label="key" attribute="key" width="40">
			<l:filter>
				<l:input attribute="key" uppercase="false" />
			</l:filter>
		</l:collumn>  
		<l:collumn label="name" attribute="name" width="60">
			<l:filter>
				<l:input attribute="name" uppercase="false" />
			</l:filter>
		</l:collumn>
	</l:table>
</l:page>