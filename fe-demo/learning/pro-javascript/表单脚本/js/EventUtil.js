var EventUtil = {
  addHandler:function(element,type,handler){
    if(element.addEventListener){
      element.addEventListener(type,handler,false);
    }else if(element.attachEvent){
      element.attachEvent("on"+type,handler);
    }else{
      element["on"+type]=handler;
    }
  },
  removeHandler:function(element,type,handler){
    if(element.removeEventListener){
      element.removeEventListener(type,handler,false);
    }else if(element.detachEvent){
      element.detachEvent("on"+type,handler);
    }else{
      element["on"+type]=null;
    }
  },
  //获取事件对象
  getEvent:function(event){
    return event?event:window.event;
  },
  //获取事件对象的目标元素
  getTarget:function(event){
    return event.target||event.srcElement;
  },
  //阻止默认行为
  preventDefault:function(event){
    if(event.preventDefault()){
      event.preventDefault();
    }else{
      event.returnValue = false;
    }
  },
  //停止事件冒泡
  stopPropagation:function(event){
    if(event.stopPropagation){
      event.stopPropagation();
    }else{
      event.cancelBubble = true;
    }
  },
  //获取鼠标事件的相关目标
  getRelatedTarget:function(event){
    if(event.relatedTarget){
      return event.relatedTarget;
    }else if(event.toElement){
      return event.toElement;
    }else if(event.fromElement){
      return event.fromElement;
    }else{
      return null;
    }
  },
  //获取鼠标事件的按钮
  getButton:function(event){
    if(document.implementation.hasFeature("MouseEvents","2.0")){
      return event.button;
    }else{
      switch(event.button){
        case 0:
        case 1:
        case 3:
        case 5:
        case 7:
          return 0;
        case 2:
        case 6:
          return 2;
        case 4:
          return 1;
      }
    }
  },
  //获取鼠标滚轮滚动值
  getWheelDelta:function(event){
    if(event.wheelDelta){
      return (window.client.engine.opera && window.client.engine.opera<9.5?
        -event.wheelDelta:event.wheelDelta);
    }else{
      return -event.detail * 40
    }
  },
  //获取键盘事件的字符编码
  getCharCode:function(event){
    if(typeof event.charCode == "number"){
      return event.charCode;
    }else{
      return event.keyCode;
    }
  }
}