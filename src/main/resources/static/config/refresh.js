//刷新
document.onkeydown = function(e) {
	var keyCode = e.keyCode;
	if(keyCode == 116){
		parent.refreshView();
	}
}
