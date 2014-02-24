<meta http-equiv="content-type" content="text/html; charset=utf-8">
<html>
<head>
    <title>demo</title>
</head>
<body>
<#include "../common/head.ftl">
﻿
<div id="list">
<h1>Hello, ${store.name?html}!</h1>
<h1>id= ${store.id?html}!</h1>
<h1>address= ${store.address?html}!</h1>
</div>
</body>
</html>