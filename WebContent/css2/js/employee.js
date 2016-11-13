var employeeInfo = [];
var count = 100000;
var buttonList = '';

function addEmployee() {
    buttonList = '';
    var name = document.getElementsByClassName("name")[0].value;
    var age = document.getElementsByClassName("age")[0].value;
    var id = document.getElementById("id-0").value;
    var number = document.getElementsByClassName("number")[0].value;

    for (let i = 0; i < employeeInfo.length; i++) {
        if (id == employeeInfo[i].id) {
            document.getElementById("id-0").focus();
            return document.getElementById("emp_no_id").innerHTML = "<h5>员工编号已存在<h5>";
        }
    }
    employeeInfo.push({id: id, name: name, age: age, number: number});

    for (var i = 0; i < employeeInfo.length; i++) {
        buttonList += `<button  type="button" class="btn btn-info btn-lg btn-block" onclick="displayInfo(${employeeInfo[i].name})">${employeeInfo[i].name}</button><br>`;
    }
    return document.getElementById("display").innerHTML = buttonList;
}

function displayInfo(id) {
    for (var i = 0; i < employeeInfo.length; i++) {
        if (id == employeeInfo[i].id) {
            document.getElementById("add-2").innerHTML = `
             <div class="form-group">
                        <label for="exampleInputFile">员工姓名</label>
                        <div class="form-control">${employeeInfo[i].name}</div>
             </div>
             
             <div class="form-group">
                <label for="exampleInputFile">员工工龄</label>
                <div class="form-control">${employeeInfo[i].age}</div>
             </div>

             <div class="form-group">
                <label for="exampleInputFile">电话号码</label>
                <div class="form-control">${employeeInfo[i].number}</div>
             </div>
             `;
        }
    }
}

function showForm() {
    count++;

    return document.getElementById("add-2").innerHTML = `
             <div class="col-md-4">
                <form role="form" name="myform" action="" method="post">
                    <div class="form-group">
                        <label for="exampleInputEmail1">工号</label>
                        <input type="text" class="form-control" value="${count}" id="id-0">
                    </div>

                    <div class="form-group">
                        <label for="exampleInputEmail1">姓名</label>
                        <input type="text" class="form-control name" id="exampleInputEmail1" placeholder="请输入姓名"
                               name="emp_no" onblur="checkEmpName()"/>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputPassword1">工龄</label>
                        <input type="text" class="form-control age" id="exampleInputPassword1" placeholder="请输入工龄"
                               name="emp_center" onblur="checkEmpAge()"/>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputNumber1">电话号码</label>
                        <input type="text" class="form-control number" id="exampleInputNumber1" placeholder="请输入电话号码"
                               name="emp_buttom" onblur='return checkEmpTelNum();'>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputNumber1"></label>
                        <input type="button" class="btn btn-info form-control" onclick="checkAll()" value="确认添加">
                    </div>
                </form>
            </div>

            <div class="col-md-4">
                <br>
                <br>
                <div class="div-inline-right">
                    <label id="emp_no_buttom" class="control-label" style="color:red"></label>
                </div>

                <br>
                <br>
                <br>
                <div class="div-inline-right">
                    <label id="emp_no_id" class="control-label" style="color:red"></label>
                </div>

                <br>
                <br>
                <br>
                <div class="div-inline-right">
                    <label id="emp_no_tip" class="control-label" style="color:red"></label>
                </div>

                <br>
                <br>
                <br>
                <div class="div-inline-right">
                    <label id="emp_no_center" class="control-label" style="color:red"></label>
                </div>

            </div>`;
}

function checkEmpName() {
    var reg = /^[\u4e00-\u9fa5]{2,10}$/;
    var emp_name = document.getElementsByClassName("name")[0];
    if (!reg.test(emp_name.value)) {
        document.getElementById("emp_no_id").innerHTML = "<img src='img/error.jpg'>请输入真实姓名,10字以内中文!";
        emp_name.focus();
        return false;
    }
    else {
        document.getElementById("emp_no_id").innerHTML = "<img src='img/right.png'>";
        return true;
    }
}

function checkEmpAge() {
    var age = document.getElementsByClassName('age')[0];
    var pattern = /^[0-9]{1,2}$/;
    if (!pattern.test(age.value)) {
        document.getElementById("emp_no_tip").innerHTML = "<h5>请输入数字,长度1-2位!<h5>";
        age.focus();

        return false;
    }
    document.getElementById("emp_no_tip").innerHTML = "<img src='img/right.png'>";
    return true;
}

function checkEmpTelNum() {
    var reg = /^1[3-8][0-9]{9}$/;
    var emp_tel_num = document.getElementsByClassName('number')[0];
    if (!reg.test(emp_tel_num.value)) {
        document.getElementById("emp_no_center").innerHTML = "<img src='img/error.jpg'>请输入11位正确手机号码!";
        emp_tel_num.focus();
        return false;
    }
    else {
        document.getElementById("emp_no_center").innerHTML = "<img src='img/right.png'>";
        return true;
    }
}

function checkAll() {
    if (!checkEmpName() || !checkEmpAge() || !checkEmpTelNum())
        return false;
    else
        addEmployee();
    return true;
}

function deleteEmployee() {
    let id = prompt("请输入要删除的员工编号");
    let temp = [];
    var l = employeeInfo.length;
    let j = 0;

    for (let i = 0; i < employeeInfo.length; i++) {
        if (employeeInfo[i].id != id) {
            temp.push(employeeInfo[i]);
        }
    }
    employeeInfo = temp;

    if (employeeInfo.length < l) {
       return alert("删除成功!");
    }
    return alert("该员工不存在");
}

function findEmployee() {
    var id = document.getElementsByClassName("find-id")[0].value;
    for (var i = 0; i < employeeInfo.length; i++) {
        if (id == employeeInfo[i].id) {
            return document.getElementById("display").innerHTML = `
            <button  type='button' class="btn btn-primary btn-lg btn-block" onclick="displayInfo(${employeeInfo[i].id})">${employeeInfo[i].name}</button>`;
        }
    }
    alert('该员工不存在');
}