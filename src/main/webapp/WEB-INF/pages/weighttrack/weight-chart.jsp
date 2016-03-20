<script src="${pageContext.request.contextPath}/js/chartjs/Chart.min.js"></script>
<div class="col-md-6">
    <canvas id="weight-chart" min-width="150" height="100"></canvas>
</div>
<script>
    var lineChartData = {
        //["01 Mar", "02 Mar", "03 Mar", "04 Mar", "05 Mar", "06 Mar", "07 Mar"]
        //[70.5, 70.2, 69.8, 69.7, 70.3, 70, 69.4]
        labels: ${weightDateJSON},
        datasets: [
            {
                label: "My Weight List",
                strokeColor: "rgba(220,220,220,1)",
                pointColor: "rgba(220,220,220,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)",
                data: ${weightValueJSON}
            }
        ]

    }
    window.onload = function () {
        var ctx = document.getElementById("weight-chart").getContext("2d");
        window.myLine = new Chart(ctx).Line(lineChartData, {
            responsive: true,
            pointDotStrokeWidth: 1,
            bezierCurve: false,
            datasetFill: false,
            datasetStroke: true
        });
    }
</script>