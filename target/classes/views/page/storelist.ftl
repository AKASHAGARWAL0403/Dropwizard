﻿<#-- @ftlvariable name="" type="com.mt.views.BaseView" -->
﻿<meta http-equiv="content-type" content="text/html; charset=utf-8">
<html>
<body>
    <#include "../common/head.ftl">
      <form method="post" action="/demo/add/">
          ﻿<table class="gridtable">
              <tr>
                  <th>id</th>
                  <th>name</th>
                  <th>address</th>
                  <th>productName</th>
                  <th>action</th>
              </tr>
              <tr>
                  <input type='text' name='searchid' id='searchid' />&nbsp<input type='button' style="width: 70;" id='btnsearch' value='search'>
              </tr>
              <#list getStores() as store>
                  <tr>
                      <td>${store.id}</td>
                      <td>${store.name}</td>
                      <td>${store.address}</td>
                      <td>${store.getProductName()}</td>
                      <td>﻿<span id="canceledit${store.id}" style='display: none'><a href='#' onclick='canceledit(${store.id})'>canceledit</a></span>&nbsp<span id="edit${store.id}"><a href='#' onclick='editstore("${store.id}","${store.name}","${store.address}","${store.productId}")'>edit</a></span>

                          <a href='#' onclick='delstore(${store.id})'>delete</a>
                      </td>
                  </tr>
              </#list>
              <tr>
                  <td>
                      <input type='text' name='id' id='id' /></td>
                  <td>
                      <input type='text' name='name' id='name' /></td>
                  <td>
                      <input type='text' name='address' id='address' /></td>
                  <td>
                      <select id='productId' name='productId'>
                        <option value="1">p1</option>
                        <option value="2">p2</option>
                        <option value="3">p3</option>
                      </select>

                  <td>
                      <span>
                          <input type='submit' style="width: 70" id='btnadd'   value='add'>
                          <input type='button' style="width: 70; display: none" id='btnedit' value='edit'>
                      </span>
                  </td>

              </tr>
          </table>
      </form>
</body>
﻿<script type="text/javascript">
     $(document).ready(function () {
         $('#btnsearch').click(function () {
             var a = $('#searchid').val();
             $.ajax({
                 async: false,
                 url: "/api/stat/search",
                 type: "post",
                 dataType: 'json',
                 data: { id: a},
                 error: function (msg) {
                 },
                 success: function (tt) {
                    var json = eval(tt);
                    if(json!=null)
                    {
                        $("#id").attr("value",json.id);
                        $("#name").attr("value",json.name);
                        $("#address").attr("value",json.address);
                        $("#productId").val(json.productId);
                    }
                 }
             });
         });
          $('#btnedit').click(function () {
                  var a = $('#id').val();
                  var b = $('#name').val();
                  var c = $('#address').val();
                  var d = $('#productId').val();
                  $.ajax({
                      async: false,
                      url: "/api/stat/edit",
                      type: "post",
                      dataType: 'json',
                      data: { id: a, name: b, address: c ,productId: d},
                      error: function (msg) {
                      },
                      success: function (msg) {
                      }
                  });
                  location.reload();
           });
     });

      function editstore(id,name,address,productId) {
              $('#btnedit').show();
              $('#btnadd').hide();
              $('#canceledit' + id).show();
              $('#edit' + id).hide();
              $("#id").attr("value",id);
              $("#name").attr("value",name);
              $("#address").attr("value",address);
              $("#productId").val(productId)
              $("#id").attr("disabled", "true");
          }

     function delstore(value) {
         jQuery.ajax({
             async: false,
             url: "/api/stat/delete",
             type: "post",
             dataType: 'json',
             data: { id: value },
             beforeSend: function () {
                 alert('准备删除');
             },
             error: function (msg) {
             },
             success: function (msg) {

             }
         });
         location.reload();
     }

     function canceledit(id)
     {
         $('#btnedit').hide();
         $('#btnadd').show();
         $('#canceledit' + id).hide();
         $('#edit' + id).show();
         $("#id").attr("value","");
         $("#name").attr("value","");
         $("#address").attr("value","");
         $("#productId").val(productId);
         $("#id").removeAttr("disabled");
     }

</script>
</html>
