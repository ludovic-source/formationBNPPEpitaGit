<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
	</head>
	<body>
	
		<h1>Rencontre avec One Piece</h1>
		<h2>En route vers Grand Line !</h2>
		
		<ul>
			<li>
				<%
					String nomPersonnage = (String) request.getAttribute("name");
					out.println(nomPersonnage);
				%>
			</li>
		</ul>	
		
	</body>
</html>