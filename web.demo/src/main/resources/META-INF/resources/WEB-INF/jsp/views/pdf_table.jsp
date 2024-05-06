<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<script>
$(document).ready( function () {
    $('#table_id').DataTable({
    "searching": true,
    "processing": true,
    "serverSide": true,
   "bPaginate": true,
    "ajax": {url: 'filesTable',
    dataSrc: 'data',

                     type: "GET"},



    columns: [{ data: "id" },
              { data: "fileName", render: function ( data, type, row ) {return '<A href="#" onclick="mijena(this)">'+data+'</A>';} },
              { data: "fileSize", render: function ( data, type, row ) {return bytesToSize(data);} },
              { data: "extension", render: function ( data, type, row ) {return extIcon(data);} }
              ],rowId: 'id'




});


} );


</script>
<style>

thead {color: #fff;}
#table_id_info {color: #fff;}
#table_id_next {color: #fff;}
#table_id_paginate {color: #fff;}
#table_id_length {color: #fff;}
#table_id_filter {color: #fff;}
#sve {margin:30px;width: 85%;}
.dataTables_wrapper .dataTables_length select {border: 1px solid white;}
</style>

<div id="sve">
  <table id="table_id" class="display">
      <thead>
            <tr><th>Id</th><th>File name</th><th>File size</th><th>Extension</th></tr>
      </thead>
<tbody>
</tbody>
  </table>
  <embed id="emb" src="../../pdfs/uto_20220111_1200.pdf" width="500px" height="700px" />

  </div>
  <script>
 function bytesToSize(object) {
    let bytes = object;
    var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
    if (bytes == 0) return '0 Byte';
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
    bytes = Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i];
    return bytes;
 }
 function extIcon(object) {
 let icon = "";
 if (object == "pdf") {icon='<i class="far fa-file-pdf"></i>'} else {
 icon='<i class="far fa-file"></i>'}
 return icon;
 }



 </script>
</html>
