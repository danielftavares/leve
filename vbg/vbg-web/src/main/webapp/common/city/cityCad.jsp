<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="cad" title="city.cad" >
	<l:header  />
	<l:form dto="org.leve.vbg.beans.common.City" action="/city" >
		<l:goTo attribute="state"/>
		
		<l:formColDef>
			<l:formLine>
				<l:input attribute="name"/>
				<l:select attribute="state"/>
			</l:formLine>
		</l:formColDef>
	</l:form>
</l:page>