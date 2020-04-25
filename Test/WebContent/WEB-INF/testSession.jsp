<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
	</head>
	<body>
	
		<h1>Bienvenue sur mon site</h1>
		<%@ include file="menu.jsp" %>
		
		<c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
		
		<c:if test="${ !empty sessionScope.nom && !empty sessionScope.prenom }">
			<p><c:out value="Vous êtes ${ sessionScope.prenom } ${ sessionScope.nom }" /></p>
		</c:if>
		
		<c:if test="${ !empty valeurCookie }">
			<p><c:out value="Le cookie magique est : ${ valeurCookie }" /></p>
		</c:if>
		
		<form method="post" action="testSession">
			<p>
				<label for="nom">Nom : </label>
				<input type="text" name="nom" id="nom" />
			</p>	 
			<p>
				<label for="prenom">Prénom : </label>
				<input type="text" name="prenom" id="prenom" />
			</p>	
			<input type="submit" />
		</form>
		
		<ul>
	        <c:forEach var="utilisateur" items="${ utilisateurs }">
	            <li><c:out value="${ utilisateur.prenom }" /> <c:out value="${ utilisateur.nom }" /></li>
	        </c:forEach>
    	</ul>  
				
	</body>
</html>