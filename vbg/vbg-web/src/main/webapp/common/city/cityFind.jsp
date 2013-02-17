<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="find" title="city.find">
	<l:header />
	<l:table dto="org.leve.vbg.beans.common.CityFindDto" listAction="/city/list" >
		<l:collumn label="name" attribute="name" width="40" />
		<l:collumn label="state" attribute="state.name" width="30">
			<l:filter>
				<l:select attribute="state" />
			</l:filter>
		</l:collumn>
		<l:collumn label="country" attribute="state.country.name" width="30" filtrable="false"/>  
	</l:table>
</l:page>