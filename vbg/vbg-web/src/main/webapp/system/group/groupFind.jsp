<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="group.find">
	<l:header />
	<l:table dto="org.leve.beans.GroupFindDto" listAction="/group/list">
		<l:collumn label="name" attribute="name" width="100" />
	</l:table>
</l:page>