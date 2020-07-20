var style;

//表格显示的数据
var cols_data = [
    [{
        field: '',
        title: '序号',
        align: 'center',
        type: 'numbers'
    }, {
        field: 'cardNo',
        title: '卡号',
        align: 'center'
    },{
        field: 'userName',
        title: '姓名',
        align: 'center'
    }, {
        field: 'certificateType',
        title: '证件类别',
        align: 'center'
    }, {
        field: 'certificateNo',
        title: '证件编号',
        align: 'center'
    },{
        field: 'cardType',
        title: '票卡类型',
        align: 'center'
    }, {
        field: 'cardBalance',
        title: '卡余额',
        align: 'center'
    }, {
        field: 'depositMoney',
        title: '押金',
        align: 'center'
    }, {
        field: 'recoveryFlag',
        title: '票卡回收标志',
        align: 'center'
    },
    {
        field: 'appId',
        title: 'appid',
        align: 'center'
    },
    {
        field: 'faceFeature',
        title: '人脸特征',
        align: 'center'
    }]
]
layui.config({
    base: 'main/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['table', 'jquery', 'form', 'element', 'layer', 'laydate','upload'], function() {
    //变量定义
    var table = layui.table;

    //渲染列表
    table.render({
        elem: '#LAY-completeAccount-manage',
        toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
        method: "post",
        url: ajax_ip + "mainDataRest/mainDataList",
        cols: cols_data,
        done:function(){

        },
        limit: 30,
        page: true,
        id: 'completeAccountTable',
    });

});

//操作后重置页面
function tablereload() {
    var table = layui.table
    table.reload('vatable', {
        page: {
            curr: 1 //重新从第 1 页开始
        }
    });
};
