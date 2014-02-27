﻿<meta http-equiv="content-type" content="text/html; charset=utf-8">
<html>
<head>
    <title>demo</title>
</head>
<body>
    <#include "../common/head.ftl">

    <div class="table">
        <div class="table-header">
            <div class="id">id</div>
            <div class="name">name</div>
            <div class="address">address</div>
            <div class="action">action</div>
        </div>
        <div id="table-body">
            <div class='row'>
                <div width="16px">
                    <input type='text' name='id' id='id' /></div>
                <div width="56px">
                    <input type='text' name='name' id='name' /></div>
                <div width="56px">
                    <input type='text' name='address' id='address' /></div>
                <div width="56px">
                    <input type='button' id='add' value='add'></div>
            </div>
        </div>
    </div>
    ﻿<script type="text/javascript">
         $(function () {
             $.ajax({
                 url: '../api/stat/list',
                 type: 'GET',
                 dataType: 'json',
                 timeout: 1000,
                 cache: false,
                 beforeSend: LoadFunction, //加载执行方法
                 error: erryFunction,  //错误执行方法
                 success: succFunction //成功执行方法
             })
             function LoadFunction() {

             }
             function erryFunction() {
                 alert("error");
             }
             function succFunction(tt) {
                 var json = eval(tt); //数组
                 alert(json);

                 $.each(json, function (index, item) {
                     //循环获取数据
                     var id = json[index].id;
                     var name = json[index].name;
                     var address = json[index].address;
                     $("#table-body").append("<div class='row'><div class='id'>" + id + "</div><div class='name'>" + name + "</div><div class='address'>" + address
                     + "</div><div class='action'>" + "<a href='#' onclick='deletestore(" + id + ")' >delete</a>"
                     + "</div></div>");
                 });

             }
         });
         function deletestore(value) {
             jQuery.ajax({
                 url: "../api/stat/delete",             // 提交的页面
                 type: "POST",                       // 设置请求类型为"POST"，默认为"GET"
                 dataType: 'text',
                 data: { id: value }, // 从表单中获取数据
                 beforeSend: function () {             // 设置表单提交前方法
                     alert('准备删除');
                 },
                 error: function (request) {           // 设置表单提交出错

                 },
                 success: function (msg) {
                     alert(msg);                       // 设置表单提交完成使用方法
                 }
             });
             return false;
         }
         $(document).ready(function () {
             $('#add').click(function () {
                 var a = $('#id').val(),
                     b = $('#name').val(),
                     c = $('#address').val();
                 jQuery.ajax({
                     url: "../api/stat/add",             // 提交的页面
                     type: "POST",                       // 设置请求类型为"POST"，默认为"GET"
                     dataType: 'text',
                     data: { id: a, name: b, address: c }, // 从表单中获取数据
                     beforeSend: function () {             // 设置表单提交前方法
                         //alert('表单提交前');
                     },
                     error: function (request) {           // 设置表单提交出错
                         alert("表单提交出错，请稍候再试");
                     },
                     success: function (msg) {
                         alert(msg);                       // 设置表单提交完成使用方法
                     }
                 });
                 return false;
             });
         });
    </script>
</body>
</html>
