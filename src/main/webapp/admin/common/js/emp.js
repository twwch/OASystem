function empIsNotEmpty(addOrSave) {
    var name = null;
    var eId = null;
    //var dept = null;
    var phoneNum = null;
    //var position = null;
    var inWithTime = null;
    var idCardNo = null;
    var bankAccount = null;
    var email = null;
    var lowSalary = null;
    var flag = true;

    if (addOrSave == 'add') {
        name = $("input[name='name']")[0].value
        eId = $("input[name='eId']")[0].value
        phoneNum = $("input[name='phoneNum']")[0].value
        //dept = $("input[name='dept']")[0].value
        //position = $("input[name='position']")[0].value
        inWithTime = $("input[name='inWithTime']")[0].value
        idCardNo = $("input[name='idCardNo']")[0].value
        bankAccount = $("input[name='bankAccount']")[0].value
        email = $("input[name='email']")[0].value
        lowSalary = $("input[name='lowSalary']")[0].value
    } else if (addOrSave == 'save') {
        name = $("input[name='name']")[1].value
        eId = $("input[name='eId']")[1].value
        phoneNum = $("input[name='phoneNum']")[1].value
        // dept = $("input[name='dept']")[1].value
        //position = $("input[name='position']")[1].value
        inWithTime = $("input[name='inWithTime']")[1].value
        idCardNo = $("input[name='idCardNo']")[1].value
        bankAccount = $("input[name='bankAccount']")[1].value
        email = $("input[name='email']")[1].value
        lowSalary = $("input[name='lowSalary']")[1].value
    }

    if (eId == null || eId.length == 0) {
        $.messager.alert("操作提示", "操作失败,工号不能为空！", "error");
        flag = false;
        return false;
    }
    if (name == null || name.length == 0) {
        $.messager.alert("操作提示", "操作失败,姓名不能为空！", "error");
        flag = false;
        return false;
    }
    if (phoneNum != "") {
        // console.log("电话号码不为空")
        var pattern = /^1[3456789]\d{9}$/;
        if (!pattern.test(phoneNum)) {
            $.messager.alert("操作提示", "操作失败,请检查电话号码是否输入正确！", "error");
            flag = false;
            return false;
        }
    }
    if (inWithTime.length != 0) {
        var pattern = /(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)/g;
        if (!pattern.test(inWithTime)) {
            $.messager.alert("操作提示", "操作失败,请检查时间输入格式！", "error");
            flag = false;
            return false;
        }
    }
    if (idCardNo.length != 0) {
        //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
        //var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        //6位地址编码+8位出生日期+3位顺序号+1位校验位
        //var pattern = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|[xX])$/
        var pattern = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|[Xx])$/;
        if (!pattern.test(idCardNo)) {
            $.messager.alert("操作提示", "操作失败,请检查身份证输入是否正确！", "error");
            flag = false;
            return false;
        }
    }

    if (bankAccount.length != 0) {
        //15-18位
        var pattern =  /^([1-9]{1})(\d{15}|\d{18})$/;
        if (!pattern.test(bankAccount)) {
            $.messager.alert("操作提示", "操作失败,请输入正确的银行卡号！", "error");
            flag = false;
            return false;
        }
    }
    if (email.length != 0) {
        var pattern = /^\w+@[a-zA-Z0-9]{2,10}(?:\.[a-z]{2,4}){1,3}$/;
        if (!pattern.test(email)) {
            $.messager.alert("操作提示", "操作失败,请检查邮箱输入是否正确！", "error");
            flag = false;
            return false;
        }
    }

    //console.log(lowSalary)
    if (lowSalary.length != 0) {
        var pattern = /^(0|[1-9][0-9]*)(\.\d+)?$/;
        if (!pattern.test(lowSalary)) {
            $.messager.alert("操作提示", "操作失败,请输入正确的薪资！", "error");
            flag = false;
            return false;
        }
    }

    return flag;
}