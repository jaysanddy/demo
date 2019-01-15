
      // 显示说明输入框
      // function addDes(desLength){
      //   $(".add-des-layer,.add-des").show();
      //   $("#desLength").val(desLength);
      // }
      // 点击任意隐藏输入框
      // $(".add-des-layer").click(function(){
      //   $(".add-des,.add-des-layer").hide();
      // })
      // 添加说明按钮
      // $(".add-dex-btn").click(function(){
      //   $(".add-des,.add-des-layer").hide();
      //   var desLength=$("#desLength").val();
      //   var addDesVal=$("#addDesIpt").val();
      //   var code='';
      //   code+='<li style="padding-right:30px;color: #6BBB88;border-color: #6BBB88;width:100%">'+addDesVal+'<a class="del">-</a></li>';
      //   if(desLength=="1"){
      //     $("#addLengthOne").append(code);
      //   }else if(desLength=="2"){
      //     $("#addLengthTwo").append(code);
      //   }else{
      //     $("#addLengthThree").append(code);
      //   }
      //   $(".del").click(function(){
      //     var _this=$(this);
      //     layer.open({
      //       content: '确定要删除吗？',
      //       btn: ['删除', '取消'],
      //       skin: 'footer',
      //       yes: function(index) {
      //         _this.parent().remove();
      //         layer.open({
      //           content: '删除成功！',
      //           skin: 'msg',
      //           time:1
      //         });
      //       }
      //     });
      //   })
      // })
      $(".add-sm").click(function(){
        $(this).prev().show();
        $(this).hide();
      })
      var imgNum=0;
      $("#imgUpload").change(function(){
        var pic=this.files[0];
        preview_picture(pic);
      })
      function preview_picture(pic){
        imgNum++;
        var r=new FileReader();
        r.readAsDataURL(pic);
        r.onload=function(e){
          var code="";
          code+='<li><img id='+imgNum+' src='+this.result+'><a class="remove">-</a></li>';
          $(".add-img").append(code);
          // 图片删除
          $(".remove").click(function() {
            var _this=$(this);
            layer.open({
              content: '确定要删除吗？',
              btn: ['删除', '取消'],
              skin: 'footer',
              yes: function(index) {
                _this.parent().remove();
                layer.open({
                  content: '删除成功！',
                  skin: 'msg',
                  time:1
                });
              }
            });
          })
        }
      }
      
      // 提交表单
      $(".btn-primary").click(function(){

        var detailDetail={
            ids:'',
            name:'',
            score:''
          }
          var detail=[];

          $("#addLengthOne li.active").each(function(){
            detailDetail.ids=$(this).find("input.ids").val();
            detailDetail.name=$(this).find("b.name").text();
            detailDetail.score=$(this).find("input.scoreIpt").val();
            detail.push(detailDetail);
          });
          console.log(detail);

          
        var title=$("#title").val();
        var name=$("#selectBox").html();
        var num=$("#num").val();
        var adr=$("#adr").val();
        var des=$("#des").val();
        var riverPd=$(".river-pd li.active").text();
        var score=$("#score").text();
        var des1=$("#dex1").val();
        var des2=$("#dex2").val();
        var des3=$("#dex3").val();
        var des4=$("#dex4").val();
        if(title==""){
          layer.open({
            content: '请输入标题',
            skin: 'footer',
            time:1
          });
          return false;
        }else if(name=="<span>请选择河道名称</span>"){
          layer.open({
            content: '请选择河道',
            skin: 'footer',
            time:1
          });
          return false;
        }else if(adr==""){
          layer.open({
            content: '请输入所属乡镇',
            skin: 'footer',
            time:1
          });
          return false;
        }else if(des==""){
          layer.open({
            content: '请输入描述',
            skin: 'footer',
            time:1
          });
          return false;
        }else{
          console.log(title);
          console.log(name);
          console.log(num);
          console.log(adr);
          console.log(des);
          console.log(riverPd);
          console.log(score);
          var isChange=0;
          if ($('.js-switch').is(':checked')){
              isChange=1;
          }
          console.log(isChange);
          console.log(des1);
          console.log(des2);
          console.log(des3);
          console.log(des4);
       
        var img=[];
        $(".add-img li").each(function(){
          console.log($(this).find("img").attr("src"));
        })
        console.log(img);
        var result=[{
            id: '1',
            des:'',
            name:['有废弃船只','有垃圾等漂浮物','有大量绿萍、水草、蓝藻、水葫芦等水生植物'],
            score:[1,2,5]
          },
          {
            id: '2',
            des:'',
             detail:[
                  {ids:1,name:'污染',score:1},
                  {ids:2,name:'污染',score:1},
                  {ids:3,name:'污染',score:1},
                  {ids:4,name:'污染',score:1}
        ]

            score:1
          },
          {
            id: '3',
            des:'',
            name:['沾河填埋','河道淤塞未疏浚','沾河养殖（养鱼、养家禽、种菱等）','行水障碍物','挡水圩堰和坝埂','占河违搭'],
            score:[1,2,5,6,5,4]
          },
          {
            id: '4',
            des:'',
            name:['河水发臭或变色','污水排入'],
            score:[0,2]
          }
          ];



          var score2=[],name2=[],ids2=[];
          $("#addLengthTwo li.active").each(function(){
            name2.push($(this).find("b.name").text());
            score2.push($(this).find("input.scoreIpt").val());
            ids2.push($(this).find("input.ids").val());
          });
          var score3=[],name3=[],ids3=[];
          $("#addLengthThree li.active").each(function(){
            name3.push($(this).find("b.name").text());
            score3.push($(this).find("input.scoreIpt").val());
            ids3.push($(this).find("input.ids").val());
          });
          var score4=[],name4=[],ids4=[];
          $("#addLengthFour li.active").each(function(){
            name4.push($(this).find("b.name").text());
            score4.push($(this).find("input.scoreIpt").val());
            ids4.push($(this).find("input.ids").val());
          });
          console.log(detailDetail);console.log(detail);
        }



      })
      
    // 分数监听
    function changeScore(){
        var numOne=0;var numTwo=0;var numThree=0;var numFour=0;
        $("#addLengthOne li.active").each(function(){
          numOne+=Number($(this).find("input.scoreIpt").val());
        });
        $("#addLengthTwo li.active").each(function(){
          numTwo+=Number($(this).find("input.scoreIpt").val());
        });
        $("#addLengthThree li.active").each(function(){
          numThree+=Number($(this).find("input.scoreIpt").val());
        });
        $("#addLengthFour li.active").each(function(){
          numFour+=Number($(this).find("input.scoreIpt").val());
        });
        var score=100-numOne-numTwo-numThree-numFour;
    
        // 大于80分绿色
        if(score>79){
          $("#score").css("color","#6BBB88");
        }else if(score>59&&score<80){
          // 60-80橙色
          $("#score").css("color","#ff9900");
        }else if(score<60){
          // 60以下红色
          $("#score").css("color","#f00");
        }
        $("#score").html(score+'分');
    }
      $(".river-pd li").click(function(){
        $(this).addClass("active");
        $(this).siblings().removeClass("active");
      })
      $(".toggle li").click(function(){
        $(this).toggleClass("active");
        $(this).find("input").focus();
        $(this).parent().children().first().removeClass("active");
        changeScore();
      })
      $(".toggle li:first-child").click(function(){
        $(this).parent().find("li").removeClass("active");
        $(this).addClass("active");
        changeScore();
      })
      // 河道名称选择
      var addressArr = [{
          id: '1',
          value: '长江',
          num:'00000001'
        },
        {
          id: '2',
          value: '黄河',
          num:'00000002'
        },
        {
          id: '3',
          value: '淮河',
          num:'00000003'
        }
      ]

      //下拉选择框
      var mobileSelect1 = new MobileSelect({
        trigger: '#selectBox',
        title: '请选择河道',
        wheels: [{
          data: addressArr
        }],
        position: [1, 1], //初始化定位 打开时默认选中的哪个 如果不填默认为0
        callback: function(indexArr, data) {
          var riverNum=data[0].num;
          $("#num").val(riverNum);
        }
      });
      // 下拉框
      $('dl dt').live('tap', function() {
        $(this).siblings('dd').toggle();
        $(this).children('i').toggleClass('icon-fold icon-unfold');
      });
      //
      var elem = document.querySelector('.js-switch');
      var switchery = new Switchery(elem,{ size: 'small' });  

    // 百度地图API功能
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(116.331398,39.897445);
    map.centerAndZoom(point,12);

    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function(r){
        if(this.getStatus() == BMAP_STATUS_SUCCESS){
            var mk = new BMap.Marker(r.point);
            map.addOverlay(mk);
            map.panTo(r.point);
            alert('您的位置：'+r.point.lng+','+r.point.lat);
        }
        else {
            alert('failed'+this.getStatus());
        }
    },{enableHighAccuracy: true})
    //关于状态码
    //BMAP_STATUS_SUCCESS    检索成功。对应数值“0”。
    //BMAP_STATUS_CITY_LIST    城市列表。对应数值“1”。
    //BMAP_STATUS_UNKNOWN_LOCATION    位置结果未知。对应数值“2”。
    //BMAP_STATUS_UNKNOWN_ROUTE    导航结果未知。对应数值“3”。
    //BMAP_STATUS_INVALID_KEY    非法密钥。对应数值“4”。
    //BMAP_STATUS_INVALID_REQUEST    非法请求。对应数值“5”。
    //BMAP_STATUS_PERMISSION_DENIED    没有权限。对应数值“6”。(自 1.1 新增)
    //BMAP_STATUS_SERVICE_UNAVAILABLE    服务不可用。对应数值“7”。(自 1.1 新增)
    //BMAP_STATUS_TIMEOUT    超时。对应数值“8”。(自 1.1 新增)
