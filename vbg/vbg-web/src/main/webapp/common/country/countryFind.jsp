<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="country.find">
	<l:header />
	<l:table listAction="/country/list" dto="org.leve.vbg.beans.common.CountryFindDto">
		<l:collumn label="abbreviation" attribute="abbreviation" width="20" />
		<l:collumn label="name" attribute="name" width="80" />  
	</l:table>
</l:page>