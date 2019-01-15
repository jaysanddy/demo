
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
      function ImgToBase64(file, maxLen, callBack) {
               var img = new Image();
               var reader = new FileReader();//读取客户端上的文件
               reader.onload = function () {
                       var url = reader.result;//读取到的文件内容.这个属性只在读取操作完成之后才有效,并且数据的格式取决于读取操作是由哪个方法发起的.所以必须使用reader.onload，
                       img.src = url;//reader读取的文件内容是base64,利用这个url就能实现上传前预览图片
                   };
               img.onload = function () {
                       //生成比例
                       var width = img.width, height = img.height;
                       //计算缩放比例
                       var rate = 1;
                       if (width >= height) {
                               if (width > maxLen) {
                                       rate = maxLen / width;
                                   }
                          } else {
                               if (height > maxLen) {
                                       rate = maxLen / height;
                                   }
                           };
                      img.width = width * rate;
                      img.height = height * rate;
                       //生成canvas
                       var canvas = document.createElement("canvas");
                       var ctx = canvas.getContext("2d");
                       canvas.width = img.width;
                       canvas.height = img.height;
                       ctx.drawImage(img, 0, 0, img.width, img.height);
                       var base64 = canvas.toDataURL('image/jpeg', 0.9);
                       callBack(base64);
                   };
              reader.readAsDataURL(file);
          }
      getSecond();
      function getSecond(){
          var userId=$("#userId").val();
          $.post("/riverQuestion/getAllRiverQuestion",{},
              function(data){
                  var code1='';
                  var code2='';
                  var code3='';
                  var code4='';
                  data=data.data;
                  console.log(data);
                  for(var i=0;i<data.length;i++){
                      if(data[i].firstName=="河面清洁"){
                          code1+='<li>';
                          code1+='<b class="name"><input type="hidden" value="'+data[i].secondId+'" class="ids">'+data[i].secondName+'</b>';
                          code1+='<input onchange="changeScore()" type="number" value="" class="scoreIpt"><span>-</span>';
                          code1+='</li>';
                          $("#addLengthOne").html(code1);
                          $("#firstId1").val(data[i].firstId);
                          $("#addLengthOne li:last-child").addClass("active");
                      }
                      if(data[i].firstName=="河岸整齐"){
                          code2+='<li>';
                          code2+='<b class="name"><input type="hidden" value="'+data[i].secondId+'" class="ids">'+data[i].secondName+'</b>';
                          code2+='<input onchange="changeScore()" type="number" value="" class="scoreIpt"><span>-</span>';
                          code2+='</li>';
                          $("#firstId2").val(data[i].firstId);
                          $("#addLengthTwo").html(code2);
                          $("#addLengthTwo li:last-child").addClass("active");
                      }
                      if(data[i].firstName=="河道通畅"){
                          code3+='<li>';
                          code3+='<b class="name"><input type="hidden" value="'+data[i].secondId+'" class="ids">'+data[i].secondName+'</b>';
                          code3+='<input onchange="changeScore()" type="number" value="" class="scoreIpt"><span>-</span>';
                          code3+='</li>';
                          $("#firstId3").val(data[i].firstId);
                          $("#addLengthThree").html(code3);
                          $("#addLengthThree li:last-child").addClass("active");;
                      }
                      if(data[i].firstName=="河道水质"){
                          code4+='<li>';
                          code4+='<b class="name"><input type="hidden" value="'+data[i].secondId+'" class="ids">'+data[i].secondName+'</b>';
                          code4+='<input onchange="changeScore()" type="number" value="" class="scoreIpt"><span>-</span>';
                          code4+='</li>';
                          $("#firstId4").val(data[i].firstId);
                          $("#addLengthFour").html(code4);
                          $("#addLengthFour li:last-child").addClass("active");
                      }
                      $(".river-qj li:last-child").find("span").hide();
                      $(".river-qj li:last-child").find("input.scoreIpt").hide();
                  }
                  $(".toggle li").click(function(){
                      $(this).toggleClass("active");
                      $(this).find("input").focus();
                      $(this).parent().children().last().removeClass("active");
                      changeScore();
                  })
                  $(".toggle li:last-child").click(function(){
                      $(this).find("span").hide();
                      $(this).find("input.scoreIpt").hide();
                      $(this).parent().find("li").removeClass("active");
                      $(this).addClass("active");
                      changeScore();
                  })
              });
      }
      $(".add-sm").click(function(){
        $(this).prev().show();
        $(this).hide();
      })


      var imgNum=0;
      var files=[];
       $("#imgUpload").change(function () {
               var pic = $(this)[0].files[0];//获取input file控件选择的文件
                preview_picture(pic);
               ImgToBase64(pic, 720, function (base64) {
                   base64=base64.split(',')[base64.split(',').length - 1];
                   files.push(base64);//预览页面上预留一个img元素，载入base64
                   $(".add-img img")[0].width = 300;//设定宽高，不然会自动按照压缩过的图片宽高设定，有可能超出预想的范围。
                    //直接利用ajax上传base64到服务器，完毕
               });
               console.log(files);
           })

      // $("#imgUpload").change(function(){
      //   var pic=this.files[0];
      //   preview_picture(pic);
      //     console.log(imgNum);
      // })
      function preview_picture(pic){
        imgNum++;
        var r=new FileReader();
        r.readAsDataURL(pic);
        r.onload=function(e){
          var code="";
          code+='<li><img id='+imgNum+' src='+this.result+'><a class="remove">-</a></li>';
          if(imgNum<4){
              $(".add-img").append(code);
          }else{
              imgNum--;
              layer.open({
                  content: '最多上传3张图片！',
                  skin: 'msg',
                  time:1
              });
          }
          // 图片删除
          $(".remove").click(function() {
            var _this=$(this);
            layer.open({
              content: '确定要删除吗？',
              btn: ['删除', '取消'],
              skin: 'footer',
              yes: function(index) {
                imgNum--;
                _this.parent().remove();
                  files.splice(imgNum,1);
                layer.open({
                  content: '删除成功！',
                  skin: 'msg',
                  time:1
                });
                console.log(files);
              }
            });
          })
        }
      }
      
      // 提交表单
      $(".btn-primary").click(function(){
        var areaId=$("#areaId").val();
        var title=$("#title").val();
        var riverName=$("#selectBox").html();
        var riverId=$("#riverId").val();
        var riverNo=$("#num").val();
        var towns=$("#adr").val();
        var baiduMap=$("#baiduMap").text();
        var lat=$("#lat").val();
        var lng=$("#lng").val();
        var address=$("#baiduMap").text();
        var describe=$("#des").val();
        if(describe==""){
            describe="无";
        }
        var firstAssess=$(".river-pd li.active input").val();
        var score=$("#score").text();
        var rectification=0;
          var photoFiles=files;
          // var photoFiles=[];
          // $(".add-img li").each(function(){
          //     var imgSrc=$(this).find("img").attr("src");
          //     if(imgSrc!=undefined){
          //         imgSrc=imgSrc.split(',')[imgSrc.split(',').length - 1];
          //         photoFiles.push(imgSrc);
          //     }
          // });
          if ($('.js-switch').is(':checked')){
              rectification=1;
          }
          var list=[];
          var list1={id:'',description:'',detail:[]};
          list1.description=$("#des1").val();
          list1.id=$("#firstId1").val();
          $("#addLengthOne li.active").each(function(){
              var detailDetail={ids:'',name:'',score:''};
              detailDetail.ids=$(this).find("input.ids").val();
              detailDetail.name=$(this).find("b.name").text();
              detailDetail.score=$(this).find("input.scoreIpt").val();
              list1.detail.push(detailDetail);
          });

          var list2={id:'',description:'',detail:[]};
          list2.description=$("#des2").val();
          list2.id=$("#firstId2").val();
          $("#addLengthTwo li.active").each(function(){
              var detailDetail={ids:'',name:'',score:''};
              detailDetail.ids=$(this).find("input.ids").val();
              detailDetail.name=$(this).find("b.name").text();
              detailDetail.score=$(this).find("input.scoreIpt").val();
              list2.detail.push(detailDetail);
          });

          var list3={id:'',description:'',detail:[]};
          list3.description=$("#des3").val();
          list3.id=$("#firstId3").val();
          $("#addLengthThree li.active").each(function(){
              var detailDetail={ids:'',name:'',score:''};
              detailDetail.ids=$(this).find("input.ids").val();
              detailDetail.name=$(this).find("b.name").text();
              detailDetail.score=$(this).find("input.scoreIpt").val();
              list3.detail.push(detailDetail);
          });

          var list4={id:'',description:'',detail:[]};
          list4.description=$("#des4").val();
          list4.id=$("#firstId4").val();
          $("#addLengthFour li.active").each(function(){
              var detailDetail={ids:'',name:'',score:''};
              detailDetail.ids=$(this).find("input.ids").val();
              detailDetail.name=$(this).find("b.name").text();
              detailDetail.score=$(this).find("input.scoreIpt").val();
              list4.detail.push(detailDetail);
          });
          list.push(list1);
          list.push(list2);
          list.push(list3);
          list.push(list4);
        if(title==""){
          layer.open({
            content: '请输入标题',
            skin: 'footer',
            time:1
          });
          return false;
        }else if(riverName=="<span>请选择河道名称</span>"){
          layer.open({
            content: '请选择河道',
            skin: 'footer',
            time:1
          });
          return false;
        }else if(towns==""){
          layer.open({
            content: '请输入所属乡镇',
            skin: 'footer',
            time:1
          });
          return false;
        }else if(baiduMap=="请选择当前位置"){
            layer.open({
                content: '请选择当前位置',
                skin: 'footer',
                time:1
            });
            return false;
        }else if(photoFiles==''){
            layer.open({
                content: '请上传照片',
                skin: 'footer',
                time:1
            });
            return false;
        }else if(list1.detail==''){
            layer.open({
                content: '请选择河面清洁问题 ',
                skin: 'footer',
                time:1
            });
            return false;
        }else if(list2.detail==''){
            layer.open({
                content: '请选择河岸整洁问题',
                skin: 'footer',
                time:1
            });
            return false;
        }else if(list3.detail==''){
            layer.open({
                content: '请选择河道通畅问题',
                skin: 'footer',
                time:1
            });
            return false;
        }else if(list4.detail==''){
            layer.open({
                content: '请选择河道水质问题',
                skin: 'footer',
                time:1
            });
            return false;
        }else{
            layer.open({
                content: '确定要上传吗？',
                btn: ['确定', '取消'],
                skin: 'footer',
                yes: function() {
                    $("#layui-m-layer0").hide();
                    $.post("/inspectionRecord/createRecord",
                        {
                            areaId:areaId,
                            firstAssess:firstAssess,
                            title:title,
                            riverName:riverName,
                            riverId:riverId,
                            riverNo:riverNo,
                            towns:towns,
                            lat:lat,
                            lng:lng,
                            address:address,
                            describe:describe,
                            photoFiles:JSON.stringify(photoFiles),
                            score:score,
                            rectification:rectification,
                            detail:JSON.stringify(list)
                        },
                        function(status){

                            layer.open({
                                content: '上传成功！',
                                skin: 'msg',
                                time:1
                            });
                            setTimeout("window.location.href='../index'",1000)
                        }
                    );
                }
            });

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
          $("#score").css("color","#6BBB88");
            if(score>79){
        }else if(score>59&&score<80){
          // 60-80橙色
          $("#score").css("color","#ff9900");
        }else if(score<60){
          // 60以下红色
          $("#score").css("color","#f00");
        }
        $("#score").text(score);
    }
      $(".river-pd li").click(function(){
        $(this).addClass("active");
        $(this).siblings().removeClass("active");
      })

      // 河道名称选择
      var userId=$("#userId").val();
      $.post("/river/getRiversByApp",
          {
          },
          function(data){
              //下拉选择框
              console.log(data.data);
              var mobileSelect1 = new MobileSelect({
                  trigger: '#selectBox',
                  title: '请选择河道',
                  wheels: [{
                      data: data.data
                  }],
                  position: [1, 1], //初始化定位 打开时默认选中的哪个 如果不填默认为0
                  callback: function(indexArr, data) {
											console.log("------------------")
											console.log(data)
                      var riverNo=data[0].riverNo;
                      var riverId=data[0].id;
                      $("#num").val(riverNo);
                      $("#riverId").val(riverId);
                      $("#adr").val(data[0].areaName);
                  }
              });
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
    // var map = new BMap.Map("allmap");
    // var point = new BMap.Point(116.331398,39.897445);
    // map.centerAndZoom(point,12);
    //
    // var geolocation = new BMap.Geolocation();
    // geolocation.getCurrentPosition(function(r){
    //     if(this.getStatus() == BMAP_STATUS_SUCCESS){
    //         var mk = new BMap.Marker(r.point);
    //         map.addOverlay(mk);
    //         map.panTo(r.point);
    //         var url = 'http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location='+r.point.lat+','+r.point.lng+'&output=json&pois=1&ak=wwz42MgLZ6vWNKqvGR109ZEcCTiOfggd';
    //         // $("#baiduMap").text('您的位置：'+r.point.lng+','+r.point.lat);
    //         $.get(url, function(result){
    //             $("#baiduMap").html(result);
    //         });
    //         $("#lat").val(r.point.lat);
    //         $("#lng").val(r.point.lng);
    //     }
    //     else {
    //         alert('定位失败');
    //     }
    // },{enableHighAccuracy: true})

      // var map = new BMap.Map("allmap");
      // var point = new BMap.Point(116.331398,39.897445);
      // map.centerAndZoom(point,12);
      // var geoc = new BMap.Geocoder();
      //
      // var geolocation = new BMap.Geolocation();
      // geolocation.getCurrentPosition(function(r){
      //     if(this.getStatus() == BMAP_STATUS_SUCCESS){
      //         var mk = new BMap.Marker(r.point);
      //         map.addOverlay(mk);
      //         map.panTo(r.point);
      //         $("#lat").val(r.point.lat);
      //         $("#lng").val(r.point.lng);
      //     }
      //     else {
      //         alert('failed'+this.getStatus());
      //     }
      //     geoc.getLocation(r.point, function(rs){
      //         var addComp = rs.addressComponents;
      //         //alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
      //         var areaStr = addComp.province+addComp.city+addComp.district+addComp.street+ addComp.streetNumber;
      //         $("#baiduMap").html(areaStr);
      //     });
      // },{enableHighAccuracy: true})


