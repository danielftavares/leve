<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="cad" title="product.cad">
	<l:header />
	<l:form dto="org.leve.vbg.beans.common.Product" action="/product" >
		<l:input attribute="key" disabled="true"/>
		<l:lookup attribute="productType"/>
		<l:lookup attribute="brand"/>
		<l:input attribute="code"/>
		<l:input attribute="purchasePrice"/>
		<l:input attribute="salePrice"/>
	</l:form>
</l:page>