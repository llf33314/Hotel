webpackJsonp([1],{107:function(t,e,o){o(255);var a=o(4)(o(172),o(276),"data-v-5bf9b3e7",null);t.exports=a.exports},108:function(t,e,o){o(250);var a=o(4)(o(173),o(271),null,null);t.exports=a.exports},109:function(t,e,o){o(261);var a=o(4)(o(174),o(282),"data-v-923d7f70",null);t.exports=a.exports},161:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=o(0),r=o(11),n=(o(9),o(70)),i=o(107),s=o.n(i),l=o(108),c=o.n(l),u=o(109),d=o.n(u),h=o(3);a.default.use(n.a);var f=[{path:"/",redirect:function(t){return"/Increase"}},{path:"/Increase",component:c.a},{path:"/SelectStores",component:d.a},{path:"/Editor/:hotelId",component:s.a}];a.default.use(r.a);var p=new r.a({routes:f});new a.default({router:p,store:h.a}).$mount("#app")},172:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=o(30),r=(o.n(a),o(1)),n=o(12);e.default={data:function(){return{details:{},records:"",store:"",rules:{name:[{required:!0,message:"请输入酒店名称"}],phone:[{required:!0,validator:n.phonetest}],address:[{required:!0,message:"请输入酒店地址"}],desc:[{required:!0,message:"酒店介绍"}]},longitude:null,latitude:null,map:null,addressArr:[],geocoder:null}},methods:{onSubmit:function(t){var e=this;this.$refs[t].validate(function(t){if(!t)return!1;e.geocoder.getLocation(e.details.address,function(t,o){"complete"==t&&"OK"==o.info?r.f.ADDdetailsmodify({params:{hotelId:e.details.hotelId,shopId:e.details.shopId,name:e.details.name,tel:e.details.phone,addr:e.details.address,longitude:o.geocodes[0].location.lng,latitude:o.geocodes[0].location.lat,desc:e.details.desc}}).then(function(t){0==t.code&&(e.$message({message:"修改成功",type:"success"}),setTimeout(function(){e.$router.go(-1)},1e3))}):e.$message.error("获取经纬度失败")})})},mapfn:function(){var t=this;t.map=new AMap.Map("container",{resizeEnable:!0,zoom:11,center:[t.longitude,t.latitude]}),AMap.service("AMap.Geocoder",function(){t.geocoder=new AMap.Geocoder({})})},mapsearch:function(t,e){var o=this;AMap.service(["AMap.PlaceSearch"],function(){var t=new AMap.PlaceSearch({type:"住宿服务|餐饮服务",pageSize:5,pageIndex:1,city:"010",map:o.map});o.addressArr=[],o.latitude=null,o.longitude=null,t.search(o.details.address,function(t,a){a&&a.poiList&&a.poiList.pois&&(a.poiList.pois.forEach(function(t){o.addressArr.push({value:t.pname+t.cityname+t.adname+t.address+t.name,address:t.pname+t.cityname+t.adname+t.address+t.name})}),e(o.addressArr))})})}},mounted:function(){var t=this;r.a.Hotelinfo({Suffix:this.$route.params.hotelId}).then(function(e){if(0==e.code){t.details=e.data;var o=t;o.longitude=o.details.longitude,o.latitude=o.details.latitude,t.$nextTick(function(t){o.mapfn()})}})}}},173:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=o(1),r=o(46),n=o.n(r);o(3);e.default={data:function(){return{dialogTableVisible:!1,dialogFormVisible1:!1,Hotellist:[],deleteid:"",codedata:"",baseURL:""}},methods:{deleteBox:function(t){this.deleteid=t,this.dialogFormVisible1=!0},deletefn:function(){var t=this,e=this;a.f.ADDdelete({params:"["+e.deleteid+"]"}).then(function(o){0==o.code&&(t.$message({message:"删除成功",type:"success"}),setTimeout(function(){e.$router.go(0)},1e3))}),this.dialogFormVisible1=!1},linkbtn:function(t){var e=this;a.f.hotelLink({Suffix:t+"/link"}).then(function(t){if(0==t.code){e.codedata=t.data,e.dialogTableVisible=!0;var o=e;e.$nextTick(function(t){new n.a(document.getElementById("qrcode"),o.codedata.longUrl)})}})},linkcopy:function(t){var e=this.$refs[t].$refs.input;e.focus();var o=document.activeElement;e.setSelectionRange(0,e.value.length),document.execCommand("copy",!0),o.focus()},downloadfn:function(){window.open(this.baseURL+"/back/hotel/qrcode?url="+this.codedata.longUrl)}},mounted:function(){var t=this;a.a.Hotellist().then(function(e){0==e.code&&(0!=e.data.records.length?t.Hotellist=e.data.records:t.$message("无酒店,请添加酒店"))}),this.$store.dispatch("getbackconfig").then(function(){t.baseURL=t.$store.state.baseURL})}}},174:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=o(13),r=o.n(a),n=o(30),i=(o.n(n),o(1)),s=o(12),l=o(68),c=o.n(l);e.default={data:function(){return{active:0,step1:-1,records:[],form:{name:"",phone:"",address:"",introduction:""},rules:{name:[{required:!0,message:"请输入酒店名称"}],phone:[{required:!0,validator:s.phonetest}],address:[{required:!0,message:"请输入酒店地址"}],introduction:[{required:!0,message:"请输入酒店介绍"}]},longitude:null,latitude:null,map:null,addressArr:[],geocoder:null}},methods:{step1fn:function(t,e){if(t.hotelId)return this.$message({message:"该门店已存在酒店",type:"warning"}),!1;this.step1=e},nextStep:function(){this.active++,this.mapfn()},onSubmit:function(t){var e=this;this.$refs[t].validate(function(t){if(!t)return!1;e.geocoder.getLocation(e.form.address,function(t,o){"complete"==t&&"OK"==o.info?i.f.ADDdetailsmodify({params:{shopId:e.records[e.step1].shopId,name:e.form.name,tel:e.form.phone,addr:e.form.address,longitude:o.geocodes[0].location.lng,latitude:o.geocodes[0].location.lat,desc:e.form.introduction}}).then(function(t){0==t.code&&e.active++}):e.$message.error("获取经纬度失败")})})},mapfn:function(){function t(t){var e=JSON.parse(r()(t));a.form.address=e.formattedAddress,a.latitude=e.position.lat,a.longitude=e.position.lng}function e(t){}var o,a=this;a.map=new AMap.Map("container",{zoom:10,resizeEnable:!0}),a.map.plugin("AMap.Geolocation",function(){o=new AMap.Geolocation({enableHighAccuracy:!0,timeout:1e5,buttonOffset:new AMap.Pixel(10,20),zoomToAccuracy:!0,buttonPosition:"RB"}),a.map.addControl(o),o.getCurrentPosition(),AMap.event.addListener(o,"complete",t),AMap.event.addListener(o,"error",e)}),AMap.service("AMap.Geocoder",function(){a.geocoder=new AMap.Geocoder({})})},mapsearch:function(t,e){var o=this;AMap.service(["AMap.PlaceSearch"],function(){var t=new AMap.PlaceSearch({type:"住宿服务|餐饮服务",pageSize:5,pageIndex:1,city:"010",map:o.map});o.addressArr=[],o.latitude=null,o.longitude=null,t.search(o.form.address,function(t,a){a&&a.poiList&&a.poiList.pois&&(a.poiList.pois.forEach(function(t){o.addressArr.push({value:t.pname+t.cityname+t.adname+t.address+t.name,address:t.pname+t.cityname+t.adname+t.address+t.name})}),e(o.addressArr))})})}},mounted:function(){var t=this,e=this;i.f.ADDstorelist().then(function(o){if(0==o.code)if(0!=o.data.length){e.records=o.data;for(var a=0;a<e.records.length;a++){var r=e.records[a];if(!r.hotelId)return e.step1=a,!1}}else t.$message({message:"请添加门店",type:"warning"})})},components:{AddSuccess:c.a}}},250:function(t,e){},255:function(t,e){},261:function(t,e){},271:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"Increase"},[o("div",{staticClass:"video-top"},[o("router-link",{staticClass:"el-button el-button--primary el-button--small",attrs:{to:"/SelectStores",tag:"el-button"}},[t._v("新增酒店")]),t._v(" "),o("el-button",{staticClass:"video fr",attrs:{type:"warning",size:"small"}},[o("i",{staticClass:"iconfont icon-shipin"}),t._v("视频教程")])],1),t._v(" "),o("div",{staticClass:"pl50"},[t._m(0),t._v(" "),o("div",{staticClass:"clearfix"},t._l(t.Hotellist,function(e,a){return o("div",{key:a,staticClass:"item"},[o("div",{staticClass:"above clearfix"},[o("img",{attrs:{src:e.logoUrl}}),t._v(" "),o("div",{staticClass:"text"},[o("el-tooltip",{attrs:{effect:"dark",content:e.name,placement:"top-start"}},[o("h3",[t._v(t._s(e.name))])]),t._v(" "),o("el-tooltip",{attrs:{effect:"dark",content:e.phone,placement:"top-start"}},[o("p",[t._v("联系电话："+t._s(e.phone))])]),t._v(" "),o("el-tooltip",{attrs:{effect:"dark",content:e.address,placement:"top-start"}},[o("p",{staticClass:"ellipsis"},[t._v("地址："+t._s(e.address))])])],1)]),t._v(" "),o("div",{staticClass:"item-bottom"},[o("router-link",{staticClass:"el-button blue-button el-button--default el-button--small",attrs:{to:"/Editor/"+e.hotelId,tag:"el-button"}},[t._v("编辑")]),t._v(" "),o("el-button",{staticClass:"blue-button",attrs:{size:"small"},on:{click:function(o){t.linkbtn(e.hotelId)}}},[t._v("链接")]),t._v(" "),o("el-button",{attrs:{size:"small"},on:{click:function(o){t.deleteBox(e.hotelId)}}},[t._v("删除")])],1)])}))]),t._v(" "),o("el-dialog",{attrs:{title:"酒店链接",visible:t.dialogTableVisible,"modal-append-to-body":!1,"close-on-click-modal":!1,"custom-class":"tinymodal HotelLink"},on:{"update:visible":function(e){t.dialogTableVisible=e}}},[o("div",{staticClass:"itemColumn clearfix"},[o("span",{staticClass:"Columnleft"},[t._v("酒店二维码：")]),t._v(" "),o("div",{staticClass:"Columnright"},[o("div",{attrs:{id:"qrcode"}}),t._v(" "),o("el-button",{attrs:{type:"primary"},on:{click:t.downloadfn}},[t._v("下载二维码")])],1)]),t._v(" "),o("div",{staticClass:"itemColumn clearfix"},[o("span",{staticClass:"Columnleft"},[t._v("酒店链接：")]),t._v(" "),o("div",{staticClass:"Columnright"},[o("el-input",{ref:"longUrl",attrs:{placeholder:"请输入内容"},model:{value:t.codedata.longUrl,callback:function(e){t.codedata.longUrl=e},expression:"codedata.longUrl"}}),t._v(" "),o("el-button",{attrs:{type:"primary"},on:{click:function(e){t.linkcopy("longUrl")}}},[t._v("复制")])],1)]),t._v(" "),o("div",{staticClass:"itemColumn clearfix"},[o("span",{staticClass:"Columnleft"},[t._v("短信链接：")]),t._v(" "),o("div",{staticClass:"Columnright"},[o("el-input",{ref:"shortUrl",attrs:{placeholder:"请输入内容"},model:{value:t.codedata.shortUrl,callback:function(e){t.codedata.shortUrl=e},expression:"codedata.shortUrl"}}),t._v(" "),o("el-button",{attrs:{type:"primary"},on:{click:function(e){t.linkcopy("shortUrl")}}},[t._v("复制")])],1)])]),t._v(" "),o("el-dialog",{attrs:{visible:t.dialogFormVisible1,"modal-append-to-body":!1,"close-on-click-modal":!1,"custom-class":"Smallmodal"},on:{"update:visible":function(e){t.dialogFormVisible1=e}}},[o("p",[t._v("是否确认删除酒店，删除后数据将无法恢复")]),t._v(" "),o("div",{staticClass:"dialog-footer",slot:"footer"},[o("el-button",{attrs:{type:"primary"},on:{click:t.deletefn}},[t._v("确 定")]),t._v(" "),o("el-button",{on:{click:function(e){t.dialogFormVisible1=!1}}},[t._v("取 消")])],1)])],1)},staticRenderFns:[function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"Increase-title"},[o("h2",[t._v("已创建的酒店")])])}]}},276:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"Editor"},[o("div",{staticClass:"pagePath pl50"},[o("span",{staticClass:"bulefont",on:{click:function(e){t.$router.go(-1)}}},[t._v("酒店管理")]),t._v(" /\n    "),o("span",[t._v("编辑")])]),t._v(" "),o("el-form",{ref:"details",attrs:{model:t.details,rules:t.rules,"label-width":"100px"}},[o("el-form-item",{staticClass:"mb",attrs:{label:"所属门店："}},[o("span",{domProps:{textContent:t._s(t.details.shopName)}})]),t._v(" "),o("el-form-item",{staticClass:"mb",attrs:{label:"联系电话："}},[o("span",{domProps:{textContent:t._s(t.details.shopPhone)}})]),t._v(" "),o("el-form-item",{attrs:{label:"地址："}},[o("span",{domProps:{textContent:t._s(t.details.shopAddr)}})]),t._v(" "),o("el-form-item",{attrs:{label:"酒店名称：",prop:"name"}},[o("el-input",{model:{value:t.details.name,callback:function(e){t.details.name=e},expression:"details.name"}})],1),t._v(" "),o("el-form-item",{attrs:{label:"酒店电话：",prop:"phone"}},[o("el-input",{model:{value:t.details.phone,callback:function(e){t.details.phone=t._n(e)},expression:"details.phone"}})],1),t._v(" "),o("el-form-item",{attrs:{label:"酒店地址：",prop:"address"}},[o("el-autocomplete",{staticClass:"location",attrs:{"fetch-suggestions":t.mapsearch,placeholder:"请输入内容"},model:{value:t.details.address,callback:function(e){t.details.address=e},expression:"details.address"}}),t._v(" "),o("div",{attrs:{id:"container"}})],1),t._v(" "),o("el-form-item",{attrs:{label:"酒店介绍：",prop:"desc"}},[o("el-input",{attrs:{type:"textarea"},model:{value:t.details.desc,callback:function(e){t.details.desc=e},expression:"details.desc "}})],1),t._v(" "),o("el-form-item",[o("el-button",{attrs:{type:"primary"},on:{click:function(e){t.onSubmit("details")}}},[t._v("保存")]),t._v(" "),o("router-link",{staticClass:"el-button el-button--default",attrs:{to:"/Increase",tag:"el-button"}},[t._v("返回")])],1)],1)],1)},staticRenderFns:[]}},282:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"addStep"},[o("div",{staticClass:"step-top"},[o("el-steps",{attrs:{active:t.active,center:!0,"align-center":!0}},[o("el-step",{attrs:{title:"选择门店"}}),t._v(" "),o("el-step",{attrs:{title:"酒店基本信息"}}),t._v(" "),o("el-step",{attrs:{title:"新增酒店成功"}})],1)],1),t._v(" "),o("div",{directives:[{name:"show",rawName:"v-show",value:0==t.active,expression:"active==0"}],staticClass:"step1 pl50"},[o("div",{staticClass:"clearfix"},t._l(t.records,function(e,a){return o("div",{key:a,staticClass:"item",class:{active:t.step1==a,Optional:!e.hotelId},on:{click:function(o){t.step1fn(e,a)}}},[o("div",{staticClass:"above clearfix"},[o("img",{attrs:{src:e.image}}),t._v(" "),o("div",{staticClass:"text"},[o("el-tooltip",{attrs:{effect:"dark",content:e.name,placement:"top-start"}},[o("h3",[t._v(t._s(e.name))])]),t._v(" "),o("el-tooltip",{attrs:{effect:"dark",content:e.tel,placement:"top-start"}},[o("p",[t._v("联系电话："+t._s(e.tel))])]),t._v(" "),o("el-tooltip",{attrs:{effect:"dark",content:e.addr,placement:"top-start"}},[o("p",{staticClass:"ellipsis"},[t._v("地址："+t._s(e.addr))])])],1)])])})),t._v(" "),o("div",{staticClass:"step1-bottom"},[0!=t.records.length&&t.step1>-1?o("el-button",{attrs:{type:"primary"},on:{click:t.nextStep}},[t._v("下一步")]):t._e(),t._v(" "),o("el-button",{on:{click:function(e){t.$router.go(-1)}}},[t._v("返回")])],1)]),t._v(" "),o("div",{directives:[{name:"show",rawName:"v-show",value:1==t.active,expression:"active==1"}],staticClass:"step2"},[o("el-form",{ref:"form",attrs:{model:t.form,rules:t.rules,"label-width":"100px"}},[o("el-form-item",{attrs:{label:"酒店名称：",prop:"name"}},[o("el-input",{model:{value:t.form.name,callback:function(e){t.form.name=e},expression:"form.name"}})],1),t._v(" "),o("el-form-item",{attrs:{label:"酒店电话：",prop:"phone"}},[o("el-input",{model:{value:t.form.phone,callback:function(e){t.form.phone=e},expression:"form.phone"}})],1),t._v(" "),o("el-form-item",{attrs:{label:"酒店地址：",prop:"address"}},[o("el-autocomplete",{staticClass:"location",attrs:{"fetch-suggestions":t.mapsearch,placeholder:"请输入内容"},model:{value:t.form.address,callback:function(e){t.form.address=e},expression:"form.address"}}),t._v(" "),o("div",{attrs:{id:"container"}})],1),t._v(" "),o("el-form-item",{attrs:{label:"酒店介绍：",prop:"introduction"}},[o("el-input",{attrs:{type:"textarea"},model:{value:t.form.introduction,callback:function(e){t.form.introduction=e},expression:"form.introduction"}})],1),t._v(" "),o("el-form-item",[o("el-button",{attrs:{type:"primary"},on:{click:function(e){t.onSubmit("form")}}},[t._v("保存")]),t._v(" "),o("el-button",{on:{click:function(e){t.active--}}},[t._v("返回")])],1)],1)],1),t._v(" "),o("div",{directives:[{name:"show",rawName:"v-show",value:2==t.active,expression:"active==2"}],staticClass:"step3"},[o("AddSuccess")],1)])},staticRenderFns:[]}},46:function(t,e){var o;!function(){function t(t){this.mode=c.MODE_8BIT_BYTE,this.data=t,this.parsedData=[];for(var e=[],o=0,a=this.data.length;a>o;o++){var r=this.data.charCodeAt(o);r>65536?(e[0]=240|(1835008&r)>>>18,e[1]=128|(258048&r)>>>12,e[2]=128|(4032&r)>>>6,e[3]=128|63&r):r>2048?(e[0]=224|(61440&r)>>>12,e[1]=128|(4032&r)>>>6,e[2]=128|63&r):r>128?(e[0]=192|(1984&r)>>>6,e[1]=128|63&r):e[0]=r,this.parsedData=this.parsedData.concat(e)}this.parsedData.length!=this.data.length&&(this.parsedData.unshift(191),this.parsedData.unshift(187),this.parsedData.unshift(239))}function e(t,e){this.typeNumber=t,this.errorCorrectLevel=e,this.modules=null,this.moduleCount=0,this.dataCache=null,this.dataList=[]}function a(t,e){if(void 0==t.length)throw new Error(t.length+"/"+e);for(var o=0;o<t.length&&0==t[o];)o++;this.num=new Array(t.length-o+e);for(var a=0;a<t.length-o;a++)this.num[a]=t[a+o]}function r(t,e){this.totalCount=t,this.dataCount=e}function n(){this.buffer=[],this.length=0}function i(){var t=!1,e=navigator.userAgent;return/android/i.test(e)&&(t=!0,aMat=e.toString().match(/android ([0-9]\.[0-9])/i),aMat&&aMat[1]&&(t=parseFloat(aMat[1]))),t}function s(t,e){for(var o=1,a=l(t),r=0,n=m.length;n>=r;r++){var i=0;switch(e){case u.L:i=m[r][0];break;case u.M:i=m[r][1];break;case u.Q:i=m[r][2];break;case u.H:i=m[r][3]}if(i>=a)break;o++}if(o>m.length)throw new Error("Too long data");return o}function l(t){var e=encodeURI(t).toString().replace(/\%[0-9a-fA-F]{2}/g,"a");return e.length+(e.length!=t?3:0)}t.prototype={getLength:function(){return this.parsedData.length},write:function(t){for(var e=0,o=this.parsedData.length;o>e;e++)t.put(this.parsedData[e],8)}},e.prototype={addData:function(e){var o=new t(e);this.dataList.push(o),this.dataCache=null},isDark:function(t,e){if(0>t||this.moduleCount<=t||0>e||this.moduleCount<=e)throw new Error(t+","+e);return this.modules[t][e]},getModuleCount:function(){return this.moduleCount},make:function(){this.makeImpl(!1,this.getBestMaskPattern())},makeImpl:function(t,o){this.moduleCount=4*this.typeNumber+17,this.modules=new Array(this.moduleCount);for(var a=0;a<this.moduleCount;a++){this.modules[a]=new Array(this.moduleCount);for(var r=0;r<this.moduleCount;r++)this.modules[a][r]=null}this.setupPositionProbePattern(0,0),this.setupPositionProbePattern(this.moduleCount-7,0),this.setupPositionProbePattern(0,this.moduleCount-7),this.setupPositionAdjustPattern(),this.setupTimingPattern(),this.setupTypeInfo(t,o),this.typeNumber>=7&&this.setupTypeNumber(t),null==this.dataCache&&(this.dataCache=e.createData(this.typeNumber,this.errorCorrectLevel,this.dataList)),this.mapData(this.dataCache,o)},setupPositionProbePattern:function(t,e){for(var o=-1;7>=o;o++)if(!(-1>=t+o||this.moduleCount<=t+o))for(var a=-1;7>=a;a++)-1>=e+a||this.moduleCount<=e+a||(this.modules[t+o][e+a]=o>=0&&6>=o&&(0==a||6==a)||a>=0&&6>=a&&(0==o||6==o)||o>=2&&4>=o&&a>=2&&4>=a)},getBestMaskPattern:function(){for(var t=0,e=0,o=0;8>o;o++){this.makeImpl(!0,o);var a=h.getLostPoint(this);(0==o||t>a)&&(t=a,e=o)}return e},createMovieClip:function(t,e,o){var a=t.createEmptyMovieClip(e,o);this.make();for(var r=0;r<this.modules.length;r++)for(var n=1*r,i=0;i<this.modules[r].length;i++){var s=1*i,l=this.modules[r][i];l&&(a.beginFill(0,100),a.moveTo(s,n),a.lineTo(s+1,n),a.lineTo(s+1,n+1),a.lineTo(s,n+1),a.endFill())}return a},setupTimingPattern:function(){for(var t=8;t<this.moduleCount-8;t++)null==this.modules[t][6]&&(this.modules[t][6]=0==t%2);for(var e=8;e<this.moduleCount-8;e++)null==this.modules[6][e]&&(this.modules[6][e]=0==e%2)},setupPositionAdjustPattern:function(){for(var t=h.getPatternPosition(this.typeNumber),e=0;e<t.length;e++)for(var o=0;o<t.length;o++){var a=t[e],r=t[o];if(null==this.modules[a][r])for(var n=-2;2>=n;n++)for(var i=-2;2>=i;i++)this.modules[a+n][r+i]=-2==n||2==n||-2==i||2==i||0==n&&0==i}},setupTypeNumber:function(t){for(var e=h.getBCHTypeNumber(this.typeNumber),o=0;18>o;o++){var a=!t&&1==(1&e>>o);this.modules[Math.floor(o/3)][o%3+this.moduleCount-8-3]=a}for(var o=0;18>o;o++){var a=!t&&1==(1&e>>o);this.modules[o%3+this.moduleCount-8-3][Math.floor(o/3)]=a}},setupTypeInfo:function(t,e){for(var o=this.errorCorrectLevel<<3|e,a=h.getBCHTypeInfo(o),r=0;15>r;r++){var n=!t&&1==(1&a>>r);6>r?this.modules[r][8]=n:8>r?this.modules[r+1][8]=n:this.modules[this.moduleCount-15+r][8]=n}for(var r=0;15>r;r++){var n=!t&&1==(1&a>>r);8>r?this.modules[8][this.moduleCount-r-1]=n:9>r?this.modules[8][15-r-1+1]=n:this.modules[8][15-r-1]=n}this.modules[this.moduleCount-8][8]=!t},mapData:function(t,e){for(var o=-1,a=this.moduleCount-1,r=7,n=0,i=this.moduleCount-1;i>0;i-=2)for(6==i&&i--;;){for(var s=0;2>s;s++)if(null==this.modules[a][i-s]){var l=!1;n<t.length&&(l=1==(1&t[n]>>>r));var c=h.getMask(e,a,i-s);c&&(l=!l),this.modules[a][i-s]=l,-1==--r&&(n++,r=7)}if(0>(a+=o)||this.moduleCount<=a){a-=o,o=-o;break}}}},e.PAD0=236,e.PAD1=17,e.createData=function(t,o,a){for(var i=r.getRSBlocks(t,o),s=new n,l=0;l<a.length;l++){var c=a[l];s.put(c.mode,4),s.put(c.getLength(),h.getLengthInBits(c.mode,t)),c.write(s)}for(var u=0,l=0;l<i.length;l++)u+=i[l].dataCount;if(s.getLengthInBits()>8*u)throw new Error("code length overflow. ("+s.getLengthInBits()+">"+8*u+")");for(s.getLengthInBits()+4<=8*u&&s.put(0,4);0!=s.getLengthInBits()%8;)s.putBit(!1);for(;!(s.getLengthInBits()>=8*u)&&(s.put(e.PAD0,8),!(s.getLengthInBits()>=8*u));)s.put(e.PAD1,8);return e.createBytes(s,i)},e.createBytes=function(t,e){for(var o=0,r=0,n=0,i=new Array(e.length),s=new Array(e.length),l=0;l<e.length;l++){var c=e[l].dataCount,u=e[l].totalCount-c;r=Math.max(r,c),n=Math.max(n,u),i[l]=new Array(c);for(var d=0;d<i[l].length;d++)i[l][d]=255&t.buffer[d+o];o+=c;var f=h.getErrorCorrectPolynomial(u),p=new a(i[l],f.getLength()-1),m=p.mod(f);s[l]=new Array(f.getLength()-1);for(var d=0;d<s[l].length;d++){var v=d+m.getLength()-s[l].length;s[l][d]=v>=0?m.get(v):0}}for(var g=0,d=0;d<e.length;d++)g+=e[d].totalCount;for(var _=new Array(g),b=0,d=0;r>d;d++)for(var l=0;l<e.length;l++)d<i[l].length&&(_[b++]=i[l][d]);for(var d=0;n>d;d++)for(var l=0;l<e.length;l++)d<s[l].length&&(_[b++]=s[l][d]);return _};for(var c={MODE_NUMBER:1,MODE_ALPHA_NUM:2,MODE_8BIT_BYTE:4,MODE_KANJI:8},u={L:1,M:0,Q:3,H:2},d={PATTERN000:0,PATTERN001:1,PATTERN010:2,PATTERN011:3,PATTERN100:4,PATTERN101:5,PATTERN110:6,PATTERN111:7},h={PATTERN_POSITION_TABLE:[[],[6,18],[6,22],[6,26],[6,30],[6,34],[6,22,38],[6,24,42],[6,26,46],[6,28,50],[6,30,54],[6,32,58],[6,34,62],[6,26,46,66],[6,26,48,70],[6,26,50,74],[6,30,54,78],[6,30,56,82],[6,30,58,86],[6,34,62,90],[6,28,50,72,94],[6,26,50,74,98],[6,30,54,78,102],[6,28,54,80,106],[6,32,58,84,110],[6,30,58,86,114],[6,34,62,90,118],[6,26,50,74,98,122],[6,30,54,78,102,126],[6,26,52,78,104,130],[6,30,56,82,108,134],[6,34,60,86,112,138],[6,30,58,86,114,142],[6,34,62,90,118,146],[6,30,54,78,102,126,150],[6,24,50,76,102,128,154],[6,28,54,80,106,132,158],[6,32,58,84,110,136,162],[6,26,54,82,110,138,166],[6,30,58,86,114,142,170]],G15:1335,G18:7973,G15_MASK:21522,getBCHTypeInfo:function(t){for(var e=t<<10;h.getBCHDigit(e)-h.getBCHDigit(h.G15)>=0;)e^=h.G15<<h.getBCHDigit(e)-h.getBCHDigit(h.G15);return(t<<10|e)^h.G15_MASK},getBCHTypeNumber:function(t){for(var e=t<<12;h.getBCHDigit(e)-h.getBCHDigit(h.G18)>=0;)e^=h.G18<<h.getBCHDigit(e)-h.getBCHDigit(h.G18);return t<<12|e},getBCHDigit:function(t){for(var e=0;0!=t;)e++,t>>>=1;return e},getPatternPosition:function(t){return h.PATTERN_POSITION_TABLE[t-1]},getMask:function(t,e,o){switch(t){case d.PATTERN000:return 0==(e+o)%2;case d.PATTERN001:return 0==e%2;case d.PATTERN010:return 0==o%3;case d.PATTERN011:return 0==(e+o)%3;case d.PATTERN100:return 0==(Math.floor(e/2)+Math.floor(o/3))%2;case d.PATTERN101:return 0==e*o%2+e*o%3;case d.PATTERN110:return 0==(e*o%2+e*o%3)%2;case d.PATTERN111:return 0==(e*o%3+(e+o)%2)%2;default:throw new Error("bad maskPattern:"+t)}},getErrorCorrectPolynomial:function(t){for(var e=new a([1],0),o=0;t>o;o++)e=e.multiply(new a([1,f.gexp(o)],0));return e},getLengthInBits:function(t,e){if(e>=1&&10>e)switch(t){case c.MODE_NUMBER:return 10;case c.MODE_ALPHA_NUM:return 9;case c.MODE_8BIT_BYTE:case c.MODE_KANJI:return 8;default:throw new Error("mode:"+t)}else if(27>e)switch(t){case c.MODE_NUMBER:return 12;case c.MODE_ALPHA_NUM:return 11;case c.MODE_8BIT_BYTE:return 16;case c.MODE_KANJI:return 10;default:throw new Error("mode:"+t)}else{if(!(41>e))throw new Error("type:"+e);switch(t){case c.MODE_NUMBER:return 14;case c.MODE_ALPHA_NUM:return 13;case c.MODE_8BIT_BYTE:return 16;case c.MODE_KANJI:return 12;default:throw new Error("mode:"+t)}}},getLostPoint:function(t){for(var e=t.getModuleCount(),o=0,a=0;e>a;a++)for(var r=0;e>r;r++){for(var n=0,i=t.isDark(a,r),s=-1;1>=s;s++)if(!(0>a+s||a+s>=e))for(var l=-1;1>=l;l++)0>r+l||r+l>=e||(0!=s||0!=l)&&i==t.isDark(a+s,r+l)&&n++;n>5&&(o+=3+n-5)}for(var a=0;e-1>a;a++)for(var r=0;e-1>r;r++){var c=0;t.isDark(a,r)&&c++,t.isDark(a+1,r)&&c++,t.isDark(a,r+1)&&c++,t.isDark(a+1,r+1)&&c++,(0==c||4==c)&&(o+=3)}for(var a=0;e>a;a++)for(var r=0;e-6>r;r++)t.isDark(a,r)&&!t.isDark(a,r+1)&&t.isDark(a,r+2)&&t.isDark(a,r+3)&&t.isDark(a,r+4)&&!t.isDark(a,r+5)&&t.isDark(a,r+6)&&(o+=40);for(var r=0;e>r;r++)for(var a=0;e-6>a;a++)t.isDark(a,r)&&!t.isDark(a+1,r)&&t.isDark(a+2,r)&&t.isDark(a+3,r)&&t.isDark(a+4,r)&&!t.isDark(a+5,r)&&t.isDark(a+6,r)&&(o+=40);for(var u=0,r=0;e>r;r++)for(var a=0;e>a;a++)t.isDark(a,r)&&u++;return o+=Math.abs(100*u/e/e-50)/5*10}},f={glog:function(t){if(1>t)throw new Error("glog("+t+")");return f.LOG_TABLE[t]},gexp:function(t){for(;0>t;)t+=255;for(;t>=256;)t-=255;return f.EXP_TABLE[t]},EXP_TABLE:new Array(256),LOG_TABLE:new Array(256)},p=0;8>p;p++)f.EXP_TABLE[p]=1<<p;for(var p=8;256>p;p++)f.EXP_TABLE[p]=f.EXP_TABLE[p-4]^f.EXP_TABLE[p-5]^f.EXP_TABLE[p-6]^f.EXP_TABLE[p-8];for(var p=0;255>p;p++)f.LOG_TABLE[f.EXP_TABLE[p]]=p;a.prototype={get:function(t){return this.num[t]},getLength:function(){return this.num.length},multiply:function(t){for(var e=new Array(this.getLength()+t.getLength()-1),o=0;o<this.getLength();o++)for(var r=0;r<t.getLength();r++)e[o+r]^=f.gexp(f.glog(this.get(o))+f.glog(t.get(r)));return new a(e,0)},mod:function(t){if(this.getLength()-t.getLength()<0)return this;for(var e=f.glog(this.get(0))-f.glog(t.get(0)),o=new Array(this.getLength()),r=0;r<this.getLength();r++)o[r]=this.get(r);for(var r=0;r<t.getLength();r++)o[r]^=f.gexp(f.glog(t.get(r))+e);return new a(o,0).mod(t)}},r.RS_BLOCK_TABLE=[[1,26,19],[1,26,16],[1,26,13],[1,26,9],[1,44,34],[1,44,28],[1,44,22],[1,44,16],[1,70,55],[1,70,44],[2,35,17],[2,35,13],[1,100,80],[2,50,32],[2,50,24],[4,25,9],[1,134,108],[2,67,43],[2,33,15,2,34,16],[2,33,11,2,34,12],[2,86,68],[4,43,27],[4,43,19],[4,43,15],[2,98,78],[4,49,31],[2,32,14,4,33,15],[4,39,13,1,40,14],[2,121,97],[2,60,38,2,61,39],[4,40,18,2,41,19],[4,40,14,2,41,15],[2,146,116],[3,58,36,2,59,37],[4,36,16,4,37,17],[4,36,12,4,37,13],[2,86,68,2,87,69],[4,69,43,1,70,44],[6,43,19,2,44,20],[6,43,15,2,44,16],[4,101,81],[1,80,50,4,81,51],[4,50,22,4,51,23],[3,36,12,8,37,13],[2,116,92,2,117,93],[6,58,36,2,59,37],[4,46,20,6,47,21],[7,42,14,4,43,15],[4,133,107],[8,59,37,1,60,38],[8,44,20,4,45,21],[12,33,11,4,34,12],[3,145,115,1,146,116],[4,64,40,5,65,41],[11,36,16,5,37,17],[11,36,12,5,37,13],[5,109,87,1,110,88],[5,65,41,5,66,42],[5,54,24,7,55,25],[11,36,12],[5,122,98,1,123,99],[7,73,45,3,74,46],[15,43,19,2,44,20],[3,45,15,13,46,16],[1,135,107,5,136,108],[10,74,46,1,75,47],[1,50,22,15,51,23],[2,42,14,17,43,15],[5,150,120,1,151,121],[9,69,43,4,70,44],[17,50,22,1,51,23],[2,42,14,19,43,15],[3,141,113,4,142,114],[3,70,44,11,71,45],[17,47,21,4,48,22],[9,39,13,16,40,14],[3,135,107,5,136,108],[3,67,41,13,68,42],[15,54,24,5,55,25],[15,43,15,10,44,16],[4,144,116,4,145,117],[17,68,42],[17,50,22,6,51,23],[19,46,16,6,47,17],[2,139,111,7,140,112],[17,74,46],[7,54,24,16,55,25],[34,37,13],[4,151,121,5,152,122],[4,75,47,14,76,48],[11,54,24,14,55,25],[16,45,15,14,46,16],[6,147,117,4,148,118],[6,73,45,14,74,46],[11,54,24,16,55,25],[30,46,16,2,47,17],[8,132,106,4,133,107],[8,75,47,13,76,48],[7,54,24,22,55,25],[22,45,15,13,46,16],[10,142,114,2,143,115],[19,74,46,4,75,47],[28,50,22,6,51,23],[33,46,16,4,47,17],[8,152,122,4,153,123],[22,73,45,3,74,46],[8,53,23,26,54,24],[12,45,15,28,46,16],[3,147,117,10,148,118],[3,73,45,23,74,46],[4,54,24,31,55,25],[11,45,15,31,46,16],[7,146,116,7,147,117],[21,73,45,7,74,46],[1,53,23,37,54,24],[19,45,15,26,46,16],[5,145,115,10,146,116],[19,75,47,10,76,48],[15,54,24,25,55,25],[23,45,15,25,46,16],[13,145,115,3,146,116],[2,74,46,29,75,47],[42,54,24,1,55,25],[23,45,15,28,46,16],[17,145,115],[10,74,46,23,75,47],[10,54,24,35,55,25],[19,45,15,35,46,16],[17,145,115,1,146,116],[14,74,46,21,75,47],[29,54,24,19,55,25],[11,45,15,46,46,16],[13,145,115,6,146,116],[14,74,46,23,75,47],[44,54,24,7,55,25],[59,46,16,1,47,17],[12,151,121,7,152,122],[12,75,47,26,76,48],[39,54,24,14,55,25],[22,45,15,41,46,16],[6,151,121,14,152,122],[6,75,47,34,76,48],[46,54,24,10,55,25],[2,45,15,64,46,16],[17,152,122,4,153,123],[29,74,46,14,75,47],[49,54,24,10,55,25],[24,45,15,46,46,16],[4,152,122,18,153,123],[13,74,46,32,75,47],[48,54,24,14,55,25],[42,45,15,32,46,16],[20,147,117,4,148,118],[40,75,47,7,76,48],[43,54,24,22,55,25],[10,45,15,67,46,16],[19,148,118,6,149,119],[18,75,47,31,76,48],[34,54,24,34,55,25],[20,45,15,61,46,16]],r.getRSBlocks=function(t,e){var o=r.getRsBlockTable(t,e);if(void 0==o)throw new Error("bad rs block @ typeNumber:"+t+"/errorCorrectLevel:"+e);for(var a=o.length/3,n=[],i=0;a>i;i++)for(var s=o[3*i+0],l=o[3*i+1],c=o[3*i+2],u=0;s>u;u++)n.push(new r(l,c));return n},r.getRsBlockTable=function(t,e){switch(e){case u.L:return r.RS_BLOCK_TABLE[4*(t-1)+0];case u.M:return r.RS_BLOCK_TABLE[4*(t-1)+1];case u.Q:return r.RS_BLOCK_TABLE[4*(t-1)+2];case u.H:return r.RS_BLOCK_TABLE[4*(t-1)+3];default:return}},n.prototype={get:function(t){var e=Math.floor(t/8);return 1==(1&this.buffer[e]>>>7-t%8)},put:function(t,e){for(var o=0;e>o;o++)this.putBit(1==(1&t>>>e-o-1))},getLengthInBits:function(){return this.length},putBit:function(t){var e=Math.floor(this.length/8);this.buffer.length<=e&&this.buffer.push(0),t&&(this.buffer[e]|=128>>>this.length%8),this.length++}};var m=[[17,14,11,7],[32,26,20,14],[53,42,32,24],[78,62,46,34],[106,84,60,44],[134,106,74,58],[154,122,86,64],[192,152,108,84],[230,180,130,98],[271,213,151,119],[321,251,177,137],[367,287,203,155],[425,331,241,177],[458,362,258,194],[520,412,292,220],[586,450,322,250],[644,504,364,280],[718,560,394,310],[792,624,442,338],[858,666,482,382],[929,711,509,403],[1003,779,565,439],[1091,857,611,461],[1171,911,661,511],[1273,997,715,535],[1367,1059,751,593],[1465,1125,805,625],[1528,1190,868,658],[1628,1264,908,698],[1732,1370,982,742],[1840,1452,1030,790],[1952,1538,1112,842],[2068,1628,1168,898],[2188,1722,1228,958],[2303,1809,1283,983],[2431,1911,1351,1051],[2563,1989,1423,1093],[2699,2099,1499,1139],[2809,2213,1579,1219],[2953,2331,1663,1273]],v=function(){var t=function(t,e){this._el=t,this._htOption=e};return t.prototype.draw=function(t){function e(t,e){var o=document.createElementNS("http://www.w3.org/2000/svg",t);for(var a in e)e.hasOwnProperty(a)&&o.setAttribute(a,e[a]);return o}var o=this._htOption,a=this._el,r=t.getModuleCount();Math.floor(o.width/r),Math.floor(o.height/r),this.clear();var n=e("svg",{viewBox:"0 0 "+String(r)+" "+String(r),width:"100%",height:"100%",fill:o.colorLight});n.setAttributeNS("http://www.w3.org/2000/xmlns/","xmlns:xlink","http://www.w3.org/1999/xlink"),a.appendChild(n),n.appendChild(e("rect",{fill:o.colorDark,width:"1",height:"1",id:"template"}));for(var i=0;r>i;i++)for(var s=0;r>s;s++)if(t.isDark(i,s)){var l=e("use",{x:String(i),y:String(s)});l.setAttributeNS("http://www.w3.org/1999/xlink","href","#template"),n.appendChild(l)}},t.prototype.clear=function(){for(;this._el.hasChildNodes();)this._el.removeChild(this._el.lastChild)},t}(),g="svg"===document.documentElement.tagName.toLowerCase(),_=g?v:function(){return"undefined"!=typeof CanvasRenderingContext2D}()?function(){function t(){this._elImage.src=this._elCanvas.toDataURL("image/png"),this._elImage.style.display="block",this._elCanvas.style.display="none"}function e(t,e){var o=this;if(o._fFail=e,o._fSuccess=t,null===o._bSupportDataURI){var a=document.createElement("img"),r=function(){o._bSupportDataURI=!1,o._fFail&&_fFail.call(o)},n=function(){o._bSupportDataURI=!0,o._fSuccess&&o._fSuccess.call(o)};return a.onabort=r,a.onerror=r,a.onload=n,void(a.src="data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==")}!0===o._bSupportDataURI&&o._fSuccess?o._fSuccess.call(o):!1===o._bSupportDataURI&&o._fFail&&o._fFail.call(o)}if(this._android&&this._android<=2.1){var o=1/window.devicePixelRatio,a=CanvasRenderingContext2D.prototype.drawImage;CanvasRenderingContext2D.prototype.drawImage=function(t,e,r,n,i,s,l,c){if("nodeName"in t&&/img/i.test(t.nodeName))for(var u=arguments.length-1;u>=1;u--)arguments[u]=arguments[u]*o;else void 0===c&&(arguments[1]*=o,arguments[2]*=o,arguments[3]*=o,arguments[4]*=o);a.apply(this,arguments)}}var r=function(t,e){this._bIsPainted=!1,this._android=i(),this._htOption=e,this._elCanvas=document.createElement("canvas"),this._elCanvas.width=e.width,this._elCanvas.height=e.height,t.appendChild(this._elCanvas),this._el=t,this._oContext=this._elCanvas.getContext("2d"),this._bIsPainted=!1,this._elImage=document.createElement("img"),this._elImage.style.display="none",this._el.appendChild(this._elImage),this._bSupportDataURI=null};return r.prototype.draw=function(t){var e=this._elImage,o=this._oContext,a=this._htOption,r=t.getModuleCount(),n=a.width/r,i=a.height/r,s=Math.round(n),l=Math.round(i);e.style.display="none",this.clear();for(var c=0;r>c;c++)for(var u=0;r>u;u++){var d=t.isDark(c,u),h=u*n,f=c*i;o.strokeStyle=d?a.colorDark:a.colorLight,o.lineWidth=1,o.fillStyle=d?a.colorDark:a.colorLight,o.fillRect(h,f,n,i),o.strokeRect(Math.floor(h)+.5,Math.floor(f)+.5,s,l),o.strokeRect(Math.ceil(h)-.5,Math.ceil(f)-.5,s,l)}this._bIsPainted=!0},r.prototype.makeImage=function(){this._bIsPainted&&e.call(this,t)},r.prototype.isPainted=function(){return this._bIsPainted},r.prototype.clear=function(){this._oContext.clearRect(0,0,this._elCanvas.width,this._elCanvas.height),this._bIsPainted=!1},r.prototype.round=function(t){return t?Math.floor(1e3*t)/1e3:t},r}():function(){var t=function(t,e){this._el=t,this._htOption=e};return t.prototype.draw=function(t){for(var e=this._htOption,o=this._el,a=t.getModuleCount(),r=Math.floor(e.width/a),n=Math.floor(e.height/a),i=['<table style="border:0;border-collapse:collapse;">'],s=0;a>s;s++){i.push("<tr>");for(var l=0;a>l;l++)i.push('<td style="border:0;border-collapse:collapse;padding:0;margin:0;width:'+r+"px;height:"+n+"px;background-color:"+(t.isDark(s,l)?e.colorDark:e.colorLight)+';"></td>');i.push("</tr>")}i.push("</table>"),o.innerHTML=i.join("");var c=o.childNodes[0],u=(e.width-c.offsetWidth)/2,d=(e.height-c.offsetHeight)/2;u>0&&d>0&&(c.style.margin=d+"px "+u+"px")},t.prototype.clear=function(){this._el.innerHTML=""},t}();o=function(t,e){if(this._htOption={width:256,height:256,typeNumber:4,colorDark:"#000000",colorLight:"#ffffff",correctLevel:u.H},"string"==typeof e&&(e={text:e}),e)for(var o in e)this._htOption[o]=e[o];"string"==typeof t&&(t=document.getElementById(t)),this._android=i(),this._el=t,this._oQRCode=null,this._oDrawing=new _(this._el,this._htOption),this._htOption.text&&this.makeCode(this._htOption.text)},o.prototype.makeCode=function(t){this._oQRCode=new e(s(t,this._htOption.correctLevel),this._htOption.correctLevel),this._oQRCode.addData(t),this._oQRCode.make(),this._el.title=t,this._oDrawing.draw(this._oQRCode),this.makeImage()},o.prototype.makeImage=function(){"function"==typeof this._oDrawing.makeImage&&(!this._android||this._android>=3)&&this._oDrawing.makeImage()},o.prototype.clear=function(){this._oDrawing.clear()},o.CorrectLevel=u}(),t.exports=o},47:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={data:function(){return{}},methods:{},mounted:function(){},props:["msg"]}},66:function(t,e){},67:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",[o("i",{staticClass:"el-icon-circle-check"}),t._v(" "),o("p",[t._v(t._s(t.msg))]),t._v(" "),o("el-button",{attrs:{type:"primary"},on:{click:function(e){t.$router.go(0)}}},[t._v("继续添加")]),t._v(" "),o("el-button",{on:{click:function(e){t.$router.go(-1)}}},[t._v("返回")])],1)},staticRenderFns:[]}},68:function(t,e,o){o(66);var a=o(4)(o(47),o(67),null,null);t.exports=a.exports}},[161]);