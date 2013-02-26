<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="product.find">
	<l:header />
	<l:table dto="org.leve.vbg.beans.common.ProductFindDto" listAction="/product/list" >
		<l:collumnFunction />
		<l:collumn attribute="key" label="key"  width="20" />
		<l:collumn attribute="brand.name" label="brand" width="30" >
			<l:filter>
				<l:lookup attribute="brand"/>
			</l:filter>
		</l:collumn>
		<l:collumn attribute="productType.name" label="productType" width="30" >
			<l:filter>
				<l:lookup attribute="productType"/>
			</l:filter>
		</l:collumn>
		<l:collumn attribute="code" label="code" width="20" />   
	</l:table>
</l:page>