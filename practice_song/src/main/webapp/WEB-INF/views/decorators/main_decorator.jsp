<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html lang="kr">
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><decorator:title default="Rinoale's cabin" /></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/foundation.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/rinoale.css" />
		<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/vendor/modernizr.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/vendor/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/foundation.min.js"></script>
		<decorator:head />
	</head>
	
	<body>
		<div class="row" id="header">
			<div class="large-12 columns">
				<div class="nav-bar right">
					<ul class="button-group">
						<li><a href="/" class="button" onClick="">Link 1</a></li>
						<li><a href="/" class="button">Link 2</a></li>
						<li><a href="/" class="button">Link 3</a></li>
						<li><a href="/feed_content" class="button">Link 4</a></li>
					</ul>
		    	</div>
		    <h1>Rinoale's cabin <small>개인 서랍장</small></h1>
		    <hr/>
			</div>
		</div>

		<decorator:body />
		<footer class="row">
			<div class="large-12 columns">
				<hr/>
				<div class="row">
					<div class="large-6 columns">
						<p>© Copyright no one at all. Go to town.</p>
					</div>
					<div class="large-6 columns">
					<ul class="inline-list right">
						<li><a href="#">Link 1</a></li>
						<li><a href="#">Link 2</a></li>
						<li><a href="#">Link 3</a></li>
						<li><a href="#">Link 4</a></li>
					</ul>
					</div>
				</div>
			</div>
		</footer>
	</body>
</html>
