<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
	</head>
	<body>
	
		<h1>Bienvenue sur mon site</h1>
		<%@ include file="menu.jsp" %>
		
		<c:if test="${ !form.resultat }">
			<p><c:out value="${ form.resultat }" /></p>
		</c:if>
		
		<c:if test="${ !empty sessionScope.nom && !empty sessionScope.prenom }">
			<p><c:out value="Vous �tes ${ sessionScope.prenom } ${ sessionScope.nom }" /></p>
		</c:if>
		
		<form method="post" action="accueil">
			<p>
				<label for="login">Login : </label>
				<input type="text" name="login" id="login" />
			</p>	 
			<p>
				<label for="pass">Password : </label>
				<input type="password" name="pass" id="pass" />
			</p>	
			<input type="submit" />
		</form>
				
	</body>
</html>