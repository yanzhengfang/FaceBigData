layui.config({
    base: 'main/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['table', 'jquery', 'form', 'element', 'layer', 'laydate','upload'], function() {
    //变量定义
    var table = layui.table;

    //渲染列表
    table.render({
        elem: '#LAY-dataAnalysis-manage',
        toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
        method: "post",
        url: ajax_ip + "",
        cols: [],
        done:function(){

        },
        limit: 30,
        page: true,
        id: 'dataAnalysisTable',
    });
});

//数据分析
function  dataAnalysis(){

}