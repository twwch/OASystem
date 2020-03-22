function isIP(addOrSave) {
    var flag = true;
    var ip = null;
    var ipName = null;

    if(addOrSave == 'add'){
        ip = $("input[name='ip']")[0].value
        ipName = $("input[name='ipName']")[0].value
    }
    if(addOrSave == 'save'){
        ip = $("input[name='ip']")[1].value
        ipName = $("input[name='ipName']")[1].value
    }

    if(ip.length == 0){
        $.messager.alert("操作提示", "操作失败,IP地址不能为空！", "error");
        flag = false;
        return false;
    }else if(ip.length != 0){
        var pattern = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
        if (!pattern.test(ip)) {
            $.messager.alert("操作提示", "操作失败,请检查IP地址是否输入正确！", "error");
            flag = false;
            return false;
        }
    }

    return flag;
}