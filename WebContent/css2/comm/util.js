/**
 * 
 * @param datestr 要转换的日期串
 * @param dateformat 转换成的形式
 * @param precision 转换的精度，如转换成y:年，m:月,d：日,h：时,mi：分,s:秒
 * @returns {String}
 */
function getDateFormat(datestr,dateformat,precision){
	var d = new Date(datestr);
    var year = d.getFullYear();
    var month = d.getMonth()+1;
    var date =d.getDate();
    var hour = d.getHours();
    var minute = d.getMinutes();
    var second = d.getSeconds(); 
    if(precision=='y'){
    	return year;
    }
    if(precision=='m'){
    	if(dateformat=='/'){
    		return year+"/"+month
        }
        if(dateformat=='-'){
        	return year+"-"+month
        }
    }
    if(precision=='d'){
    	if(dateformat=='/'){
    		return year+"/"+month+"/"+date
        }
        if(dateformat=='-'){
        	return year+"-"+month+"-"+date
        }
    }
    if(precision=='mi'){
    	if(dateformat=='/'){
    		return year+"/"+month+"/"+date+" "+hour+":"+minute;
        }
        if(dateformat=='-'){
        	return year+"-"+month+"-"+date+" "+hour+":"+minute;
        }
    }
    if(precision=='s'){
    	if(dateformat=='/'){
    		return year+"/"+month+"/"+date+" "+hour+":"+minute+":"+second;
        }
        if(dateformat=='-'){
        	return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
        }
    }
    
    if(dateformat=='l'){//toLocaleString  当地的时间格式
    	return d.toLocaleString();
    }
    
    return '';
}