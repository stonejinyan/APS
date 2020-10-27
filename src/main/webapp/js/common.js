//转换日期格式(时间戳转换为datetime格式)
function changeDateFormat(cellval,type) {
	var dateVal = cellval + "";
	if (cellval != null) {
		var date = new Date(dateVal);
		var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
		var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
		var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
		var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
		var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
		if(type==1){
		return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
		}
		return date.getFullYear() + "-" + month + "-" + currentDate;
	}
}
