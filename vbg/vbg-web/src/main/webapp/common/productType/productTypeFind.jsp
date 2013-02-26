<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="productType.find">
	<l:header />
	<l:table dto="org.leve.vbg.beans.common.ProductTypeFindDto" listAction="/productType/list" >
		<l:collumnFunction />
		<l:collumn label="name" attribute="name" width="100" />  
	</l:table>
</l:page>