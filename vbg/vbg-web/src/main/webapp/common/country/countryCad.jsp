<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="cad" title="country.cad">
	<l:header />
	<l:form dto="org.leve.vbg.beans.common.Country" action="/country">
		<l:formColDef cols="1">
			<l:formLine>
				<l:input attribute="abbreviation" />
			</l:formLine>
			<l:formLine>
				<l:input attribute="name" />
			</l:formLine>
		</l:formColDef>
	</l:form>
</l:page>