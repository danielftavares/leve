<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="product.find">
	<l:header />
	<l:form dto="org.leve.vbg.beans.common.ProductFindDto" action="/product/list" id="find" >
		<l:input attribute="key"/>
		<l:lookup attribute="brand"/>
		<l:lookup attribute="productType"/>
	</l:form>
	<l:table formId="find">
		<l:collumn attribute="key" label="key"  width="20" />
		<l:collumn attribute="brand.name" label="brand" width="30" />
		<l:collumn attribute="productType.name" label="productType" width="30" />
		<l:collumn attribute="code" label="code" width="20" />   
	</l:table>
</l:page>