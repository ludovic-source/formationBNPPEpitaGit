<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
	</head>
	<body>
	
		<h1>Bienvenue sur mon site</h1>
		<%@ include file="menu.jsp" %>		

	    <c:if test="${ !empty fichier }">
	    	<p><c:out value="Le fichier ${ fichier } (${ description }) a été uploadé !" /></p>
	    </c:if>
	    
	    <form method="post" action="chargementFichier" enctype="multipart/form-data">
	        <p>
	            <label for="description">Description du fichier : </label>
	            <input type="text" name="description" id="description" />
	        </p>
	        <p>
	            <label for="fichier">Fichier à envoyer : </label>
	            <input type="file" name="fichier" id="fichier" />
	        </p>
	        
	        <input type="submit" />
	    </form>
    
	</body>
</html>