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
                  <th>CityName</th>
                  <th>action</th>
              </tr>
              <tr>
                  <input type='text' name='searchid' id='searchid' />&nbsp<input type='button' style="width: 70;" id='btnsearch' value='search'>
              </tr>
              <tr id="search_tr"  style='display: none'>
                    <td id="search_id"></td>
                    <td id="search_name"></td>
                    <td id="search_address"></td>
                    <td id="search_cityName"></td>
                     <td ><a href='#' onclick='tableshow()'>return</a></td>
              </tr>
              <#list getStores() as store>
                  <tr class="storeTable" id="storeTable${store.id}">
                      <td>${store.id}</td>
                      <td>${store.name}</td>
                      <td>${store.address}</td>
                      <td>${store.getCityName()}</td>
                      <td>﻿<span id="canceledit${store.id}" style='display: none'><a href='#' onclick='canceledit(${store.id})'>canceledit</a></span>&nbsp<span id="edit${store.id}"><a href='#' onclick='editstore("${store.id}","${store.name}","${store.address}","${store.cityId}")'>edit</a></span>

                          <a href='#' onclick='delstore(${store.id})'>delete</a>
                      </td>
                  </tr>
              </#list>
              <tr id="storeTable_add" class="storeTable">
                  <td>
                      <input type='text' name='id' id='id' /></td>
                  <td>
                      <input type='text' name='name' id='name' /></td>
                  <td>
                      <input type='text' name='address' id='address' /></td>
                  <td>
                      <select id='cityId' name='cityId'>
                        <option value="1">c1</option>
                        <option value="2">c2</option>
                        <option value="3">c3</option>
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
                        $("#search_id").text(json.id);
                        $("#search_name").text(json.name);
                        $("#search_address").text(json.address);
                        $("#search_cityName").text(json.cityName);
                        $('#search_tr').show();
                        $('.storeTable').hide();
                    }
                 }
             });
         });
          $('#btnedit').click(function () {
                  var a = $('#id').val();
                  var b = $('#name').val();
                  var c = $('#address').val();
                  var d = $('#cityId').val();
                  $.ajax({
                      async: false,
                      url: "/api/stat/edit",
                      type: "post",
                      dataType: 'json',
                      data: { id: a, name: b, address: c ,cityId: d},
                      error: function (msg) {
                      },
                      success: function (msg) {
                      }
                  });
                  location.reload();
           });
     });

      function editstore(id,name,address,cityId) {
              $('#btnedit').show();
              $('#btnadd').hide();
              $('#canceledit' + id).show();
              $('#edit' + id).hide();
              $("#id").attr("value",id);
              $("#name").attr("value",name);
              $("#address").attr("value",address);
              $("#cityId").val(cityId)
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
         $("#cityId").val(cityId);
         $("#id").removeAttr("disabled");
     }

      function tableshow()
      {
         $('#search_tr').hide();
         $('.storeTable').show();
      }

</script>
</html>
