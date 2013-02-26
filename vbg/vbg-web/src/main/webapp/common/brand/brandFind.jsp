<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="brand.find">
	<l:header />
	<l:table dto="org.leve.vbg.beans.common.BrandFindDto" listAction="/brand/list">
		<l:collumnFunction />
		<l:collumn label="name" attribute="name" width="100" />  
	</l:table>
</l:page>