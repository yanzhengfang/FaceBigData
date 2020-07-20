var style;

//表格显示的数据
var cols_data = [
    [{
        field: '',
        title: '序号',
        align: 'center',
        type: 'numbers'
    }, {
        field: 'key',
        title: '卡号',
        align: 'center'
    },{
        field: 'value',
        title: '特征',
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
        elem: '#LAY-localFeature-manage',
        toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
        method: "post",
        url: ajax_ip + "redisDataRest/localRedisList",
        cols: cols_data,
        done:function(){

        },
        limit: 30,
        page: true,
        id: 'localFeatureTable',
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
