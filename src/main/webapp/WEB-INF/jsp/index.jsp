<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="header.jsp"%>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-7">
			<h4>
			<span id="initScore" class="label label-success">initScore:0</span>
			<span id="hardScore" class="label label-danger">hardScore:0</span>
			<span id="mediumScore" class="label label-warning">mediumScore:0</span>
			<span id="softScore" class="label label-info">softScore:0</span>
			<span class="label label-default">Time Spent:
			<span id="id_H">00:</span>
			<span id="id_M">00:</span>
			<span id="id_S">00</span>
			</span>
			</h4>
		</div>
		<div class="col-lg-2">

		</div>
		<div class="col-lg-3">
			<button onclick="solve()" class="btn btn-success">Solve</button>
			<button onclick="terminate()" class="btn btn-success">Terminate Solving Early</button>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12" id="main" style="width: 100%;height:500px;"></div>
	</div>
</div>
<script>
	var count = 0;
	function solve(){
		$.ajax({
			url: '/solverRest/solve',
			type: 'GET',
			success: function(data) {
			}
		});
		$('body').everyTime('1s','B',clock);
		count = 0;
	}
	function terminate(){
		$.ajax({
			url: '/solverRest/terminate',
			type: 'GET',
			success: function(data) {
				if(data==1){
					alert("计算已结束！");
				}else{
					alert("系统错误，请联系管理员！");
				}
			}
		});
		$('body').stopTime ('B');
	}
	var xAxisData = [];	
	var data1 = [];
	var data2 = [];
	var data3 = [];
	var data4 = [];
	
	for (var i = 0; i < 30; i++) {
	    data1.push((Math.random() * 2).toFixed(2));
	    data2.push(Math.random().toFixed(2));
	    data3.push((Math.random() * 5).toFixed(2));
	    data4.push((Math.random() + 0.3).toFixed(2));
	}
	
	option = {
		title: {
		    text: 'Planning Distribution',
		    subtext: '',
		    textStyle: {
		        align: 'center'
		    },
		    subtextStyle: {
		        align: 'center'
		    }
		}, 
		dataZoom: [
        {   // 这个dataZoom组件，默认控制x轴。
            type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
            start: 0,      // 左边在 10% 的位置。
            end: 30         // 右边在 60% 的位置。
        }
		],
	    backgroundColor: '#FFFFFF',
	    legend: {
	        data: ['115(1MT)','115-S','115(2MT)','115(3MT)','115-3','115-70','230', 'PFC', 'ATS', 'VSD/SS', '70-2P', '70-2M',
                '70-2IM', '70-MP', '70-MM', '70-MIM', '70-F', 'APF','SPE',
                'Da(1MT)','Da(2MT)', 'Da(4K)', 'Da(5K)', 'Df', 'Daf', 'Mf', 'Dc', 'ATS','VSD/SS', 'MxP', 'MxM',
                'MxIM', 'UP', 'UM', 'UIM','Corner','APF','SPE'],
	        right: 0,
			orient: 'vertical',
	    },
	    tooltip: {},
	    xAxis: {
	        data: xAxisData,
	        name: 'Date',
	        axisLine: {onZero: false},
	        splitLine: {show: false},
	        splitArea: {show: true}
	    },
	    yAxis: {
	        inverse: false,
			name: 'Quantity',
	        splitArea: {show: true},
			max: function (value) {
			    return value.max + 5;
			}
	    },
	    grid: {
	        left: 30
	    },
	    series: [{
            name: '70-2P',
            type: 'bar',
            stack: 'one',
            data: [1]
        },
        {
            name: '115(2MT)',
            type: 'bar',
            stack: 'one',
            data: [1]
        },
        {
            name: '115(1MT)',
            type: 'bar',
            stack: 'two',
            data: [1]
        },
        {
            name: '115(3MT)',
            type: 'bar',
            stack: 'two',
            data: [1]
        }]
	};
	
	var myChart = echarts.init(document.getElementById('main'),'macarons');
	myChart.setOption(option);
	
	$.get('/moTableRest/mainDashboard').done(function (data) {
	    // 填入数据
	    myChart.setOption({
	        xAxis: {
	            data: data.dates
	        },
	        series: data.series
	    });
	});
	$('body').everyTime('3s','A',function(){
	  $.get('/moTableRest/mainDashboard').done(function (data) {
	      // 填入数据
	      myChart.setOption({
	          xAxis: {
	              data: data.dates
	          },
	          series: data.series
	      });
	  });
	  $.get('/moTableRest/hardMediumSoftScore').done(function (data) {
		  // 填入数据
		  if(data.hardScore!=null){
			$('#initScore').text('initScore:'+data.initScore);
			$('#hardScore').text('hardScore:'+data.hardScore);
			$('#mediumScore').text('mediumScore:'+data.mediumScore);
			$('#softScore').text('softScore:'+data.softScore);
		  }
	  });
	});
	
	function clock(){
		count++;
		$('#id_S').text(showNum(count % 60));
		$('#id_M').text(showNum(parseInt(count / 60) % 60)+':');
		$('#id_H').text(showNum(parseInt(count / 60 / 60))+':');
	};
	//封装一个处理单位数字的函数
	   function showNum(num) {
	    if (num < 10) {
	     return '0' + num
	    }
	    return num
	   }
</script> 
<%@include file="footer.jsp"%>