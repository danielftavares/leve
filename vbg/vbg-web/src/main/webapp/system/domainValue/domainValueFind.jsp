<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="domainValue.find">
	<l:header />
	<l:table dto="org.leve.beans.DomainValueFindDto" listAction="/domainValue/list">
		<l:collumnFunction />
		<l:collumn label="domain" attribute="domain.name" width="30">
			<l:filter>
				<l:lookup attribute="domain" />
			</l:filter>
		</l:collumn>
		<l:collumn label="index" attribute="index" width="20" filtrable="false" />
		<l:collumn label="value" attribute="value" width="20" >
			<l:filter>
				<l:input attribute="value" uppercase="false" />
			</l:filter>
		</l:collumn>
		<l:collumn label="description" attribute="description" width="30">
			<l:filter>
				<l:input attribute="description" uppercase="false" />
			</l:filter>
		</l:collumn>
	</l:table>
</l:page>