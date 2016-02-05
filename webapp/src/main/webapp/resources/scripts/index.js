var IndexTable = function () {

    var initTable = function () {
        $("#indexTable").on('click', '.mobject', function (e) {
            location.href = "/monitorInfo?objectId=" + $(this).attr("id").substring(2);
        });
    }

    var flushTable = function () {
        var url = "/index/listMonitorObject";
        $.getJSON(url, function (result) {
            var tableHtml = "";
            var tdNormalTemplate = '<td bgcolor="#90ee90" align="center" class="mobject" style="cursor:pointer" id="td{0}"><img src="/resources/images/computer.png" width="128"><br><span style="font-size: 48px;">${1}</span></td>';
            var tdErrorTemplate = '<td bgcolor="#f08080" align="center" class="mobject" style="cursor: pointer" id="td{0}"><img src="/resources/images/computer.png" width="128"><br><span style="font-size: 48px;">${1}</span></td>';
            $.each(result, function (i, item) {
                if (i % 5 == 0)
                    tableHtml += "<tr>";
                var tdHtml = "";
                if (item.healthyStatus == "0")
                    tdHtml = tdNormalTemplate.format(item.id, item.name);
                if (item.healthyStatus == "1")
                    tdHtml = tdErrorTemplate.format(item.id, item.name);
                tableHtml += tdHtml;
                if (i % 5 == 4)
                    tableHtml += "</tr>";
            });
            $("#indexTable").html(tableHtml);
        });
    }

    return {

        //main function to initiate the module
        init: function () {
            activeMenu('/');
            initTable();
            flushTable();
            window.setInterval(flushTable, 3000);
        }

    };

}();
