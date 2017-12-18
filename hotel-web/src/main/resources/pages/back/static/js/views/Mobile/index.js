webpackJsonp([2],{112:function(e,a,t){t(261);var i=t(4)(t(177),t(282),"data-v-76502b23",null);e.exports=i.exports},165:function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var i=t(0),o=t(11),r=(t(9),t(112)),l=t.n(r),s=t(3),n=t(1),d=[{path:"/",redirect:function(e){return"/MobileSet"}},{path:"/MobileSet",component:l.a}];i.default.use(o.a);var m=new o.a({routes:d});new i.default({store:s.a,router:m}).$mount("#app"),n.a.backconfig({fn:function(e){s.a.commit("backConfig",e)}})},169:function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var i=t(13),o=t.n(i),r=t(1),l=t(22),s=t.n(l),n=t(3),d=t(12);a.default={data:function(){return{test:[],addform:{name:"",breakfastEnable:1,breakfastTime:[],lunchEnable:1,lunchTime:[],dinnerEnable:1,dinnerTime:[],supperEnable:1,supperTime:[],foodProvides:0,foodProvidesName:"",orderReceivePhone:"",foodImage:"",price:"",deliveryTime:"",id:null},addformrules:{name:[{required:!0,validator:d.empty,message:"请输入菜名"}],breakfastTime:[{required:!1,message:"请选择早餐时间段"}],lunchTime:[{required:!1,message:"请选择午餐时间段"}],dinnerTime:[{required:!1,message:"请选择晚餐时间段"}],supperTime:[{required:!1,message:"请选择宵夜时间段"}],orderReceivePhone:[{required:!0,validator:d.phonetest,message:"请输入正确的电话号码"}],foodImage:[{required:!0,message:"请选择菜品图片"}],foodProvidesName:[{required:!0,message:"请输入合作方名称"}],price:[{required:!0,validator:d.integertest,message:"请输入菜品单价"}],deliveryTime:[{required:!0,validator:d.integertest,message:"请输入正确的配送时间"}]},dialogFormVisible:!1,library:!1,materialUrl:""}},methods:{onSubmit:function(e){var a=this,i=this;this.$refs[e].validate(function(e){if(!e)return!1;if(1==a.addform.breakfastEnable&&1==a.addform.lunchEnable&&1==a.addform.dinnerEnable&&1==a.addform.supperEnable)return a.$message.error("请选择菜品提供时段"),!1;var l=JSON.parse(o()(a.addform));l.breakfastBegin=t.i(d.TimeFormat)(l.breakfastTime[0]),l.breakfastEnd=t.i(d.TimeFormat)(l.breakfastTime[1]),l.lunchBegin=t.i(d.TimeFormat)(l.lunchTime[0]),l.lunchEnd=t.i(d.TimeFormat)(l.lunchTime[1]),l.dinnerBegin=t.i(d.TimeFormat)(l.dinnerTime[0]),l.dinnerEnd=t.i(d.TimeFormat)(l.dinnerTime[1]),l.supperBegin=t.i(d.TimeFormat)(l.supperTime[0]),l.supperEnd=t.i(d.TimeFormat)(l.supperTime[1]),l.price=100*l.price,l.hotelId=a.hotelId,r.d.keepfood({Suffix:a.hotelId+"/food",params:l,fn:function(e){a.dialogFormVisible=!0,setTimeout(function(){i.dialogFormVisible=!1,i.$emit("update:isActive",!0)},3e3)}})})},revertfn:function(e){this.$emit("update:isActive",!0)},changeA:function(e,a){e.target.checked?this.addformrules.breakfastTime[0].required=!0:this.addformrules.breakfastTime[0].required=!1},changeB:function(e,a){e.target.checked?this.addformrules.lunchTime[0].required=!0:this.addformrules.lunchTime[0].required=!1},changeC:function(e,a){e.target.checked?this.addformrules.dinnerTime[0].required=!0:this.addformrules.dinnerTime[0].required=!1},changeD:function(e,a){e.target.checked?this.addformrules.supperTime[0].required=!0:this.addformrules.supperTime[0].required=!1},materiallayer:function(){this.materialUrl=n.a.state.library,this.library=!0}},mounted:function(){var e=this;window.addEventListener("message",function(a){var t=a.data.length-1;if(!e.library)return!1;if(!a.data.length)return!1;var i=[];a.data.substring(6,t).split(",").forEach(function(e){i.push(e.substring(1,e.length-1))}),i[1]&&(e.addform.foodImage=i[1]),e.library=!1})},watch:{foodid:function(e,a){var t=this;this.$refs.addform.resetFields(),this.addform.foodImage="",this.addformrules.breakfastTime[0].required=!1,this.addformrules.lunchTime[0].required=!1,this.addformrules.dinnerTime[0].required=!1,this.addformrules.supperTime[0].required=!1,this.addform.id=null,e&&(this.addform.id=e,r.d.editfood({Suffix:this.hotelId+"/food/"+e,fn:function(e){t.addform=e,t.addform.price=t.addform.price/100,t.$set(t.addform,"breakfastTime",[]),t.$set(t.addform,"lunchTime",[]),t.$set(t.addform,"dinnerTime",[]),t.$set(t.addform,"supperTime",[]),t.addform.breakfastBegin&&t.addform.breakfastEnd&&(t.addform.breakfastTime[0]=new Date("January 12,2006 "+t.addform.breakfastBegin+":35"),t.addform.breakfastTime[1]=new Date("January 12,2006 "+t.addform.breakfastEnd+":35")),t.addform.lunchBegin&&t.addform.lunchEnd&&(t.addform.lunchTime[0]=new Date("January 12,2006 "+t.addform.lunchBegin+":35"),t.addform.lunchTime[1]=new Date("January 12,2006 "+t.addform.lunchEnd+":35")),t.addform.dinnerBegin&&t.addform.dinnerEnd&&(t.addform.dinnerTime[0]=new Date("January 12,2006 "+t.addform.dinnerBegin+":35"),t.addform.dinnerTime[1]=new Date("January 12,2006 "+t.addform.dinnerEnd+":35")),t.addform.supperBegin&&t.addform.supperEnd&&(t.addform.supperTime[0]=new Date("January 12,2006 "+t.addform.supperBegin+":35"),t.addform.supperTime[1]=new Date("January 12,2006 "+t.addform.supperEnd+":35"))}}))},isActive:function(e,a){this.foodid||(this.$refs.addform.resetFields(),this.addform.foodImage="",this.addformrules.breakfastTime[0].required=!1,this.addformrules.lunchTime[0].required=!1,this.addformrules.dinnerTime[0].required=!1,this.addformrules.supperTime[0].required=!1)}},props:["foodid","isActive","hotelId"],components:{Confirm:s.a}}},177:function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var i=t(13),o=t.n(i),r=t(287),l=t.n(r),s=t(22),n=t.n(s),d=t(1),m=t(12),c=t(3);a.default={data:function(){return{hotelId1:"",hotelId2:"",Hoteldata:[],activeName:"first",pagedata:{imageurls:[],installations:[],noticeEnable:0,bulletin:"",remnantRoomEnable:0,payMode:1,smsEnable:0,smsPhone:"",roomReservationEnable:0,foodPayMode:1,reservationCheckOutEnable:0,reservationCheckOutPhone:"",invoiceCategorys:[]},installations:[],invoice:[],pagedatarules:{imageurls:[{required:!0,message:"请选择图片"}],bulletin:[{required:!0,message:"请输入公告内容"}],payMode:[{required:!0,message:"请选择支付方式"}],smsPhone:[{required:!0,message:"请输入正确的手机号",validator:m.phonetest}],foodPayMode:[{required:!0,message:"请选择餐饮支付方式"}],reservationCheckOutPhone:[{required:!0,message:"请输入正确的手机号",validator:m.phonetest}],invoiceCategorys:[{required:!0,message:"请选择发票类目"}]},dialogFormVisible:!1,currentPage:1,footlist:[],Deleteid:"",foodid:"",isActive:!0,pages:1,dialogFormVisible2:!1,library:!1,materialUrl:""}},methods:{changeHotel1:function(){sessionStorage.setItem("hotelId",this.hotelId1),this.getMobiledata()},changeHotel2:function(){sessionStorage.setItem("hotelId",this.hotelId2),this.getfootdata()},getMobiledata:function(){var e=this;d.d.getinfrastructure({fn:function(a){e.installations=a}}),d.d.getinvoice({fn:function(a){e.invoice=a.records}}),d.d.getMobilepage({Suffix:this.hotelId1,fn:function(a){if(a){e.pagedata=a;var t=[];a.installations.forEach(function(e){t.push(e.infrastructureId)},e),e.pagedata.installations=t;var i=[];a.invoiceCategorys&&(i=a.invoiceCategorys.split(",")),e.pagedata.invoiceCategorys=JSON.parse("["+String(i)+"]")}}})},onSubmit:function(e){var a=this;this.$refs[e].validate(function(e){if(!e)return!1;a.pagedata.hotelId=a.hotelId1;var t=[];a.pagedata.installations.forEach(function(e){t.push({infrastructureId:e})},a);var i="";a.pagedata.invoiceCategorys.forEach(function(e){i=i+e+","},a),i=i.substr(0,i.length-1);var r=JSON.parse(o()(a.pagedata));r.installations=t,r.invoiceCategorys=i,d.d.keepMobilepage({params:r,fn:function(e){var t=a;a.dialogFormVisible=!0,setTimeout(function(){t.dialogFormVisible=!1},3e3)}})})},materiallayer:function(){this.materialUrl=c.a.state.librarys,this.library=!0},getfootdata:function(){var e=this;d.d.getfootpage({Suffix:this.hotelId2+"/food",params:{hotelId:this.hotelId2,page:this.currentPage},fn:function(a){e.pages=a.pages,e.footlist=a.records}})},handleCurrentChange:function(e){this.getfootdata()},handleDelete:function(e){this.Deleteid=e,this.dialogFormVisible2=!0},Deletefn:function(){var e=this;d.d.fooddelete({Suffix:this.hotelId2+"/food",params:"["+this.Deleteid+"]",fn:function(a){e.dialogFormVisible2=!1,e.$message({message:"删除成功",type:"success"}),e.getfootdata()}})},newadd:function(){this.foodid="",this.isActive=!1},handleClick:function(){var e=this.isActive;if(this.isActive=!0,0==this.Hoteldata.length)return this.$message("无酒店,请添加酒店"),!1;"first"==this.activeName&&this.hotelId1!=sessionStorage.getItem("hotelId")&&e&&(sessionStorage.getItem("hotelId")?this.hotelId1=Number(sessionStorage.getItem("hotelId")):this.hotelId1=this.Hoteldata[0].hotelId),"second"==this.activeName&&this.hotelId2!=sessionStorage.getItem("hotelId")&&e&&(sessionStorage.getItem("hotelId")?this.hotelId2=Number(sessionStorage.getItem("hotelId")):this.hotelId2=this.Hoteldata[0].hotelId)},handleEdit:function(e){this.foodid=e,this.isActive=!1},closeImg:function(e){this.pagedata.imageurls.splice(e,1)},decide:function(e,a){for(var t=0;t<e.length;t++)if(e[t].path==a)return t;return-1}},mounted:function(){var e=this;d.a.Hotellist({fn:function(a){0!=a.records?(e.Hoteldata=a.records,e.$nextTick(function(){sessionStorage.getItem("hotelId")?e.hotelId1=Number(sessionStorage.getItem("hotelId")):(e.hotelId1=a.records[0].hotelId,sessionStorage.setItem("hotelId",a.records[0].hotelId))})):e.$message("无酒店,请添加酒店")}});var a=this;window.addEventListener("message",function(e){var t=e.data.length-1;if(!a.library)return!1;if("go_back()"==e.data)return a.library=!1,!1;var i=JSON.parse(e.data.substring(6,t).replace(",'undefined'","").slice(1,-1)),r=JSON.parse(o()(a.pagedata.imageurls));i.forEach(function(e){a.pagedata.imageurls.length<6?-1==a.decide(r,e.url)&&a.pagedata.imageurls.push({id:null,path:e.url,name:e.url.substr(e.url.lastIndexOf("/")+1)}):a.$message({message:"最多上传6张",type:"warning"})},this),a.library=!1})},watch:{isActive:function(){this.isActive&&"first"==this.activeName?this.getMobiledata():this.isActive&&"second"==this.activeName&&this.getfootdata()}},components:{FoodAdd:l.a,Confirm:n.a}}},260:function(e,a){},261:function(e,a){},281:function(e,a){e.exports={render:function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("div",{staticClass:"FoodAdd"},[t("el-form",{ref:"addform",attrs:{model:e.addform,rules:e.addformrules,"label-width":"180px"}},[t("el-form-item",{attrs:{label:"菜名：",prop:"name"}},[t("el-input",{model:{value:e.addform.name,callback:function(a){e.addform.name=a},expression:"addform.name"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"菜品提供时段："}},[t("div",{staticClass:"limitbox"},[t("div",[t("el-form-item",{attrs:{prop:"breakfastEnable"}},[t("el-checkbox",{attrs:{"true-label":0,"false-label":1},on:{change:function(a){e.changeA(a,0)}},model:{value:e.addform.breakfastEnable,callback:function(a){e.addform.breakfastEnable=a},expression:"addform.breakfastEnable"}},[e._v("早餐")])],1),e._v(" "),t("el-form-item",{attrs:{prop:"breakfastTime"}},[t("el-time-picker",{attrs:{"is-range":"",disabled:e._f("option")(e.addform.breakfastEnable),format:"HH:mm",placeholder:"请选择时间段"},model:{value:e.addform.breakfastTime,callback:function(a){e.addform.breakfastTime=a},expression:"addform.breakfastTime"}})],1)],1),e._v(" "),t("div",[t("el-form-item",{attrs:{prop:"lunchEnable"}},[t("el-checkbox",{attrs:{"true-label":0,"false-label":1},on:{change:function(a){e.changeB(a,0)}},model:{value:e.addform.lunchEnable,callback:function(a){e.addform.lunchEnable=a},expression:"addform.lunchEnable"}},[e._v("午餐")])],1),e._v(" "),t("el-form-item",{attrs:{prop:"lunchTime"}},[t("el-time-picker",{attrs:{"is-range":"",disabled:e._f("option")(e.addform.lunchEnable),format:"HH:mm",placeholder:"请选择时间段"},model:{value:e.addform.lunchTime,callback:function(a){e.addform.lunchTime=a},expression:"addform.lunchTime"}})],1)],1),e._v(" "),t("div",[t("el-form-item",{attrs:{prop:"dinnerEnable"}},[t("el-checkbox",{attrs:{"true-label":0,"false-label":1},on:{change:function(a){e.changeC(a,0)}},model:{value:e.addform.dinnerEnable,callback:function(a){e.addform.dinnerEnable=a},expression:"addform.dinnerEnable"}},[e._v("晚餐")])],1),e._v(" "),t("el-form-item",{attrs:{prop:"dinnerTime"}},[t("el-time-picker",{attrs:{"is-range":"",disabled:e._f("option")(e.addform.dinnerEnable),format:"HH:mm",placeholder:"请选择时间段"},model:{value:e.addform.dinnerTime,callback:function(a){e.addform.dinnerTime=a},expression:"addform.dinnerTime"}})],1)],1),e._v(" "),t("div",[t("el-form-item",{attrs:{prop:"supperEnable"}},[t("el-checkbox",{attrs:{"true-label":0,"false-label":1},on:{change:function(a){e.changeD(a,0)}},model:{value:e.addform.supperEnable,callback:function(a){e.addform.supperEnable=a},expression:"addform.supperEnable"}},[e._v("宵夜")])],1),e._v(" "),t("el-form-item",{attrs:{prop:"supperTime"}},[t("el-time-picker",{attrs:{"is-range":"",disabled:e._f("option")(e.addform.supperEnable),format:"HH:mm",placeholder:"请选择时间段"},model:{value:e.addform.supperTime,callback:function(a){e.addform.supperTime=a},expression:"addform.supperTime"}})],1)],1)])]),e._v(" "),t("el-form-item",{attrs:{label:"菜品提供方：",prop:"foodProvides"}},[t("el-radio-group",{model:{value:e.addform.foodProvides,callback:function(a){e.addform.foodProvides=a},expression:"addform.foodProvides"}},[t("el-radio",{attrs:{label:0}},[e._v("本酒店")]),e._v(" "),t("el-radio",{attrs:{label:1}},[e._v("合作方")])],1)],1),e._v(" "),1==e.addform.foodProvides?t("el-form-item",{attrs:{label:"合作方名称：",prop:"foodProvidesName"}},[t("el-input",{model:{value:e.addform.foodProvidesName,callback:function(a){e.addform.foodProvidesName=a},expression:"addform.foodProvidesName"}})],1):e._e(),e._v(" "),t("el-form-item",{staticClass:"tishi",attrs:{label:"新订单接收电话：",prop:"orderReceivePhone"}},[t("el-input",{model:{value:e.addform.orderReceivePhone,callback:function(a){e.addform.orderReceivePhone=a},expression:"addform.orderReceivePhone"}}),e._v(" "),0!=e.addform.foodProvides?t("p",[e._v("本酒店用于接收新客房订餐订单的电话")]):e._e(),e._v(" "),1==e.addform.foodProvides?t("p",[e._v("合作方用于接收新客房订餐订单的电话")]):e._e()],1),e._v(" "),t("el-form-item",{attrs:{label:"菜品图片：",prop:"foodImage"}},[t("div",{staticClass:"uploader-box"},[t("div",{staticClass:"avatar-uploader",on:{click:e.materiallayer}},[e.addform.foodImage?e._e():t("div",{staticClass:"el-upload el-upload--text"},[t("i",{staticClass:"el-icon-plus avatar-uploader-icon"}),e._v(" "),t("input",{staticClass:"el-upload__input",attrs:{type:"file",name:"file"}})]),e._v(" "),e.addform.foodImage?t("img",{staticClass:"avatar",attrs:{src:e.addform.foodImage}}):e._e()]),e._v(" "),t("span",{staticClass:"text"},[e._v("图片尺寸：120*120px")])])]),e._v(" "),t("el-form-item",{attrs:{label:"菜品单价：",prop:"price"}},[t("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.addform.price,callback:function(a){e.addform.price=a},expression:"addform.price"}},[t("template",{slot:"prepend"},[e._v("￥")])],2)],1),e._v(" "),t("el-form-item",{attrs:{label:"配送时间：",prop:"deliveryTime"}},[t("el-input",{attrs:{placeholder:"请输入配送时间"},model:{value:e.addform.deliveryTime,callback:function(a){e.addform.deliveryTime=a},expression:"addform.deliveryTime"}}),e._v(" "),t("span",{staticClass:"Min"},[e._v("分钟")])],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:function(a){e.onSubmit("addform")}}},[e._v("保存")]),e._v(" "),t("el-button",{on:{click:function(a){e.revertfn("addform")}}},[e._v("返回")])],1)],1),e._v(" "),t("el-dialog",{attrs:{title:"提示",visible:e.dialogFormVisible,"modal-append-to-body":!1,"close-on-click-modal":!1,"custom-class":"Smallmodal storey"},on:{"update:visible":function(a){e.dialogFormVisible=a}}},[t("Confirm",{attrs:{Popup:"保存成功",flag:e.dialogFormVisible}})],1),e._v(" "),t("el-dialog",{ref:"materialUrl",attrs:{title:"素材库",visible:e.library,"modal-append-to-body":!1,"close-on-click-modal":!1},on:{"update:visible":function(a){e.library=a}}},[t("div",[t("iframe",{staticStyle:{width:"100%",height:"450px",border:"0px"},attrs:{src:e.materialUrl,scrolling:"no"}})])])],1)},staticRenderFns:[]}},282:function(e,a){e.exports={render:function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("div",{staticClass:"MobileSet"},[t("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(a){e.activeName=a},expression:"activeName"}},[t("el-tab-pane",{staticClass:"first-box",attrs:{label:"手机页面设置",name:"first"}},[e.Hoteldata?t("div",{staticClass:"video-top"},[t("div",{staticClass:"clearfix"},[e.Hoteldata?t("el-form",{staticClass:"demo-form-inline fl Hoteldata",attrs:{inline:!0}},[t("el-form-item",{attrs:{label:"选择酒店："}},[t("el-select",{attrs:{placeholder:"请选择酒店"},on:{change:e.changeHotel1},model:{value:e.hotelId1,callback:function(a){e.hotelId1=a},expression:"hotelId1"}},e._l(e.Hoteldata,function(e){return t("el-option",{key:e.hotelId,attrs:{label:e.name,value:e.hotelId}})}))],1)],1):e._e()],1)]):e._e(),e._v(" "),e.Hoteldata?t("div",{staticClass:"clearfix MobileSet-region",class:{active:!e.isActive},staticStyle:{width:"1400px"}},[t("el-form",{ref:"pagedata",attrs:{rules:e.pagedatarules,model:e.pagedata,"label-width":"180px"}},[t("el-form-item",{attrs:{label:"酒店图片：",prop:"imageurls"}},[t("div",{staticClass:"upload-demo upload"},[t("div",{staticClass:"el-upload el-upload--picture"},[t("button",{staticClass:"el-button el-button--primary el-button--small",attrs:{"data-v-76502b23":"",type:"button"},on:{click:e.materiallayer}},[t("span",[e._v("点击上传")])]),e._v(" "),t("input",{staticClass:"el-upload__input",attrs:{type:"file",name:"file"}})]),e._v(" "),t("div",{staticClass:"el-upload__tip",attrs:{"data-v-76502b23":""}},[e._v("只能上传JPG/PNG文件，且不超过500KB，图片尺寸750*430px（最多上传6张）")]),e._v(" "),t("ul",{staticClass:"el-upload-list el-upload-list--picture"},e._l(e.pagedata.imageurls,function(a,i){return t("li",{key:i,staticClass:"el-upload-list__item is-success"},[t("img",{staticClass:"el-upload-list__item-thumbnail",attrs:{src:a.path}}),e._v(" "),t("a",{staticClass:"el-upload-list__item-name"},[t("i",{staticClass:"el-icon-document"}),e._v(e._s(a.name)+"\n                  ")]),e._v(" "),t("label",{staticClass:"el-upload-list__item-status-label"},[t("i",{staticClass:"el-icon-upload-success el-icon-check"})]),e._v(" "),t("i",{staticClass:"el-icon-close",on:{click:function(a){e.closeImg(i)}}})])}))])]),e._v(" "),t("el-form-item",{attrs:{label:"基础设施："}},[t("div",{staticClass:"equipment"},[t("el-checkbox-group",{model:{value:e.pagedata.installations,callback:function(a){e.pagedata.installations=a},expression:"pagedata.installations"}},e._l(e.installations,function(a){return t("el-checkbox",{key:a.id,attrs:{label:a.id}},[t("i",{staticClass:"iconfont ",class:a.iconUrl}),e._v(" "),t("span",{staticClass:"text"},[e._v(e._s(a.name))])])}))],1)]),e._v(" "),t("el-form-item",{attrs:{label:"酒店公告："}},[t("el-switch",{attrs:{"on-text":"","off-text":"","on-value":0,"off-value":1},model:{value:e.pagedata.noticeEnable,callback:function(a){e.pagedata.noticeEnable=a},expression:"pagedata.noticeEnable"}}),e._v(" "),0==e.pagedata.noticeEnable?t("div",{staticClass:"limitbox"},[t("el-form-item",{attrs:{label:"公告内容：","label-width":"120px",prop:"bulletin"}},[t("el-input",{staticClass:"w420",attrs:{type:"textarea"},model:{value:e.pagedata.bulletin,callback:function(a){e.pagedata.bulletin=a},expression:"pagedata.bulletin"}})],1)],1):e._e()],1),e._v(" "),t("el-form-item",{attrs:{label:"显示剩余房间数："}},[t("el-switch",{attrs:{"on-text":"","off-text":"","on-value":0,"off-value":1},model:{value:e.pagedata.remnantRoomEnable,callback:function(a){e.pagedata.remnantRoomEnable=a},expression:"pagedata.remnantRoomEnable"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"支付方式：",prop:"payMode"}},[t("el-radio-group",{model:{value:e.pagedata.payMode,callback:function(a){e.pagedata.payMode=a},expression:"pagedata.payMode"}},[t("el-radio",{attrs:{label:1}},[e._v("在线支付")]),e._v(" "),t("el-radio",{attrs:{label:2}},[e._v("到店支付")]),e._v(" "),t("el-radio",{attrs:{label:3}},[e._v("在线支付&到店支付")])],1)],1),e._v(" "),t("el-form-item",{staticClass:"tishi",attrs:{label:"订房通知："}},[t("el-switch",{attrs:{"on-text":"","off-text":"","on-value":0,"off-value":1},model:{value:e.pagedata.smsEnable,callback:function(a){e.pagedata.smsEnable=a},expression:"pagedata.smsEnable"}}),e._v(" "),t("p",[e._v("若有新订房消息，商家将收到短信通知")]),e._v(" "),0==e.pagedata.smsEnable?t("div",{staticClass:"limitbox"},[t("el-form-item",{attrs:{label:"接收短信的手机号：",prop:"smsPhone"}},[t("el-input",{staticClass:"w220",model:{value:e.pagedata.smsPhone,callback:function(a){e.pagedata.smsPhone=a},expression:"pagedata.smsPhone"}})],1)],1):e._e()],1),e._v(" "),t("el-form-item",{staticClass:"tishi",attrs:{label:"客房订餐："}},[t("el-switch",{attrs:{"on-text":"","off-text":"","on-value":0,"off-value":1},model:{value:e.pagedata.roomReservationEnable,callback:function(a){e.pagedata.roomReservationEnable=a},expression:"pagedata.roomReservationEnable"}}),e._v(" "),0==e.pagedata.roomReservationEnable?t("div",{staticClass:"limitbox"},[t("el-form-item",{attrs:{label:"餐饮支付方式：",prop:"foodPayMode"}},[t("el-radio-group",{model:{value:e.pagedata.foodPayMode,callback:function(a){e.pagedata.foodPayMode=a},expression:"pagedata.foodPayMode"}},[t("el-radio",{attrs:{label:1}},[e._v("在线支付")]),e._v(" "),t("el-radio",{attrs:{label:2}},[e._v("到店支付")]),e._v(" "),t("el-radio",{attrs:{label:3}},[e._v("在线支付&到店支付")])],1)],1)],1):e._e()],1),e._v(" "),t("el-form-item",{staticClass:"tishi",attrs:{label:"预约退房："}},[t("el-switch",{attrs:{"on-text":"","off-text":"","on-value":0,"off-value":1},model:{value:e.pagedata.reservationCheckOutEnable,callback:function(a){e.pagedata.reservationCheckOutEnable=a},expression:"pagedata.reservationCheckOutEnable"}}),e._v(" "),0==e.pagedata.reservationCheckOutEnable?t("div",{staticClass:"limitbox"},[t("el-form-item",{attrs:{label:"接收短信的手机号：",prop:"reservationCheckOutPhone"}},[t("el-input",{staticClass:"w220",model:{value:e.pagedata.reservationCheckOutPhone,callback:function(a){e.pagedata.reservationCheckOutPhone=a},expression:"pagedata.reservationCheckOutPhone"}}),e._v(" "),t("p",[e._v("有新的退房消息将会收到短信通知")])],1)],1):e._e()],1),e._v(" "),t("el-form-item",{attrs:{label:"发票类目：",prop:"invoiceCategorys"}},[t("el-checkbox-group",{model:{value:e.pagedata.invoiceCategorys,callback:function(a){e.pagedata.invoiceCategorys=a},expression:"pagedata.invoiceCategorys"}},e._l(e.invoice,function(a){return t("el-checkbox",{key:a.id,attrs:{label:a.id}},[e._v("\n                "+e._s(a.dictCnName)+"\n              ")])}))],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:function(a){e.onSubmit("pagedata")}}},[e._v("保存")])],1)],1)],1):e._e()]),e._v(" "),t("el-tab-pane",{attrs:{label:"客房订餐设置",name:"second"}},[e.Hoteldata?t("div",{staticClass:"second-box MobileSet-region",class:{active:!e.isActive}},[t("div",{staticClass:"video-top"},[t("div",{staticClass:"clearfix"},[e.Hoteldata?t("el-form",{staticClass:"demo-form-inline fl Hoteldata",attrs:{inline:!0}},[t("el-form-item",{attrs:{label:"选择酒店："}},[t("el-select",{attrs:{placeholder:"请选择酒店"},on:{change:e.changeHotel2},model:{value:e.hotelId2,callback:function(a){e.hotelId2=a},expression:"hotelId2"}},e._l(e.Hoteldata,function(e){return t("el-option",{key:e.hotelId,attrs:{label:e.name,value:e.hotelId}})}))],1)],1):e._e(),e._v(" "),t("el-button",{staticClass:"video fr",attrs:{type:"warning",size:"small"}},[t("i",{staticClass:"iconfont icon-shipin"}),e._v(" 视频教程")])],1),e._v(" "),t("el-button",{attrs:{type:"primary",size:"small"},on:{click:e.newadd}},[e._v("新增")])],1),e._v(" "),t("div",{staticClass:"plr50"},[t("h2",[e._v("已创建的菜品列表")]),e._v(" "),t("el-table",{staticStyle:{width:"100%","margin-top":"15px"},attrs:{data:e.footlist}},[t("el-table-column",{attrs:{prop:"name",label:"菜名"}}),e._v(" "),t("el-table-column",{attrs:{prop:"price",label:"单价(元)"},scopedSlots:e._u([{key:"default",fn:function(a){return[e._v("\n                "+e._s(e._f("money")(a.row.price))+"\n              ")]}}])}),e._v(" "),t("el-table-column",{attrs:{prop:"deliveryTime",label:"配送时间(min)"}}),e._v(" "),t("el-table-column",{attrs:{prop:"foodProvidesName",label:"菜品提供方"},scopedSlots:e._u([{key:"default",fn:function(a){return[0==a.row.foodProvides?t("span",[e._v("本酒店")]):t("span",[e._v(e._s(a.row.foodProvidesName))])]}}])}),e._v(" "),t("el-table-column",{attrs:{label:"操作",width:"180"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("el-button",{staticClass:"blue-button",attrs:{size:"small"},on:{click:function(t){e.handleEdit(a.row.id)}}},[e._v("编辑")]),e._v(" "),t("el-button",{attrs:{size:"small"},on:{click:function(t){e.handleDelete(a.row.id)}}},[e._v("删除")])]}}])})],1),e._v(" "),t("el-pagination",{attrs:{"current-page":e.currentPage,"page-count":e.pages,layout:"prev, pager, next, jumper"},on:{"current-change":e.handleCurrentChange,"update:currentPage":function(a){e.currentPage=a}}})],1)]):e._e()])],1),e._v(" "),t("FoodAdd",{class:{active:e.isActive},attrs:{foodid:e.foodid,hotelId:e.hotelId2,isActive:e.isActive},on:{"update:isActive":function(a){e.isActive=a}}}),e._v(" "),t("el-dialog",{attrs:{title:"提示",visible:e.dialogFormVisible,"modal-append-to-body":!1,"close-on-click-modal":!1,"custom-class":"Smallmodal storey"},on:{"update:visible":function(a){e.dialogFormVisible=a}}},[t("Confirm",{attrs:{Popup:"保存成功",flag:e.dialogFormVisible}})],1),e._v(" "),t("el-dialog",{attrs:{visible:e.dialogFormVisible2,"modal-append-to-body":!1,"close-on-click-modal":!1,"custom-class":"Smallmodal header-hidder"},on:{"update:visible":function(a){e.dialogFormVisible2=a}}},[e._v("\n    确认删除？\n    "),t("div",{staticClass:"dialog-footer",slot:"footer"},[t("el-button",{attrs:{type:"primary"},on:{click:e.Deletefn}},[e._v("确 定")]),e._v(" "),t("el-button",{on:{click:function(a){e.dialogFormVisible2=!1}}},[e._v("取 消")])],1)]),e._v(" "),t("el-dialog",{ref:"materialUrl",attrs:{title:"素材库",visible:e.library,"modal-append-to-body":!1,"close-on-click-modal":!1},on:{"update:visible":function(a){e.library=a}}},[t("div",[t("iframe",{staticStyle:{width:"100%",height:"450px",border:"0px"},attrs:{src:e.materialUrl,scrolling:"no"}})])])],1)},staticRenderFns:[]}},287:function(e,a,t){t(260);var i=t(4)(t(169),t(281),"data-v-764ca87a",null);e.exports=i.exports}},[165]);