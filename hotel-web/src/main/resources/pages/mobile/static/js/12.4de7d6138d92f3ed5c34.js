webpackJsonp([12],{BRLH:function(e,i,t){"use strict";var s=t("fQyU");t("d9AR");i.a={data:function(){return{hotelId:sessionStorage.getItem("hotelId"),dialog1:!1,orderId:this.$route.params.orderId,orderinfo:"",falg:!0}},mounted:function(){var e=this;s.a.paymentOrder({Suffix:sessionStorage.getItem("hotelId")+"/order/"+this.orderId}).then(function(i){0==i.code&&(e.orderinfo=i.data,e.orderinfo.activityDetailVo&&2==e.orderinfo.activityDetailVo.activityType||(e.orderinfo.roomInTime=e.orderinfo.roomInTime.slice(0,10),e.orderinfo.roomOutTime=e.orderinfo.roomOutTime.slice(0,10)),e.orderinfo.roomCost=Number(e.orderinfo.realPrice)+Number(e.orderinfo.integralDiscount)+Number(e.orderinfo.couponsDiscount)-Number(e.orderinfo.deposit*e.orderinfo.number))})},methods:{mapfn:function(){location.href="http://apis.map.qq.com/tools/routeplan/eword="+this.orderinfo.address+"&epointx="+this.orderinfo.longitude+"&epointy="+this.orderinfo.latitude+"?referer=mapqq&key=GQKBZ-BKZW6-ZCVSQ-MD5GX-MYNBQ-LSBPK"},payFn:function(){var e=this;if(this.falg){this.falg=!1;var i=this;s.a.getconfig().then(function(t){0==t.code&&(location.href=t.data.hostUrl+"mobile/78CDF1/room/"+sessionStorage.getItem("hotelId")+"/pay/"+e.orderId,setTimeout(function(){i.falg=!1},100))})}},cancelOrderfn:function(){var e=this;s.a.cancelOrder({Suffix:sessionStorage.getItem("hotelId")+"/cancel/"+this.orderId,fn:function(i){e.Toast({message:"取消成功",duration:2e3});var t=e;setTimeout(function(){t.$router.go(-1)},1e3)}})}}}},H93D:function(e,i,t){var s=t("zxRY");"string"==typeof s&&(s=[[e.i,s,""]]),s.locals&&(e.exports=s.locals);t("8bSs")("42dfd69c",s,!0)},Wl27:function(e,i,t){"use strict";function s(e){t("H93D")}Object.defineProperty(i,"__esModule",{value:!0});var a=t("BRLH"),l=t("aKZi"),r=t("46Yf"),n=s,c=r(a.a,l.a,!1,n,"data-v-06030a08",null);i.default=c.exports},aKZi:function(e,i,t){"use strict";var s=function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div",{staticClass:"Payment"},[t("main",[t("div",{staticClass:"weui-cells weui-cells_form"},[t("div",{staticClass:"weui-cell"},[e._m(0),e._v(" "),t("div",{staticClass:"weui-cell__bd"},[e._v("\n                    "+e._s(e.orderinfo.customerName)+"\n                ")])]),e._v(" "),t("div",{staticClass:"weui-cell"},[e._m(1),e._v(" "),t("div",{staticClass:"weui-cell__bd"},[e._v("\n                    "+e._s(e.orderinfo.customerPhone)+"\n                ")])]),e._v(" "),t("div",{staticClass:"weui-cell"},[e._m(2),e._v(" "),t("div",{staticClass:"weui-cell__bd"},[e._v("\n                    "+e._s(e.orderinfo.roomInTime)+"\n                ")])]),e._v(" "),t("div",{staticClass:"weui-cell"},[e._m(3),e._v(" "),t("div",{staticClass:"weui-cell__bd"},[e._v("\n                    "+e._s(e.orderinfo.roomOutTime)+"\n                ")])]),e._v(" "),t("div",{staticClass:"weui-cell"},[e._m(4),e._v(" "),t("div",{staticClass:"weui-cell__bd"},[e._v("\n                    "+e._s(e.orderinfo.categoryName)+"\n                ")])]),e._v(" "),t("div",{staticClass:"weui-cell"},[e._m(5),e._v(" "),t("div",{staticClass:"weui-cell__bd"},[e._v("\n                    "+e._s(e.orderinfo.number)+"间\n                ")])]),e._v(" "),t("div",{staticClass:"weui-cell"},[e._m(6),e._v(" "),t("div",{staticClass:"weui-cell__bd"},[e._v("\n                    "+e._s(e._f("PaymentMethod")(e.orderinfo.payType))+"\n                ")])]),e._v(" "),t("div",{staticClass:"weui-cell"},[e._m(7),e._v(" "),t("div",{staticClass:"weui-cell__bd"},[e.orderinfo.remark?[e._v(e._s(e.orderinfo.remark))]:[e._v("无")]],2)]),e._v(" "),t("div",{staticClass:"weui-cell"},[t("div",{staticClass:"weui-cell__bd"},[e._v("\n                    "+e._s(e.orderinfo.name)+"\n                ")])]),e._v(" "),t("div",{staticClass:"weui-cells information"},[t("a",{staticClass:"weui-cell weui-cell_access",attrs:{href:"tel:"+e.orderinfo.phone}},[t("div",{staticClass:"weui-cell__hd"},[t("svg",{staticClass:"icon",attrs:{"aria-hidden":"true"}},[t("use",{attrs:{"xlink:href":"#icon-dianhuahaoma"}})])]),e._v(" "),t("div",{staticClass:"weui-cell__bd"},[t("p",[e._v(e._s(e.orderinfo.phone))])]),e._v(" "),t("div",{staticClass:"weui-cell__ft"})]),e._v(" "),t("a",{staticClass:"weui-cell weui-cell_access",attrs:{href:"javascript:;"},on:{click:e.mapfn}},[t("div",{staticClass:"weui-cell__hd"},[t("svg",{staticClass:"icon",attrs:{"aria-hidden":"true"}},[t("use",{attrs:{"xlink:href":"#icon-weizhi"}})])]),e._v(" "),t("div",{staticClass:"weui-cell__bd"},[t("p"),t("p",[e._v(e._s(e.orderinfo.address))]),e._v(" "),t("p")]),e._v(" "),t("div",{staticClass:"weui-cell__ft"})])])])]),e._v(" "),t("div",{staticClass:"totalSum"},[t("p",[e._v("订单总额：\n            "),t("span",{staticClass:"Price"},[e._v("￥"+e._s(e._f("money")(e.orderinfo.realPrice)))]),e._v(" "),t("a",{staticClass:"minutia",on:{click:function(i){e.dialog1=!0}}},[e._v("明细")])]),e._v(" "),t("div",{staticClass:"btn"},[t("a",{staticClass:"weui-btn weui-btn_primary",attrs:{href:"javascript:;"},on:{click:e.payFn}},[e._v("立即支付")]),e._v(" "),t("a",{staticClass:"weui-btn weui-btn_primary",attrs:{href:"javascript:;"},on:{click:e.cancelOrderfn}},[e._v("取消订单")])])]),e._v(" "),e.dialog1?t("div",{staticClass:"js_dialog"},[t("div",{staticClass:"weui-mask",on:{click:function(i){e.dialog1=!1}}}),e._v(" "),t("div",{staticClass:"weui-dialog Paymentdetails"},[t("i",{staticClass:"weui-icon-cancel",on:{click:function(i){e.dialog1=!1}}}),e._v(" "),t("div",{staticClass:"weui-dialog__hd"},[e._v("\n                费用明细\n            ")]),e._v(" "),t("div",{staticClass:"weui-dialog__bd"},[t("h4",{staticClass:"title mt15"},[e._v("费用明细")]),e._v(" "),t("div",{staticClass:"weui-flex"},[t("div",{staticClass:"weui-flex__item"},[t("div",{staticClass:"placeholder"},[e._v("\n                            房费*"+e._s(e.orderinfo.number)+"\n                        ")])]),e._v(" "),t("div",{staticClass:"weui-flex__item right"},[t("div",{staticClass:"placeholder"},[e._v("\n                            +￥"+e._s(e._f("money")(e.orderinfo.roomCost))+"\n                        ")])])]),e._v(" "),t("div",{staticClass:"weui-flex"},[t("div",{staticClass:"weui-flex__item"},[t("div",{staticClass:"placeholder"},[e._v("\n                            押金*"+e._s(e.orderinfo.number)+"\n                        ")])]),e._v(" "),t("div",{staticClass:"weui-flex__item right"},[t("div",{staticClass:"placeholder"},[e._v("\n                            +￥"+e._s(e._f("money")(e.orderinfo.deposit*e.orderinfo.number))+"\n                        ")])])]),e._v(" "),e.orderinfo.couponsDiscount?t("div",{staticClass:"weui-flex"},[e._m(8),e._v(" "),t("div",{staticClass:"weui-flex__item right"},[t("div",{staticClass:"placeholder"},[e._v("\n                            -￥"+e._s(e._f("money")(e.orderinfo.couponsDiscount))+"\n                        ")])])]):e._e(),e._v(" "),e.orderinfo.integralDiscount?t("div",{staticClass:"weui-flex"},[e._m(9),e._v(" "),t("div",{staticClass:"weui-flex__item right"},[t("div",{staticClass:"placeholder"},[e._v("\n                            -￥"+e._s(e._f("money")(e.orderinfo.integralDiscount))+"\n                        ")])])]):e._e(),e._v(" "),t("div",{staticClass:"weui-flex"},[e._m(10),e._v(" "),t("div",{staticClass:"weui-flex__item right"},[t("div",{staticClass:"placeholder"},[e._v("\n                            +￥"+e._s(e._f("money")(e.orderinfo.realPrice))+"\n                        ")])])])])])]):e._e()])},a=[function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div",{staticClass:"weui-cell__hd"},[t("label",{staticClass:"weui-label"},[e._v("预约人")])])},function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div",{staticClass:"weui-cell__hd"},[t("label",{staticClass:"weui-label"},[e._v("预约电话")])])},function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div",{staticClass:"weui-cell__hd"},[t("label",{staticClass:"weui-label"},[e._v("入住时间")])])},function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div",{staticClass:"weui-cell__hd"},[t("label",{staticClass:"weui-label"},[e._v("离店时间")])])},function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div",{staticClass:"weui-cell__hd"},[t("label",{staticClass:"weui-label"},[e._v("预订房型")])])},function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div",{staticClass:"weui-cell__hd"},[t("label",{staticClass:"weui-label"},[e._v("预订数量")])])},function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div",{staticClass:"weui-cell__hd"},[t("label",{staticClass:"weui-label"},[e._v("支付方式")])])},function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div",{staticClass:"weui-cell__hd"},[t("label",{staticClass:"weui-label"},[e._v("备注")])])},function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div",{staticClass:"weui-flex__item"},[t("div",{staticClass:"placeholder"},[e._v("\n                            优惠券\n                        ")])])},function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div",{staticClass:"weui-flex__item"},[t("div",{staticClass:"placeholder"},[e._v("\n                            积分\n                        ")])])},function(){var e=this,i=e.$createElement,t=e._self._c||i;return t("div",{staticClass:"weui-flex__item"},[t("div",{staticClass:"placeholder"},[e._v("\n                            合计\n                        ")])])}],l={render:s,staticRenderFns:a};i.a=l},zxRY:function(e,i,t){i=e.exports=t("BkJT")(!1),i.push([e.i,".Payment[data-v-06030a08]{display:-webkit-box;display:-ms-flexbox;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;-ms-flex-flow:column;flex-flow:column;min-height:100vh}main[data-v-06030a08]{-webkit-box-flex:1;-ms-flex:1;flex:1;background:#f4f4f4}",""])}});