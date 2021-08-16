// 发送新增或修改请求
function saveMenu(urlRequest,id,pid,model){
    // 收集数据
    let name = $.trim($("#"+model+" [name=name]").val());// 提出字符串
    let url = $.trim($("#"+model+" [name=url]").val());
    // 定位到被选中的
    let icon = $.trim($("#"+model+" [name=icon]:checked").val());
    // 防止存在空值
    if(name == "" || url == "" || icon == ""){
        layer.msg("信息没有填写完整name = " + name + ", url = "+ url + ", icon = "+icon );
        return;
    }
    // 发送ajax请求
    $.ajax({
        "url": urlRequest,
        "type": "post",
        "data" :{
            "id": id,
            "pid": pid,
            "name": name,
            "url": url,
            "icon": icon
        },
        "dataType": "json",
        "success": function (response){
            successR(response);
        },
        "error": function (response) {
            errorR(response);
        }
    })
    // 关闭模态框
    $("#"+ model).modal("hide");



}

// 刷新界面
function  refresh(){
    let setting = {
        "view": {
            "addDiyDom": myAddDiyDom,
            "addHoverDom": myAddHoverDom,
            "removeHoverDom": myRemoveHoverDom

        },
        "data": {
            "key": {
                "url": "mimi" // 使超链接失效（指定mimi为treeNode不存在的属性名，故没有url值
            }
        }
    };
    commonTree("menu/get/whole/tree.json","treeDemo",setting);
}
// 鼠标移入删除按钮组
function myRemoveHoverDom(treeId,treeNode){

    // 找到按钮组id
    let btnGroupId = treeNode.tId + "_btnGrp";
    // 移除对应元素
    $("#"+btnGroupId).remove();
}


// 鼠标移入添加按钮组
function myAddHoverDom(treeId,treeNode){

    // 按钮组的标签结构：<span><a><i></i></a></span>
    // 按钮组出现在节点后面treeDemo_3_a
    // 为了需要移除span，对span设置有规律的id
    let btnGroupId = treeNode.tId + "_btnGrp";
    // 判断之前是否加过按钮组
    if($("#"+btnGroupId).length > 0)
        return;

    // 准备各个按钮的标签
    let addBtn =" <a id='"+treeNode.id+"' class=\"addBtn btn btn-info dropdown-toggle btn-xs\" style=\"margin-left:10px;padding-top:0px;\" href=\"#\" title=\"添加节点\">&nbsp;&nbsp;<i class=\"fa fa-fw fa-plus rbg \"></i></a>";
    let removeBtn = " <a id='"+treeNode.id+"' class=\"removeBtn btn btn-info dropdown-toggle btn-xs\" style=\"margin-left:10px;padding-top:0px;\" href=\"#\" title=\"删除节点\">&nbsp;&nbsp;<i class=\"fa fa-fw fa-times rbg \"></i></a>";
    let editBtn = " <a id='"+treeNode.id+"' class=\"editBtn btn btn-info dropdown-toggle btn-xs\" style=\"margin-left:10px;padding-top:0px;\" href=\"#\" title=\"修改节点\">&nbsp;&nbsp;<i class=\"fa fa-fw fa-edit rbg \"></i></a>";
    // 找到节点
    let anchorId = treeNode.tId + "_a";
    let level = treeNode.level;
    // 声明一个变量存储拼装好的结果
    let btnHtml = "";
    // 判断当前节点级别
    switch (level){
        case 0 :// 根节点
            btnHtml = addBtn;
            break;
        case 1 :// 支节点
            // 获取当前子节点长度
            let length = treeNode.children.length;
            // 没有子节点可以删
            let removeSFBtn = length == 0 ? removeBtn : "";
            btnHtml = editBtn + removeSFBtn +addBtn;
            break;
        case 2 :// 叶节点
            btnHtml = editBtn + removeBtn;
            break;

    }
    // 执行添加操作
    $("#"+anchorId).after("<span id='"+btnGroupId+"'>"+btnHtml+"</span>");

}


function myAddDiyDom(treeId,treeNode){
    // treeId是附着的静态资源的id
    console.log("treeId="+treeId);
    // 当前属性节点的全部数据
    console.log(treeNode);
    // 根据控制span（根据id（treeDemo_6_ico
    let spanId = treeNode.tId + "_ico";
    // 根据id

    // 清理span class
    $("#" + spanId).removeClass();
    // 添加自己的icon class
    $("#" + spanId).addClass(treeNode.icon);
}