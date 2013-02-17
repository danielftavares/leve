<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="domain.find">
	<l:header />
	<l:table dto="org.leve.beans.DomainFindDto" listAction="/domain/list">
		<l:collumn label="name" attribute="name" width="100" />
	</l:table>
</l:page>