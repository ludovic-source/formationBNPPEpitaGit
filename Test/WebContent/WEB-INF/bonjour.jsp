<!DOCTYPE html>
<html>
	<head>
		<meta charset=\"utf-8\" />
		<title>Test</title>
	</head>
	<body>
	
		<%@ include file="menu.jsp" %>
		
		<p>Essai JSTL, nom : <c:out value="${ name }" default="vous avez oublié de mettre le name en paramètre" /></p>
		
		<c:set var="pseudo" value="kiki" scope="page" />
		<p><c:out value="${ pseudo }" /></p>
		
		<!--  si on veut supprimer la valeur de la mémoire -->
		<c:remove var="pseudo" scope="page" />
	
		<!--  si on veut modifier l'objet bean auteur, le prenom ici -->	
		<c:set target="${ auteur }" property="prenom" value="Mary" />
		<p><c:out value="${ auteur.prenom }" /></p>
		
		<p>Voici un test en JSTL<br /></p>
		<c:if test="${ 50 > 10 }" var="variable"> <!-- var permet de conserver le resultat du if pour le réutiliser plus loin dans la page HTML, voir dans la session avec scope="session" -->
    		C'est vrai !
		</c:if>
		
		<!--  Comme en JSTL on ne peut pas faire de else, voici une autre alternative  -->
		<p>Voici un autre test multiple</p>
		<c:choose>
		    <c:when test="${ variable }">Du texte</c:when>
		    <c:when test="${ autreVariable }">Du texte</c:when>
		    <c:when test="${ encoreUneAutreVariable }">Du texte</c:when>
		    <c:otherwise>loupé</c:otherwise>
		</c:choose>
		
		<!-- TEST JSTL sur les boucles forEach -->
		<c:forEach var="i" begin="0" end="10" step="2">
    		<p>Un message n°<c:out value="${ i }" /> !</p>
		</c:forEach>
		
		<c:forEach items="${ titres }" var="titre" varStatus="status">
   		 	<p>N°<c:out value="${ status.count }" /> : <c:out value="${ titre }" /> !</p>
		</c:forEach>
		
		<c:forEach items="${ titres }" var="titre" begin="0" end="1">
   		 	<p><c:out value="${ titre }" /> !</p>
		</c:forEach>
		
		<!--  boucle forTokens reservée aux découpage des chaines de caractères -->
		<c:forTokens var="morceau" items="Un élément/Encore un autre élément/Un dernier pour la route" delims="/">
    		<p>${ morceau }</p>
		</c:forTokens>
		 
		<c:forTokens var="morceau" items="Un élément/Encore un autre élément/Un dernier pour la route" delims="/ ">
    		<p>${ morceau }</p>
		</c:forTokens>
		
		<!--  tests avec les Expression Language -->
		<p>Bonjour ${ auteur.prenom } ${ auteur.nom }</p>
		<p>${ auteur.actif ? 'Vous êtes très actif !' : 'Vous êtes inactif !' }</p>
		
		<p>Bonjour ${ empty name ? 'inconnu' : name } </p>
		
		<p>${ noms[0] }</p>

	
		<p>
			<%
				String heure = (String) request.getAttribute("heure");
				String name = (String) request.getAttribute("name");
			
				if (heure.equals("jour")) {
					out.println("Bonjour ");
					//out.println(name);
					out.println(" !");
				}
				else {
					out.println("Bonsoir ");
					//out.println(name);
					out.println(" !");
				}
			%>
		</p>
		
		<p>
		    <%
		        for (int i = 0 ; i < 20 ; i++) {
		            out.println("Une nouvelle ligne !<br />");
		        }
		    %>
		</p>

		
	</body>
</html>