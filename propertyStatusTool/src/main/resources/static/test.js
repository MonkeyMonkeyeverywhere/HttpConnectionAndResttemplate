function getBase64(img){//传入图片路径，返回base64
    function getBase64Image(img,width,height) {
        var canvas = document.createElement("canvas");
        canvas.width = width ? width : img.width;
        canvas.height = height ? height : img.height;

        var ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
        var dataURL = canvas.toDataURL();
        return dataURL;
    }
    var image = new Image();
    image.crossOrigin = '';
    image.src = img;

    var base64='';
    if(img){
        image.onload =function (){
            base64=getBase64Image(image);
            console.log(base64);//位置一
        }
        console.log(base64);//位置二
    }
}

///////////////////////////////////////////////////

var defer1 = $q.defer();

function fun() {
    var deferred = $q.defer();
    $timeout(function () {
        deferred.notify("notify");
        if (iWantResolve) {
            deferred.resolve("resolved");
        } else {
            deferred.reject("reject");
        }
    }, 500);
    return deferred.promise;
}

$q.when(fun())
    .then(function(success){
        console.log("success");
        console.log(success);
    },function(err){
        console.log("error");
        console.log(err);
    },function(notify){
        console.log("notify");
        console.log(notify);
    })
    .catch(function(reson){
        console.log("catch");
        console.log(reson);
    })
    .finally(function(final){
        console.log('finally');
        console.log(final);
    });