$(document).ready(function() {
    $('#example').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "search",
            "type": "POST"
        },
        "columns": [
            { "data": "first_name" },
            { "data": "last_name" },
            { "data": "position" },
            { "data": "office" },
            { "data": "start_date" },
            { "data": "salary" }
        ]
    } );

} );

//https://datatables.net/examples/server_side/post.html