// 装配AuthTree的模型结构
function fillAuthTree(roleId) {
    // 定制样式,简单树形结构// 需要修正
    let setting = {
        "data": {
            "simpleData": {
                "enable": true,
                "pIdKey": "categoryId"
            },
            "key": {
                "name": "title"
            }
        },
        "check": {
            "enable": true
        }

    };
    commonTree("assign/get/all/auth.json", "authTreeDemo", setting);
    // 调用方法把节点展开
    let zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
    zTreeObj.expandAll(true);

    // 查询已经有的资源

    let paramData = {"roleId": roleId};
    let  ajaxReturn = getSynchroJSON("assign/get/auth/by/roleId.json",paramData);
    if(ajaxReturn.status != 200){
        layer.msg("请求处理出错了！状态响应码是："+ajaxReturn.status+"，说明是："+ajaxReturn.statusText);
        return;
    }
    // 从响应结果获得id组
    let authIdList = ajaxReturn.responseJSON.data;
    // alert(authIdList);
    // 把对应的有的勾选上
    // 遍历数组authIdList
    for (let i = 0; i < authIdList.length; i++) {
        let authId = authIdList[i];
        // 根据id查询对应节点
        let treeNode = zTreeObj.getNodeByParam("id",authId);

        // 回显状态
        let checked = true;
        // 主次联动
        let checkTypeFlag = false;
        zTreeObj.checkNode(treeNode,checked,checkTypeFlag);
    }


}

// 声明专门的函数显示删除模态框
function showConfirmMode(roleArray) {
    // 打开模态框
    $("#confirmModel").modal("show");
    // 清除之前数据
    $("#roleNameDiv").empty();

    // 在全局上创建数组存入角色id
    window.roleIdArray = [];
    // 遍历roleArray数组
    for (let i = 0; i < roleArray.length; i++) {
        let role = roleArray[i];
        let roleName = role.roleName;
        $("#roleNameDiv").append(roleName + "  ");
        let roleId = role.roleId;
        window.roleIdArray.push(roleId);

    }

}


// 生成页面效果,调用它生成分页页面
function generatePage(pn) {
    // console.log("进了");
    // 清除全选框状态
    $("#summaryBox").prop("checked", false);
    // 获取分页数据
    let pageInfo = getPageInfoRemote(pn);
    // 填充表格
    fillTableBody(pageInfo);


}

// 获取远程分页数据
function getPageInfoRemote(pn) {
    // 调用ajax请求并接收返回值
    let ajaxResult = $.ajax({
        "url": "role/get/page/info.json",
        "type": "post",
        "data": {// form data的形式
            "pageNum": pn,
            "pageSize": window.pageSize,
            "keyword": window.keyword,
            "pageCount": window.pageCount
        },
        "async": false,
        "dataType": "json"// 返回数据类型
        // "success": function (response){
        //     let pageInfo = response.data;
        //
        // },
        // "error": function (){
        //
        // }
    });
    // console.log(ajaxResult);
    let statusCode = ajaxResult.status;
    // 如果响应码不是200，说明发生错误，显示提示消息
    if (statusCode != 200) {
        layer.msg("失败！响应状态码=" + statusCode + ",说明信息" + ajaxResult.statusText);
        return false;
    }
    let resultEntity = ajaxResult.responseJSON;
    let result = resultEntity.result;
    // 判断result是否成功
    if (result == "FAIL") {
        layer.msg(resultEntity.result + resultEntity.message);
        return false;
    }else {
        let pageInfo = resultEntity.data;
        window.pageNN = pageInfo.pageNum;
        return pageInfo;
    }



}

// 填充表格
function fillTableBody(pageInfo) {
    // 清除旧数据
    $("#rolePageBody").empty();
    // console.log("进入拼接");

    // 判断是否pageInfo对象有效
    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0) {
        console.log("没有对象？");
        $("#rolePageBody").append("<tr align=\"center\"><td colspan='4'>抱歉！没有查询到您搜索的数据</td></tr>");
        // 不显示页面导航条
        $("#tFoot").hide();
        return;
    }
    $("#tFoot").show();
    // 使用对象数据填充table
    for (let i = 0; i < pageInfo.list.length; i++) {
        let role = pageInfo.list[i];

        let roleId = role.id;

        let roleName = role.name;

        let numberTd = "<td>" + (i + 1) + "</td>";

        let checkboxTd = "<td><input roleId=" + roleId + " class='itemBox' type='checkbox'></td>";

        let roleNameTd = "<td>" + roleName + "</td>";

        let checkedButton = "<button roleId=" + roleId + " type=\"button\" class=\"btn btn-success btn-xs checkBtn\"><i class=\" glyphicon glyphicon-check\"></i></button>";
        let pencilButton = "<button roleId=" + roleId + " type=\"button\" class=\"btn btn-primary btn-xs pencilBtn\"><i class=\" glyphicon glyphicon-pencil\"></i></button>";
        let removeButton = "<button roleId=" + roleId + " type=\"button\" class=\"btn btn-danger btn-xs removeBtn\"><i class=\" glyphicon glyphicon-remove\"></i></button>";
        let buttonTd = "<td>" + checkedButton + " " + pencilButton + " " + removeButton + "</td>";
        let tr = "<tr>" + numberTd + checkboxTd + roleNameTd + buttonTd + "</tr>";
        $("#rolePageBody").append(tr);
    }
    // console.log("拼接完毕");
    // 生成分页导航条
    generateNavigator(pageInfo);

}

// 填充导航条
function generateNavigator(pageInfo) { // footUl拼接它

    let footUl = $("#footUl");
    // 先清空
    footUl.empty();
    let homePage = liA("首页");
    // console.log(homePage);
    let upPage = liA("上一页");

    let downPage = liA("下一页");
    let lastPage = liA("末页");

    footUl.append(homePage).append(upPage);
    for (let i = 0; i < pageInfo.navigatepageNums.length; i++) {
        let page = liA(pageInfo.navigatepageNums[i]);
        if (pageInfo.pageNum == pageInfo.navigatepageNums[i]) {
            page.addClass("active");
        }
        paginationCallBack(page, pageInfo.navigatepageNums[i]);
        footUl.append(page);

    }

    footUl.append(downPage).append(lastPage); // 拼接完成
    // 调整分页导航
    if (pageInfo.isFirstPage) {
        homePage.addClass("disabled");
        upPage.addClass("disabled");
    }
    paginationCallBack(homePage, window.pageNum);
    paginationCallBack(upPage, pageInfo.pageNum - 1);


    if (pageInfo.isLastPage) {
        downPage.addClass("disabled");
        lastPage.addClass("disabled");
    }
    paginationCallBack(downPage, pageInfo.pageNum + 1);
    paginationCallBack(lastPage, pageInfo.pages);


}

// liA通用
function liA(chars) {
    let li = "<li></li>";
    let a = "<a href=\"#\"></a>";
    let page = $(li).append($(a).append(chars));
    return page;
}

// 跳转导航
function paginationCallBack(homePage, page) {
    homePage.click(function () {
        generatePage(page);
        return false;
    });

}

// 处理返回的成功请求
function successR(response) {
    let result = response.result;
    if (result == "SUCCESS") {
        layer.msg("操作成功");
        // 重新加载分页数据
        generatePage(window.pageNN);
    }
    if (result == "FAIL") {
        layer.msg("操作失败," + response.message);
    }
}

// 处理返回的错误请求
function errorR(response) {
    layer.msg("操作失败," + response.message);
}