<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="state.find">
	<l:header />
	<l:table dto="org.leve.vbg.beans.common.StateFindDto" listAction="/state/list" >
		<l:collumnFunction />
		<l:collumn label="abbreviation" attribute="abbreviation" width="20" />
		<l:collumn label="country" width="40" attribute="country.name">
			<l:filter>
				<l:lookup attribute="country" />
			</l:filter>
		</l:collumn>
		<l:collumn label="name" attribute="name" width="40" /> 
	</l:table>
</l:page>