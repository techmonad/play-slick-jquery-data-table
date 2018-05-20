$(document).ready(function() {
    $('#example').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "search",
            "type": "POST"
        },
        "columns": [
            { "data": "name" },
            { "data": "position" },
            { "data": "office" },
            { "data": "startDate" },
            { "data": "salary" }
        ]
    } );

} );

//https://datatables.net/examples/server_side/post.html