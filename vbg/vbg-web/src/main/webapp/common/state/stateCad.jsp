<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="cad" title="state.cad">
	<l:header />
	<l:form dto="org.leve.vbg.beans.common.State" action="/state">
		<l:goTo attribute="country" />
		<l:formColDef>
			<l:formLine>
				<l:input attribute="abbreviation" />
				<l:input attribute="name" />
			</l:formLine>
			<l:formLine>
				<l:lookup attribute="country" />
			</l:formLine>
		</l:formColDef>
	</l:form>
</l:page>