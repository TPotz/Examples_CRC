<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>


<script>
$(document).ready( function () {

    var table = $('#table_id').DataTable({
    "searching": true,
    "processing": true,
    "serverSide": true,
   "bPaginate": true,
   pagingType: "simple_numbers",
   columnDefs: [ {
         targets: 6,
         render: $.fn.dataTable.render.moment('YYYY-MM-DDTHH:mm:ssZ','DD.MM.YYYY HH:mm:ss' )
       } ],

    "ajax": {url: 'elastic',
    dataSrc: 'data',
                     type: "GET"},

    columns: [{
                              "className":      'dt-control',
                              "orderable":      false,
                              "data":           null,
                              "defaultContent": ''
                          },
    { data: "id" },
              { data: "url" },
              { data: "method_name"},
              { data: "params" },
              { data: "user_name" },
              { data: "timestamp" },
              { data: "connection" },
              { data: "cacheControl" },
              { data: "encoding" },
              { data: "language" }

              ],rowId: 'id',
              language: {
                  'paginate': {
                    'previous': '<',
                    'next': '>'
                  }
                }

});
function format ( d ) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<td>Cookies:</td>'+
            '<td>'+d.cookies+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>IP adress:</td>'+
            '<td>'+d.ip+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Host:</td>'+
            '<td>'+d.hostt+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>User Agent:</td>'+
            '<td>'+d.userAgent+'</td>'+
        '</tr>'+
    '</table>';
}

 // Add event listener for opening and closing details
    $('#table_id tbody').on('click', 'td.dt-control', function () {
        var tr = $(this).closest('tr');
        var row = table.row( tr );
        console.log("ušao u metodu za click")

        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( format(row.data()) ).show();
            tr.addClass('shown');
        }
    } );

} );


</script>
<style>
thead {color: #fff;}
#table_id_info {color: #fff;}
#table_id_next {color: #fff;}
#table_id_length {color: #fff;}
#table_id_filter {color: #fff;}
#sve {margin:auto;width: 85%;}

#impRow {width:65%;margin-left: 12%;height:50px;display:flex;gap:20px;margin-top:13px;justify-content:flex-end;align-items:center;color:white;font-size:20px;}
.impNew {font-size:20px;width:150px;transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;border-radius:.25rem;border: 1px solid #ced4da;}

#btnAdd{position: absolute;
bottom: 8px;
right: 15px;
cursor: pointer;
z-index: 12;
color:#38424b;
font-size: 18px;
font-weight: bold;border-radius:5px;}
#btnAdd:hover {background-color:#81dd7a;border-radius: 5px;box-shadow:0px 0px 5px white}

#btnChange{position: absolute;
bottom: 8px;
right: 15px;
cursor: pointer;
z-index: 12;
color:#38424b;
font-size: 18px;
font-weight: bold;}
#btnChange:hover {background-color:#DDD77A;border-radius: 5px;box-shadow:0px 0px 5px white}
#fDatum {font-size: 20px;height: 36px;}


</style>
<div id="sve">

  <table id="table_id" class="display">
      <thead>
            <tr><th></th><th>Id</th><th>Url</th><th>Method</th><th>Params</th><th>User</th><th>Timestamp</th><th>Connection</th><th>Cache Control</th><th>Encoding</th><th>Language</th></tr>
      </thead>
<tbody>

</tbody>
  </table>
  </div>


</html>
