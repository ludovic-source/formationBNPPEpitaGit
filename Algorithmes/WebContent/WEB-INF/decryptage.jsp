<!DOCTYPE html>

<html>
	
	<head>
		<meta charset="UTF-8">
		<title>Algorithmes : chiffrement - décryptage</title>
	</head>

	<body>

		<h1>ALGORITHMES</h1>
		<h2>chiffrement & décryptage</h2>
		
		<p><a href = "decryptage">Réinitialiser</a></p>
	
		<c:if test="${ empty texteNonChiffre }">	
			<form method="post" action="decryptage">
				<p>
					<label for="texteNonChiffre">Texte à chiffrer : </label><br />
					<textarea name="texteNonChiffre" id="texteNonChiffre" rows="10" cols="100">Ecrivez dans cette zone le texte que vous souhaitez chiffrer</textarea>
					<br /><br />
					<label for="decalage">Decalage souhaité :</label>
					<select name="decalage" id="decalage">
						<c:forEach var="i" begin="0" end="25" step="1">		
							<option value="${i}">${i}</option>
						</c:forEach>					
					</select>
				</p>		
				<input type="submit" value="lancer le chiffrement" />
			</form>
		</c:if>

		<c:if test="${ !empty texteNonChiffre }">
				<p><strong>Le texte avant chiffrement est : </strong></p>
				<p>${ texteNonChiffre }</p>
		</c:if>

		<c:if test="${ !empty texteChiffre }">
				<p><strong>Le texte chiffré est : </strong></p>
				<p>${ texteChiffre }</p>
		</c:if>
		
		<c:if test="${ !empty texteDecrypte }">
				<p><strong>Le texte décrypté est : </strong></p>
				<p>${ texteDecrypte }</p>
		</c:if>
		
		<c:if test="${ !empty tabLettresAdjacentes }">
			<table>
					<caption>Statistiques des lettres adjacentes :</caption>					
					<tbody>				
						<c:forEach items="${ tabLettresAdjacentes }" var="ligne">
								<tr>
									<c:forEach items="${ ligne }" var="colonne">
										<th><c:out value="${ colonne }" /></th>
									</c:forEach>
								</tr>									
						</c:forEach>
					</tbody>
			</table>
		</c:if>
		
		<c:if test="${ !empty mapFrequenceLettres }">
			<table>
				<caption>Analyse de frequence d'apparition des lettres (en %) :</caption>
				<thead>
					<tr>
						<th>Lettre</th>
						<th>Frequence</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lettres" items="${ mapFrequenceLettres }">
						<tr>
							<th><c:out value="${ lettres.key }" /></th>
							<th><c:out value="${ lettres.value }" /></th>
						</tr>
					</c:forEach>						
				</tbody>		
			</table>		
		</c:if>

	</body>	
	
</html>