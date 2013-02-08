<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<l:page pageType="main">
	<l:header />








	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".navbar-inverse-collapse"> <span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">Vestindo by Gerda</a>
				<div class="nav-collapse collapse navbar-inverse-collapse">
					<ul class="nav pull-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">${pageContext.request.userPrincipal.name}<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#"><l:label label="shortcuts" /></a></li>
								<li><a href="#"><l:label label="settings" /></a></li>
								<li class="divider"></li>
								<li><a href="leve/user/logout"><l:label label="logout" /></a></li>
							</ul></li>
					</ul>
				</div>
				<!-- /.nav-collapse -->
			</div>
		</div>
		<!-- /navbar-inner -->
	</div>





	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2">
				<div id="sm_menu" class="well sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header">Cadastro</li>
						<li><a href="common/country/countryCad.jsp">Pais</a></li>
						<li><a href="common/state/stateCad.jsp">Estado</a></li>
						<li><a href="common/city/cityCad.jsp">Cidade</a></li>
						<li><a href="common/brand/brandCad.jsp">Marca</a></li>
						<li><a href="common/productType/productTypeCad.jsp">Tipo
								Produto</a></li>
						<li><a href="common/product/productCad.jsp">Produto</a></li>
						<li class="nav-header">Estoque</li>
						<li><a href="stock/invoice/invoiceCad.jsp">Nota Fiscal</a></li>
						<li class="nav-header">Financeiro</li>
						<li><a href="#">Link</a></li>
						<li class="nav-header">Sistema</li>
						<li><a href="system/user/userCad.jsp">Usuário</a></li>
						<li><a href="system/group/groupCad.jsp">Grupo</a></li>
						<li><a href="system/numbering/numberingCad.jsp">Numeração</a></li>
						<li><a href="system/domain/domainCad.jsp">Domínio</a></li>
						<li><a href="system/domainValue/domainValueCad.jsp">Valor Domínio</a></li>
						<li><a href="system/message/messageCad.jsp">Mensagem</a></li>
					</ul>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
			<div class="span10" id="sm_content"></div>
			<!--/span-->
		</div>
		<!--/row-->
	</div>
	<!--/.fluid-container-->
</l:page>