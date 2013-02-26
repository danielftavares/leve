<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="numbering.find">
	<l:header />
	<l:table dto="org.leve.beans.NumberingFindDto" listAction="/numbering/list" >
		<l:collumnFunction />
		<l:collumn label="class" attribute="clazz" width="30">
			<l:filter>
				<l:input attribute="clazz" label="class" uppercase="false"/>
			</l:filter>
		</l:collumn>
		<l:collumn label="format" attribute="format" width="30" filtrable="false" />
		<l:collumn label="counter" attribute="counter" width="20" filtrable="false" />
		<l:collumn label="year" attribute="year" width="20" filtrable="false" />  
	</l:table>
</l:page>